package me.secretagent.floorchairbase.listeners;

import me.secretagent.floorchairbase.FloorChairBase;
import me.secretagent.floorchairbase.api.FloorChairAPI;
import me.secretagent.floorchairbase.api.user.FloorChairUser;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FloorChairAPI api = FloorChairBase.getPlugin().getApi();
        FloorChairUser user = api.getUser(player);
        user.setXP(user.getXP() + 25);
        player.sendMessage(ChatColor.GREEN + "+25 Experience: Joined FloorChair!");
    }

}
