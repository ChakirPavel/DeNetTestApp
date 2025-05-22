package com.denet.domain.models

data class Node(
    val id: Int,
    val name: String,
    val children: List<Node> = emptyList(),
    val parent: Node? = null
)