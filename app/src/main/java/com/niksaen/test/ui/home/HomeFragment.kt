package com.niksaen.test.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.niksaen.test.DishesApplication
import com.niksaen.test.MainActivity
import com.niksaen.test.R
import com.niksaen.test.adapters.CategoriesAdapter
import com.niksaen.test.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _ui: FragmentHomeBinding? = null
    lateinit var adapter:CategoriesAdapter
    private val ui get() = _ui!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        homeViewModel.activity = requireActivity() as MainActivity
        homeViewModel.requestCategories()
        homeViewModel.requestUserCity()
        homeViewModel.requestCurrentDate()
        _ui = FragmentHomeBinding.inflate(inflater, container, false)
        val root = ui.root
        homeViewModel.categoriesResponse.observe(viewLifecycleOwner){
            adapter = CategoriesAdapter(requireContext(),it.list)
            ui.listCategories.adapter = adapter
            adapter.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                run {
                    (requireActivity() as MainActivity).categoryName=it.list[position].name
                    (this.requireActivity() as MainActivity).navController.navigate(R.id.action_home_to_dishes)
                }
            }
        }
        homeViewModel.userCity.observe(viewLifecycleOwner){ ui.userCityView.text = it }
        homeViewModel.currentDate.observe(viewLifecycleOwner){ui.dateView.text = it}
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _ui = null
    }
}