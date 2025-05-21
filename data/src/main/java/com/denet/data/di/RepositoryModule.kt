package com.denet.data.di

import com.denet.data.repository.NodeRepositoryImpl
import com.denet.domain.repository.NodeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindNodeRepository(
        impl: NodeRepositoryImpl
    ): NodeRepository
}