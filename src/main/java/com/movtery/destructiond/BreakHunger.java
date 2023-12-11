package com.movtery.destructiond;

import com.movtery.destructiond.Config.ModAutoConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

public class BreakHunger {
    private static final Random rand = new Random();
    public static void setFoodLevel(Player player, ModAutoConfig config) {
        float chance = rand.nextFloat() * 100;
        int foodLevel = player.getFoodData().getFoodLevel();
        if (!(foodLevel == 0) && chance < config.foodLevelChance) {
            int newValue = foodLevel - config.removeFoodLevel;
            player.getFoodData().setFoodLevel(Math.max(newValue, 0));
            actionbarYes(config, chance, config.foodLevelChance, player);
        } else {
            actionbarNo(config, chance, config.foodLevelChance, player);
        }
    }

    public static void setExhaustionLevel(Player player, ModAutoConfig config) {
        float chance = rand.nextFloat() * 100;
        float foodLevel = player.getFoodData().getFoodLevel();
        float Exhaustionlevel = player.getFoodData().getExhaustionLevel();
        if (!(foodLevel == 0) && chance < config.exhaustionLevelChance) {
            float newValue = Exhaustionlevel + config.addExhaustionLevel;
            player.getFoodData().setExhaustion(newValue);
            actionbarYes(config, chance, config.exhaustionLevelChance, player);
        } else {
            actionbarNo(config, chance, config.exhaustionLevelChance, player);
        }
    }

    private static void actionbarYes(ModAutoConfig config, float chance1, float chance2, Player player) {
        if (config.actionbarFoodLevelChance) {
            Component message = Component.translatable("text.difficultyd.randomnumbers")
                    .append(String.valueOf(chance1))
                    .append(" < ")
                    .append(String.valueOf(chance2))
                    .append(Component.translatable("text.difficultyd.randomnumbers.yes"));
            player.displayClientMessage(message, true);
        }
    }
    private static void actionbarNo(ModAutoConfig config, float chance1, float chance2, Player player) {
        if (config.actionbarFoodLevelChance) {
            Component message = Component.translatable("text.difficultyd.randomnumbers")
                    .append(String.valueOf(chance1))
                    .append(" > ")
                    .append(String.valueOf(chance2))
                    .append(Component.translatable("text.difficultyd.randomnumbers.no"));
            player.displayClientMessage(message, true);
        }
    }
}
