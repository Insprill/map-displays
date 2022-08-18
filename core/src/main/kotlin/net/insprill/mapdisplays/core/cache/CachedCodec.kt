package net.insprill.mapdisplays.core.cache

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

interface CachedCodec<T> {

    fun encode(obj: T): ByteArrayOutputStream

    fun decode(input: ByteArrayInputStream): T

}
