## **Builder Pattern**

Builder Pattern digunakan untuk memisahkan proses konstruksi objek yang kompleks dari representasinya sehingga proses
pembuatan yang sama dapat menghasilkan bentuk yang berbeda.

```mermaid
classDiagram
    class Director {
    +construct()
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

Builder Pattern digunakan untuk membangun hero dan dungeon. `HeroBuilder` digunakan untuk membuat hero dengan cara
menetapkan atribut seperti nama, health, attack, defense, serta strategi serangan sebelum akhirnya hero dibuat dengan
metode `build()`. `HeroDirector` berperan sebagai pengarah yang menyediakan metode pembuatan hero sesuai dengan
kelasnya, seperti Warrior, Mage, Assassin, dan Tank, tanpa perlu mengatur atributnya secara manual setiap kali membuat
hero baru.

```mermaid
classDiagram
direction TB

    %% Hero Builder
    class Hero {
        -String name
        -int health
        -int attackPower
        -int defense
        -AttackStrategy attackStrategy
        +displayStats() void
    }

    class HeroBuilder {
        +setName(String name) HeroBuilder
        +setHealth(int health) HeroBuilder
        +setAttack(int attack) HeroBuilder
        +setDefense(int defense) HeroBuilder
        +setAttackStrategy(AttackStrategy strategy) HeroBuilder
        +build() Hero
    }

    class HeroDirector {
        +createWarrior() Hero
        +createMage() Hero
        +createAssassin() Hero
        +createTank() Hero
    }

		class AttackStrategy {
				+attack(Character target) void
		}

    Hero ..> AttackStrategy : uses
    HeroBuilder ..> Hero : builds
    HeroDirector ..> HeroBuilder : directs

    %% Dungeon Builder
    class Dungeon {
        -RoomIterator iterator
        +exploreNextRoom() void
        +isComplete() boolean
    }

    class DungeonBuilder {
        -int roomCount
        -Random random
        +setRoomCount(int count) DungeonBuilder
        +build() Dungeon
    }

    class RoomIterator {
        +hasNext() boolean
        +next() Room
    }

    DungeonBuilder --> Dungeon : builds
    Dungeon --> RoomIterator : uses
```

Pembuatan dungeon menggunakan `DungeonBuilder`, yang bertanggung jawab untuk membentuk dungeon dengan sejumlah ruangan
dan mengisi setiap ruangan dengan objek secara acak. `DungeonBuilder` memiliki metode `setRoomCount()` untuk menentukan
jumlah ruangan sebelum akhirnya dipanggil `build()` untuk membuat dungeon. Dungeon yang dihasilkan akan memiliki
sekumpulan `Room` yang disusun dalam `iterator` sehingga pemain dapat menjelajahinya satu per satu.

---

## **Factory Method Pattern**

Factory Method Pattern digunakan untuk mendefinisikan antarmuka dalam pembuatan objek, tetapi memungkinkan subclass yang
menentukan kelas mana yang akan diinstansiasi. Pola ini digunakan ketika kelas tidak dapat menentukan objek mana yang
harus dibuat pada saat kompilasi dan menyerahkan keputusan ini kepada subclass.

```mermaid
classDiagram
    class Creator {
        +createProduct() Product
    }

    class ConcreteCreatorA {
        +createProduct() Product
    }

    class ConcreteCreatorB {
        +createProduct() Product
    }

    class Product {
    }

    class ConcreteProductA {
    }

    class ConcreteProductB {
    }

    <<interface>> Product
    Creator <|.. ConcreteCreatorA
    Creator <|.. ConcreteCreatorB
    Product <|.. ConcreteProductA
    Product <|.. ConcreteProductB
    Creator --> Product : creates
