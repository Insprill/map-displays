package net.insprill.mapdisplays.image

import net.insprill.mapdisplays.core.MapCoord
import net.insprill.mapdisplays.image.rendering.ImageRenderer
import org.bukkit.Bukkit
import org.bukkit.map.MapView

class Image {

    val pixels: ArrayList<Pixel>
    val multiPos: MapCoord

    constructor(pixels: List<Pixel>) : this(pixels, MapCoord(0, 0))

    constructor(pixels: List<Pixel>, multiPos: MapCoord) {
        this.pixels = ArrayList(pixels)
        this.multiPos = multiPos
    }

    fun createMap(): MapView {
        val map = Bukkit.createMap(Bukkit.getWorlds()[0])
        applyToMap(map)
        return map
    }

    fun applyToMap(view: MapView) {
        view.renderers.forEach { r -> view.removeRenderer(r) }
        view.addRenderer(ImageRenderer(this))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Image

        if (pixels != other.pixels) return false
        if (multiPos != other.multiPos) return false

        return true
    }

    override fun hashCode(): Int {
        var result = pixels.hashCode()
        result = 31 * result + multiPos.hashCode()
        return result
    }

    override fun toString(): String {
        return "Image(pixels=$pixels, multiPos=$multiPos)"
    }

}
