package com.denet.data.use_cases

import com.denet.domain.repository.NodeRepository
import com.denet.domain.use_cases.AddNodeUseCase
import jakarta.inject.Inject

class AddNodeUseCaseImpl @Inject constructor(
    private val repository: NodeRepository
): AddNodeUseCase {
    override suspend fun invoke(parentId: Int?) = repository.addNode(parentId)
}