package com.denet.data.repository

import androidx.room.Transaction
import com.denet.data.local.dao.NodeDao
import com.denet.data.local.entities.NodeEntity
import com.denet.data.local.utls.UtilsString
import com.denet.data.mappers.NodeEntityMapper
import com.denet.domain.models.Node
import com.denet.domain.repository.NodeRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID

class NodeRepositoryImpl @Inject constructor(
    private val nodeDao: NodeDao,
    private val mapper: NodeEntityMapper,
    private val utils: UtilsString
) : NodeRepository {

    @Transaction
    override suspend fun addNode(parentId: Int?) {
        val newNodeId = nodeDao.insert(
            NodeEntity(
                parentId = parentId,
                name = utils.generateName(UUID.randomUUID().toString())
            )
        ).toInt()
        if (parentId != null) {
            nodeDao.getNode(parentId)?.let { parent ->
                val updatedParent = parent.copy(
                    childIds = parent.childIds + newNodeId
                )
                nodeDao.updateNode(updatedParent)
            }
        }
    }

    @Transaction
    override suspend fun deleteNode(id: Int) {
        val nodesMap = nodeDao.getAllSync().associateBy { it.id }
        val node = nodesMap[id] ?: return

        val allIdsToDelete = collectChildIds(node, nodesMap)
        nodeDao.deleteByIds(allIdsToDelete)
    }

    @Transaction
    override suspend fun removeListNodes(ids: List<Int>) = nodeDao.deleteByIds(ids)

    override suspend fun getFlowNodes(): Flow<Map<Int, Node>> = nodeDao.getAll().map {
        mapper.toDomain(it)
    }

    private fun collectChildIds(
        node: NodeEntity,
        nodesMap: Map<Int, NodeEntity>
    ): List<Int> {
        val result = mutableListOf(node.id)
        recurseCollectNodeIds(node, result, nodesMap)
        return result
    }

    private fun recurseCollectNodeIds(
        node: NodeEntity,
        list: MutableList<Int>,
        nodesMap: Map<Int, NodeEntity>
    ) {
        node.childIds.forEach { idChild ->
            list.add(idChild)
            nodesMap[idChild]?.let { recurseCollectNodeIds(it, list, nodesMap) }
        }
    }
}