package net.insprill.mapdisplays.core.codec

import java.io.InputStream
import java.io.OutputStream

interface Codec<T> {

    fun encode(obj: T): OutputStream

    fun decode(stream: InputStream): T

}
