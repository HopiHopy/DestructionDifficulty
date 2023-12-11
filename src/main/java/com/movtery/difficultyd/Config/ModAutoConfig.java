package com.movtery.difficultyd.Config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.Arrays;
import java.util.List;

@Config(name = "difficultyd")
public class ModAutoConfig implements ConfigData {
    public boolean disable = false;
    public boolean blockWhitelistDisable = false;
    public List<String> blockWhitelist = Arrays.asList("minecraft:chest");
    public float silkTouchChance = 80f;
    public float fortuneChance = 65f;
    public float normalChance = 45f;
    public boolean emptyHanded = false;
    public float emptyHandedChance = 15f;
    public boolean actionbarChance = false;

    public boolean slowMining = false;
    public int slowMiningSpeed = 4;
    public boolean slowBlockWhitelistDisable = false;
    public List<String> slowBlockWhitelist = Arrays.asList("minecraft:chest");
    public boolean foodLevel = false;
    public float foodLevelChance = 40f;
    public int removeFoodLevel = 1;
    public boolean exhaustionLevel = false;
    public float exhaustionLevelChance = 40f;
    public float addExhaustionLevel = 1;
    public boolean actionbarFoodLevelChance = false;
}
