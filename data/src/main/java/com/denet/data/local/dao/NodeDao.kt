package com.denet.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.denet.data.local.entities.NodeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NodeDao {
    @Query("SELECT * FROM node_entity")
    fun getAll(): Flow<List<NodeEntity>>

    @Query("SELECT * FROM node_entity")
    suspend fun getAllSync(): List<NodeEntity>

    @Insert
    suspend fun insert(node: NodeEntity): Long

    @Query("SELECT COUNT(*) FROM node_entity")
    suspend fun getCount(): Int

    @Query("SELECT * FROM node_entity WHERE id = :id")
    suspend fun getNode(id: Int): NodeEntity?

    @Update
    suspend fun updateNode(node: NodeEntity)

    @Query("UPDATE node_entity SET name = :newName WHERE id = :id")
    suspend fun updateName(id: Int, newName: String)

    @Query("DELETE FROM node_entity WHERE id IN (:ids)")
    suspend fun deleteByIds(ids: List<Int>)

    @Query("DELETE FROM node_entity WHERE id == :id")
    suspend fun deleteById(id: Int)
}