```

### **Struktur Kelas Factory Method Pattern**

Factory Method Pattern digunakan untuk membuat objek dalam dungeon secara dinamis. `RoomObjectFactory` adalah kelas
abstrak yang berfungsi sebagai pabrik, sedangkan `EnemyFactory`, `PotionFactory`, dan `TrapFactory` bertindak sebagai
subclass yang menghasilkan objek sesuai dengan tipe masing-masing.

```mermaid
classDiagram
direction TB

    class RoomObject {
        +interact(Hero hero) void
    }

    class Enemy {
        +interact(Hero hero) void
    }

    class Potion {
        +interact(Hero hero) void
    }

    class Trap {
        +interact(Hero hero) void
    }

    class RoomObjectFactory {
        +createObject() RoomObject
    }

    class EnemyFactory {
        +createObject() RoomObject
    }

    class PotionFactory {
        +createObject() RoomObject
    }

    class TrapFactory {
        +createObject() RoomObject
    }

		<<interface>> RoomObject
		<<abstract>> RoomObjectFactory

    RoomObject <|.. Enemy
    RoomObject <|.. Potion
    RoomObject <|.. Trap
    RoomObjectFactory <|.. EnemyFactory
    RoomObjectFactory <|.. PotionFactory
    RoomObjectFactory <|.. TrapFactory
    RoomObjectFactory --> RoomObject : creates
```

Dalam implementasinya, `RoomObjectFactory` mendefinisikan metode `createObject()`, yang kemudian diimplementasikan oleh
subclass `EnemyFactory`, `PotionFactory`, dan `TrapFactory`. `EnemyFactory` menghasilkan musuh menggunakan
`EnemyPrototype`, `PotionFactory` menciptakan potion dengan jumlah penyembuhan tertentu, dan `TrapFactory` membuat
jebakan dengan nilai damage yang telah ditentukan.

---

## **Prototype Pattern**

Prototype Pattern digunakan untuk membuat objek baru dengan menyalin (cloning) objek yang sudah ada, daripada membuatnya
dari awal. Pola ini berguna ketika pembuatan objek memerlukan biaya tinggi atau memiliki konfigurasi kompleks yang tidak
mudah dibuat ulang secara manual.

```mermaid
classDiagram
    class Prototype {
        +clone() Prototype
    }

    class ConcretePrototype {
        +clone() Prototype
    }

    <<interface>> Prototype
    Prototype <|.. ConcretePrototype
```

### **Struktur Kelas Prototype Pattern**

Prototype Pattern digunakan untuk membuat musuh berdasarkan template yang telah didefinisikan sebelumnya.
`EnemyPrototype` menyimpan berbagai tipe musuh yang dapat dikloning sesuai kebutuhan.

```mermaid
classDiagram
direction TB
    class Character {
        -String name
        -int health
        -int attackPower
        -int defense
        +clone() Character
    }

    class Enemy {
    }

    class EnemyPrototype {
        +getPrototype(String type) Enemy
    }

		<<abstract>> Character

    Character <|-- Enemy
    EnemyPrototype --> Enemy : stores
```

Dalam implementasinya, `EnemyPrototype` menyimpan daftar musuh yang telah dibuat sebelumnya, seperti **Goblin, Orc,
Skeleton, dan Troll**. Saat permainan membutuhkan musuh baru, ia tidak perlu membuat objek baru dari awal, tetapi cukup
mengkloning salah satu prototype yang sudah ada.

---

## **Singleton Pattern**

Singleton Pattern digunakan untuk memastikan bahwa sebuah kelas hanya memiliki satu instance yang dapat diakses secara
global. Pola ini sering digunakan untuk mengelola sumber daya bersama, seperti pengaturan konfigurasi, manajemen
koneksi, atau dalam kasus ini, status permainan.

```mermaid
classDiagram
direction LR
    class Singleton {
        -static Singleton instance
        -Singleton()
        +getInstance() Singleton
    }

		Singleton --> Singleton
```

### **Struktur Kelas Singleton Pattern**

Singleton Pattern digunakan untuk memastikan bahwa hanya ada satu instance dari `Game` yang berjalan selama permainan
berlangsung. `Game` bertindak sebagai pusat logika permainan yang mengatur status permainan, pemain, dan dungeon.

```mermaid
classDiagram
	direction LR
    class Game {
        -static Game instance
        -boolean running
        -Hero player
        -Dungeon dungeon
        -GameState gameState
        +getInstance() Game
        +start() void
        +setGameState(GameState state) void
        +notify(EventType type, String message) void
    }

    class EventManager {
        +subscribe(EventListener listener) void
        +unsubscribe(EventListener listener) void
        +notify(EventType type, String message) void
    }

    Game --> EventManager : uses
    Game --> Game
