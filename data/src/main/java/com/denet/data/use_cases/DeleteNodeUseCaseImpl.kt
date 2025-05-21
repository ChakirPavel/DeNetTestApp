package com.denet.data.use_cases

import com.denet.domain.models.Node
import com.denet.domain.repository.NodeRepository
import com.denet.domain.use_cases.DeleteNodeUseCase
import jakarta.inject.Inject

class DeleteNodeUseCaseImpl @Inject constructor(
    private val repository: NodeRepository
): DeleteNodeUseCase {
    override suspend fun invoke(ids: List<Int>) = repository.removeListNodes(ids)
}