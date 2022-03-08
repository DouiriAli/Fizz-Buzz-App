package com.rss.fizzbuzzapp.secondscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rss.fizzbuzzapp.base.BaseFragment
import com.rss.fizzbuzzapp.databinding.FragmentFizzBuzzSecondBinding
import com.rss.fizzbuzzapp.util.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class FizzBuzzSecondFragment : BaseFragment() {

    private val viewModel by viewModel<FizzBuzzSecondViewModel>()

    private var binding: FragmentFizzBuzzSecondBinding? = null

    private val args: FizzBuzzSecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFizzBuzzSecondBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findFizzBuzz()
        observeChangeUi()
    }

    /**
     * Find Fizz Buzz result
     */
    private fun findFizzBuzz(){
        viewModel.findFizzBuzz(args.fizzBuzzUiModel)
    }

    /**
     * Observe uiData changes :
     * if status is [Status.SUCCESS] then we display a recycler view
     * else if [Status.ERROR] the we display an error
     */
    private fun observeChangeUi() {
        viewModel.uiData.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    buildRecyclerView(resource.data.orEmpty())
                    hideProgress()
                }
                Status.LOADING -> {
                    showProgress()
                }
                Status.ERROR -> {
                    hideProgress()
                }
            }
        }
    }

    /**
     * Build a recyclerview
     */
    private fun buildRecyclerView(fizzBuzzList: List<String>) {
        binding?.fizzBuzzSecondRecyclerView?.apply {
            this.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            val adapter = FizzBuzzAdapter()
            this.adapter = adapter
            adapter.submitList(fizzBuzzList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}