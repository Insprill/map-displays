package net.insprill.mapdisplays.core.codec

import java.io.File
import java.io.InputStream

interface Decoder<T, R> {

    fun decode(input: File): T {
        return input.inputStream().use { s -> decode(s) }
    }

    fun decode(input: InputStream): T

    fun decode(input: R): T

}
