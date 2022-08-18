package net.insprill.mapdisplays.core.codec

import java.io.File
import java.io.InputStream

interface Decoder<T> {

    fun decode(input: File): T

    fun decode(input: InputStream): T

}
