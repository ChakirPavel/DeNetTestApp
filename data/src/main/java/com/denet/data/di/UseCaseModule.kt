package com.denet.data.di

import com.denet.data.use_cases.AddNodeUseCaseImpl
import com.denet.data.use_cases.DeleteNodeUseCaseImpl
import com.denet.data.use_cases.GetNodesUseCaseImpl
import com.denet.domain.use_cases.AddNodeUseCase
import com.denet.domain.use_cases.DeleteNodeUseCase
import com.denet.domain.use_cases.GetNodesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
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