package structural.bridge;

public class AdvancedRemote extends RemoteControl {
    public AdvancedRemote(Device device) {
        super(device);
    }

    public void mute() {
        System.out.println("Mute function activated");
        device.setVolume(0);
    }

    public void setFavoriteChannel(int channelNumber) {
        System.out.println("Setting to favorite channel " + channelNumber);
        device.setChannel(channelNumber);
    }
}
