# Laporan Design Pattern

## Muhamad Saladin Eka Septian

--- 

## **Strategy Pattern**

Strategy Pattern adalah sebuah pola yang digunakan untuk mendefinisikan sebuah keluarga algoritma, mengenkapsulasi masing-masing algoritma, dan membuatnya dapat dipertukarkan. Pola ini memungkinkan algoritma untuk bervariasi secara independen dari klien yang menggunakannya.

```mermaid
classDiagram
    class Strategy {
    +execute()
    }

    class ConcreteStrategyA {
    +execute()
    }

    class ConcreteStrategyB {
    +execute()
    }

    <<interface>> Strategy

    Context *-- Strategy
    Strategy <|.. ConcreteStrategyA
    Strategy <|.. ConcreteStrategyB
```

### **Struktur Kelas Strategy Pattern**

Strategy Pattern digunakan untuk menentukan strategi serangan yang bisa diganti-ganti pada runtime. Dengan pola ini, karakter dalam game dapat memiliki berbagai gaya bertarung yang dapat diubah tanpa perlu mengubah struktur utama dari objek karakter itu sendiri.

```mermaid
classDiagram
direction TB
    class Hero {
        +setAttackStrategy(IAttackStrategy strategy) void
    }
    class AttackStrategy {
        +attack(Character target) void
    }
    class MeleeAttack {
        +attack(Character target) void
    }
    class MagicAttack {
        +attack(Character target) void
    }
    class StealthAttack {
        +attack(Character target) void
    }

    <<interface>> AttackStrategy

    Hero --> AttackStrategy : uses
    AttackStrategy <|.. MeleeAttack
    AttackStrategy <|.. MagicAttack
    AttackStrategy <|.. StealthAttack
```

Dengan struktur ini, strategi serangan dapat diubah tanpa perlu memodifikasi kelas `Hero`, cukup dengan mengganti objek
`AttackStrategy` yang digunakan.

---

## **Builder Pattern**

Builder Pattern adalah salah satu creational design pattern yang memisahkan proses konstruksi suatu objek kompleks dari representasinya, sehingga proses konstruksi yang sama dapat menghasilkan berbagai representasi yang berbeda.

```mermaid
classDiagram
    class Director {
    +execute()
    }

    class Builder {
    +buildPart()
    }

    class ConcreteBuilder {
    +buildPart()
    +getResult()
    }

    <<interface>> Builder

    Director *-- Builder
    Builder <|.. ConcreteBuilder
```

### **Struktur Kelas Builder Pattern**

Builder Pattern digunakan untuk membuat karakter dalam game dengan konfigurasi yang fleksibel. Alih-alih menggunakan
konstruktor dengan banyak parameter, pola ini memungkinkan pembuatan objek secara bertahap dengan hanya menyertakan
atribut yang dibutuhkan. Dengan demikian, proses inisialisasi karakter menjadi lebih terstruktur dan mudah dibaca.

```mermaid
classDiagram
direction TB
    class HeroBuilder {
        +setName(String name) HeroBuilder
        +setHealth(int health) HeroBuilder
        +setAttack(int attack) HeroBuilder
        +setDefense(int defense) HeroBuilder
        +setAttackStrategy(AttackStrategy strategy) HeroBuilder
        +build() Hero
    }
    class Hero {
        -String name
        -int health
        -int attackPower
        -int defense
        -AttackStrategy attackStrategy
        +setAttackStrategy(IAttackStrategy strategy) void
    }
    class AttackStrategy {
        +attack(Character target) void
    }

    <<interface>> AttackStrategy

    HeroBuilder --> Hero : builds
    Hero --> AttackStrategy : uses
```

Dengan menggunakan `HeroBuilder`, objek `Hero` dapat dibuat secara bertahap tanpa harus menggunakan konstruktor dengan
parameter panjang.

---

## **Iterator Pattern**

Iterator Pattern adalah salah satu behavioral design pattern yang menyediakan cara untuk mengakses elemen dari suatu objek agregat secara berurutan tanpa mengekspos representasi dasarnya.

