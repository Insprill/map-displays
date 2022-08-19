package net.insprill.mapdisplays.plugin.command

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Subcommand
import net.insprill.mapdisplays.image.codec.source.ImageDecoder
import net.insprill.mapdisplays.image.codec.source.MultiImageDecoder
import net.insprill.mapdisplays.video.codec.source.MultiVideoDecoder
import net.insprill.mapdisplays.video.codec.source.VideoDecoder
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.MapMeta
import org.bukkit.plugin.java.JavaPlugin

@CommandAlias("mapdisplays|md|maps")
class MapDisplaysCommand(private val plugin: JavaPlugin) : BaseCommand() {

    @Subcommand("giveImage")
    fun giveImage(player: Player) {
        val img = ImageDecoder.decode(plugin.getResource("image.png")!!)
        val map = img.createMap()
        val item = ItemStack(Material.FILLED_MAP)
        val meta = item.itemMeta as MapMeta
        meta.mapView = map
        item.itemMeta = meta
        player.inventory.addItem(item)
    }

    @Subcommand("giveMultiImage")
    fun giveMultiImage(player: Player) {
        val images = MultiImageDecoder.decode(plugin.getResource("image_large.png")!!)
        for (img in images) {
            val map = img.createMap()
            val item = ItemStack(Material.FILLED_MAP)
            val meta = item.itemMeta as MapMeta
            meta.setDisplayName("${img.multiPos.x} ${img.multiPos.y}")
            meta.mapView = map
            item.itemMeta = meta
            player.inventory.addItem(item)
        }
    }

    @Subcommand("giveVideo")
    fun giveVideo(player: Player) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, Runnable {
            val video = VideoDecoder.decode(plugin.getResource("train.mp4")!!)
            val map = video.createMap()
            val item = ItemStack(Material.FILLED_MAP)
            val meta = item.itemMeta as MapMeta
            meta.setDisplayName("${video.multiPos.x} ${video.multiPos.y}")
            meta.mapView = map
            item.itemMeta = meta
            player.inventory.addItem(item)
        })
    }

    @Subcommand("giveMultiVideo")
    fun giveMultiVideo(player: Player) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, Runnable {
            val videos = MultiVideoDecoder.decode(plugin.getResource("train.mp4")!!)
            for (vid in videos) {
                val map = vid.createMap()
                val item = ItemStack(Material.FILLED_MAP)
                val meta = item.itemMeta as MapMeta
                meta.setDisplayName("${vid.multiPos.x} ${vid.multiPos.y}")
                meta.mapView = map
                item.itemMeta = meta
                player.inventory.addItem(item)
            }
        })
    }

}
