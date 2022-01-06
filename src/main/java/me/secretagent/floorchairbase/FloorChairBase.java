package me.secretagent.floorchairbase;

import co.aikar.commands.BukkitCommandManager;
import me.secretagent.floorchairbase.api.FloorChairAPI;
import me.secretagent.floorchairbase.api.user.rank.FloorChairRank;
import me.secretagent.floorchairbase.commands.RankCommand;
import me.secretagent.floorchairbase.listeners.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class FloorChairBase extends JavaPlugin {

    private static FloorChairBase plugin;

    private FloorChairAPI api;

    @Override
    public void onEnable() {
        plugin = this;
        api = new FloorChairAPI();
        BukkitCommandManager manager = new BukkitCommandManager(this);
        manager.registerCommand(new RankCommand());
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static FloorChairBase getPlugin() {
        return plugin;
    }

    public FloorChairAPI getApi() {
        return api;
    }

}
