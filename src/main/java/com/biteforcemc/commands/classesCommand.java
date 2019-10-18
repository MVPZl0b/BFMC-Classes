package com.biteforcemc.commands;

import com.biteforcemc.classes.Main;
import com.biteforcemc.gui.npcGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class classesCommand implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("classes")) {
            if(args.length == 0) {
                if(Main.getInstance().getClassHandler().getClass(player).equals("Default")) {
                    npcGUI.openInventory(player, true);
                    return false;
                }else{
                    npcGUI.openInventory(player, false);
                    return false;
                }

            }
            if (args[0].equalsIgnoreCase("give") && player.hasPermission("classes.give")) {
                if(args.length == 3 && args[2] != null && isInt(args[2])) {
                    int amt = Integer.parseInt(args[2]);
                    Player target = Bukkit.getPlayer(args[1]);
                    if(target != null) {
                        Main.getInstance().getClassHandler().giveToken(target, amt);
                    }
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + "Usage: /classes [give] [player] [amt]");
                    return false;
                }
            }
            if (args[0].equalsIgnoreCase("set") && player.hasPermission("classes.set")) {
                if(args.length == 3 && args[2] != null && Main.getInstance().getClassHandler().isClass(args[2])) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if(target != null) {
                        Main.getInstance().getClassHandler().setClass(target, args[2]);
                        return true;
                    } else player.sendMessage(ChatColor.RED + "Usage: /classes [set] [player] [class]");
                } else {
                    player.sendMessage(ChatColor.RED + "Usage: /classes [set] [player] [class]");
                    return false;
                }
            }
        }
        return false;
    }

    private boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