```mermaid
classDiagram
    class Client

    class Aggregate {
        +createIterator()
    }
    class ConcreteAggregate {
        +createIterator() Context
    }

    class Iterator {
        +next()
    }
    class ConcreteIterator {
        +next() Context
    }

    <<interface>> Aggregate
    <<interface>> Iterator

    Client --> Aggregate
    Client --> Iterator

    Aggregate <|-- ConcreteAggregate
    Iterator <|-- ConcreteIterator
```

### **Struktur Kelas Iterator Pattern**

Iterator Pattern digunakan untuk mengiterasi ruangan dalam dungeon tanpa mengekspos implementasi koleksi secara
langsung. Dengan menggunakan iterator, pemain dapat menjelajahi setiap ruangan dalam dungeon satu per satu tanpa perlu
mengetahui bagaimana ruangan disimpan di dalam game. Pola ini membuat eksplorasi lebih modular dan meningkatkan
enkapsulasi dalam sistem.

```mermaid
classDiagram
direction TB
    class Dungeon {
        +iterator() RoomIterator
    }
    class RoomIterator {
        +hasNext() bool
        +next() Room
    }
    class Room {
        -String description
    }
    Dungeon --> RoomIterator : uses
    RoomIterator --> Room : iterates
```

Dengan pola ini, eksplorasi dungeon menjadi lebih terstruktur karena `RoomIterator` bertanggung jawab atas iterasi
ruangan, sementara `Dungeon` hanya menyediakan akses ke iterator tanpa mengekspos struktur data internalnya.

---

## **CLI App**

Program ini merupakan game RPG Dungeon Crawler di mana pemain dapat memilih tipe karakter, menjelajahi dungeon yang
dibuat secara prosedural,
berinteraksi dengan objek di ruangan, seperti musuh, perangkap, atau potion, menggunakan strategi serangan yang berbeda
tergantung pada tipe karakter.

### **Use Case Diagram**

![Use Case Diagram](./docs/UseCase.svg)

1. **Aktor**:
    - **Player**: Pemain yang mengontrol karakter dalam game.
2. **Use-Case Utama**:
    - **Create Character**: Pemain membuat karakter.
    - **Explore Dungeon**: Pemain menjelajahi dungeon.
3. **Use-Case yang Termasuk dalam Explore Dungeon**:
    - **Battle Enemy**: Pemain bertarung dengan musuh.
    - **Find Exit**: Pemain menemukan jalan keluar.
    - **Collect Potion**: Pemain mengumpulkan potion.
    - **Trigger Trap**: Pemain memicu perangkap.
4. **Use-Case yang Memperluas Battle Enemy**:
    - **Use Potion**: Pemain menggunakan potion selama pertarungan.
    - **Attack Enemy**: Pemain menyerang musuh selama pertarungan.

### **Class Diagram**

```mermaid
classDiagram
direction TB
    class Character {
        -String name
        -int health
        -int attackPower
        -int defense
        +performAttack(Character target) void
        +takeDamage(int damage) void
        +isAlive() bool
        +getName() String
        +getHealth() int
        +getAttackPower() int
        +getDefense() int
    }
    class Hero {
        -List~Potion~ inventory
        -AttackStrategy attackStrategy
        +setAttackStrategy(IAttackStrategy attackStrategy) void
        +usePotion() void
        +addPotion(Potion potion) void
        +getPotionCount() int
    }
    class Enemy {
        +performAttack(Character target) void
    }
    class HeroBuilder {
        +setName(String name) void
        +setHealth(int health) void
        +setAttack(int attack) void
        +setDefense(int defense) void
        +setAttackStrategy(IAttackStrategy attackStrategy) void
        +build() Hero
    }
    class Dungeon {
        -List~Room~ rooms
        -Random random
        +iterator() Iterator
        +generateRooms(int roomCount) void
    }
    class Room {
        -String description
        -List~Object~ objects
        +addObject(Object object) void
        +getDescription() String
        +getObjects() List~Object~
    }
    class Trap {
        -int damage
        +trigger(Character character) void
    }
    class Potion {
        -int healingPower
        +getHealingPower() int
    }
    class Game {
        -Hero player
        -Dungeon dungeon
        +start() void
        +chooseCharacter() void
        +createDungeon(int roomCount) void
        +exploreDungeon() void
    }
    class AttackStrategy {
        +attack(Character target) void
    }
    class StealthAttack {
        +attack(Character target) void
    }
    class MagicAttack {
        +attack(Character target) void
    }
    class MeleeAttack {
        +attack(Character target) void
    }
    class RoomIterator {
        -List~Room~ rooms
        -int position
        +hasNext() boolean
        +next() Room
    }
    class Random {
        +nextInt(int bound) int
    }

    <<abstract>> Character
    <<interface>> AttackStrategy

    Game --> Hero : has
    Game --> Dungeon : has
    Game --> HeroBuilder : uses
    Dungeon --> Room : contains
    Dungeon --> RoomIterator : uses
    Dungeon --> Random : uses
    Room --> Trap : contains
    Room --> Enemy : contains
    Room --> Potion : contains
    Character <|-- Hero
    Character <|-- Enemy
    Trap --> Character : triggers
    Hero --> AttackStrategy : uses
    HeroBuilder --> Hero : builds
    HeroBuilder --> AttackStrategy : uses
    AttackStrategy <|.. StealthAttack
    AttackStrategy <|.. MagicAttack
    AttackStrategy <|.. MeleeAttack
```

