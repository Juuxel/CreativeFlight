package juuxel.creativeflight.mixin;

import com.mojang.minecraft.player.Input;
import com.mojang.minecraft.player.OptionsInput;
import juuxel.creativeflight.CreativeFlight;
import juuxel.creativeflight.FlyExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsInput.class)
abstract class OptionsInputMixin extends Input implements FlyExtension {
    @Unique
    private boolean flying;
    @Unique
    private boolean descending;

    @Override
    public boolean creativeFlight_isFlying() {
        return flying;
    }

    @Override
    public boolean creativeFlight_isDescending() {
        return descending;
    }

    @Inject(method = "a(IZ)V", at = @At("RETURN"))
    private void onSetKey(int key, boolean down, CallbackInfo info) {
        if (down && key == CreativeFlight.FLYING_KEYBIND.key) {
            flying = !flying;
        }

        if (key == CreativeFlight.DESCENDING_KEYBIND.key) {
            descending = down;
        }
    }
}
