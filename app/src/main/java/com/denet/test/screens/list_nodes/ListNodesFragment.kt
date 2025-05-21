package com.denet.test.screens.list_nodes

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.denet.domain.models.Node
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
    private val adapter by lazy {
        ListNodesAdapter(
            onItemClick = { viewModel.onClickNode(it) },
            onDeleteClick = { viewModel.onClickDeleteNode(it) }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        initClicks()
        subscribeViewModelState()
    }


    private fun initRecycler() = with(binding.listNodesRV) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = this@ListNodesFragment.adapter
        addItemDecoration(ListNodesItemDecoration(requireContext()))
    }

    private fun initClicks() {
        binding.toolbar.setNavigationOnClickListener {
            viewModel.onClickBack()
        }
        binding.addNewNodeBtn.setOnClickListener {
            viewModel.onClickAddNewNode()
        }
    }

    private fun subscribeViewModelState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    adapter.submitList(it?.children)
                    updateToolbar(it)
                }
            }
        }
    }

    private fun updateToolbar(node: Node?) = with(binding.toolbar) {
        title = node?.name
        binding.toolbar.navigationIcon = when {
            node?.parent != null -> ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_launcher_foreground
            )
            else -> null
        }
    }
}