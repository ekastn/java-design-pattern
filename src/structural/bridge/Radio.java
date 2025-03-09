package structural.bridge;

public class Radio implements Device {
    private boolean on = false;
    private int volume = 20;
    private int channel = 98; // FM frequency

    @Override
    public boolean isEnabled() {
        return on;
    }

    @Override
    public void enable() {
        on = true;
        System.out.println("Radio turned on");
    }

    @Override
    public void disable() {
        on = false;
        System.out.println("Radio turned off");
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int volume) {
        // Volume can only be between 0 and 100
        this.volume = Math.max(0, Math.min(100, volume));
        System.out.println("Radio volume set to " + this.volume);
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        // FM frequencies typically range from 88-108
        this.channel = Math.max(88, Math.min(108, channel));
        System.out.println("Radio frequency set to " + this.channel + " MHz");
    }

    @Override
    public void printStatus() {
        System.out.println("--------------------");
        System.out.println("| Radio is " + (on ? "enabled" : "disabled"));
        System.out.println("| Current volume is " + volume + "%");
        System.out.println("| Current frequency is " + channel + " MHz");
        System.out.println("--------------------");
    }
}
