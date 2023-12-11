package com.movtery.difficultyd.Config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Collections;

public class ModConfig implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> getConfigScreen().build();
    }
    private ConfigBuilder getConfigScreen() {
        ConfigBuilder builder = ConfigBuilder.create()
                .setTitle(Text.translatable("title.difficultyd.config"))
                .setDefaultBackgroundTexture(new Identifier("minecraft:textures/block/stone.png"))
                .setSavingRunnable(() -> {
                    getConfigScreen().build();
                });

        ConfigCategory general1 = builder.getOrCreateCategory(Text.translatable("category.difficultyd.c1"));

        ConfigCategory general2 = builder.getOrCreateCategory(Text.translatable("category.difficultyd.c2"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ModAutoConfig config = AutoConfig.getConfigHolder(ModAutoConfig.class).getConfig();

        general1.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.difficultyd.disable"), config.disable)
                .setDefaultValue(false)
                .setTooltip(Text.translatable("option.difficultyd.disable.tooptip"))
                .setSaveConsumer(newValue -> {
                    config.disable = newValue;
                    AutoConfig.getConfigHolder(ModAutoConfig.class).save();
                })
                .build());

        general1.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.difficultyd.blockwhitelistdisable"), config.blockWhitelistDisable)
                .setDefaultValue(false)
                .setTooltip(Text.translatable("option.difficultyd.blockwhitelistdisable.tooptip"))
                .setSaveConsumer(newValue -> {
                    config.blockWhitelistDisable = newValue;
                    AutoConfig.getConfigHolder(ModAutoConfig.class).save();
                })
                .build());

        general1.addEntry(entryBuilder.startStrList(Text.translatable("option.difficultyd.blockwhitelist"), config.blockWhitelist)
                .setDefaultValue(Collections.singletonList("minecraft:chest"))
                .setTooltip(Text.translatable("option.difficultyd.blockwhitelist.tooptip"))
                .setSaveConsumer(newValue -> {
                    config.blockWhitelist = newValue;
                    AutoConfig.getConfigHolder(ModAutoConfig.class).save();
                })
                .build());

        general1.addEntry(entryBuilder.startFloatField(Text.translatable("option.difficultyd.hasSilkTouch"), config.silkTouchChance)
                .setDefaultValue(80f)
                .setMax(100f).setMin(0)
                .setTooltip(Text.translatable("option.difficultyd.hasSilkTouch.tooptip"))
                .setSaveConsumer(newValue -> {
                    config.silkTouchChance = newValue;
                    AutoConfig.getConfigHolder(ModAutoConfig.class).save();
                })
                .build());

        general1.addEntry(entryBuilder.startFloatField(Text.translatable("option.difficultyd.hasFortune"), config.fortuneChance)
                .setDefaultValue(65f)
                .setMax(100f).setMin(0)
                .setTooltip(Text.translatable("option.difficultyd.hasFortune.tooptip"))
                .setSaveConsumer(newValue -> {
                    config.fortuneChance = newValue;
                    AutoConfig.getConfigHolder(ModAutoConfig.class).save();
                })
                .build());

        general1.addEntry(entryBuilder.startFloatField(Text.translatable("option.difficultyd.normal"), config.normalChance)
                .setDefaultValue(45f)
                .setMax(100f).setMin(0)
                .setTooltip(Text.translatable("option.difficultyd.normal.tooptip"))
                .setSaveConsumer(newValue -> {
                    config.normalChance = newValue;
                    AutoConfig.getConfigHolder(ModAutoConfig.class).save();
                })
                .build());

        general1.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.difficultyd.emptyhanded"), config.emptyHanded)
                .setDefaultValue(false)
                .setTooltip(Text.translatable("option.difficultyd.emptyhanded.tooptip"))
                .setSaveConsumer(newValue -> {
                    config.emptyHanded = newValue;
                    AutoConfig.getConfigHolder(ModAutoConfig.class).save();
                })
                .build());

        general1.addEntry(entryBuilder.startFloatField(Text.translatable("option.difficultyd.emptyhandedchance"), config.emptyHandedChance)
                .setDefaultValue(15f)
                .setMax(100f).setMin(0)
                .setTooltip(Text.translatable("option.difficultyd.emptyhandedchance.tooptip"))
                .setSaveConsumer(newValue -> {
                    config.emptyHandedChance = newValue;
                    AutoConfig.getConfigHolder(ModAutoConfig.class).save();
                })
                .build());

        general1.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.difficultyd.actionbarchance"), config.actionbarChance)
                .setDefaultValue(false)
                .setTooltip(Text.translatable("option.difficultyd.actionbarchance.tooptip"))
                .setSaveConsumer(newValue -> {
                    config.actionbarChance = newValue;
                    AutoConfig.getConfigHolder(ModAutoConfig.class).save();
                })
                .build());


        general2.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.difficultyd.slowblockwhitelistdisable"), config.slowBlockWhitelistDisable)
                .setDefaultValue(false)
                .setTooltip(Text.translatable("option.difficultyd.slowblockwhitelistdisable.tooptip"))
                .setSaveConsumer(newValue -> {
                    config.slowBlockWhitelistDisable = newValue;
                    AutoConfig.getConfigHolder(ModAutoConfig.class).save();
                })
                .build());

        general2.addEntry(entryBuilder.startStrList(Text.translatable("option.difficultyd.slowblockwhitelist"), config.slowBlockWhitelist)
                .setDefaultValue(Collections.singletonList("minecraft:chest"))
                .setTooltip(Text.translatable("option.difficultyd.slowblockwhitelist.tooptip"))
                .setSaveConsumer(newValue -> {
                    config.slowBlockWhitelist = newValue;
                    AutoConfig.getConfigHolder(ModAutoConfig.class).save();
                })
                .build());

        general2.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.difficultyd.slowmining"), config.slowMining)
                .setDefaultValue(false)
                .setTooltip(Text.translatable("option.difficultyd.slowmining.tooptip"))
                .setSaveConsumer(newValue -> {
                    config.slowMining = newValue;
                    AutoConfig.getConfigHolder(ModAutoConfig.class).save();
                })
                .build());

        general2.addEntry(entryBuilder.startIntSlider(Text.translatable("option.difficultyd.slowminingspeed"), config.slowMiningSpeed, 1, 100)
                .setDefaultValue(4)
                .setTooltip(Text.translatable("option.difficultyd.slowminingspeed.tooptip"))
                .setSaveConsumer(newValue -> {
                    config.slowMiningSpeed = newValue;
                    AutoConfig.getConfigHolder(ModAutoConfig.class).save();
                })
                .build());

        general2.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.difficultyd.foodlevel"), config.foodLevel)
                .setDefaultValue(false)
                .setTooltip(Text.translatable("option.difficultyd.foodlevel.tooptip"))
                .setSaveConsumer(newValue -> {
                    config.foodLevel = newValue;
                    AutoConfig.getConfigHolder(ModAutoConfig.class).save();
                })
                .build());

        general2.addEntry(entryBuilder.startFloatField(Text.translatable("option.difficultyd.foodlevelchance"), config.foodLevelChance)
                .setDefaultValue(40f)
                .setMax(100f).setMin(0)
                .setTooltip(Text.translatable("option.difficultyd.foodlevelchance.tooptip"))
                .setSaveConsumer(newValue -> {
                    config.foodLevelChance = newValue;
                    AutoConfig.getConfigHolder(ModAutoConfig.class).save();
                })
                .build());

        general2.addEntry(entryBuilder.startIntSlider(Text.translatable("option.difficultyd.removefoodlevel"), config.removeFoodLevel , 1, 20)
                .setDefaultValue(1)
                .setTooltip(Text.translatable("option.difficultyd.removefoodlevel.tooptip"))
                .setSaveConsumer(newValue -> {
                    config.removeFoodLevel = newValue;
                    AutoConfig.getConfigHolder(ModAutoConfig.class).save();
                })
                .build());

        general2.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.difficultyd.exhaustionlevel"), config.exhaustionLevel)
                .setDefaultValue(false)
                .setTooltip(Text.translatable("option.difficultyd.exhaustionlevel.tooptip"))
                .setSaveConsumer(newValue -> {
                    config.exhaustionLevel = newValue;
                    AutoConfig.getConfigHolder(ModAutoConfig.class).save();
                })
                .build());

        general2.addEntry(entryBuilder.startFloatField(Text.translatable("option.difficultyd.exhaustionlevelchance"), config.exhaustionLevelChance)
                .setDefaultValue(40f)
                .setMax(100f).setMin(0)
                .setTooltip(Text.translatable("option.difficultyd.exhaustionlevelchance.tooptip"))
                .setSaveConsumer(newValue -> {
                    config.exhaustionLevelChance = newValue;
                    AutoConfig.getConfigHolder(ModAutoConfig.class).save();
                })
                .build());

        general2.addEntry(entryBuilder.startFloatField(Text.translatable("option.difficultyd.addexhaustionlevel"), config.addExhaustionLevel)
                .setDefaultValue(1)
                .setMax(4).setMin(0)
                .setTooltip(Text.translatable("option.difficultyd.addexhaustionlevel.tooptip"))
                .setSaveConsumer(newValue -> {
                    config.addExhaustionLevel = newValue;
                    AutoConfig.getConfigHolder(ModAutoConfig.class).save();
                })
                .build());

        general2.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.difficultyd.actionbarchance"), config.actionbarFoodLevelChance)
                .setDefaultValue(false)
                .setTooltip(Text.translatable("option.difficultyd.actionbarchance.tooptip"))
                .setSaveConsumer(newValue -> {
                    config.actionbarFoodLevelChance = newValue;
                    AutoConfig.getConfigHolder(ModAutoConfig.class).save();
                })
                .build());

        return builder;
    }
}