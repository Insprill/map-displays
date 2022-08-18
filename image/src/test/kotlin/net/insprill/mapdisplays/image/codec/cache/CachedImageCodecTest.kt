package net.insprill.mapdisplays.image.codec.cache

import net.insprill.mapdisplays.core.MapCoord
import net.insprill.mapdisplays.image.Image
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CachedImageCodecTest {

    private lateinit var image: Image

    @BeforeEach
    fun setup() {
        image = SourceImageCodec.decode(this.javaClass.classLoader.getResourceAsStream("image.png")!!)
    }

    @Test
    fun encode() {
        val encoded = CachedImageCodec.encode(image)

        assertNotNull(encoded)
        assertTrue(encoded.size() > 0)
    }

    @Test
    fun decode() {
        val encoded = CachedImageCodec.encode(image)

        val decoded = CachedImageCodec.decode(encoded.toByteArray().inputStream())

        assertNotNull(decoded)
        assertTrue(decoded.pixels.size > 0)
        assertEquals(MapCoord(0, 0), decoded.multiPos)
    }

    @Test
    fun encode_Decode_SameImage() {
        val encoded = CachedImageCodec.encode(image)
        val decoded = CachedImageCodec.decode(encoded.toByteArray().inputStream())

        assertEquals(image, decoded)
    }

}
