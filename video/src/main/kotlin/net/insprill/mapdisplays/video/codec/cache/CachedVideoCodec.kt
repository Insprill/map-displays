package net.insprill.mapdisplays.video.codec.cache

import com.google.common.primitives.Ints
import net.insprill.mapdisplays.core.MapCoord
import net.insprill.mapdisplays.core.codec.cache.CachedCodec
import net.insprill.mapdisplays.core.exception.DecodeException
import net.insprill.mapdisplays.video.Video
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.OutputStream

object CachedVideoCodec : CachedCodec<Video> {

    private const val CODEC_VERSION = 0

    override fun encode(obj: Video): ByteArrayOutputStream {
        val output = ByteArrayOutputStream()
        output.write(CODEC_VERSION)
        output.write(obj.multiPos.x)
        output.write(obj.multiPos.y)
        output.writeLarge(obj.frameCount)
        output.writeLarge(obj.frameData.size)
        for (data in obj.frameData) {
            output.writeLarge(data.key)
            output.write(data.value.size)
            for (change in data.value) {
                output.write(change.key.x)
                output.write(change.key.y)
                output.write(change.value.toInt())
            }
        }
        return output
    }

    override fun decode(input: ByteArrayInputStream): Video {
        val codec = input.read()
        if (codec != CODEC_VERSION)
            throw DecodeException("Unknown codec version $codec")
        val multiPos = MapCoord(input.read(), input.read())
        val frameCount = input.readLarge()
        val frameChanges = input.readLarge()
        val frameData = HashMap<Int, HashMap<MapCoord, Byte>>()
        repeat(frameChanges) {
            val frameNum = input.readLarge()
            val changeCount = input.read()
            val changes = HashMap<MapCoord, Byte>()
            repeat(changeCount) {
                val x = input.read()
                val y = input.read()
                val color = input.read()
                changes[MapCoord(x, y)] = color.toByte()
            }
            frameData[frameNum] = changes
        }
        return Video(frameData, frameCount, multiPos)
    }

    private fun OutputStream.writeLarge(i: Int) {
        if (i > Byte.MAX_VALUE || i < Byte.MIN_VALUE) {
            this.write(1)
            this.write(Ints.toByteArray(i))
        } else {
            this.write(0)
            this.write(i)
        }
    }

    private fun InputStream.readLarge(): Int {
        return if (this.read() == 1) {
            Ints.fromByteArray(this.readNBytes(4))
        } else {
            this.read()
        }
    }

}
