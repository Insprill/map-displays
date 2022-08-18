package net.insprill.mapdisplays.image.codec.cache

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
        image = CachedImageCodec.decode(this.javaClass.classLoader.getResourceAsStream("image.png")!!)
    }

    @Test
    fun encode() {
        val encoded = CachedImageCodec.encode(image)

        assertTrue(encoded.size() > 0)
        assertNotNull(encoded)
    }

    @Test
    fun decode() {
        val encoded = CachedImageCodec.encode(image)

        val decoded = CachedImageCodec.decode(encoded.toByteArray().inputStream())
        assertTrue(decoded.pixels.size > 0)
        assertNotNull(decoded)
    }

    @Test
    fun encode_Decode_SameImage() {
        val encoded = CachedImageCodec.encode(image)
        val decoded = CachedImageCodec.decode(encoded.toByteArray().inputStream())

        assertEquals(image, decoded)
    }

}
