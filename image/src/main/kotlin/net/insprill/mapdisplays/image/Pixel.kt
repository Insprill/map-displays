package net.insprill.mapdisplays.image

import net.insprill.mapdisplays.core.MapCoord

class Pixel(val color: Byte) {

    val mapCoords = ArrayList<MapCoord>()

    fun addCoord(mapCoord: MapCoord) {
        mapCoords.add(mapCoord)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pixel

        if (color != other.color) return false
        if (mapCoords != other.mapCoords) return false

        return true
    }

    override fun hashCode(): Int {
        var result = color.toInt()
        result = 31 * result + mapCoords.hashCode()
        return result
    }

    override fun toString(): String {
        return "Pixel(color=$color, mapCoords=$mapCoords)"
    }

}
