package com.denet.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.security.MessageDigest

@Entity(tableName = "node_entity")
data class NodeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "parent_id")
    val parentId: Int? = null,
    @ColumnInfo(name = "child_ids")
    val childIds: MutableList<Int> = mutableListOf()
)