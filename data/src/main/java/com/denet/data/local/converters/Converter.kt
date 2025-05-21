package com.denet.data.local.converters

import androidx.room.TypeConverter
import jakarta.inject.Inject

class Converter {
    @TypeConverter
    fun fromList(value: MutableList<Int>): String = value.joinToString(",")

    @TypeConverter
    fun toList(value: String): MutableList<Int> = value
        .split(",")
        .filter { it.isNotBlank() }
        .map { it.toInt() }
        .toMutableList()
}