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

    private var _binding: FragmentBagBinding? = null
    private val binding get() = checkNotNull(_binding)
    private val bagViewModel by viewModel<BagViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bagViewModel.activity = requireActivity() as MainActivity
        bagViewModel.requestCurrentDate()
        bagViewModel.requestUserCity()
        bagViewModel.requestFullPrice()
        bagViewModel.requestBagList()
        bagViewModel.setDataChangedAction()

        _binding = FragmentBagBinding.inflate(inflater, container, false)

        bagViewModel.userCity.observe(viewLifecycleOwner){binding.userCityView.text = it}
        bagViewModel.currentDate.observe(viewLifecycleOwner){binding.dateView.text = it}
        bagViewModel.fullPrice.observe(viewLifecycleOwner){
            if(it != 0) {
                binding.pay.text = "Оплатить " + it
            }else{
                binding.pay.text = "Оплатить"
            }
        }
        bagViewModel.bagList.observe(viewLifecycleOwner){
            binding.list.adapter = BagAdapter(requireContext(), it)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}