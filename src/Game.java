import character.*;
import dungeon.Dungeon;
import dungeon.Potion;
import dungeon.Room;
import dungeon.Trap;

import java.util.Iterator;
import java.util.Scanner;

public class Game {
    private final Scanner scanner;
    private Hero player;
    private Dungeon dungeon;

    public Game() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        clearConsole();
        System.out.println("Selamat datang di Petualangan Dungeon!");
        System.out.println("Tujuanmu adalah menjelajahi dungeon dan bertahan hidup.");

        chooseCharacter();
        createDungeon(5);
        exploreDungeon();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void chooseCharacter() {
        System.out.println("\nPilih karakter Anda:");
        System.out.println("1. Warrior");
        System.out.println("2. Mage");
        System.out.println("3. Assassin");
        System.out.println("4. Tank");
        System.out.print("\nMasukkan pilihan (1-4): ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Membersihkan newline

        System.out.print("Masukkan nama karakter: ");
        String name = scanner.nextLine();

        player = switch (choice) {
            case 1 -> new Warrior(name);
            case 2 -> new Mage(name);
            case 3 -> new Assassin(name);
            case 4 -> new Tank(name);
            default -> {
                System.out.println("Pilihan tidak valid. Memilih Warrior secara default.");
                yield new Warrior(name);
            }
        };
    }

    private void createDungeon(int roomCount) {
        dungeon = new Dungeon(roomCount);
    }

    private void exploreDungeon()  {
        Iterator<Room> iterator = dungeon.iterator();
        while (iterator.hasNext()) {
            clearConsole();
            Room room = iterator.next();
            System.out.println(room.getDescription());
            System.out.println();

            for (Object obj : room.getObjects()) {
                if (obj instanceof Enemy enemy) {
                    System.out.println("Kamu bertemu dengan " + enemy.getName() + "!");
                    System.out.println("Mulai bertarung!");


                    try {
                        Thread.sleep(2000);
                        handleCombat(enemy);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    if (!player.isAlive()) {
                        System.out.println("Game over!");
                        return;
                    } else {
                        System.out.println("Anda mengalahkan " + enemy.getName() + "!");
                    }
                } else if (obj instanceof Potion potion) {
                    player.addPotion(potion);
                    System.out.println("Anda menemukan potion!");
                } else if (obj instanceof Trap trap) {
                    trap.trigger(player);
                }
            }

            if (room.getDescription().contains("Jalan keluar")) {
                System.out.println("Selamat! Anda menemukan jalan keluar!");
                return;
            }

            System.out.println("\nTekan Enter untuk melanjutkan...");
            scanner.nextLine();
        }

        System.out.println("Dungeon selesai dijelajahi!");
    }

    private void handleCombat(Enemy enemy) throws InterruptedException {
        while (enemy.isAlive() && player.isAlive()) {
            clearConsole();
            player.info();

            System.out.println("\nPilih aksi:");
            System.out.println("1. Serang");
            System.out.println("2. Gunakan Potion");
            System.out.print("Masukkan pilihan (1-2): ");
            int action = scanner.nextInt();

            System.out.println();
            Thread.sleep(1000);

            switch (action) {
                case 1 -> player.performAttack(enemy);
                case 2 -> player.usePotion();
                default -> {
                    System.out.println("Pilihan tidak valid. Menyerang secara default.");
                    player.performAttack(enemy);
                }
            }

            Thread.sleep(1000);

            if (enemy.isAlive()) {
                enemy.performAttack(player);
            }

            Thread.sleep(2000);
        }
    }

    private void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Gagal membersihkan console.");
        }
    }
}