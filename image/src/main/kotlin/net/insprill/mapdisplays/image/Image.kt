package net.insprill.mapdisplays.image

class Image(pixels: List<Pixel>) {

    val pixels: ArrayList<Pixel>

    init {
        this.pixels = ArrayList(pixels)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Image

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
