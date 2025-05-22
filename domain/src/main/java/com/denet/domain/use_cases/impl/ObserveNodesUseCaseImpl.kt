package com.denet.domain.use_cases.impl

import com.denet.domain.models.Node
import com.denet.domain.repository.NodeRepository
import com.denet.domain.use_cases.ObserveNodesUseCase
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveNodesUseCaseImpl @Inject constructor(
    private val repository: NodeRepository
): ObserveNodesUseCase {
    override suspend fun invoke(): Flow<Map<Int, Node>> = repository.observeNodes()
}