| **Kelas**                                             | **Atribut**                                                                   | **Metode**                                                                                                                                                                                                                  | **Deskripsi**                                                          |
|-------------------------------------------------------|-------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------|
| `Character`                                           | `-String name`  <br>`-int health`  <br>`-int attackPower`  <br>`-int defense` | `+performAttack(Character target) void`  <br>`+takeDamage(int damage) void`  <br>`+isAlive() bool`  <br>`+getName() String`  <br>`+getHealth() int`  <br>`+getAttackPower() int`  <br>`+getDefense() int`                   | Kelas abstrak yang merepresentasikan karakter dalam game (hero/musuh). |
| `Hero`                                                | `-List~Potion~ inventory`  <br>`-AttackStrategy attackStrategy`               | `+setAttackStrategy(IAttackStrategy attackStrategy) void`  <br>`+usePotion() void`  <br>`+addPotion(Potion potion) void`  <br>`+getPotionCount() int`                                                                       | Turunan dari `Character`, merepresentasikan hero.                      |
| `Enemy`                                               | -                                                                             | `+performAttack(Character target) void`                                                                                                                                                                                     | Turunan dari `Character`, merepresentasikan musuh.                     |
| `HeroBuilder`                                         | -                                                                             | `+setName(String name) void`  <br>`+setHealth(int health) void`  <br>`+setAttack(int attack) void`  <br>`+setDefense(int defense) void`  <br>`+setAttackStrategy(IAttackStrategy attackStrategy) void`  <br>`+build() Hero` | Builder untuk membuat objek `Hero`.                                    |
| `Dungeon`                                             | `-List~Room~ rooms`  <br>`-Random random`                                     | `+iterator() Iterator`  <br>`+generateRooms(int roomCount) void`                                                                                                                                                            | Dungeon yang berisi ruangan-ruangan.                                   |
| `Room`                                                | `-String description`  <br>`-List~Object~ objects`                            | `+addObject(Object object) void`  <br>`+getDescription() String`  <br>`+getObjects() List~Object~`                                                                                                                          | Representasi ruangan di dungeon.                                       |
| `Trap`                                                | `-int damage`                                                                 | `+trigger(Character character) void`                                                                                                                                                                                        | Perangkap yang memberikan damage.                                      |
| `Potion`                                              | `-int healingPower`                                                           | `+getHealingPower() int`                                                                                                                                                                                                    | Potion untuk memulihkan kesehatan.                                     |
| `Game`                                                | `-Hero player`  <br>`-Dungeon dungeon`                                        | `+start() void`  <br>`+chooseCharacter() void`  <br>`+createDungeon(int roomCount) void`  <br>`+exploreDungeon() void`                                                                                                      | Kelas utama yang mengatur alur permainan.                              |
| `AttackStrategy`                                      | -                                                                             | `+attack(Character target) void`                                                                                                                                                                                            | Interface strategi serangan.                                           |
| `StealthAttack`  <br>`MagicAttack`  <br>`MeleeAttack` | -                                                                             | `+attack(Character target) void`                                                                                                                                                                                            | Implementasi strategi serangan.                                        |
| `RoomIterator`                                        | `-List~Room~ rooms`  <br>`-int position`                                      | `+hasNext() boolean`  <br>`+next() Room`                                                                                                                                                                                    | Iterator untuk menjelajahi dungeon.                                    |
| `Random`                                              | -                                                                             | `+nextInt(int bound) int`                                                                                                                                                                                                   | Generator angka acak.                                                  |

