package com.movtery.difficultyd;

import com.movtery.difficultyd.Config.ModAutoConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.Random;

public class DifficultyD implements ModInitializer {
    private static double dropChance;
    private static String dropJudgment;
    private static double differentToolDropChance;
    private final Random rand = new Random();

    public static String getDropJudgment() {
        return dropJudgment;
    }

    public static void setDropJudgment(String dropJudgment) {
        DifficultyD.dropJudgment = dropJudgment;
    }

    public static double getDropChance() {
        return dropChance;
    }

    public static void setDropChance(double dropChance1) {
        DifficultyD.dropChance = dropChance1;
    }

    public static double getDifferentToolDropChance() {
        return differentToolDropChance;
    }

    public static void setDifferentToolDropChance(double dropChance1) {
        DifficultyD.differentToolDropChance = dropChance1;
    }

    private void setAir(World world, BlockPos pos) {
        world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_LISTENERS | Block.REDRAW_ON_MAIN_THREAD);
    }

    @Override
    public void onInitialize() {
        AutoConfig.register(ModAutoConfig.class, JanksonConfigSerializer::new);
        ModAutoConfig config = AutoConfig.getConfigHolder(ModAutoConfig.class).getConfig();

        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, entity) -> {
            if (!config.disable && !(player.isCreative())) {

                ItemStack heldItem = player.getMainHandStack();
                Item item = player.getMainHandStack().getItem();
                String blockName;
                float chance = rand.nextFloat() * 100;
                setDropChance(chance);

                if (config.blockWhitelistDisable) {
                    blockName = Registry.BLOCK.getId(state.getBlock()).toString();
                } else {
                    blockName = Blocks.AIR.toString();
                }

                if (!config.blockWhitelist.contains(blockName)) {
                    if (item instanceof MiningToolItem) {
                        if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, heldItem) > 0) {
                            if (chance > config.silkTouchChance) {
                                setAir(world, pos);
                                setDifferentToolDropChance(config.silkTouchChance);
                                setDropJudgment(" > ");
                            } else {
                                setDropJudgment(" < ");
                            }
                        }
                        if (EnchantmentHelper.getLevel(Enchantments.FORTUNE, heldItem) > 0) {
                            if (chance > config.fortuneChance) {
                                setAir(world, pos);
                                setDifferentToolDropChance(config.fortuneChance);
                                setDropJudgment(" > ");
                            } else {
                                setDropJudgment(" < ");
                            }
                        }
                        if (chance > config.normalChance) {
                            setAir(world, pos);
                            setDifferentToolDropChance(config.normalChance);
                            setDropJudgment(" > ");
                        } else {
                            setDropJudgment(" < ");
                        }
                    } else {
                        if (!config.emptyHanded) {
                            if (chance > config.emptyHandedChance) {
                                setAir(world, pos);
                                setDifferentToolDropChance(config.emptyHandedChance);
                                setDropJudgment(" > ");
                            } else {
                                setDropJudgment(" < ");
                            }
                        } else {
                            setAir(world, pos);
                        }
                    }
                } else {
                    setDropJudgment(" ? ");
                }

                if (config.foodLevel) {
                    BreakHunger.setFoodLevel(player, config);
                }
                if (config.exhaustionLevel) {
                    BreakHunger.setExhaustionLevel(player, config);
                }
                if (config.randomnumber) {
                    ShowRandomNumber.RandomNumber(player);
                }
            }
            return true;
        });
    }
}