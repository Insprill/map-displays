package net.insprill.mapdisplays.image.codec

import net.insprill.mapdisplays.core.MapCoord
import net.insprill.mapdisplays.core.codec.Codec
import net.insprill.mapdisplays.core.exception.DecodeException
import net.insprill.mapdisplays.image.Image
import net.insprill.mapdisplays.image.Pixel
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

object ImageCodec : Codec<Image> {

    private const val CODEC_VERSION = 1

    override fun encode(obj: Image): ByteArrayOutputStream {
        val output = ByteArrayOutputStream()
        output.write(CODEC_VERSION)
        output.write(obj.pixels.size)
        for (pixel in obj.pixels) {
            output.write(pixel.color.toInt())
            output.write(pixel.mapCoords.size)
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
        val pixelCount = input.read()
        repeat(pixelCount) {
            val pixelColor = input.read()
            val pixel = Pixel(pixelColor.toByte())
            val coordCount = input.read()
            repeat(coordCount) {
                val x = input.read()
                val y = input.read()
                pixel.addCoord(MapCoord(x, y))
            }
            pixels.add(pixel)
        }
        return Image(pixels)
    }

}
