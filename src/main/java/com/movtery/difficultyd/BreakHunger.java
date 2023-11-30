package com.movtery.difficultyd;

import com.movtery.difficultyd.Config.ModAutoConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Random;

public class BreakHunger implements ModInitializer {
    private static final Random rand = new Random();
    public static void setHunger(PlayerEntity player, ModAutoConfig config) {
        float chance = rand.nextFloat() * 100;
        int foodLevel = player.getHungerManager().getFoodLevel();
        if (!(foodLevel == 0) && chance < config.foodLevelChance) {
            int foodlevel = foodLevel - config.removeFoodLevel;
            player.getHungerManager().setFoodLevel(Math.max(foodlevel, 0));
        }
    }

    @Override
    public void onInitialize() {
    }
}