---

### **Sequence Diagram: Choose Character**

```mermaid
sequenceDiagram
    actor Player
    participant Game
    participant HeroBuilder
    participant AttackStrategy
    participant Hero

    Player->>+Game: Mulai Permainan
    Game->>Player: Tampilkan pilih karakter
    Player->>Game: Input karakter (1-4) dan nama
    Game->>+HeroBuilder: Membuat hero
    HeroBuilder->>HeroBuilder: setName(nama)
    alt pilihan = 1 (Warrior)
        HeroBuilder->>HeroBuilder: Health(120), Attack(25), Defense(20)
        HeroBuilder->>+AttackStrategy: Set attack strategy (MeleeAttack)
        AttackStrategy-->>-HeroBuilder: Attack strategy set
    else pilihan = 2 (Mage)
        HeroBuilder->>HeroBuilder: Health(80), Attack(30), Defense(10)
        HeroBuilder->>+AttackStrategy: Set attack strategy (MagicAttack)
        AttackStrategy-->>-HeroBuilder: Attack strategy set
    else pilihan = 3 (Assassin)
        HeroBuilder->>HeroBuilder: Health(90), Attack(35), Defense(15)
        HeroBuilder->>+AttackStrategy: Set attack strategy (StealthAttack)
        AttackStrategy-->>-HeroBuilder: Attack strategy set
    else pilihan = 4 (Tank)
        HeroBuilder->>HeroBuilder: Health(150), Attack(15), Defense(30)
        HeroBuilder->>+AttackStrategy: Set attack strategy (MeleeAttack)
        AttackStrategy-->>-HeroBuilder: Attack strategy set
    end
    HeroBuilder->>+Hero: build()
    Hero-->>-HeroBuilder: Hero terbuat
    HeroBuilder-->>-Game: Hero terbuat
    Game->>-Player: Karakter terpilih
```

1. **Player Memulai Permainan**:
    - Pemain memulai permainan dengan memanggil`Mulai Permainan`pada`Game`.
2. **Game Menampilkan Pilihan Karakter**:
    - `Game`menampilkan pilihan karakter (Warrior, Mage, Assassin, Tank) kepada pemain.
3. **Player Memilih Karakter**:
    - Pemain memilih karakter (1-4) dan memasukkan nama.
4. **Game Meminta HeroBuilder Membuat Hero**:
    - `Game`meminta`HeroBuilder`untuk membuat karakter sesuai pilihan pemain.
5. **HeroBuilder Mengatur Atribut Karakter**:
    - `HeroBuilder`mengatur atribut karakter (`Health`,`Attack`,`Defense`) berdasarkan pilihan pemain.
6. **HeroBuilder Mengatur Attack Strategy**:
    - `HeroBuilder`mengatur strategi serangan (`AttackStrategy`) berdasarkan tipe karakter:
        - **Warrior**:`MeleeAttack`
        - **Mage**:`MagicAttack`
        - **Assassin**:`StealthAttack`
        - **Tank**:`MeleeAttack`
7. **HeroBuilder Membuat Hero**:
    - `HeroBuilder`memanggil metode`build()`untuk membuat objek`Hero`.
8. **HeroBuilder Mengembalikan Hero ke Game**:
    - `HeroBuilder`mengembalikan objek`Hero`yang sudah dibuat ke`Game`.
9. **Game Mengonfirmasi Karakter Terpilih**:
    - `Game`mengonfirmasi kepada pemain bahwa karakter telah berhasil dipilih.

### **Sequence Diagram: Dungeon Generation**

