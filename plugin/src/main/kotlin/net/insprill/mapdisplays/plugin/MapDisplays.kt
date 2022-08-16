package net.insprill.mapdisplays.plugin

import co.aikar.commands.PaperCommandManager
import net.insprill.mapdisplays.plugin.command.MapDisplaysCommand
import org.bukkit.plugin.java.JavaPlugin

class MapDisplays : JavaPlugin() {

    override fun onEnable() {
        val cmdManager = PaperCommandManager(this)
        cmdManager.registerCommand(MapDisplaysCommand(this))
    }

}
