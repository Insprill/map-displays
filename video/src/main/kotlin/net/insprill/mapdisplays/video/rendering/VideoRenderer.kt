package net.insprill.mapdisplays.video.rendering

import net.insprill.mapdisplays.video.Video
import org.bukkit.entity.Player
import org.bukkit.map.MapCanvas
import org.bukkit.map.MapRenderer
import org.bukkit.map.MapView

class VideoRenderer(private val video: Video) : MapRenderer() {

    override fun render(map: MapView, canvas: MapCanvas, player: Player) {
        val changes = video.nextChanges() ?: return
        for (change in changes) {
            canvas.setPixel(change.key.x, change.key.y, change.value)
        }
    }

}
