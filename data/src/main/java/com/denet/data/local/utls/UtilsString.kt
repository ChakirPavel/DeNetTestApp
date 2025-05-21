package com.denet.data.local.utls

import jakarta.inject.Inject
import java.security.MessageDigest

class UtilsString @Inject constructor() {
    fun generateName(text: String): String {
        val hash = MessageDigest.getInstance("SHA-256").digest(text.toByteArray())

        val last20Bytes = hash.takeLast(20).toByteArray()
        val hexString = last20Bytes.joinToString("") { "%02x".format(it) }
        return "0x$hexString"
    }
}