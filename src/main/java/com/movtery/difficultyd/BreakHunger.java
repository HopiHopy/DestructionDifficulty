package com.movtery.difficultyd;

import com.movtery.difficultyd.Config.ModAutoConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

import java.util.Random;

public class BreakHunger implements ModInitializer {
    private static final Random rand = new Random();
    public static void setFoodLevel(PlayerEntity player, ModAutoConfig config) {
        float chance = rand.nextFloat() * 100;
        int foodLevel = player.getHungerManager().getFoodLevel();
        if (!(foodLevel == 0) && chance < config.foodLevelChance) {
            int newValue = foodLevel - config.removeFoodLevel;
            player.getHungerManager().setFoodLevel(Math.max(newValue, 0));
            actionbarYes(config, chance, config.foodLevelChance, player);
        } else {
            actionbarNo(config, chance, config.foodLevelChance, player);
        }
    }

    public static void setExhaustionLevel(PlayerEntity player, ModAutoConfig config) {
        float chance = rand.nextFloat() * 100;
        float foodLevel = player.getHungerManager().getFoodLevel();
        float Exhaustionlevel = player.getHungerManager().getExhaustion();
        if (!(foodLevel == 0) && chance < config.exhaustionLevelChance) {
            float newValue = Exhaustionlevel + config.addExhaustionLevel;
            player.getHungerManager().setExhaustion(newValue);
            actionbarYes(config, chance, config.exhaustionLevelChance, player);
        } else {
            actionbarNo(config, chance, config.exhaustionLevelChance, player);
        }
    }

    private static void actionbarYes(ModAutoConfig config, float chance1, float chance2, PlayerEntity player) {
        if (config.actionbarFoodLevelChance) {
            Text message = Text.translatable("text.difficultyd.randomnumbers")
                    .append(String.valueOf(chance1))
                    .append(" < ")
                    .append(String.valueOf(chance2))
                    .append(Text.translatable("text.difficultyd.randomnumbers.yes"));
            player.sendMessage(message, true);
        }
    }
    private static void actionbarNo(ModAutoConfig config, float chance1, float chance2, PlayerEntity player) {
        if (config.actionbarFoodLevelChance) {
            Text message = Text.translatable("text.difficultyd.randomnumbers")
                    .append(String.valueOf(chance1))
                    .append(" > ")
                    .append(String.valueOf(chance2))
                    .append(Text.translatable("text.difficultyd.randomnumbers.no"));
            player.sendMessage(message, true);
        }
    }

    @Override
    public void onInitialize() {
    }
}
