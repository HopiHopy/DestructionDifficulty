package com.movtery.destructiond;

import com.movtery.destructiond.Config.ModAutoConfig;
import com.movtery.destructiond.Config.ModConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

@Mod(DestructionD.MODID)
public class DestructionD {
    public static final String MODID = "destructiond";
    private static final Random rand = new Random();
    public DestructionD() {
        AutoConfig.register(ModAutoConfig.class, JanksonConfigSerializer::new);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ModConfig::registerModsPage);
    }
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = MODID)
    public static class EventL {
        @SubscribeEvent
        public static void playerBlockBreak(BlockEvent.BreakEvent event) {
            ModAutoConfig config = AutoConfig.getConfigHolder(ModAutoConfig.class).getConfig();
            Player player = event.getPlayer();

            if (!config.disable && !(player.isCreative())) {
                BlockPos blockPos = event.getPos();
                Block block = event.getState().getBlock();
                Level level = (Level) event.getLevel();
                ItemStack heldItem = player.getMainHandItem();
                Item item = player.getMainHandItem().getItem();

                float chance = rand.nextFloat() * 100;
                String blockName;

                if (config.foodLevel) {
                    BreakHunger.setFoodLevel(player, config);
                }
                if (config.exhaustionLevel) {
                    BreakHunger.setExhaustionLevel(player, config);
                }
                if (config.blockWhitelistDisable) {
                    blockName = ForgeRegistries.BLOCKS.getKey(block).toString();
                } else {
                    blockName = Blocks.AIR.toString();
                }

                if (!config.blockWhitelist.contains(blockName)) {
                    if (item instanceof DiggerItem) {
                        if (EnchantmentHelper.getTagEnchantmentLevel(Enchantments.SILK_TOUCH, heldItem) > 0) {
                            if (chance > config.silkTouchChance) {
                                setAir(level, blockPos);
                                actionbarNo(config, chance,  config.silkTouchChance, player);
                            } else {
                                actionbarYes(config, chance,  config.silkTouchChance, player);
                            }
                        }
                        if (EnchantmentHelper.getTagEnchantmentLevel(Enchantments.BLOCK_FORTUNE, heldItem) > 0) {
                            if (chance > config.fortuneChance) {
                                setAir(level, blockPos);
                                actionbarNo(config, chance,  config.fortuneChance, player);
                            } else {
                                actionbarYes(config, chance,  config.fortuneChance, player);
                            }
                        }
                        if (chance > config.normalChance) {
                            setAir(level, blockPos);
                            actionbarNo(config, chance,  config.normalChance, player);
                        } else {
                            actionbarYes(config, chance,  config.normalChance, player);
                        }
                    } else {
                        if (!config.emptyHanded) {
                            if (chance > config.emptyHandedChance) {
                                setAir(level, blockPos);
                                actionbarNo(config, chance,  config.emptyHandedChance, player);
                            } else {
                                actionbarYes(config, chance,  config.emptyHandedChance, player);
                            }
                        } else {
                            setAir(level, blockPos);
                        }
                    }
                }

            }
        }
    }
    private static void setAir(Level world, BlockPos pos) {
        world.setBlock(pos, Blocks.AIR.defaultBlockState(), 1);
    }
    private static void actionbarYes(ModAutoConfig config, float chance1, float chance2, Player player) {
        if (config.actionbarChance) {
            Component message = Component.translatable("text.difficultyd.randomnumbers")
                    .append(String.valueOf(chance1))
                    .append(" < ")
                    .append(String.valueOf(chance2))
                    .append(Component.translatable("text.difficultyd.randomnumbers.yes"));
            player.displayClientMessage(message, true);
        }
    }
    private static void actionbarNo(ModAutoConfig config, float chance1, float chance2, Player player) {
        if (config.actionbarChance) {
            Component message = Component.translatable("text.difficultyd.randomnumbers")
                    .append(String.valueOf(chance1))
                    .append(" > ")
                    .append(String.valueOf(chance2))
                    .append(Component.translatable("text.difficultyd.randomnumbers.no"));
            player.displayClientMessage(message, true);
        }
    }
}

