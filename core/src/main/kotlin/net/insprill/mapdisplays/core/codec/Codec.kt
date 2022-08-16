package net.insprill.mapdisplays.core.codec

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

interface Codec<T> {

    fun encode(obj: T): ByteArrayOutputStream

    fun decode(stream: ByteArrayInputStream): T

}
