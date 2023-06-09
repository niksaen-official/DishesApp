package com.niksaen.test.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.niksaen.test.DishesApplication
import com.niksaen.test.MainActivity
import com.niksaen.test.R
import com.niksaen.test.adapters.CategoriesAdapter
import com.niksaen.test.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _ui: FragmentHomeBinding? = null
    lateinit var adapter:CategoriesAdapter
    private val ui get() = _ui!!
    private val homeViewModel by viewModel<HomeViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        homeViewModel.requestCategories()
        _ui = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel.categoriesResponse.observe(viewLifecycleOwner){
            adapter = CategoriesAdapter(requireContext(),it.list)
            ui.listCategories.adapter = adapter
            adapter.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                run {
                    (requireActivity() as MainActivity).categoryName=it.list[position].name
                    findNavController().navigate(R.id.dishes)
                }
            }
        }
        ui.dateView.text = homeViewModel.getCurrentDate()
        ui.userCityView.text = homeViewModel.getCity()
        return ui.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _ui = null
    }
}