```

Dalam implementasinya, `Game` memiliki metode `getInstance()`, yang memastikan bahwa hanya satu instance yang dibuat.
Jika instance belum ada, maka objek `Game` akan dibuat, tetapi jika sudah ada, metode ini hanya akan mengembalikan
instance yang sudah ada.

---

## **Abstract Factory Pattern**

Abstract Factory Pattern adalah pola desain yang menyediakan antarmuka untuk membuat keluarga objek terkait atau
bergantung tanpa menentukan kelas konkret mereka. Pola ini berguna untuk membuat sistem yang dapat dikonfigurasi dengan
beberapa keluarga objek.

```mermaid
classDiagram
    class Client

    class AbstractFactory {
        <<interface>>
        +createProductA()
        +createProductB()
    }

    class ConcreteFactory {
        +createProductA()
        +createProductB()
    }

    class AbstractProduct {
    }

    class ConcreteProduct
		
		<<interface>> AbstractProduct
		<<interface>> AbstractFactory

    Client --> AbstractFactory : Uses
    Client --> AbstractProduct : Uses

    AbstractFactory <|-- ConcreteFactory : Implements
    AbstractProduct <|-- ConcreteProduct : Implements
```

### **Struktur Kelas Abstract Factory Pattern**

Dalam implementasi ini, Abstract Factory Pattern digunakan untuk membuat komponen UI untuk sistem operasi yang berbeda.
`Button`, `Checkbox`, dan `TextField` adalah produk abstrak, sementara `WindowsButton`, `WindowsCheckbox`,
`WindowsTextField`, `MacOSButton`, `MacOSCheckbox`, dan `MacOSTextField` adalah produk konkret. `GUIFactory` adalah
pabrik abstrak, dan `WindowsFactory` serta `MacOSFactory` adalah pabrik konkret.

```mermaid
classDiagram
direction TB
    class Button {
        +render() void
        +onClick() void
    }

    class Checkbox {
        +render() void
        +toggle() void
    }

    class TextField {
        +render() void
        +getText() void
    }

    class WindowsButton {
        +render() void
        +onClick() void
    }

    class WindowsCheckbox {
        -boolean checked
        +render() void
        +toggle() void
    }

    class WindowsTextField {
        +render() void
        +getText() void
    }

    class MacOSButton {
        +render() void
        +onClick() void
    }

    class MacOSCheckbox {
        -boolean checked
        +render() void
        +toggle() void
    }

    class MacOSTextField {
        +render() void
        +getText() void
    }

    class GUIFactory {
        +createButton() Button
        +createCheckbox() Checkbox
        +createTextField() TextField
    }

    class WindowsFactory {
        +createButton() Button
        +createCheckbox() Checkbox
        +createTextField() TextField
    }

    class MacOSFactory {
        +createButton() Button
        +createCheckbox() Checkbox
        +createTextField() TextField
    }

    class Application {
        -Button button
        -Checkbox checkbox
        -TextField textField
        +createUI() void
        +simulateUserInteraction() void
    }
		
		<<interface>> Button
		<<interface>> Checkbox
		<<interface>> TextField
		<<interface>> GUIFactory

    Button <|-- WindowsButton
    Button <|-- MacOSButton
    Checkbox <|-- WindowsCheckbox
    Checkbox <|-- MacOSCheckbox
    TextField <|-- WindowsTextField
    TextField <|-- MacOSTextField
    GUIFactory <|-- WindowsFactory
    GUIFactory <|-- MacOSFactory
    Application --> GUIFactory
    Application --> Button
    Application --> Checkbox
    Application --> TextField
```

Dalam implementasinya, `Application` menggunakan `GUIFactory` untuk membuat komponen UI. Metode `createUI`
menginisialisasi komponen, dan metode `simulateUserInteraction` mensimulasikan interaksi pengguna dengan komponen UI.
Pendekatan ini memungkinkan aplikasi dikonfigurasi dengan berbagai keluarga komponen UI tanpa mengubah kodenya.