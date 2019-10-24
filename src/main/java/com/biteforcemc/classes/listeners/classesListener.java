package com.biteforcemc.classes.listeners;

import com.biteforcemc.classes.Main;
import com.biteforcemc.classes.gui.npcGUI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class classesListener implements Listener {

    @EventHandler
    public void inventoryEvent(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getInventory().getTitle().equals(ChatColor.DARK_GRAY + "Class Selector")) {
            e.setCancelled(true);
            if (e.getCurrentItem() != null)
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&c&lExit"))) {
                    player.closeInventory();
                    return;
                }

            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&a&lMedic"))) {
                if (Main.getInstance().getClassHandler().getClass(player).equals("Default")) {
                    Main.getInstance().getClassHandler().setClass(player, "Medic");
                    player.getActivePotionEffects().clear();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have set your class to &dMedic"));
                    player.closeInventory();
                    return;
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYour class is already set to &c" + Main.getInstance().getClassHandler().getClass(player)));
                }
            }

            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&a&lArcher"))) {
                if (Main.getInstance().getClassHandler().getClass(player).equals("Default")) {
                    Main.getInstance().getClassHandler().setClass(player, "Archer");
                    player.getActivePotionEffects().clear();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have set your class to &dArcher"));
                    player.closeInventory();
                    return;
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYour class is already set to &c" + Main.getInstance().getClassHandler().getClass(player)));
                }
            }

            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&a&lJuggernaut"))) {
                if (Main.getInstance().getClassHandler().getClass(player).equals("Default")) {
                    Main.getInstance().getClassHandler().setClass(player, "Juggernaut");
                    player.getActivePotionEffects().clear();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have set your class to &dJuggernaut"));
                    player.closeInventory();
                    return;
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYour class is already set to &c" + Main.getInstance().getClassHandler().getClass(player)));
                }
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&a&lMiner"))) {
                if (Main.getInstance().getClassHandler().getClass(player).equals("Default")) {
                    Main.getInstance().getClassHandler().setClass(player, "Miner");
                    player.getActivePotionEffects().clear();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have set your class to &dMiner"));
                    player.closeInventory();
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYour class is already set to &c" + Main.getInstance().getClassHandler().getClass(player)));
                }
            }

        }
    }


    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (e.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Class Token"))
            e.setCancelled(true);
    }

    @EventHandler
    public void itemRightClick(PlayerInteractEvent e) {
        if (e.getItem() != null) {
            if (e.getItem().getItemMeta().equals(Main.getInstance().getClassHandler().getClassToken(1).getItemMeta())) {
                if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    npcGUI.openInventory(e.getPlayer(), true);
                    e.getPlayer().getActivePotionEffects().forEach(effect -> e.getPlayer().removePotionEffect(effect.getType()));
                    Main.getInstance().getClassHandler().setClass(e.getPlayer(), "Default");
                    if (e.getPlayer().getInventory().getItemInHand().getAmount() > 1)
                        e.getPlayer().getInventory().getItemInHand().setAmount(e.getPlayer().getItemInHand().getAmount() - 1);
                    else e.getPlayer().getInventory().removeItem(e.getItem());
                }
            }
        }
    }


    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        if (Main.getInstance().getPlayers().getString("Players." + e.getPlayer().getUniqueId()) == null) {
            Main.getInstance().getClassHandler().setClass(e.getPlayer(), "Default");
            Main.getInstance().savePlayerFile();
        }
    }

    @EventHandler
    public void bowShootEvent(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Arrow) {
            Arrow arrow = (Arrow) e.getDamager();
            if (arrow.getShooter() instanceof Player) {
                Player p = (Player) arrow.getShooter();
                if (Main.getInstance().getClassHandler().getClass(p).equals("Archer")) {
                    e.setDamage(e.getDamage() * 2);
                }
            }
        }
    }

}
