package com.denet.test.screens.list_nodes

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.denet.test.R
import com.denet.test.databinding.ListNodesFragmentBinding
import com.denet.test.screens.list_nodes.adapter.ListNodesAdapter
import com.denet.test.screens.list_nodes.adapter.ListNodesItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListNodesFragment : Fragment(R.layout.list_nodes_fragment) {

    private val viewModel: ListNodesVM by viewModels()
    private val binding: ListNodesFragmentBinding by viewBinding()
    private val adapter = ListNodesAdapter({
        viewModel.onClickNode(it)
    }, {
        viewModel.onClickDeleteNode(it)
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listNodesRV.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.listNodesRV.addItemDecoration(ListNodesItemDecoration(requireContext()))
        binding.listNodesRV.adapter = adapter

        binding.toolbar.setNavigationOnClickListener {
            viewModel.onClickBack()
        }
        binding.addNewNodeBtn.setOnClickListener {
            viewModel.onClickAddNewNode()
        }

        lifecycleScope.launch {
            viewModel.state.collect {
                adapter.submitList(it?.children)
                binding.toolbar.setTitle(it?.name)
                binding.toolbar.navigationIcon = if (it?.parent != null) {
                    ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_foreground, null)
                } else {
                    null
                }
            }
        }
    }
}