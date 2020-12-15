package me.woutergritter.wenchantments.customenchant.potionenchant;

import me.woutergritter.wenchantments.customenchant.CustomEnchantment;
import org.bukkit.potion.PotionEffectType;

import static org.bukkit.potion.PotionEffectType.*;

public class PotionEffectEnchantment extends CustomEnchantment {
    private final PotionEffectType potionEffectType;

    public PotionEffectEnchantment(PotionEffectType potionEffectType) {
        super(potionEffectType.getName(), getDisplayName(potionEffectType));

        this.potionEffectType = potionEffectType;
    }

    public PotionEffectType getPotionEffectType() {
        return potionEffectType;
    }

    private static String getDisplayName(PotionEffectType potionEffectType) {
        if (SPEED.equals(potionEffectType)) {
            return "Speed";
        } else if (SLOW.equals(potionEffectType)) {
            return "Slowness";
        } else if (FAST_DIGGING.equals(potionEffectType)) {
            return "Haste";
        } else if (SLOW_DIGGING.equals(potionEffectType)) {
            return "Mining Fatigue";
        } else if (INCREASE_DAMAGE.equals(potionEffectType)) {
            return "Strength";
        } else if (HEAL.equals(potionEffectType)) {
            return "Instant Health";
        } else if (HARM.equals(potionEffectType)) {
            return "Instant Damage";
        } else if (JUMP.equals(potionEffectType)) {
            return "Jump Boost";
        } else if (CONFUSION.equals(potionEffectType)) {
            return "Nausea";
        } else if (REGENERATION.equals(potionEffectType)) {
            return "Regeneration";
        } else if (DAMAGE_RESISTANCE.equals(potionEffectType)) {
            return "Resistance";
        } else if (FIRE_RESISTANCE.equals(potionEffectType)) {
            return "Fire Resistance";
        } else if (WATER_BREATHING.equals(potionEffectType)) {
            return "Water Breathing";
        } else if (INVISIBILITY.equals(potionEffectType)) {
            return "Invisibility";
        } else if (BLINDNESS.equals(potionEffectType)) {
            return "Blindness";
        } else if (NIGHT_VISION.equals(potionEffectType)) {
            return "Night Vision";
        } else if (HUNGER.equals(potionEffectType)) {
            return "Hunger";
        } else if (WEAKNESS.equals(potionEffectType)) {
            return "Weakness";
        } else if (POISON.equals(potionEffectType)) {
            return "Poison";
        } else if (WITHER.equals(potionEffectType)) {
            return "Wither";
        } else if (HEALTH_BOOST.equals(potionEffectType)) {
            return "Health Boost";
        } else if (ABSORPTION.equals(potionEffectType)) {
            return "Absorption";
        } else if (SATURATION.equals(potionEffectType)) {
            return "Saturation";
        }

        return "Unknown potion effect";
    }
}
