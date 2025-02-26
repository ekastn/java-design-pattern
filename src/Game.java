import builder.HeroBuilder;
import character.*;
import strategy.*;
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
        System.out.println("Selamat datang di The Dungeon Adventure!");
        System.out.println("Tujuanmu hanya satu, bertahan hidup!");

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

        HeroBuilder heroBuilder = new HeroBuilder().setName(name);

        switch (choice) {
            case 1 -> heroBuilder
                    .setHealth(120)
                    .setAttack(25)
                    .setDefense(20)
                    .setAttackStrategy(new MeleeAttack());
            case 2 -> heroBuilder
                    .setHealth(80)
                    .setAttack(30)
                    .setDefense(10)
                    .setAttackStrategy(new MagicAttack());
            case 3 -> heroBuilder
                    .setHealth(90)
                    .setAttack(35)
                    .setDefense(15)
                    .setAttackStrategy(new StealthAttack());
            case 4 -> heroBuilder
                    .setHealth(150)
                    .setAttack(15)
                    .setDefense(30)
                    .setAttackStrategy(new MeleeAttack());
            default -> {
                System.out.println("Pilihan tidak valid. Memilih Warrior secara default.");
                heroBuilder
                    .setHealth(120)
                    .setAttack(25)
                    .setDefense(20)
                    .setAttackStrategy(new MeleeAttack());
            }
        }

        player = heroBuilder.build();
    }

    private void createDungeon(int roomCount) {
        dungeon = new Dungeon(roomCount);
    }

    private void exploreDungeon() {
        Iterator<Room> iterator = dungeon.iterator();
        while (iterator.hasNext()) {
            clearConsole();
            Room room = iterator.next();
            System.out.print("========= ");
            System.out.print(room.getDescription());
            System.out.print(" dari ");
            System.out.print(dungeon.getRoomCount());
            System.out.print(" =========");
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