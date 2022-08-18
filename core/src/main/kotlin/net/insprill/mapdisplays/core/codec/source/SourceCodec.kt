package net.insprill.mapdisplays.core.codec.source

import net.insprill.mapdisplays.core.codec.Decoder
import java.io.File

interface SourceCodec<T> : Decoder<T> {

    override fun decode(input: File): T {
        return input.inputStream().use { s -> decode(s) }
    }

}
