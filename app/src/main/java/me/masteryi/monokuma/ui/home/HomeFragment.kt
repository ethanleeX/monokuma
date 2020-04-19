package me.masteryi.monokuma.ui.home

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import me.masteryi.monokuma.R
import me.masteryi.monokuma.base.BaseFragment
import me.masteryi.monokuma.databinding.FragmentHomeBinding

/**
 * home fragment
 *
 * @author Ethan Lee
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = CategoryAdapter(context!!)
    }
}