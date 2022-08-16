package net.insprill.mapdisplays.image

import net.insprill.mapdisplays.core.MapCoord

class Pixel(val color: Byte) {

    val mapCoords = ArrayList<MapCoord>()

    fun addCoord(mapCoord: MapCoord) {
        mapCoords.add(mapCoord)
    }

}
