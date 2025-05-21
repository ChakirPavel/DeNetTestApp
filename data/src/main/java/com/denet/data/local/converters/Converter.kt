package com.denet.data.local.converters

import androidx.room.TypeConverter

class Converter {
    @TypeConverter
    fun fromList(value: List<Int>): String = value.joinToString(",")

    @TypeConverter
    fun toList(value: String): List<Int> = value
        .split(",")
        .filter { it.isNotBlank() }
        .map { it.toInt() }
}