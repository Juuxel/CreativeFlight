package juuxel.creativeflight.mixin;

import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.gui.InGameHud;
import com.mojang.minecraft.player.Player;
import juuxel.creativeflight.FlyExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
abstract class MinecraftMixin {
    @Shadow public Player player;
    @Shadow public InGameHud hud;

    @Inject(method = "tick", at = @At("RETURN"))
    private void onTick(CallbackInfo info) {
        FlyExtension ext = (FlyExtension) player;
        if (ext.creativeFlying_wasFlying() != ext.creativeFlight_isFlying()) {
            String message = ext.creativeFlight_isFlying() ? "Started flying!" : "Stopped flying!";
            hud.addMessage(message);
        }
    }
}
