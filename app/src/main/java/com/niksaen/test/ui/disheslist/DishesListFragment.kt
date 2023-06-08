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

class DishesListFragment : Fragment() {

    private lateinit var dishesListViewModel:DishesListViewModel
    private var _binding: FragmentDisheslistBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDisheslistBinding.inflate(inflater, container, false)
        dishesListViewModel = ViewModelProvider(this)[DishesListViewModel::class.java]
        dishesListViewModel.activity = requireActivity() as MainActivity
        dishesListViewModel.getTags()
        dishesListViewModel.requestDishesResponse()

        val root: View = binding.root
        binding.titleView.text = (requireActivity() as MainActivity).categoryName
        dishesListViewModel.tags.observe(viewLifecycleOwner){
            val adapterTag=TagsAdapter(requireContext(),it)
            binding.tags.adapter=adapterTag
            adapterTag.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                run{
                    setDishesAdapter(dishesListViewModel.filterDishesByTag(it[position]))
                }
            }
        }
        dishesListViewModel.dishesResponse.observe(viewLifecycleOwner){
            setDishesAdapter(it.dishes)
        }
        binding.backButn.setOnClickListener {
            (requireActivity() as MainActivity).navController.navigate(R.id.action_dishes_to_home)
        }
        return root
    }

    private fun setDishesAdapter(dishesList:ArrayList<DishesItem>){
        val adapter = DishesAdapter(requireContext(),dishesList)
        binding.dishesList.adapter = adapter
        adapter.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            run {
                val dialog = DishesDialog(requireContext(),dishesList[position])
                dialog.setAddToCartButtonListener{
                    dishesListViewModel.addToBag(dishesList[position])
                }
                dialog.show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}