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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.Random;

public class DifficultyD implements ModInitializer {
    private final Random rand = new Random();

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

                if (config.foodLevel) {
                    BreakHunger.setFoodLevel(player, config);
                }
                if (config.exhaustionLevel) {
                    BreakHunger.setExhaustionLevel(player, config);
                }
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
                                actionbarNo(config, chance,  config.silkTouchChance, player);
                            } else {
                                actionbarYes(config, chance,  config.silkTouchChance, player);
                            }
                            return true;
                        }
                        if (EnchantmentHelper.getLevel(Enchantments.FORTUNE, heldItem) > 0) {
                            if (chance > config.fortuneChance) {
                                setAir(world, pos);
                                actionbarNo(config, chance,  config.fortuneChance, player);
                            } else {
                                actionbarYes(config, chance,  config.fortuneChance, player);
                            }
                            return true;
                        }
                        if (chance > config.normalChance) {
                            setAir(world, pos);
                            actionbarNo(config, chance,  config.normalChance, player);
                        } else {
                            actionbarYes(config, chance,  config.normalChance, player);
                        }
                        return true;
                    } else {
                        if (!config.emptyHanded) {
                            if (chance > config.emptyHandedChance) {
                                setAir(world, pos);
                                actionbarNo(config, chance,  config.emptyHandedChance, player);
                            } else {
                                actionbarYes(config, chance,  config.emptyHandedChance, player);
                            }
                            return true;
                        } else {
                            setAir(world, pos);
                        }
                    }
                }
            }
            return true;
        });
    }

    private void setAir(World world, BlockPos pos) {
        world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_LISTENERS | Block.REDRAW_ON_MAIN_THREAD);
    }

    private void actionbarYes(ModAutoConfig config, float chance1, float chance2, PlayerEntity player) {
        if (config.actionbarChance) {
            Text message = Text.translatable("text.difficultyd.randomnumbers")
                    .append(String.valueOf(chance1))
                    .append(" < ")
                    .append(String.valueOf(chance2))
                    .append(Text.translatable("text.difficultyd.randomnumbers.yes"));
            player.sendMessage(message, true);
        }
    }
    private void actionbarNo(ModAutoConfig config, float chance1, float chance2, PlayerEntity player) {
        if (config.actionbarChance) {
            Text message = Text.translatable("text.difficultyd.randomnumbers")
                    .append(String.valueOf(chance1))
                    .append(" > ")
                    .append(String.valueOf(chance2))
                    .append(Text.translatable("text.difficultyd.randomnumbers.no"));
            player.sendMessage(message, true);
        }
    }
}