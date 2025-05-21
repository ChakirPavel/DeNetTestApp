package com.denet.domain.use_cases

import com.denet.domain.models.Node

interface DeleteNodeUseCase {
    suspend operator fun invoke(ids: List<Int>)
}