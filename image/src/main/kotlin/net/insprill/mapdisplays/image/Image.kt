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
        val imagePixels = mapOf<Byte, Pixel>()
        this.pixels = ArrayList()
        repeat(MAP_SIZE) { x ->
            repeat(MAP_SIZE) { y ->
                val mapCoord = MapCoord(x, y)
                val color = Color(resizedImage.getRGB(x, y))
                @Suppress("DEPRECATION")
                val mapColor = MapPalette.matchColor(color.red, color.green, color.blue)
                val pixel = imagePixels.getOrDefault(mapColor, Pixel(mapColor))
                pixel.addCoord(mapCoord)
                pixels.add(pixel)
            }
        }
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
