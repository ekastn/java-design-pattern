package structural.bridge;

public class TV implements Device {
    private boolean on = false;
    private int volume = 30;
    private int channel = 1;

    @Override
    public boolean isEnabled() {
        return on;
    }

    @Override
    public void enable() {
        on = true;
        System.out.println("TV turned on");
    }

    @Override
    public void disable() {
        on = false;
        System.out.println("TV turned off");
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int volume) {
        // Volume can only be between 0 and 100
        this.volume = Math.max(0, Math.min(100, volume));
        System.out.println("TV volume set to " + this.volume);
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
        System.out.println("TV channel set to " + this.channel);
    }

    @Override
    public void printStatus() {
        System.out.println("--------------------");
        System.out.println("| TV is " + (on ? "enabled" : "disabled"));
        System.out.println("| Current volume is " + volume + "%");
        System.out.println("| Current channel is " + channel);
        System.out.println("--------------------");
    }
}
