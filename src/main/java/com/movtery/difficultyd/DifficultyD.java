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
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class DifficultyD implements ModInitializer {
    private void setAir(World world, BlockPos pos) {
        world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_LISTENERS | Block.REDRAW_ON_MAIN_THREAD);
    }
    private final Random rand = new Random();
    private void actionbar(ModAutoConfig config, float chance1, String string, float chance2, String string2, PlayerEntity player) {
        if (config.actionbarChance) {
            Text message = Text.translatable("text.difficultyd.randomnumbers")
                    .append(String.valueOf(chance1))
                    .append(string)
                    .append(String.valueOf(chance2))
                    .append(Text.translatable(string2));
            player.sendMessage(message, true);
        }
    }
    @Override
    public void onInitialize() {
        AutoConfig.register(ModAutoConfig.class, JanksonConfigSerializer::new);
        ModAutoConfig config = AutoConfig.getConfigHolder(ModAutoConfig.class).getConfig();

        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, entity) -> {
            if (!config.disable && config.foodLevel && !(player.isCreative())) {
                BreakHunger.setHunger(player, config);
            }

            ItemStack heldItem = player.getMainHandStack();
            Item item = heldItem.getItem();
            String blockName;
            if (config.blockWhitelistDisable) {
                blockName = Registries.BLOCK.getId(state.getBlock()).toString();
            } else {
                blockName = Blocks.AIR.toString();
            }

            float chance = rand.nextFloat() * 100;
            if (!config.disable && !config.blockWhitelist.contains(blockName) && !(player.isCreative())) {

                if (item instanceof MiningToolItem) {
                    if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, heldItem) > 0) {
                        if (chance > config.silkTouchChance) {
                            setAir(world, pos);
                            actionbar(config, chance, " > ", config.silkTouchChance, "text.difficultyd.randomnumbers.no", player);
                        } else {
                            actionbar(config, chance, " < ", config.silkTouchChance, "text.difficultyd.randomnumbers.yes", player);
                        }
                        return true;
                    }
                    if (EnchantmentHelper.getLevel(Enchantments.FORTUNE, heldItem) > 0) {
                        if (chance > config.fortuneChance) {
                            setAir(world, pos);
                            actionbar(config, chance, " > ", config.fortuneChance, "text.difficultyd.randomnumbers.no", player);
                        } else {
                            actionbar(config, chance, " < ", config.fortuneChance, "text.difficultyd.randomnumbers.yes", player);
                        }
                        return true;
                    }
                    if (chance > config.normalChance) {
                        setAir(world, pos);
                        actionbar(config, chance, " > ", config.normalChance, "text.difficultyd.randomnumbers.no", player);
                    } else {
                        actionbar(config, chance, " < ", config.normalChance, "text.difficultyd.randomnumbers.yes", player);
                    }
                    return true;
                } else {
                    if (!config.emptyHanded) {
                        if (chance > config.emptyHandedChance) {
                            setAir(world, pos);
                            actionbar(config, chance, " > ", config.emptyHandedChance, "text.difficultyd.randomnumbers.no", player);
                        } else {
                            actionbar(config, chance, " < ", config.emptyHandedChance, "text.difficultyd.randomnumbers.yes", player);
                        }
                        return true;
                    } else {
                        setAir(world, pos);
                    }
                }
            }
            return true;
        });
    }
}