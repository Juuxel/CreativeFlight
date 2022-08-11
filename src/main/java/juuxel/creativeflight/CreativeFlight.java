/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package juuxel.creativeflight;

import com.mojang.minecraft.input.Keybind;
import org.lwjgl.input.Keyboard;

public class CreativeFlight {
    public static final float SPEED = 3;
    public static final Keybind FLYING_KEYBIND = new Keybind("Fly", Keyboard.KEY_G);
    public static final Keybind DESCENDING_KEYBIND = new Keybind("Descend", Keyboard.KEY_LSHIFT);
}