```mermaid
sequenceDiagram
    actor Player
    participant Game
    participant Dungeon
    participant Room
    participant Random

    Player->>+Game: Mulai Permainan
    Game->>+Dungeon: Buat dungeon (roomCount)
    Dungeon->>+Random: Generate random objects
    Random-->>-Dungeon: random objects
    loop Untuk setiap ruangan
        Dungeon->>+Room: Buat ruangan baru
        Room-->>-Dungeon: Ruangan selesai dibuat
        alt objek = Enemy
            Dungeon->>Room: Tambahkan Enemy
        else objek = Potion
            Dungeon->>Room: Tambahkan Potion
        else objek = Trap
            Dungeon->>Room: Tambahkan Trap
        end
    end
    Dungeon-->>-Game: Dungeon siap digunakan
    Game-->>-Player: Dungeon berhasil dibuat
```

1. **Player Memulai Permainan**:
    - Pemain memulai permainan dengan memanggil`Mulai Permainan`pada`Game`.
2. **Game Meminta Dungeon Dibuat**:
    - `Game`meminta`Dungeon`untuk membuat dungeon dengan jumlah ruangan tertentu (`roomCount`).
3. **Dungeon Menggunakan Random untuk Generate Objek**:
    - `Dungeon`menggunakan`Random`untuk menghasilkan objek acak (musuh, potion, perangkap) di setiap ruangan.
    - `Random`mengembalikan objek acak ke`Dungeon`.
4. **Pembuatan Ruangan**:
    - Untuk setiap ruangan:
        - `Dungeon`membuat objek`Room`baru.
        - `Dungeon`menambahkan objek acak (`Enemy`,`Potion`, atau`Trap`) ke dalam ruangan.
5. **Dungeon Siap Digunakan**:
    - Setelah semua ruangan selesai dibuat,`Dungeon`mengembalikan dungeon yang siap digunakan ke`Game`.
6. **Game Mengonfirmasi Dungeon Berhasil Dibuat**:
    - `Game`mengonfirmasi kepada pemain bahwa dungeon telah berhasil dibuat.

### **Sequence Diagram: Combat**

```mermaid
sequenceDiagram
    actor Player
    participant Game
    participant Hero
    participant Enemy

    Player->>+Game: Mulai pertarungan
    Game->>Player: Tampilkan info enemy
    Game->>Hero: Tampilkan status hero

    loop Selama enemy dan hero masih hidup
        Game->>Player: Tampilkan pilihan aksi (serang/gunakan potion)
        Player->>Game: Pilih aksi
        alt pilihan = serang
            Game->>Hero: Lakukan serangan
            Hero->>Enemy: Serang enemy
            Enemy-->>Hero: Hasil serangan
            Game->>Player: Tampilkan hasil serangan
        else pilihan = gunakan potion
            Game->>Hero: Gunakan potion
            Hero-->>Game: Potion digunakan
            Game->>Player: Tampilkan pesan potion digunakan
        end

        alt enemy masih hidup
            Game->>Enemy: Serang hero
            Enemy->>Hero: Berikan damage
            Hero-->>Game: Hasil serangan enemy
            Game->>Player: Tampilkan hasil serangan enemy
        end
    end

    alt hero masih hidup
        Game->>Player: Tampilkan pesan menang
    else
        Game->>Player: Tampilkan pesan kalah
    end

    Game-->>-Player: Pertarungan selesai
```

1. **Player Memulai Pertarungan**:
    - Pemain memulai pertarungan dengan memanggil`Mulai pertarungan`pada`Game`.
2. **Game Menampilkan Info Enemy dan Status Hero**:
    - `Game`menampilkan informasi tentang`Enemy`dan status`Hero`kepada pemain.
3. **Loop Pertarungan**:
    - Selama`Enemy`masih hidup dan`Hero`masih hidup:
        - `Game`menampilkan pilihan aksi kepada pemain (menyerang atau menggunakan potion).
        - Pemain memilih aksi.
4. **Jika Pemain Memilih Menyerang**:
    - `Game`meminta`Hero`untuk menyerang`Enemy`.
    - `Hero`menyerang`Enemy`dan mengembalikan hasil serangan.
    - `Game`menampilkan hasil serangan kepada pemain.
5. **Jika Pemain Memilih Menggunakan Potion**:
    - `Game`meminta`Hero`untuk menggunakan potion.
    - `Hero`menggunakan potion dan mengembalikan konfirmasi ke`Game`.
    - `Game`menampilkan pesan bahwa potion telah digunakan.
