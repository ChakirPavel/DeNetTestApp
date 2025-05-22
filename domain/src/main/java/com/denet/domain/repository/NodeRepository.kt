package com.denet.domain.repository

import com.denet.domain.models.Node
import kotlinx.coroutines.flow.Flow

interface NodeRepository {
    suspend fun addNode(parentId: Int?)
    suspend fun deleteNode(id: Int)
    suspend fun removeListNodes(ids: List<Int>)
    suspend fun observeNodes(): Flow<Map<Int, Node>>
}