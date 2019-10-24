package com.biteforcemc.classes.handlers;

import com.biteforcemc.classes.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class classHandler {


    public void setClass(Player p, String c) {
        if (p == null) {
            Main.getInstance().getLogger().info("Player was null");
            return;
        }
        if (!getClass(p).equals("Medic")) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set classes.medic false");
        }
        if (c.equals("Medic")) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set classes.medic true");
        }
        if(Main.getInstance().getPlayers().getString("Players." + p.getUniqueId()) == null) {
            Main.getInstance().getPlayers().set("Players." + p.getUniqueId(), "Default");
            return;
        }
        Main.getInstance().getPlayers().set("Players." + p.getUniqueId(), c);
        Main.getInstance().savePlayerFile();
    }

    public String getClass(Player p) {
        if (p == null) {
            Main.getInstance().getLogger().info("Player was null");
            return null;
        }
        if (Main.getInstance().getPlayers() == null) {
            return "Config file was null";
        }
        if (Main.getInstance().getPlayers().getString("Players." + p.getUniqueId()) == null) {
            return "Default";
        } else {
            return Main.getInstance().getPlayers().get("Players." + p.getUniqueId()).toString();
        }
    }

    public boolean isClass(String c) {
        if (c.equals("Archer") || c.equals("Medic") || c.equals("Juggernaut") || c.equals("Miner")) {
            return true;
        } else return false;
    }

    public void giveToken(final Player p, int amount) {
        p.getInventory().addItem(getClassToken(amount));
    }

    public ItemStack getClassToken(int amt) {
        ItemStack token = new ItemStack(Material.DOUBLE_PLANT, amt, (short) 0);
        ItemMeta tokenMeta = token.getItemMeta();
        tokenMeta.setDisplayName(ChatColor.GREEN + "Class Token");
        tokenMeta.setLore(Arrays.asList("§c- §f1 time use", "§c- §fRight click this to change classes", "§c§lWARNING: Right clicking this will reset your class"));

        token.setItemMeta(tokenMeta);
        return token;
    }

    public void giveEffects(Player p) {
        if (p != null)
            if (getClass(p).equals("Miner")) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 1));
                p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 99999, 1));

            } else if (getClass(p).equals("Juggernaut")) {
                ItemStack chestPlate = p.getInventory().getChestplate();
                if (chestPlate != null && chestPlate.getItemMeta().hasLore()) {
                    if (chestPlate.getItemMeta().getLore().contains("Overload 1")) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 99999, 1));
                    } else if (chestPlate.getItemMeta().getLore().contains("Overload 2")) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 99999, 2));
                    } else if (chestPlate.getItemMeta().getLore().contains("Overload 3")) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 99999, 3));
                    } else if (chestPlate.getItemMeta().getLore().contains("Overload 4")) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 99999, 4));
                    } else if (chestPlate.getItemMeta().getLore().contains("Overload 5")) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 99999, 5));
                    } else p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 99999, 0));
                    ;
                } else {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 99999, 0));
                }
            }
    }

//    public boolean hasEffects(Player p) {
//        if (getClass(p).equals("Miner")) {
//            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 2));
//            p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 99999, 2));
//            return true;
//        } else if (getClass(p).equals("Juggernaut")) {
//            p.setMaxHealth(p.getMaxHealth() + 2);
//            return true;
//        }
//        return false;
//    }
}
