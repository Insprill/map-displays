package net.insprill.mapdisplays.image

import net.insprill.mapdisplays.core.Constants.MAP_SIZE
import net.insprill.mapdisplays.core.MapCoord
import org.bukkit.map.MapPalette
import java.awt.Color
import java.awt.Image

class Image {

    val pixels: ArrayList<Pixel>

    constructor(rawImage: Image) {
        val resizedImage = MapPalette.resizeImage(rawImage)
        val imagePixels = HashMap<Byte, Pixel>()
        this.pixels = ArrayList()
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
    }

    constructor(pixels: List<Pixel>) {
        this.pixels = ArrayList(pixels)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as net.insprill.mapdisplays.image.Image

        if (pixels != other.pixels) return false

        return true
    }

    override fun hashCode(): Int {
        return pixels.hashCode()
    }

    override fun toString(): String {
        return "Image(pixels=$pixels)"
    }

}
