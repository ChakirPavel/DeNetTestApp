package com.denet.data.local.utls

import jakarta.inject.Inject
import java.security.MessageDigest

class NodeNameGenerator @Inject constructor() {
    fun generateName(id: String, parentId: String? = null): String {
        val hash = MessageDigest.getInstance("SHA-256").digest((id + parentId).toByteArray())

        val last20Bytes = hash.takeLast(20).toByteArray()
        val hexString = last20Bytes.joinToString("") { "%02x".format(it) }
        return "0x$hexString"
    }
}