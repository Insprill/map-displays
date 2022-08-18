package net.insprill.mapdisplays.video.codec.source

import net.insprill.mapdisplays.core.codec.Decoder
import net.insprill.mapdisplays.video.Video
import org.bukkit.map.MapPalette
import org.jcodec.api.FrameGrab
import org.jcodec.common.io.ByteBufferSeekableByteChannel
import org.jcodec.scale.AWTUtil
import java.awt.image.BufferedImage
import java.io.InputStream
import java.nio.ByteBuffer
import kotlin.math.floor

class VideoDecoder : Decoder<Video> {

    override fun decode(input: InputStream): Video {
        val bytes = input.readAllBytes()
        val grab = FrameGrab.createFrameGrab(ByteBufferSeekableByteChannel(ByteBuffer.wrap(bytes), bytes.size))
        val meta = grab.videoTrack.meta
        val fps = meta.totalFrames / meta.totalDuration
        val skip = floor(fps / 20).toInt()

        var skipCounter = 0
        while (true) {
            if (skip > 0 && ++skipCounter % skip != 0) continue
            val picture = grab.nativeFrame ?: break
            val frameImage: BufferedImage = MapPalette.resizeImage(AWTUtil.toBufferedImage(picture))
        }
        TODO("Not yet implemented")
    }

}
