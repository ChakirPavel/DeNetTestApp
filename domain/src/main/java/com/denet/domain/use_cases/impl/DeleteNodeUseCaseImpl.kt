package com.denet.domain.use_cases.impl

import com.denet.domain.repository.NodeRepository
import com.denet.domain.use_cases.DeleteNodeUseCase
import jakarta.inject.Inject

class DeleteNodeUseCaseImpl @Inject constructor(
    private val repository: NodeRepository
): DeleteNodeUseCase {
    override suspend fun invoke(id: Int) = repository.deleteNode(id)
}