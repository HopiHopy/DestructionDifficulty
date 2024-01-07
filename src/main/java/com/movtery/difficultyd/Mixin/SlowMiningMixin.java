package com.movtery.difficultyd.Mixin;

import com.movtery.difficultyd.Config.ModAutoConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(PlayerEntity.class)
public class SlowMiningMixin {
    @Inject(method = "getBlockBreakingSpeed", at = @At("RETURN"), cancellable = true)
    private void slowMining(BlockState block, CallbackInfoReturnable<Float> cir) {
        ModAutoConfig config = AutoConfig.getConfigHolder(ModAutoConfig.class).getConfig();
        String blockName;
        if (config.slowBlockWhitelistDisable) {
            blockName = Registries.BLOCK.getId(block.getBlock()).toString();
        } else {
            blockName = Blocks.AIR.toString();
        }
        if (!config.disable && config.slowMining && !config.slowBlockWhitelist.contains(blockName) && !((PlayerEntity) (Object) this).isCreative()) {
            cir.setReturnValue(cir.getReturnValue() / config.slowMiningSpeed);
        }
    }
}