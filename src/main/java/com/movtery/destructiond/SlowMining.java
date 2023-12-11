package com.movtery.destructiond;

import com.movtery.destructiond.Config.ModAutoConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import static com.movtery.destructiond.DestructionD.MODID;

@Mod(MODID)
public class SlowMining {
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = MODID)
    public static class EventL {
        @SubscribeEvent
        public static void SlowMining(PlayerEvent.BreakSpeed event) {
            ModAutoConfig config = AutoConfig.getConfigHolder(ModAutoConfig.class).getConfig();
            Player player = event.getEntity();
            Block block = event.getState().getBlock();
            String blockName;
            if (config.slowBlockWhitelistDisable) {
                blockName = ForgeRegistries.BLOCKS.getKey(block).toString();
            } else {
                blockName = Blocks.AIR.toString();
            }
            if (!config.disable && config.slowMining && !config.slowBlockWhitelist.contains(blockName) && !(player.isCreative())) {
                event.setNewSpeed(event.getNewSpeed() / config.slowMiningSpeed);
            }
        }
    }
}
