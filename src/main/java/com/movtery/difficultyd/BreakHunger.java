package com.movtery.difficultyd;

import com.movtery.difficultyd.Config.ModAutoConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Random;

public class BreakHunger implements ModInitializer {
    private static final Random rand = new Random();
    public static void setFoodLevel(PlayerEntity player, ModAutoConfig config) {
        float chance = rand.nextFloat() * 100;
        int foodLevel = player.getHungerManager().getFoodLevel();
        if (!(foodLevel == 0) && chance < config.foodLevelChance) {
            int newValue = foodLevel - config.removeFoodLevel;
            player.getHungerManager().setFoodLevel(Math.max(newValue, 0));
        }
    }

    public static void setExhaustionLevel(PlayerEntity player, ModAutoConfig config) {
        float chance = rand.nextFloat() * 100;
        float foodLevel = player.getHungerManager().getFoodLevel();
        float Exhaustionlevel = player.getHungerManager().getExhaustion();
        if (!(foodLevel == 0) && chance < config.exhaustionLevelChance) {
            float newValue = Exhaustionlevel + config.addExhaustionLevel;
            player.getHungerManager().setExhaustion(newValue);
        }
    }

    @Override
    public void onInitialize() {
    }
}
