package net.insprill.mapdisplays.video.codec.source

import net.insprill.mapdisplays.core.Constants
import net.insprill.mapdisplays.core.MapCoord
import net.insprill.mapdisplays.core.codec.Decoder
import net.insprill.mapdisplays.video.Video
import org.bukkit.map.MapPalette
import org.jcodec.api.FrameGrab
import org.jcodec.common.io.ByteBufferSeekableByteChannel
import org.jcodec.scale.AWTUtil
import java.awt.Color
import java.io.InputStream
import java.nio.ByteBuffer

object VideoDecoder : Decoder<Video, FrameGrab> {

    override fun decode(input: InputStream): Video {
        val bytes = input.readAllBytes()
        return decode(FrameGrab.createFrameGrab(ByteBufferSeekableByteChannel(ByteBuffer.wrap(bytes), bytes.size)))
    }

    override fun decode(input: FrameGrab): Video {
        val meta = input.videoTrack.meta

        val width = input.mediaInfo.dim.width
        val height = input.mediaInfo.dim.height

        val video = Video(meta.totalFrames, MapCoord(0, 0))

        val resize = width != Constants.MAP_SIZE || height != Constants.MAP_SIZE

        for (frameNum in 0..meta.totalFrames) {
            val picture = input.nativeFrame ?: break
            val fullImage = AWTUtil.toBufferedImage(picture)
            val image = if (resize) {
                MapPalette.resizeImage(fullImage)
            } else {
                fullImage
            }

            repeat(Constants.MAP_SIZE) { x ->
                repeat(Constants.MAP_SIZE) { y ->
                    val coord = MapCoord(x, y)

                    @Suppress("DEPRECATION")
                    val mapColor = MapPalette.matchColor(Color(image.getRGB(x, y)))
                    val lastColor = video.getLastColor(frameNum, coord)
                    if (mapColor != lastColor) {
                        video.setColor(frameNum, coord, mapColor)
                    }
                }
            }

            println("decoded frame $frameNum of ${meta.totalFrames}")
        }
        return video
    }

}
