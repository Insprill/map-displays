package net.insprill.mapdisplays.image.codec.source

import net.insprill.mapdisplays.core.Constants.MAP_SIZE
import net.insprill.mapdisplays.core.MapCoord
import net.insprill.mapdisplays.core.codec.Decoder
import net.insprill.mapdisplays.image.Image
import net.insprill.mapdisplays.image.Pixel
import org.bukkit.map.MapPalette
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.InputStream
import javax.imageio.ImageIO

object ImageDecoder : Decoder<Image, BufferedImage> {

    override fun decode(input: InputStream): Image {
        return decode(ImageIO.read(input))
    }

    override fun decode(input: BufferedImage): Image {
        val resizedImage = MapPalette.resizeImage(input)
        val imagePixels = HashMap<Byte, Pixel>()
        val pixels = ArrayList<Pixel>()
        repeat(MAP_SIZE) { x ->
            repeat(MAP_SIZE) { y ->
                val mapCoord = MapCoord(x, y)

                @Suppress("DEPRECATION")
                val mapColor = MapPalette.matchColor(Color(resizedImage.getRGB(x, y)))
                val pixel = imagePixels.getOrDefault(mapColor, Pixel(mapColor))
                pixel.addCoord(mapCoord)
                imagePixels[mapColor] = pixel
            }
        }
        pixels.addAll(imagePixels.values)
        return Image(pixels)
    }

}
