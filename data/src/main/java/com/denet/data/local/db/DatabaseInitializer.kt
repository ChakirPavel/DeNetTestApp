package com.denet.data.local.db

import com.denet.data.local.dao.NodeDao
import com.denet.data.local.entities.NodeEntity
import com.denet.data.local.utls.UtilsString
import jakarta.inject.Inject
import java.util.UUID

class DatabaseInitializer @Inject constructor(
    private val nodeDao: NodeDao,
    private val utilsString: UtilsString
) {

    private val DATABASE_EMPTY = 0

    suspend fun initializeIfEmpty() {
        if (nodeDao.getCount() == DATABASE_EMPTY) {
            nodeDao.insert(
                NodeEntity(
                    name = utilsString.generateName(UUID.randomUUID().toString())
                )
            )
        }
    }
}