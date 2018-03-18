package plugins.flutter.lamp.lamp;

public interface Torch {

    /**
     * Toggles the torch state.
     *
     * @param enabled true to turn the torch on, false to turn it off
     * @throws IllegalStateException if the torch could not be toggled
     */
    void toggle(boolean enabled);

    /**
     * Reports the current illumination status of the torch.
     *
     * @return true if the torch is on, false if it's off
     */
    boolean isOn();

}
