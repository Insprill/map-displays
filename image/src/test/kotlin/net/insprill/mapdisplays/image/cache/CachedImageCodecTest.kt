package net.insprill.mapdisplays.image.cache

import net.insprill.mapdisplays.image.Image
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import javax.imageio.ImageIO

class CachedImageCodecTest {

    @Test
    fun encode_decode() {
        val bufferedImage = ImageIO.read(this.javaClass.classLoader.getResourceAsStream("image.png"))
        val image = Image(bufferedImage)

        val encoded = CachedImageCodec.encode(image)
        assertNotNull(encoded)

        val decoded = CachedImageCodec.decode(ByteArrayInputStream(encoded.toByteArray()))
        assertNotNull(decoded)

        assertEquals(image, decoded)
    }

}
