package behavioral.observer;

public enum EventType {
    ATTACK_MAGIC("🔮"),
    ATTACK_MELEE("⚔️"),
    ATTACK_RANGED("🏹"),
    ATTACK_STEALTH("👤"),
    CHARACTER_DEFENDED("🛡️"),
    CHARACTER_HEALED("💖"),
    CHARACTER_HURT("💢"),
    CHARACTER_FLEEING("🏃"),
    PLAYER_DEAD("💀"),
    ENEMY_DEAD("☠️"),
    POTION_USED("🧪"),
    POTION_ADDED("🎁"),
    ROOM_EXPLORED("🚪"),
    WARNING("⚠️"),
    CONGRATS("🎉"),
    GAME_STATE("🎮"),
    DUNGEON_PROGRESS("🗺️"),
    BASIC(" ");

    final String icon;

    EventType(String icon) {
        this.icon = icon;
    }
}