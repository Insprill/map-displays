package net.insprill.mapdisplays.video

import net.insprill.mapdisplays.core.MapCoord
import net.insprill.mapdisplays.video.rendering.VideoRenderer
import org.bukkit.Bukkit
import org.bukkit.map.MapView

class Video {

    val frameCount: Int
    val multiPos: MapCoord
    val frameData: HashMap<Int, HashMap<MapCoord, Byte>>
    private var currFrame = 0

    constructor(frameCount: Int, multiPos: MapCoord) {
        this.frameCount = frameCount
        this.multiPos = multiPos
        this.frameData = HashMap()
    }

    constructor(frameData: HashMap<Int, HashMap<MapCoord, Byte>>, frameCount: Int, multiPos: MapCoord) {
        this.frameData = frameData
        this.frameCount = frameCount
        this.multiPos = multiPos
    }

    fun nextChanges(): Map<MapCoord, Byte>? {
        if (currFrame >= frameCount)
            currFrame = 0
        return frameData[currFrame++]
    }

    fun setColor(frameNum: Int, coord: MapCoord, color: Byte) {
        val colors = frameData[frameNum] ?: HashMap()
        colors[coord] = color
        frameData[frameNum] = colors
    }

    fun getLastColor(newestFrameNum: Int, coord: MapCoord): Byte {
        for (num in newestFrameNum..0) {
            val data = frameData[num] ?: continue
            return data[coord] ?: continue
        }
        return -1
    }

    fun createMap(): MapView {
        val map = Bukkit.createMap(Bukkit.getWorlds()[0])
        applyToMap(map)
        return map
    }

    fun applyToMap(view: MapView) {
        view.renderers.forEach { r -> view.removeRenderer(r) }
        view.addRenderer(VideoRenderer(this))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Video

        if (frameCount != other.frameCount) return false
        if (multiPos != other.multiPos) return false
        if (frameData != other.frameData) return false
        if (currFrame != other.currFrame) return false

        return true
    }

    override fun hashCode(): Int {
        var result = frameCount
        result = 31 * result + multiPos.hashCode()
        result = 31 * result + frameData.hashCode()
        result = 31 * result + currFrame
        return result
    }

    override fun toString(): String {
        return "Video(frameCount=$frameCount, multiPos=$multiPos, frameData=$frameData, currFrame=$currFrame)"
    }

}