6. **Enemy Menyerang Balik**:
    - Jika`Enemy`masih hidup setelah serangan pemain,`Enemy`menyerang`Hero`.
    - `Enemy`memberikan damage kepada`Hero`dan mengembalikan hasil serangan ke`Game`.
    - `Game`menampilkan hasil serangan`Enemy`kepada pemain.
7. **Pengecekan Hasil Pertarungan**:
    - Jika`Hero`masih hidup setelah pertarungan,`Game`menampilkan pesan kemenangan.
    - Jika`Hero`kalah,`Game`menampilkan pesan kekalahan.
8. **Pertarungan Selesai**:
    - `Game`mengembalikan pesan bahwa pertarungan selesai kepada pemain.

### **Sequence Diagram: Explore Dungeon**

```mermaid
sequenceDiagram
    actor Player
    participant Game
    participant Dungeon
    participant RoomIterator
    participant Room
    participant Hero
    participant Potion
    participant Trap
    
    Player->>+Game: Mulai menjelajahi dungeon
    Game->>+Dungeon: Dapatkan iterator ruangan
    Dungeon->>+RoomIterator: Buat iterator
    RoomIterator-->>-Dungeon: Iterator siap
    Dungeon-->>-Game: Iterator dikembalikan
    
    loop Untuk setiap ruangan
        Game->>+RoomIterator: Ambil ruangan berikutnya
        RoomIterator->>+Room: Dapatkan ruangan
        Room-->>-RoomIterator: Ruangan dikembalikan
        RoomIterator-->>-Game: Ruangan dikembalikan
        Game->>Player: Tampilkan deskripsi ruangan
        Game->>Hero: Tampilkan status hero
        
        loop Untuk setiap objek di ruangan
            alt objek = Potion
                Game->>Potion: Tampilkan info potion
                Game->>Hero: Tambahkan potion ke inventory
                Game->>Player: Tampilkan pesan menemukan potion
            else objek = Trap
                Game->>Trap: Tampilkan info trap
                Game->>Trap: Picu trap
                Trap->>Hero: Berikan damage
                Game->>Player: Tampilkan pesan trap terpicu
            end
        end
        
        alt ruangan adalah jalan keluar
            Game->>Player: Tampilkan pesan menemukan jalan keluar
            Game->>Player: Eksplorasi dungeon selesai
        else bukan jalan keluar
            Game->>Player: Tampilkan pesan lanjut ke ruangan berikutnya
        end
    end
    
    Game-->>-Player: Eksplorasi dungeon selesai
```

1. **Player Memulai Eksplorasi Dungeon**:
    - Pemain memulai eksplorasi dungeon dengan memanggil`Mulai menjelajahi dungeon`pada`Game`.
2. **Game Meminta Iterator Ruangan**:
    - `Game`meminta`Dungeon`untuk mendapatkan iterator ruangan.
3. **Dungeon Membuat Iterator**:
    - `Dungeon`membuat`RoomIterator`dan mengembalikannya ke`Game`.
4. **Iterasi Ruangan**:
    - Untuk setiap ruangan:
        - `Game`meminta`RoomIterator`untuk mengambil ruangan berikutnya.
        - `RoomIterator`mengembalikan`Room`ke`Game`.
        - `Game`menampilkan deskripsi ruangan dan status hero kepada pemain.
5. **Interaksi dengan Objek di Ruangan**:
    - Untuk setiap objek di ruangan:
        - Jika objek adalah`Potion`, potion ditambahkan ke inventory hero.
        - Jika objek adalah`Trap`, trap dipicu dan memberikan damage kepada hero.
6. **Pengecekan Jalan Keluar**:
    - Jika ruangan adalah jalan keluar, pemain mendapatkan pesan bahwa mereka menemukan jalan keluar.
    - Jika bukan, pemain melanjutkan ke ruangan berikutnya.
7. **Eksplorasi Selesai**:
    - Setelah semua ruangan selesai dijelajahi,`Game`mengembalikan pesan bahwa eksplorasi dungeon selesai.

--- 

## **Output Program**

![Output Program](./docs/output.gif)