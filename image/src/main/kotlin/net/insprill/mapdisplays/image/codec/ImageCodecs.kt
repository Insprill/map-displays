package net.insprill.mapdisplays.image.codec

import net.insprill.mapdisplays.core.codec.Decoder
import net.insprill.mapdisplays.core.codec.cache.CachedCodec
import net.insprill.mapdisplays.image.Image
import net.insprill.mapdisplays.image.codec.cache.CachedImageCodec
import net.insprill.mapdisplays.image.codec.source.ImageDecoder
import net.insprill.mapdisplays.image.codec.source.MultiImageDecoder
import java.awt.image.BufferedImage

object ImageCodecs {

    @JvmStatic
    var imageDecoder: Decoder<Image, BufferedImage> = ImageDecoder

    @JvmStatic
    var multiImageDecoder: Decoder<List<Image>, BufferedImage> = MultiImageDecoder

    @JvmStatic
    var cachedImageCodec: CachedCodec<Image> = CachedImageCodec

}
