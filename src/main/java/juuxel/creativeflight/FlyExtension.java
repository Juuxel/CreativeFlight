package juuxel.creativeflight;

public interface FlyExtension {
    boolean creativeFlight_isFlying();
    boolean creativeFlight_isDescending();

    default boolean creativeFlying_wasFlying() {
        return creativeFlight_isFlying();
    }
}
