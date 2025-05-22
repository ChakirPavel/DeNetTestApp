package com.denet.data.local.db

import com.denet.data.local.dao.NodeDao
import com.denet.data.local.entities.NodeEntity
import com.denet.data.local.utls.NodeNameGenerator
import jakarta.inject.Inject
import java.util.UUID

const val DATABASE_EMPTY = 0

class DatabaseInitializer @Inject constructor(
    private val nodeDao: NodeDao,
    private val nodeNameGenerator: NodeNameGenerator
) {
    suspend fun initializeIfEmpty() {
        if (nodeDao.getCount() == DATABASE_EMPTY) {
            nodeDao.insert(
                NodeEntity(
                    name = nodeNameGenerator.generateName(UUID.randomUUID().toString())
                )
            )
        }
    }
}