package net.insprill.mapdisplays.video.codec.source

import net.insprill.mapdisplays.core.codec.source.SourceCodec
import net.insprill.mapdisplays.video.Video
import java.io.InputStream


class SourceVideoCodec : SourceCodec<Video> {

    override fun decode(input: InputStream): Video {
        TODO("Not yet implemented")
    }

}
