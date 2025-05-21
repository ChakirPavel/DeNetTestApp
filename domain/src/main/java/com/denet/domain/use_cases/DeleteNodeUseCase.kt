package com.denet.domain.use_cases


interface DeleteNodeUseCase {
    suspend operator fun invoke(id: Int)
}