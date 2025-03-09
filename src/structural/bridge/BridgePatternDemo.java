package structural.bridge;

public class BridgePatternDemo {
    public static void main(String[] args) {
        // TV with basic remote
        System.out.println("TV with basic remote:");
        Device tv = new TV();
        RemoteControl basicRemote = new BasicRemote(tv);
        basicRemote.togglePower();
        basicRemote.volumeUp();
        basicRemote.channelUp();
        basicRemote.printDeviceStatus();

        // Radio with advanced remote
        System.out.println("\nRadio with advanced remote:");
        Device radio = new Radio();
        AdvancedRemote advancedRemote = new AdvancedRemote(radio);
        advancedRemote.togglePower();
        advancedRemote.volumeUp();
        advancedRemote.volumeUp();
        advancedRemote.setFavoriteChannel(102);
        advancedRemote.mute();
        advancedRemote.printDeviceStatus();
    }
}
