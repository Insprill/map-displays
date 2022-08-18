package net.insprill.mapdisplays.plugin.command

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Subcommand
import net.insprill.mapdisplays.image.codec.source.SourceImageCodec
import net.insprill.mapdisplays.image.rendering.ImageRenderer
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
        val img = SourceImageCodec.decode(plugin.getResource("image.png")!!)
        val map = Bukkit.createMap(player.world)
        for (renderer in map.renderers) {
            map.removeRenderer(renderer)
        }
        map.addRenderer(ImageRenderer(img))
        val item = ItemStack(Material.FILLED_MAP)
        val meta = item.itemMeta as MapMeta
        meta.mapView = map
        item.itemMeta = meta
        player.inventory.addItem(item)
    }

}
