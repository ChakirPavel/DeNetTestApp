package com.denet.domain.use_cases

import com.denet.domain.models.Node
import kotlinx.coroutines.flow.Flow

interface GetNodesUseCase {
    suspend operator fun invoke(): Flow<Map<Int, Node>>
}