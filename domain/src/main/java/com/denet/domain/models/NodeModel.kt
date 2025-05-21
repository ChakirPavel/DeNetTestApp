package com.denet.domain.models

data class Node(
    val id: Int,
    val name: String,
    var children: List<Node>? = null,
    val parent: Node? = null
)