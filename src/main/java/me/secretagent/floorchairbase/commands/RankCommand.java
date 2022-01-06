package me.secretagent.floorchairbase.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import me.secretagent.floorchairbase.FloorChairBase;
import me.secretagent.floorchairbase.api.FloorChairAPI;
import me.secretagent.floorchairbase.api.user.FloorChairUser;
import me.secretagent.floorchairbase.api.user.rank.FloorChairRank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

@CommandAlias("rank")
public class RankCommand extends BaseCommand {

    private final FloorChairAPI api = FloorChairBase.getPlugin().getApi();

    @Default
    public void onDefault(Player player, String args[]) {

    }

    @Subcommand("promote")
    @CommandCompletion("@players")
    public void onPromote(CommandSender sender, String args[]) {
        if (!(sender instanceof ConsoleCommandSender) && api.getUser((Player) sender).getRank().getLevel() < 5) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
            return;
        }
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Please specify a player!");
            return;
        }
        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(ChatColor.RED + "That player is not online!");
            return;
        }
        FloorChairUser user = api.getUser(player);
        if (user.getRank() == FloorChairRank.OWNER) {
            sender.sendMessage(ChatColor.RED + user.getPlayer().getName() + " is already rank OWNER!");
            return;
        }
        user.setRank(user.getRank().getLevel() + 1);
        sender.sendMessage(ChatColor.GREEN + "Promoted " + user.getPlayer().getName() + " to " + user.getRank().toString());
    }

    @Subcommand("demote")
    @CommandCompletion("@players")
    public void onDemote(CommandSender sender, String args[]) {
        if (!(sender instanceof ConsoleCommandSender) && api.getUser((Player) sender).getRank().getLevel() < 5) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
            return;
        }
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Please specify a player!");
            return;
        }
        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(ChatColor.RED + "That player is not online!");
            return;
        }
        FloorChairUser user = api.getUser(player);
        if (user.getRank() == FloorChairRank.DEFAULT) {
            sender.sendMessage(ChatColor.RED + user.getPlayer().getName() + " is already rank DEFAULT!");
            return;
        }
        user.setRank(user.getRank().getLevel() - 1);
        sender.sendMessage(ChatColor.GREEN + "Demoted " + user.getPlayer().getName() + " to " + user.getRank().toString());
    }


}
