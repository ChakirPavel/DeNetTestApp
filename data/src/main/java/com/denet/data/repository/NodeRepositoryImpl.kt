package com.denet.data.repository

import com.denet.data.local.dao.NodeDao
import com.denet.data.local.entities.NodeEntity
import com.denet.data.local.utls.UtilsString
import com.denet.data.mappers.NodeEntityMapper
import com.denet.domain.models.Node
import com.denet.domain.repository.NodeRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NodeRepositoryImpl @Inject constructor(
    private val nodeDao: NodeDao,
    private val mapper: NodeEntityMapper,
    private val utils: UtilsString
) : NodeRepository {

    override suspend fun addNode(parentId: Int?) {
        val addedNodeEntityId = nodeDao.insert(
            NodeEntity(
                parentId = parentId
            )
        ).toInt()
        nodeDao.updateName(
            addedNodeEntityId,
            utils.generateName(addedNodeEntityId.toString())
        )
        parentId?.let {
            nodeDao.getNode(it)?.let { parentNode ->
                parentNode.childIds.add(addedNodeEntityId)
                nodeDao.updateNode(parentNode)
            }
        }
    }

    override suspend fun deleteNode(id: Int) = nodeDao.deleteById(
        id = id
    )

    override suspend fun removeListNodes(ids: List<Int>) = nodeDao.deleteByIds(ids)

    override suspend fun getFlowNodes(): Flow<Map<Int, Node>> = nodeDao.getAll().map {
        mapper.toDomain(it)
    }
}