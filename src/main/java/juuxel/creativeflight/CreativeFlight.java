package juuxel.creativeflight;

import com.mojang.minecraft.input.Keybind;
import org.lwjgl.input.Keyboard;

public class CreativeFlight {
    public static final float SPEED = 3;
    public static final Keybind FLYING_KEYBIND = new Keybind("Fly", Keyboard.KEY_G);
    public static final Keybind DESCENDING_KEYBIND = new Keybind("Descend", Keyboard.KEY_LSHIFT);
}
