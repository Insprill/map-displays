package net.insprill.mapdisplays.video.codec

import net.insprill.mapdisplays.core.codec.Decoder
import net.insprill.mapdisplays.core.codec.cache.CachedCodec
import net.insprill.mapdisplays.video.Video
import net.insprill.mapdisplays.video.codec.cache.CachedVideoCodec
import net.insprill.mapdisplays.video.codec.source.MultiVideoDecoder
import net.insprill.mapdisplays.video.codec.source.VideoDecoder
import org.jcodec.api.FrameGrab

object VideoCodecs {

    @JvmStatic
    var videoDecoder: Decoder<Video, FrameGrab> = VideoDecoder

    @JvmStatic
    var multiVideoDecoder: Decoder<List<Video>, FrameGrab> = MultiVideoDecoder

    @JvmStatic
    var cachedVideoCodec: CachedCodec<Video> = CachedVideoCodec

}
