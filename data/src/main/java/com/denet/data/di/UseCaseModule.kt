package com.denet.data.di

import com.denet.domain.use_cases.AddNodeUseCase
import com.denet.domain.use_cases.DeleteNodeUseCase
import com.denet.domain.use_cases.GetNodesUseCase
import com.denet.domain.use_cases.impl.AddNodeUseCaseImpl
import com.denet.domain.use_cases.impl.DeleteNodeUseCaseImpl
import com.denet.domain.use_cases.impl.GetNodesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetNodeUseCase(
        impl: GetNodesUseCaseImpl
    ): GetNodesUseCase

    @Binds
    abstract fun bindDeleteNodeUseCase(
        impl: DeleteNodeUseCaseImpl
    ): DeleteNodeUseCase

    @Binds
    abstract fun bindAddNodeUseCase(
        impl: AddNodeUseCaseImpl
    ): AddNodeUseCase
}