package me.woutergritter.wenchantments.customenchant.potionenchant;

import me.woutergritter.wenchantments.WEnchantments;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PotionEnchantManager implements Listener {
    private final static int POTION_UPDATE_INTERVAL = 10;
    private final static int POTION_EFFECT_DURATION = 20;

    private final List<PotionEffectEnchantment> potionEnchants = new ArrayList<>();
    private final List<Player>[] tickDividedPlayers = new List[POTION_UPDATE_INTERVAL];

    private int tickNumber = 0;

    public PotionEnchantManager() {
        for(PotionEffectType potionEffectType : PotionEffectType.values()) {
            PotionEffectEnchantment potionEnchant = new PotionEffectEnchantment(potionEffectType);
            potionEnchant.register();

            potionEnchants.add(potionEnchant);
        }

        for(int i = 0; i < tickDividedPlayers.length; i++) {
            tickDividedPlayers[i] = new ArrayList<>();
        }

        Bukkit.getOnlinePlayers().forEach(this::insertPlayer);

        Bukkit.getScheduler().runTaskTimer(WEnchantments.instance(), this::tick, 1, 1);
        Bukkit.getPluginManager().registerEvents(this, WEnchantments.instance());
    }

    private void tick() {
        List<Player> players = tickDividedPlayers[tickNumber];
        players.forEach(this::applyPotionEffects);

        tickNumber++;
        if(tickNumber == POTION_UPDATE_INTERVAL) {
            tickNumber = 0;
        }
    }

    private void applyPotionEffects(Player player) {
        applyPotionEffectsByItems(player,
                player.getItemInHand(),
                player.getEquipment().getHelmet(),
                player.getEquipment().getChestplate(),
                player.getEquipment().getLeggings(),
                player.getEquipment().getBoots());
    }

    private void applyPotionEffectsByItems(Player player, ItemStack... items) {
        Map<PotionEffectType, Integer> playerPotionEffects = null; // <PET, EnchantmentLevel>

        for(ItemStack item : items) {
            if(item == null || !item.hasItemMeta() || !item.getItemMeta().hasLore()) {
                continue;
            }

            for(PotionEffectEnchantment potionEnchant : potionEnchants) {
                int level = potionEnchant.getLevel(item);
                if(level <= 0) continue;

                if(playerPotionEffects == null) {
                    playerPotionEffects = new HashMap<>();
                }

                PotionEffectType pet = potionEnchant.getPotionEffectType();

                int existingLevel = playerPotionEffects.getOrDefault(pet, 0);
                if(level > existingLevel) {
                    playerPotionEffects.put(pet, level);
                }
            }
        }

        if(playerPotionEffects != null) {
            playerPotionEffects.forEach((pet, level) -> {
                player.removePotionEffect(pet);
                player.addPotionEffect(new PotionEffect(pet, POTION_EFFECT_DURATION, level - 1, true, false, false));
            });
        }
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        insertPlayer(e.getPlayer());
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e) {
        for(List<Player> playerList : tickDividedPlayers) {
            playerList.remove(e.getPlayer());
        }
    }

    private void insertPlayer(Player player) {
        int shortestListSize = Integer.MAX_VALUE;
        List<Player> shortestList = null;

        for(List<Player> playerList : tickDividedPlayers) {
            if(playerList.size() < shortestListSize) {
                shortestListSize = playerList.size();
                shortestList = playerList;
            }
        }

        // shortestList will never be null..
        shortestList.add(player);
    }
}
