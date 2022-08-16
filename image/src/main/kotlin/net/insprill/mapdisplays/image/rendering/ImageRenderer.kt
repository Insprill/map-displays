package net.insprill.mapdisplays.image.rendering

import net.insprill.mapdisplays.image.Image
import org.bukkit.entity.Player
import org.bukkit.map.MapCanvas
import org.bukkit.map.MapRenderer
import org.bukkit.map.MapView

class ImageRenderer(private val image: Image) : MapRenderer() {

    private var drawn = false

    override fun render(map: MapView, canvas: MapCanvas, player: Player) {
        if (drawn) return
        for (pixel in image.pixels) {
            for (coord in pixel.mapCoords) {
                canvas.setPixel(coord.x, coord.y, pixel.color)
            }
        }
        drawn = true
    }

}
