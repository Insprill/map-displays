package net.insprill.mapdisplays.core.codec.cache

import net.insprill.mapdisplays.core.codec.Decoder
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream

interface CachedCodec<T> : Decoder<T> {

    fun encode(obj: T): ByteArrayOutputStream

    fun decode(input: ByteArrayInputStream): T

    override fun decode(input: InputStream): T {
        return decode(input.readAllBytes().inputStream())
    }

    override fun decode(input: File): T {
        return input.inputStream().use { s -> decode(s.readAllBytes().inputStream()) }
    }

}
