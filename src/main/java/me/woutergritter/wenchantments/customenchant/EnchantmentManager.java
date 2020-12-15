package me.woutergritter.wenchantments.customenchant;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class EnchantmentManager {
    private final Map<String, WEnchantment> registeredEnchantments = new HashMap<>();

    public EnchantmentManager() {
        for(Enchantment minecraftEnchantment : Enchantment.values()) {
            register(new VanillaEnchantment(minecraftEnchantment));
        }
    }

    public void register(WEnchantment enchantment) {
        registeredEnchantments.put(enchantment.getName().toUpperCase(), enchantment);
    }

    public Collection<WEnchantment> getEnchantments() {
        return Collections.unmodifiableCollection(registeredEnchantments.values());
    }

    public WEnchantment getEnchantment(String name) {
        return registeredEnchantments.get(name.toUpperCase());
    }

    public List<WEnchantment> getEnchantments(ItemStack item) {
        List<WEnchantment> res = new ArrayList<>();
        for(WEnchantment enchantment : registeredEnchantments.values()) {
            if(enchantment.has(item)) {
                res.add(enchantment);
            }
        }

        return res;
    }
}
