package net.insprill.mapdisplays.video.codec.cache

import net.insprill.mapdisplays.core.codec.cache.CachedCodec
import net.insprill.mapdisplays.video.Video
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

object CachedVideoCodec : CachedCodec<Video> {

    override fun encode(obj: Video): ByteArrayOutputStream {
        TODO("Not yet implemented")
    }

    override fun decode(input: ByteArrayInputStream): Video {
        TODO("Not yet implemented")
    }

}
