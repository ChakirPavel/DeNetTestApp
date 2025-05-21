package com.denet.test.screens.list_nodes

import com.denet.domain.models.Node
import com.denet.domain.use_cases.AddNodeUseCase
import com.denet.domain.use_cases.DeleteNodeUseCase
import com.denet.domain.use_cases.GetNodesUseCase
import com.denet.test.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class ListNodesVM @Inject constructor(
    private val getNodesUseCase: GetNodesUseCase,
    private val addNodeUseCase: AddNodeUseCase,
    private val deleteNodeUseCase: DeleteNodeUseCase
) : BaseViewModel() {

    private val _state: MutableStateFlow<Node?> = MutableStateFlow(null)
    val state = _state.asStateFlow()

    private val mapNodes = mutableMapOf<Int, Node>()

    init {
        launchIO {
            getNodesUseCase().collect { nodes ->
                updateMap(nodes)
                _state.value = resolveCurrentNode()
            }
        }
    }

    private fun updateMap(nodes: Map<Int, Node>) {
        mapNodes.clear()
        mapNodes.putAll(nodes)
    }

    private fun resolveCurrentNode(): Node? {
        val currentId = _state.value?.id
        return currentId?.let { mapNodes[it] }
            ?: mapNodes.values.find { it.parent == null }
    }

    fun onClickDeleteNode(node: Node) {
        launchIO {
            deleteNodeUseCase(node.id)
        }
    }

    fun onClickAddNewNode() {
        launchIO {
            addNodeUseCase(_state.value?.id)
        }
    }

    fun onClickBack() {
        _state.value?.parent?.id?.let { id ->
            _state.value = mapNodes[id]
        }
    }

    fun onClickNode(node: Node) {
        _state.value = mapNodes[node.id]
    }
}