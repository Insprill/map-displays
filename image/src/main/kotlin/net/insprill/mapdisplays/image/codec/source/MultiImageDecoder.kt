package net.insprill.mapdisplays.image.codec.source

import net.insprill.mapdisplays.core.Constants.MAP_SIZE
import net.insprill.mapdisplays.core.MapCoord
import net.insprill.mapdisplays.core.codec.MultiDecoder
import net.insprill.mapdisplays.image.Image
import java.awt.image.BufferedImage
import java.io.InputStream
import javax.imageio.ImageIO
import kotlin.math.ceil
import kotlin.math.max

object MultiImageDecoder : MultiDecoder<Image, java.awt.Image> {

    override fun decode(input: InputStream): List<Image> {
        return decode(ImageIO.read(input))
    }

    override fun decode(input: java.awt.Image): List<Image> {
        val imgWidth = input.getWidth(null)
        val imgHeight = input.getHeight(null)
        val countX = nextMultiple(imgWidth, MAP_SIZE) / MAP_SIZE
        val countY = nextMultiple(imgHeight, MAP_SIZE) / MAP_SIZE
        val images = ArrayList<Image>()

        val bufferedImage: BufferedImage
        if (input !is BufferedImage) {
            bufferedImage = BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB)
            val tmpGraphics = bufferedImage.createGraphics()
            tmpGraphics.drawImage(input, 0, 0, null)
            tmpGraphics.dispose()
        } else {
            bufferedImage = input
        }

        repeat(countY) { arrY -> // Loop Y first so maps can be placed in rows, not columns
            repeat(countX) { arrX ->
                val cropped = bufferedImage.getSubimage(arrX * MAP_SIZE, arrY * MAP_SIZE, MAP_SIZE, MAP_SIZE)
                images.add(Image(ImageDecoder.decode(cropped).pixels, MapCoord(arrX, arrY)))
            }
        }
        return images
    }

    private fun nextMultiple(i: Int, multiple: Int): Int {
        return max(MAP_SIZE, (ceil((i / multiple).toFloat()) * multiple).toInt())
    }

}
