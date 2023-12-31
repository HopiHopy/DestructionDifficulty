package com.movtery.difficultyd.Config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.List;

@Config(name = "difficultyd")
public class ModAutoConfig implements ConfigData {
    public boolean disable = false;
    public boolean randomnumber = false;

    public boolean blockWhitelistDisable = false;
    public List<String> blockWhitelist = List.of("minecraft:chest");
    public float silkTouchChance = 80f;
    public float fortuneChance = 65f;
    public float normalChance = 45f;
    public boolean emptyHanded = false;
    public float emptyHandedChance = 15f;
    public boolean randomDropChance = false;

    public boolean slowMining = false;
    public int slowMiningSpeed = 4;
    public boolean slowBlockWhitelistDisable = false;
    public List<String> slowBlockWhitelist = List.of("minecraft:chest");
    public boolean foodLevel = false;
    public float foodLevelChance = 40f;
    public int removeFoodLevel = 1;
    public boolean randomFoodLevelChance = false;
    public boolean exhaustionLevel = false;
    public float exhaustionLevelChance = 40f;
    public float addExhaustionLevel = 1;
    public boolean randomExhaustionLevelChance = false;
}
