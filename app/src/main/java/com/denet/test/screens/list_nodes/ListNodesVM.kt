package com.denet.test.screens.list_nodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denet.domain.models.Node
import com.denet.domain.use_cases.AddNodeUseCase
import com.denet.domain.use_cases.DeleteNodeUseCase
import com.denet.domain.use_cases.GetNodesUseCase
import com.denet.test.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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
        viewModelScope.launch(baseDispatchersIO) {
            getNodesUseCase().collect {
                mapNodes.clear()
                mapNodes.putAll(it)
                _state.update {
                    if (_state.value == null) {
                        mapNodes.values.find { node -> node.parent == null }
                    } else {
                        mapNodes[_state.value?.id]
                    }
                }
                if (it.isEmpty()) {
                    addNodeUseCase()
                }
            }
        }
    }

    fun onClickDeleteNode(node: Node) {
        viewModelScope.launch(baseDispatchersIO) {
            val ids = collectChildIds(node)
            deleteNodeUseCase(ids)
        }
    }

    fun onClickAddNewNode() {
        viewModelScope.launch(baseDispatchersIO) {
            addNodeUseCase(_state.value?.id)
        }
    }

    fun onClickBack() {
        viewModelScope.launch(baseDispatchersIO) {
            _state.value?.parent?.id?.let { parentId ->
                mapNodes[parentId]?.let {
                    _state.value = it
                }
            }
        }
    }

    fun onClickNode(node: Node) {
        viewModelScope.launch(baseDispatchersIO) {
            mapNodes[node.id]?.let {
                _state.value = it
            }
        }
    }

    private fun collectChildIds(node: Node): List<Int> {
        val result = mutableListOf(node.id)
        recurseCollectNodeIds(node, result)
        return result
    }

    private fun recurseCollectNodeIds(node: Node, list: MutableList<Int>) {
        node.children?.forEach {
            list.add(it.id)
            recurseCollectNodeIds(it, list)
        }
    }
}