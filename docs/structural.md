## **Composite Pattern**

Composite Pattern digunakan untuk menyusun objek dalam struktur pohon sehingga klien dapat memperlakukan objek individual dan komposisi objek secara seragam. Pola ini berguna ketika sebuah elemen memiliki bagian-bagian yang dapat berisi elemen lain dengan hierarki yang sama.

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

Composite Pattern digunakan untuk mengelola objek dalam ruangan. `RoomObject` bertindak sebagai komponen dasar, sementara `Enemy`, `Potion`, dan `Trap` adalah objek konkret yang dapat berinteraksi dengan pemain. `Room` berfungsi sebagai komposit yang dapat menyimpan banyak `RoomObject`.

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

Dalam implementasinya, `Room` menyimpan daftar `RoomObject`, yang bisa berupa musuh, potion, atau jebakan. Saat `explore()` dipanggil, ruangan akan berinteraksi dengan semua objek yang ada di dalamnya. Dengan pendekatan ini, menambahkan objek baru ke ruangan tidak memerlukan perubahan besar dalam kode.