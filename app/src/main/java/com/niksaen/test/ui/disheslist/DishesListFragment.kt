package com.niksaen.test.ui.disheslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.niksaen.test.MainActivity
import com.niksaen.test.R
import com.niksaen.test.adapters.DishesAdapter
import com.niksaen.test.adapters.TagsAdapter
import com.niksaen.test.databinding.FragmentDisheslistBinding
import com.niksaen.test.dialogs.DishesDialog
import com.niksaen.test.remote.dishes.DishesItem
import org.koin.androidx.viewmodel.ext.android.viewModel

class DishesListFragment : Fragment() {

    private val dishesListViewModel by viewModel<DishesListViewModel>()
    private var _ui: FragmentDisheslistBinding? = null
    private val ui get() = _ui!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _ui = FragmentDisheslistBinding.inflate(inflater, container, false)
        dishesListViewModel.getTags(requireContext())
        dishesListViewModel.requestDishesResponse()

        val root: View = ui.root
        ui.titleView.text = (requireActivity() as MainActivity).categoryName
        dishesListViewModel.tags.observe(viewLifecycleOwner){
            val adapterTag=TagsAdapter(requireContext(),it)
            ui.tags.adapter=adapterTag
            adapterTag.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                run{
                    setDishesAdapter(dishesListViewModel.filterDishesByTag(it[position]))
                }
            }
        }
        dishesListViewModel.dishesResponse.observe(viewLifecycleOwner){
            setDishesAdapter(it.dishes)
        }
        ui.backButn.setOnClickListener {
            (requireActivity() as MainActivity).navController.navigate(R.id.navigation_home)
        }
        return root
    }

    private fun setDishesAdapter(dishesList:ArrayList<DishesItem>){
        val adapter = DishesAdapter(requireContext(),dishesList)
        ui.dishesList.adapter = adapter
        adapter.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            run {
                val dialog = DishesDialog(requireContext(),dishesList[position])
                dialog.setAddToCartButtonListener{
                    dishesListViewModel.addToBag(dishesList[position])
                    dialog.close()
                }
                dialog.show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _ui = null
    }
}