package com.niksaen.test.ui.bag

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.niksaen.test.MainActivity
import com.niksaen.test.adapters.BagAdapter
import com.niksaen.test.databinding.FragmentBagBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class BagFragment : Fragment() {

    private var _ui: FragmentBagBinding? = null
    private val ui get() = _ui!!
    private val bagViewModel by viewModel<BagViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bagViewModel.requestFullPrice()
        bagViewModel.requestBagList()
        bagViewModel.setDataChangedAction()

        _ui = FragmentBagBinding.inflate(inflater, container, false)
        bagViewModel.fullPriceText.observe(viewLifecycleOwner){
            ui.pay.text = it
        }
        bagViewModel.bagList.observe(viewLifecycleOwner){
            ui.list.adapter = BagAdapter(requireContext(),it)
        }

        ui.userCityView.text = bagViewModel.getCity()
        ui.dateView.text = bagViewModel.getDate()
        return ui.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _ui = null
    }
}