package net.insprill.mapdisplays.image.codec

import net.insprill.mapdisplays.core.codec.Decoder
import net.insprill.mapdisplays.core.codec.cache.CachedCodec
import net.insprill.mapdisplays.image.Image
import net.insprill.mapdisplays.image.codec.cache.CachedImageCodec
import net.insprill.mapdisplays.image.codec.source.ImageDecoder
import net.insprill.mapdisplays.image.codec.source.MultiImageDecoder

object ImageCodecs {

    @JvmStatic
    var imageDecoder: Decoder<Image, java.awt.Image> = ImageDecoder

    @JvmStatic
    var multiImageDecoder: Decoder<List<Image>, java.awt.Image> = MultiImageDecoder

    @JvmStatic
    var cachedImageCodec: CachedCodec<Image> = CachedImageCodec

}
