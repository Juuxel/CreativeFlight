/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package juuxel.creativeflight;

import com.mojang.minecraft.gamemode.GameMode;

public interface PlayerExtension extends FlyExtension {
    default boolean creativeFlight_wasFlying() {
        return creativeFlight_isFlying();
    }
    void creativeFlight_setGameMode(GameMode gameMode);
}
