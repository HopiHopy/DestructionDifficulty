package com.movtery.difficultyd.Config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.Arrays;
import java.util.List;

@Config(name = "difficultyd")
public class ModAutoConfig implements ConfigData {
    public boolean disable = false;
    public List<String> blockWhitelist = Arrays.asList("minecraft:chest");
    public float silkTouchChance = 80f;
    public float fortuneChance = 65f;
    public float normalChance = 15f;
    public boolean emptyHanded = true;
    public boolean slowMining = false;
    public int slowMiningSpeed = 4;
    public boolean foodLevel = false;
    public float foodLevelChance = 40f;
    public int removeFoodLevel = 1;
}
