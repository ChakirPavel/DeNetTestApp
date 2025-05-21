package com.denet.data.mappers

import com.denet.data.local.entities.NodeEntity
import com.denet.domain.models.Node
import jakarta.inject.Inject

class NodeEntityMapper @Inject constructor() {

    fun toDomain(nodeEntities: List<NodeEntity>): Map<Int, Node> {
        val entitiesMap = nodeEntities.associateBy { it.id }

        return entitiesMap.mapValues { (_, entity) ->
            toDomainWithChildren(entity, entitiesMap)
        }
    }

    private fun toDomainWithChildren(
        entity: NodeEntity,
        entitiesMap: Map<Int, NodeEntity>
    ): Node = Node(
        id = entity.id,
        name = entity.name,
        children = entity.childIds.mapNotNull {
            entitiesMap[it]?.toDomainWithoutChildren()
        },
        parent = entitiesMap[entity.parentId]?.toDomainWithoutChildren()
    )

    private fun NodeEntity.toDomainWithoutChildren(): Node = Node(
        id = id,
        name = name,
        children = emptyList(),
        parent = null
    )

//    fun toDomain(nodeEntities: List<NodeEntity>): HashMap<Int, Node> {
//        val entitiesById = nodeEntities.associateByTo(HashMap()) { it.id }
//
//        return entitiesById.mapValuesTo(HashMap()) { (_, entity) ->
//            entity.toDomainWithChildren(entitiesById)
//        }
//    }
//
//    private fun NodeEntity.toDomainWithChildren(entities: Map<Int, NodeEntity>): Node {
//        val childrenNodes = childIds.mapNotNull { id ->
//            entities[id]?.toDomainWithoutChildren()
//        }
//        val asd = mapOf<Int, Node>()
//
//        val parentNode = parentId?.let { id ->
//            entities[id]?.toDomainWithoutChildren()
//        }
//
//        return Node(
//            id = id,
//            name = name,
//            children = childrenNodes,
//            parent = parentNode
//        )
//    }
//
//    private fun NodeEntity.toDomainWithoutChildren(): Node = Node(
//        id = id,
//        name = name,
//        children = null,
//        parent = null
//    )
}