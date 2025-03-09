## **Composite Pattern**

Composite Pattern digunakan untuk menyusun objek dalam struktur pohon sehingga klien dapat memperlakukan objek
individual dan komposisi objek secara seragam. Pola ini berguna ketika sebuah elemen memiliki bagian-bagian yang dapat
berisi elemen lain dengan hierarki yang sama.

```mermaid
classDiagram
    class Component {
        +operation() void
    }

    class Leaf {
        +operation() void
    }

    class Composite {
        -List<Component> children
        +operation() void
        +add(Component c) void
        +remove(Component c) void
    }

    <<interface>> Component
    Component <|.. Leaf
    Component <|.. Composite
    Composite --> Component : contains
```

### **Struktur Kelas Composite Pattern**

Composite Pattern digunakan untuk mengelola objek dalam ruangan. `RoomObject` bertindak sebagai komponen dasar,
sementara `Enemy`, `Potion`, dan `Trap` adalah objek konkret yang dapat berinteraksi dengan pemain. `Room` berfungsi
sebagai komposit yang dapat menyimpan banyak `RoomObject`.

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

    class Room {
        -List~RoomObject~ objects
        +addObject(RoomObject object) void
        +explore(Hero hero) void
    }

		<<interface>> RoomObject

    RoomObject <|.. Enemy
    RoomObject <|.. Potion
    RoomObject <|.. Trap
    Room --> RoomObject : contains
```

Dalam implementasinya, `Room` menyimpan daftar `RoomObject`, yang bisa berupa musuh, potion, atau jebakan. Saat
`explore()` dipanggil, ruangan akan berinteraksi dengan semua objek yang ada di dalamnya. Dengan pendekatan ini,
menambahkan objek baru ke ruangan tidak memerlukan perubahan besar dalam kode.

---

## **Adapter Pattern**

Adapter Pattern adalah pola desain yang digunakan untuk membuat dua antarmuka yang tidak kompatibel bisa bekerja
bersama. Dalam beberapa kasus, ada sebuah kelas yang memiliki cara kerja berbeda dengan antarmuka yang digunakan dalam
sistem. Jika ingin menggunakan kelas tersebut tanpa mengubah strukturnya, adapter dapat digunakan sebagai perantara.
Adapter bertugas menerjemahkan perintah dari satu bentuk ke bentuk lain agar sesuai dengan antarmuka yang diharapkan.

```mermaid
classDiagram
    class Client {
        +operation() void
    }

    class Adapter {
        +operation() void
    }

    class ConcreteAdapter {
        -adaptee: Adaptee
        +operation() void
    }

    class Adaptee {
        +adaptedOperation() void
    }

    Client --> Adapter
    Adapter <|.. ConcreteAdapter
    ConcreteAdapter --> Adaptee : adapts
```

### **Struktur Kelas Adapter Pattern**

Dalam implementasi ini, Adapter Pattern digunakan untuk menghubungkan kontroler klasik dengan sistem permainan yang
hanya mendukung kontroler modern. Kontroler klasik memiliki cara kerja yang tidak sepenuhnya kompatibel dengan sistem
permainan karena menggunakan angka untuk tombol dan hanya memiliki D-pad untuk navigasi. Sedangkan sistem permainan
mengharapkan kontroler dengan tombol bernama, joystick, dan pemicu aksi berbasis tekanan.

Untuk menyelaraskan perbedaan ini, dibuatlah `ClassicControllerAdapter`. Adapter ini menerjemahkan tombol bernama
menjadi angka yang sesuai dengan kontroler klasik. Pergerakan joystick dikonversi menjadi navigasi D-pad dengan
menentukan apakah gerakan lebih dominan ke arah horizontal atau vertikal. Pemicu aksi juga disesuaikan dengan menekan
tombol tertentu jika tekanan yang diberikan memenuhi ambang batas tertentu.

```mermaid
classDiagram
    class ModernController {
        +pressButton(String button) void
        +moveJoystick(int x, int y) void
        +triggerAction(double pressure) void
    }

    class ClassicController {
        +buttonPress(int buttonId) void
        +directionPad(String direction) void
    }

    class ClassicControllerAdapter {
        -ClassicController classicController
        +pressButton(String button) void
        +moveJoystick(int x, int y) void
        +triggerAction(double pressure) void
    }

    class PS5Controller {
        +pressButton(String button) void
        +moveJoystick(int x, int y) void
        +triggerAction(double pressure) void
    }

    class Game {
        -ModernController controller
        +play() void
    }

    ModernController <|.. PS5Controller
    ModernController <|.. ClassicControllerAdapter
    ClassicControllerAdapter --> ClassicController : adapts
    Game --> ModernController : uses
```

Selain kontroler klasik, sistem juga mendukung kontroler modern seperti `PS5Controller`, yang sudah sesuai dengan
antarmuka yang diharapkan tanpa memerlukan adapter. Dengan adanya adapter, berbagai jenis kontroler bisa digunakan tanpa
perlu mengubah struktur permainan. Jika ada kontroler lain yang tidak kompatibel, cukup dibuat adapter yang sesuai agar
dapat digunakan dalam sistem.

---

## **Bridge Pattern**

Bridge Pattern adalah pola desain yang memisahkan abstraksi dari implementasinya sehingga keduanya dapat bervariasi
secara independen. Pola ini berguna untuk mengurangi kompleksitas kode dengan memisahkan antarmuka dari
fungsionalitasnya.

```mermaid
classDiagram
    class Abstraction {
        +operation() void
    }

    class RefinedAbstraction {
        +operation() void
    }

    class Implementor {
        +operationImpl() void
    }

    class ConcreteImplementorA {
        +operationImpl() void
    }

    class ConcreteImplementorB {
        +operationImpl() void
    }

    Abstraction <|-- RefinedAbstraction
    Abstraction --> Implementor
    Implementor <|-- ConcreteImplementorA
    Implementor <|-- ConcreteImplementorB
```

### **Struktur Kelas Bridge Pattern**

Dalam implementasi ini, Bridge Pattern digunakan untuk memisahkan antarmuka remote control dari perangkat yang
dikendalikannya. `RemoteControl` adalah abstraksi dasar, sementara `BasicRemote` dan `AdvancedRemote` adalah abstraksi
yang lebih spesifik. `Device` adalah implementor yang diwakili oleh `TV` dan `Radio`.

```mermaid
classDiagram
direction TB
    class RemoteControl {
        +togglePower() void
        +volumeUp() void
        +volumeDown() void
        +channelUp() void
        +channelDown() void
        +printDeviceStatus() void
    }

    class BasicRemote {
    }

    class AdvancedRemote {
        +mute() void
        +setFavoriteChannel(int channelNumber) void
    }

    class Device {
        +isEnabled() boolean
        +enable() void
        +disable() void
        +getVolume() int
        +setVolume(int volume) void
        +getChannel() int
        +setChannel(int channel) void
        +printStatus() void
    }

    class TV {
    }

    class Radio {
    }

    RemoteControl <|-- BasicRemote
    RemoteControl <|-- AdvancedRemote
    RemoteControl --> Device
    Device <|-- TV
    Device <|-- Radio
```

Dalam implementasinya, `RemoteControl` menyimpan referensi ke `Device`, yang bisa berupa `TV` atau `Radio`.
`BasicRemote` menyediakan kontrol dasar seperti menghidupkan/mematikan perangkat, mengatur volume, dan mengganti
saluran. `AdvancedRemote` menambahkan fitur tambahan seperti mute dan pengaturan saluran favorit. Dengan pendekatan ini,
menambahkan perangkat baru atau remote control baru tidak memerlukan perubahan besar dalam kode.