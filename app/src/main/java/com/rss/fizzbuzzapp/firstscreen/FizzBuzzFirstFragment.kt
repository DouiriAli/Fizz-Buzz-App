package com.rss.fizzbuzzapp.firstscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rss.fizzbuzzapp.R
import com.rss.fizzbuzzapp.base.BaseFragment
import com.rss.fizzbuzzapp.databinding.FragmentFizzBuzzFirstBinding
import com.rss.fizzbuzzapp.model.FizzBuzzUiModel
import com.rss.fizzbuzzapp.util.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class FizzBuzzFirstFragment : BaseFragment() {

    private val viewModel by viewModel<FizzBuzzFirstViewModel>()

    private var binding: FragmentFizzBuzzFirstBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFizzBuzzFirstBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeFormChanged()
        binding?.findFizzBuzz?.setOnClickListener {
            createUiModel()
        }
    }

    /**
     * Create a ui model from the data entered by the user
     */
    private fun createUiModel() {
        val int1 = binding?.fizzBuzzFirstInt1Edittext?.text.toString()
        val int2 = binding?.fizzBuzzFirstInt2Edittext?.text.toString()
        val limit = binding?.fizzBuzzFirstLimitEdittext?.text.toString()
        val str1 = binding?.fizzBuzzFirstStr1Edittext?.text.toString()
        val str2 = binding?.fizzBuzzFirstStr2Edittext?.text.toString()
        viewModel.createUiModel(int1, int2, limit, str1, str2)
    }

    /**
     * Observe view model live data
     * if status is [Status.SUCCESS] we navigate to second fragment
     * else if status is [Status.ERROR] we display an error to the user
     */
    private fun observeFormChanged() {
        viewModel.isFormValid.observe(viewLifecycleOwner) { resource ->
            when (resource?.status) {
                Status.SUCCESS -> {
                    resource.data?.let { notNullUiModel ->
                        navigateToFizzBuzzSecondFragment(notNullUiModel)
                        viewModel.clearLiveData()
                    }
                }
                Status.LOADING -> {
                    /* Nothing */
                }
                Status.ERROR  -> {
                    notify(R.string.form_error_message)
                }
            }
        }
    }

    /**
     * Navigate to second fragment
     */
    private fun navigateToFizzBuzzSecondFragment(uiModel: FizzBuzzUiModel) {
        val action =
            FizzBuzzFirstFragmentDirections.actionFizzBuzzFirstFragmentToFizzBuzzSecondFragment(
                uiModel)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}