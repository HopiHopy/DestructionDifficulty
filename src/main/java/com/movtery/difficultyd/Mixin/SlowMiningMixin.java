package com.movtery.difficultyd.Mixin;

import com.movtery.difficultyd.Config.ModAutoConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class SlowMiningMixin {
    @Inject(method = "getBlockBreakingSpeed", at = @At("RETURN"), cancellable = true)
    private void slowMining(BlockState block, CallbackInfoReturnable<Float> cir) {
        ModAutoConfig config = AutoConfig.getConfigHolder(ModAutoConfig.class).getConfig();
        if (!config.disable && config.slowMining && !((PlayerEntity)(Object)this).isCreative()) {
            cir.setReturnValue(cir.getReturnValue() / config.slowMiningSpeed);
        }
    }
}