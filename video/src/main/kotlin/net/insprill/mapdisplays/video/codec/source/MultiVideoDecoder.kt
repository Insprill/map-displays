package net.insprill.mapdisplays.video.codec.source

import net.insprill.mapdisplays.core.Constants
import net.insprill.mapdisplays.core.MapCoord
import net.insprill.mapdisplays.core.codec.MultiDecoder
import net.insprill.mapdisplays.video.Video
import org.bukkit.map.MapPalette
import org.jcodec.api.FrameGrab
import org.jcodec.common.io.ByteBufferSeekableByteChannel
import org.jcodec.scale.AWTUtil
import java.awt.Color
import java.io.InputStream
import java.nio.ByteBuffer
import kotlin.math.ceil
import kotlin.math.max

object MultiVideoDecoder : MultiDecoder<Video, FrameGrab> {

    override fun decode(input: InputStream): List<Video> {
        val bytes = input.readAllBytes()
        return decode(FrameGrab.createFrameGrab(ByteBufferSeekableByteChannel(ByteBuffer.wrap(bytes), bytes.size)))
    }

    override fun decode(input: FrameGrab): List<Video> {
        val meta = input.videoTrack.meta

        val width = input.mediaInfo.dim.width
        val height = input.mediaInfo.dim.height
        val countX = nextMultiple(width, Constants.MAP_SIZE) / Constants.MAP_SIZE
        val countY = nextMultiple(height, Constants.MAP_SIZE) / Constants.MAP_SIZE

        val videos = LinkedHashMap<MapCoord, Video>(meta.totalFrames)

        for (frameNum in 0..meta.totalFrames) {
            val picture = input.nativeFrame ?: break
            val fullImage = AWTUtil.toBufferedImage(picture)

            repeat(countY) { arrY -> // Loop Y first so maps can be placed in rows, not columns
                repeat(countX) { arrX ->
                    val cropped = fullImage.getSubimage(
                        arrX * Constants.MAP_SIZE, arrY * Constants.MAP_SIZE,
                        Constants.MAP_SIZE,
                        Constants.MAP_SIZE
                    )
                    val multiPos = MapCoord(arrX, arrY)
                    val video = videos[multiPos] ?: Video(meta.totalFrames, multiPos)

                    repeat(Constants.MAP_SIZE) { x ->
                        repeat(Constants.MAP_SIZE) { y ->
                            val coord = MapCoord(x, y)

                            @Suppress("DEPRECATION")
                            val mapColor = MapPalette.matchColor(Color(cropped.getRGB(x, y)))
                            val lastColor = video.getLastColor(frameNum, coord)
                            if (mapColor != lastColor) {
                                video.setColor(frameNum, coord, mapColor)
                            }
                        }
                    }

                    videos[multiPos] = video
                }
            }
            println("decoded frame $frameNum of ${meta.totalFrames}")
        }
        return ArrayList(videos.values)
    }

    private fun nextMultiple(i: Int, multiple: Int): Int {
        return max(Constants.MAP_SIZE, (ceil((i / multiple).toFloat()) * multiple).toInt())
    }

}
