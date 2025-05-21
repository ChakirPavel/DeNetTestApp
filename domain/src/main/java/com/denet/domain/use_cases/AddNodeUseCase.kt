package com.denet.domain.use_cases

interface AddNodeUseCase {
    suspend operator fun invoke(parentId: Int? = null)
}