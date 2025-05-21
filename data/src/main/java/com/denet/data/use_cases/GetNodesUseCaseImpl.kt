package com.denet.data.use_cases

import com.denet.domain.controllers.CurrentNodeController
import com.denet.domain.models.Node
import com.denet.domain.repository.NodeRepository
import com.denet.domain.use_cases.GetNodesUseCase
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetNodesUseCaseImpl @Inject constructor(
    private val repository: NodeRepository
): GetNodesUseCase {
    override suspend fun invoke(): Flow<Map<Int, Node>> = repository.getFlowNodes()
}