package com.biteforcemc.classes.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class npcGUI {


    public static void openInventory(final Player p, final boolean enabled) {
        final Inventory inv = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Class Selector");
        inv.setItem(4, getHelpItem(enabled));
        inv.setItem(10, getMedicItem(enabled));
        inv.setItem(12, getArcherItem(enabled));
        inv.setItem(14, getJuggernautItem(enabled));
        inv.setItem(16, getMinerItem(enabled));

        final ItemStack ph = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
        final ItemMeta meta = ph.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lExit"));
        ph.setItemMeta(meta);
        for (int i = 0; i < 9; ++i) {
            if (inv.getItem(i) == null) {
                inv.setItem(i, ph);
            }
        }
        for (int i = 18; i < 27; ++i) {
            if (inv.getItem(i) == null) {
                inv.setItem(i, ph);
            }
        }
        p.playSound(p.getLocation(), Sound.CHEST_OPEN, 0.5f, 1.0f);
        p.openInventory(inv);
    }

    public static ItemStack getMedicItem(final boolean enabled) {
        final ItemStack medic = new ItemStack(Material.GOLD_HELMET, 1, (short) 0);
        final ItemMeta medicMeta = medic.getItemMeta();
        medicMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', enabled ? "&a&lMedic" : "&c&lMedic"));
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§c§m-----------");
        lore.add("§fAbilities");
        lore.add("§c- §f/Revive (24h cooldown) - Bring back a death banned player");
        lore.add("§c§m-----------");
        lore.add(enabled ? "§aClick to set your class as Medic" : "§cYou already have a class");
        medicMeta.setLore(lore);
        medic.setItemMeta(medicMeta);

        return medic;
    }

    public static ItemStack getArcherItem(final boolean enable) {
        final ItemStack archer = new ItemStack(Material.BOW, 1, (short) 0);
        final ItemMeta archerMeta = archer.getItemMeta();
        archerMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', enable ? "&a&lArcher" : "&c&lArcher"));
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§c§m-----------");
        lore.add("§fAbilities");
        lore.add("§c- §fDeal 100% more damage with bows");
        lore.add("§c§m-----------");
        lore.add(enable ? "§aClick to set your class as Archer" : "§cYou already have a class");
        archerMeta.setLore(lore);
        archer.setItemMeta(archerMeta);

        return archer;
    }

    public static ItemStack getJuggernautItem(final boolean enabled) {
        final ItemStack juggernaut = new ItemStack(Material.DIAMOND_CHESTPLATE, 1, (short) 0);
        final ItemMeta juggernautMeta = juggernaut.getItemMeta();
        juggernautMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', enabled ? "&a&lJuggernaut" : "&c&lJuggernaut"));
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§c§m-----------");
        lore.add("§fAbilities");
        lore.add("§c- §f+2 Extra Hearts");
        lore.add("§c§m-----------");
        lore.add(enabled ? "§aClick to set your class as Juggernaut" : "§cYou already have a class");
        juggernautMeta.setLore(lore);
        juggernaut.setItemMeta(juggernautMeta);

        return juggernaut;
    }

    public static ItemStack getMinerItem(final boolean enabled) {
        final ItemStack miner = new ItemStack(Material.DIAMOND_PICKAXE, 1, (short) 0);
        final ItemMeta minerMeta = miner.getItemMeta();
        minerMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', enabled ? "&a&lMiner" : "&c&lMiner"));
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§c§m-----------");
        lore.add("§fAbilities");
        lore.add("§c- §fPermanent haste 2 and speed 2 buff");
        lore.add("§c§m-----------");
        lore.add(enabled ? "§aClick to set your class as Miner" : "§cYou already have a class");

        minerMeta.setLore(lore);
        miner.setItemMeta(minerMeta);

        return miner;
    }

    public static ItemStack getHelpItem(final boolean enabled) {
        final ItemStack help = new ItemStack(Material.BOOK, 1, (short) 0);
        final ItemMeta helpMeta = help.getItemMeta();
        helpMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lHow to Use"));
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§c§m-----------");
        lore.add("§c- §fEach Class has a unique Ability");
        lore.add("§c- §fYou can only choose 1 class per map");
        lore.add("§c- §fUsing a Class Token, you can change your class.");
        lore.add(enabled ? "§c- §fClick on a class to chose your class." : "§cYou already have a class chosen, Use a class token to reset your class");
        lore.add("§c§m-----------");
        helpMeta.setLore(lore);
        help.setItemMeta(helpMeta);

        return help;
    }
}
