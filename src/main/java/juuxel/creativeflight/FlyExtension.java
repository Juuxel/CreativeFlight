/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package juuxel.creativeflight;

public interface FlyExtension {
    boolean creativeFlight_isFlying();
    boolean creativeFlight_isDescending();

    default boolean creativeFlying_wasFlying() {
        return creativeFlight_isFlying();
    }
}
