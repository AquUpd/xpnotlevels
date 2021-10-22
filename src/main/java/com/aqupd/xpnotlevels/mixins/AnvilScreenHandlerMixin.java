package com.aqupd.xpnotlevels.mixins;

import com.aqupd.xpnotlevels.Main;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static com.aqupd.xpnotlevels.utils.AqLogger.logInfo;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMixin {

    @Redirect(method = "onTakeOutput", require = 1,
            at = @At(value = "INVOKE", target = "net.minecraft.entity.player.PlayerEntity.addExperienceLevels(I)V"))
    private void addExperienceLevels(PlayerEntity playerEntity, int levels) {
        int levelCost = -levels;
        logInfo("changing level cost to: "+ levelCost);
        playerEntity.addExperience(-Main.getExperienceCostAtLevel(levelCost, levelCost));
    }

}