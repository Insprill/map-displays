package net.insprill.mapdisplays.plugin.command

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Subcommand
import net.insprill.mapdisplays.image.codec.source.ImageDecoder
import net.insprill.mapdisplays.image.codec.source.MultiImageDecoder
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

    @Subcommand("giveArrayImage")
    fun giveArrayImage(player: Player) {
        val images = MultiImageDecoder.decode(plugin.getResource("image_large.png")!!)
        println("image count: ${images.size}")
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

}
