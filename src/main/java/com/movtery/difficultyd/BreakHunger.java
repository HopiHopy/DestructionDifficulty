package com.movtery.difficultyd;

import com.movtery.difficultyd.Config.ModAutoConfig;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Random;

public class BreakHunger {
    private static final Random rand = new Random();
    private static float foodChance;
    private static float exhaustionChance;
    private static String foodJudgment;
    private static String exhaustionJudgment;

    public static String getFoodJudgment() {
        return foodJudgment;
    }

    public static void setFoodJudgment(String foodJudgment) {
        BreakHunger.foodJudgment = foodJudgment;
    }

    public static String getExhaustionJudgment() {
        return exhaustionJudgment;
    }

    public static void setExhaustionJudgment(String exhaustionJudgment) {
        BreakHunger.exhaustionJudgment = exhaustionJudgment;
    }

    public static float getFoodChance() {
        return foodChance;
    }

    public static void setFoodChance(float foodChance) {
        BreakHunger.foodChance = foodChance;
    }

    public static float getExhaustionChance() {
        return exhaustionChance;
    }

    public static void setExhaustionChance(float exhaustionChance) {
        BreakHunger.exhaustionChance = exhaustionChance;
    }

    public static void setFoodLevel(PlayerEntity player, ModAutoConfig config) {
        float chance = rand.nextFloat() * 100;
        setFoodChance(chance);
        int foodLevel = player.getHungerManager().getFoodLevel();
        if (!(foodLevel == 0) && chance < config.foodLevelChance) {
            int newValue = foodLevel - config.removeFoodLevel;
            player.getHungerManager().setFoodLevel(Math.max(newValue, 0));
            setFoodJudgment(" < ");
        } else {
            setFoodJudgment(" > ");
        }
    }

    public static void setExhaustionLevel(PlayerEntity player, ModAutoConfig config) {
        float chance = rand.nextFloat() * 100;
        setExhaustionChance(chance);
        float foodLevel = player.getHungerManager().getFoodLevel();
        float Exhaustionlevel = player.getHungerManager().getExhaustion();
        if (!(foodLevel == 0) && chance < config.exhaustionLevelChance) {
            float newValue = Exhaustionlevel + config.addExhaustionLevel;
            player.getHungerManager().setExhaustion(newValue);
            setExhaustionJudgment(" < ");
        } else {
            setExhaustionJudgment(" > ");
        }
    }
}
