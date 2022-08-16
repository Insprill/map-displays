package net.insprill.mapdisplays.image.codec

import com.google.common.primitives.Ints
import net.insprill.mapdisplays.core.MapCoord
import net.insprill.mapdisplays.core.codec.Codec
import net.insprill.mapdisplays.core.exception.DecodeException
import net.insprill.mapdisplays.image.Image
import net.insprill.mapdisplays.image.Pixel
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.OutputStream

object ImageCodec : Codec<Image> {

    private const val CODEC_VERSION = 0

    override fun encode(obj: Image): ByteArrayOutputStream {
        val output = ByteArrayOutputStream()
        output.write(CODEC_VERSION)
        output.writeLarge(obj.pixels.size)
        for (pixel in obj.pixels) {
            output.write(pixel.color.toInt())
            output.writeLarge(pixel.mapCoords.size)
            for (coord in pixel.mapCoords) {
                output.write(coord.x)
                output.write(coord.y)
            }
        }
        return output
    }

    override fun decode(stream: ByteArrayInputStream): Image {
        val input = stream.buffered()
        val codec = input.read()
        if (codec != CODEC_VERSION)
            throw DecodeException("Unknown codec version $codec")
        val pixels = ArrayList<Pixel>()
        val pixelCount = input.readLarge()
        repeat(pixelCount) {
            val pixelColor = input.read()
            val pixel = Pixel(pixelColor.toByte())
            val coordCount = input.readLarge()
            repeat(coordCount) {
                val x = input.read()
                val y = input.read()
                pixel.addCoord(MapCoord(x, y))
            }
            pixels.add(pixel)
        }
        return Image(pixels)
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
