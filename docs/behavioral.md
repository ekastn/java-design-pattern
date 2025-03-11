## **Strategy Pattern**

Strategy Pattern adalah sebuah pola desain yang memungkinkan Anda untuk mendefinisikan sekumpulan algoritma, mengemas
masing-masing algoritma tersebut ke dalam kelas terpisah, dan membuatnya dapat dipertukarkan. Pola ini memungkinkan
algoritma untuk bervariasi secara independen dari klien yang menggunakannya.

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

Dalam proyek ini, Strategy Pattern digunakan untuk mengimplementasikan berbagai strategi serangan yang dapat digunakan
oleh karakter hero. Setiap strategi serangan dienkapsulasi dalam kelas terpisah, seperti`MeleeAttack`,`MagicAttack`, dan
`StealthAttack`.

```mermaid
classDiagram
direction TB
    class Hero {
        +setAttackStrategy(AttackStrategy strategy) void
        +performAttack(Character target) void
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

`Hero`memiliki atribut`AttackStrategy`yang dapat diatur menggunakan metode`setAttackStrategy()`. Ketika hero menyerang,
metode`performAttack()`akan memanggil strategi serangan yang sedang aktif. Ini memungkinkan hero untuk memiliki berbagai
gaya bertarung yang dapat diubah sesuai kebutuhan

---

## **Iterator Pattern**

Iterator Pattern digunakan untuk menyediakan cara untuk mengakses elemen dalam koleksi secara berurutan tanpa harus
mengetahui detail implementasi dari koleksi tersebut.

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

Iterator Pattern digunakan untuk memungkinkan eksplorasi ruangan dalam dungeon secara berurutan. `RoomIterator`
bertanggung jawab untuk mengelola iterasi melalui list `Room` di dalam `Dungeon`. `Dungeon` sendiri bertindak sebagai
koleksi yang menggunakan iterator untuk menjelajahi ruangan satu per satu.

```mermaid
classDiagram
direction TB

    class Dungeon {
        -RoomIterator iterator
        +exploreNextRoom() void
        +isComplete() boolean
    }

    class RoomIterator {
        -List~Room~ rooms
        -int index
        +hasNext() boolean
        +next() Room
    }

    class Room {
        -String description
        +explore(Player player) void
    }

    Dungeon --> RoomIterator : uses
    RoomIterator --> Room : iterates over
```

Dungeon memiliki atribut `RoomIterator`, yang digunakan untuk menjelajahi setiap ruangan. Metode `exploreNextRoom()`
akan mengambil ruangan berikutnya dari iterator, kemudian menjalankan metode `explore()` pada ruangan tersebut.
`isComplete()` akan mengecek apakah masih ada ruangan yang tersisa untuk dijelajahi. Dengan pola ini, traversal dungeon
menjadi lebih fleksibel dan dapat dikelola dengan baik.

---

## **Observer Pattern**

Observer Pattern digunakan untuk menghubungkan sekumpulan objek sehingga ketika satu objek berubah, objek lain yang
bergantung padanya akan diberi tahu secara otomatis. Pola ini cocok untuk menangani notifikasi dalam game, seperti
pemberitahuan saat pemain menerima damage, menemukan item, atau menjelajahi ruangan baru.

```mermaid
classDiagram
    class Subject {
        +attach(Observer o) void
        +detach(Observer o) void
        +notifyObservers() void
    }

    class ConcreteSubject {
        -List~Observer~ observers
        +attach(Observer o) void
        +detach(Observer o) void
        +notifyObservers() void
        +getState() : Object
        +setState(Object state) void
    }

    class Observer {
        +update() void
    }

    class ConcreteObserver {
        +update() void
    }

    <<interface>> Observer
    Subject <|-- ConcreteSubject
    Observer <|.. ConcreteObserver
    ConcreteSubject --> Observer : notifies
```

### **Struktur Kelas Observer Pattern**

Observer Pattern digunakan untuk menangani berbagai notifikasi dalam game. `EventManager` bertindak sebagai **subject**
yang mengelola daftar observer. Saat sebuah peristiwa terjadi, `EventManager` akan memberi tahu semua observer yang
terdaftar. Beberapa observer yang digunakan adalah `LoggingListener`, `DungeonProgressListener`, dan
`GameStateListener`.

```mermaid
classDiagram
direction TB

    class EventManager {
        -List~EventListener~ listeners
        +subscribe(EventListener listener) void
        +unsubscribe(EventListener listener) void
        +notify(EventType type, String message) void
    }

    class EventListener {
        +update(EventType type, String message) void
    }

    class LoggingListener {
        +update(EventType type, String message) void
    }

    class DungeonProgressListener {
        +update(EventType type, String message) void
    }

    class GameStateListener {
        +update(EventType type, String message) void
    }

    class EventType {
        <<enumeration>>
        ATTACK_MELEE
        ATTACK_MAGIC
        CHARACTER_HURT
        POTION_USED
        ROOM_EXPLORED
        DUNGEON_PROGRESS
        PLAYER_DEAD
    }

    EventManager --> EventListener : manages
    EventListener <|.. LoggingListener
    EventListener <|.. DungeonProgressListener
    EventListener <|.. GameStateListener
    EventManager --> EventType : uses
```

`EventManager` mengelola daftar observer dan menyediakan metode `subscribe()`, `unsubscribe()`, serta `notify()`.
`LoggingListener` bertanggung jawab untuk mencetak log peristiwa ke terminal dengan efek jeda untuk meningkatkan pacing
dari game. `DungeonProgressListener` digunakan untuk melacak perkembangan eksplorasi dungeon, sementara
`GameStateListener` menangani perubahan status permainan dan memperbarui tampilan saat state berubah.

---

## **Command Pattern**

Command Pattern digunakan untuk mengenkapsulasi permintaan sebagai objek, memungkinkan pemisahan antara pengirim
perintah dan eksekusinya. Pola ini mempermudah implementasi fitur seperti undo, log aksi, dan eksekusi perintah yang
lebih fleksibel.

```mermaid
classDiagram
    class Command {
        +execute() void
    }

    class ConcreteCommandA {
        +execute() void
    }

    class ConcreteCommandB {
        +execute() void
    }

    class Invoker {
        +setCommand(Command c) void
        +invoke() void
    }

    <<interface>> Command
    Command <|.. ConcreteCommandA
    Command <|.. ConcreteCommandB
    Invoker --> Command : executes
```

### **Struktur Kelas Command Pattern**

Command Pattern digunakan untuk mengelola aksi dalam sistem pertarungan. `CombatCommand` bertindak sebagai antarmuka
untuk semua perintah, sementara `AttackCommand` dan `UsePotionCommand` adalah implementasi spesifik yang menangani
serangan dan penggunaan potion dalam pertempuran.

```mermaid
classDiagram
direction TB

    class CombatCommand {
        +execute(Character attacker, Character target) void
    }

    class AttackCommand {
        +execute(Character attacker, Character target) void
    }

    class UsePotionCommand {
        -Hero hero
        +execute(Character attacker, Character target) void
    }

    class CombatManager {
        +startCombat(Hero player, Enemy enemy) void
    }

    CombatCommand <|.. AttackCommand
    CombatCommand <|.. UsePotionCommand
    UsePotionCommand --> Hero : uses
    CombatManager --> CombatCommand : executes
```

Dalam implementasinya, `CombatManager` menerima input pemain dan mengeksekusi perintah yang sesuai. `AttackCommand`
meminta karakter untuk menyerang lawan, sementara `UsePotionCommand` memungkinkan hero menggunakan potion dari
inventorinya.

---

## **State Pattern**

State Pattern digunakan untuk mengizinkan sebuah objek mengubah perilakunya ketika state internalnya berubah. Pola ini
memungkinkan setiap state memiliki implementasi yang berbeda tanpa harus menggunakan banyak pernyataan pengkondisian.

```mermaid
classDiagram
    class Context {
        +setState(State s) void
        +execute() void
    }

    class State {
        +execute(Context c) void
    }

    class ConcreteStateA {
        +execute(Context c) void
    }

    class ConcreteStateB {
        +execute(Context c) void
    }

    <<interface>> State
    State <|.. ConcreteStateA
    State <|.. ConcreteStateB
    Context --> State : has
```

### **Struktur Kelas State Pattern**

State Pattern digunakan untuk mengatur status permainan (`GameState`) dan perilaku musuh (`EnemyState`). `GameState`
mengontrol perubahan status dalam permainan, seperti memulai, menjelajah dungeon, bertarung, dan menyelesaikan
permainan. `EnemyState` mengontrol perilaku musuh berdasarkan kondisinya, seperti agresif, defensif, atau melarikan
diri.

```mermaid
classDiagram
direction TB

    %% Game State
    class Game {
        -GameState gameState
        +setGameState(GameState state) void
    }

    class GameState {
        +execute(Game game) void
    }

    class GameStartUpState {
        +execute(Game game) void
    }

    class ExploringState {
        +execute(Game game) void
    }

    class FightingState {
        +execute(Game game) void
    }

    class GameOverState {
        +execute(Game game) void
    }

    GameState <|.. GameStartUpState
    GameState <|.. ExploringState
    GameState <|.. FightingState
    GameState <|.. GameOverState
    Game --> GameState : has

    %% Enemy State
    class Enemy {
        -EnemyState state
        +setState(EnemyState state) void
    }

    class EnemyState {
        +execute(Enemy enemy, Hero hero) void
    }

    class AggressiveState {
        +execute(Enemy enemy, Hero hero) void
    }

    class DefensiveState {
        +execute(Enemy enemy, Hero hero) void
    }

    class FleeingState {
        +execute(Enemy enemy, Hero hero) void
    }

    EnemyState <|.. AggressiveState
    EnemyState <|.. DefensiveState
    EnemyState <|.. FleeingState
    Enemy --> EnemyState : has
```

Dalam implementasinya, `Game` memiliki atribut `gameState`, yang bisa berubah saat permainan berlangsung. Metode
`setGameState()` digunakan untuk mengubah state saat pemain berpindah dari satu fase permainan ke fase lainnya. `Enemy`
memiliki atribut `state`, yang menentukan bagaimana musuh bertindak dalam pertempuran. Jika kesehatan musuh rendah,
state dapat berubah dari `AggressiveState` ke `DefensiveState` atau `FleeingState`.

---

## **Chain of Responsibility Pattern**

Chain of Responsibility Pattern adalah pola desain yang memungkinkan permintaan ditangani oleh serangkaian objek
penerima. Pola ini berguna untuk menghindari pengirim permintaan mengetahui penerima yang tepat, sehingga memungkinkan
penerima untuk diproses secara dinamis.

```mermaid
classDiagram
    class Handler {
        <<interface>>
        +handleRequest()
    }

    class ConcreteHandler1 {
        +handleRequest()
    }

    class ConcreteHandler2 {
        +handleRequest()
    }

    Handler <|-- ConcreteHandler1 : Implements
    Handler <|-- ConcreteHandler2 : Implements
    Handler --> Handler : successor
```

### **Struktur Kelas Chain of Responsibility Pattern**

Dalam implementasi ini, Chain of Responsibility Pattern digunakan untuk menangani permintaan pembelian berdasarkan
jumlahnya. `PurchaseRequest` adalah kelas permintaan, `Approver` adalah kelas abstrak untuk penangan permintaan, dan
`Manager`, `Director`, `VP`, serta `CEO` adalah kelas konkret yang menangani permintaan berdasarkan batasan jumlah yang
telah ditentukan.

```mermaid
classDiagram
direction TB
    class PurchaseRequest {
        +getId() int
        +getAmount() double
        +getPurpose() String
    }

    class Approver {
        -Approver nextApprover
        -String approverName
        +setNext(Approver nextApprover) void
        +processRequest(PurchaseRequest request) void
    }

    class Manager {
        +processRequest(PurchaseRequest request) void
    }

    class Director {
        +processRequest(PurchaseRequest request) void
    }

    class VP {
        +processRequest(PurchaseRequest request) void
    }

    class CEO {
        +processRequest(PurchaseRequest request) void
    }

    class ChainOfResponsibilityDemo {
        +main(String[] args) void
    }

    Approver <|-- Manager
    Approver <|-- Director
    Approver <|-- VP
    Approver <|-- CEO
    ChainOfResponsibilityDemo --> Approver
    ChainOfResponsibilityDemo --> PurchaseRequest
```

Dalam implementasinya, `ChainOfResponsibilityDemo` membuat rantai penanganan permintaan dengan menghubungkan `Manager`,
`Director`, `VP`, dan `CEO`. Setiap permintaan pembelian diproses oleh penangan yang sesuai berdasarkan jumlahnya. Jika
penangan tidak dapat menyetujui permintaan, permintaan akan diteruskan ke penangan berikutnya dalam rantai.

---

## **Mediator Pattern**

Mediator Pattern adalah pola desain yang memungkinkan komunikasi antar objek dalam sistem melalui objek mediator. Pola
ini berguna untuk mengurangi kompleksitas komunikasi langsung antar objek dan memfasilitasi pengelolaan interaksi antar
objek.

```mermaid
classDiagram
    class Mediator

    class ConcreteMediator

    class Colleague {
        <<interface>>
    }

    class ConcreteColleague

    Mediator <|-- ConcreteMediator : Implements
    Colleague <|-- ConcreteColleague : Implements

    Mediator <-- Colleague : informs
    ConcreteMediator --> ConcreteColleague : updates
```

### **Struktur Kelas Mediator Pattern**

Dalam implementasi ini, Mediator Pattern digunakan untuk mengelola komunikasi dalam chat room. `ChatMediator` adalah
antarmuka mediator, `ChatRoom` adalah mediator konkret, dan `User` adalah kelas abstrak untuk pengguna. `RegularUser`,
`PremiumUser`, dan `AdminUser` adalah kelas konkret yang mewakili berbagai jenis pengguna dalam ruang obrolan.

```mermaid
classDiagram
direction TB
    class ChatMediator {
        +sendMessage(String message, User user) void
        +addUser(User user) void
    }

    class ChatRoom {
				-List~User~ users
        +sendMessage(String message, User user) void
        +addUser(User user) void
    }

    class User {
				-ChatMediator mediator
        +send(String message) void
        +receive(String message, String from) void
    }

    class RegularUser {
        +send(String message) void
        +receive(String message, String from) void
    }

    class PremiumUser {
        +send(String message) void
        +receive(String message, String from) void
        +sendPriorityMessage(String message) void
    }

    class AdminUser {
        +send(String message) void
        +receive(String message, String from) void
        +sendSystemMessage(String message) void
    }

    ChatMediator <|.. ChatRoom
    User <|-- RegularUser
    User <|-- PremiumUser
    User <|-- AdminUser
    ChatMediator --> User
```

Dalam implementasinya, `ChatRoom` mengelola daftar pengguna dan mendistribusikan pesan yang diterima dari satu pengguna
ke semua pengguna lain dalam ruang obrolan. `RegularUser`, `PremiumUser`, dan `AdminUser` memiliki metode `send` dan
`receive` untuk mengirim dan menerima pesan. `PremiumUser` dan `AdminUser` memiliki metode tambahan untuk mengirim pesan
prioritas dan pesan sistem. Pendekatan ini memisahkan logika komunikasi dari pengguna individu dan memusatkannya dalam
mediator.

---

## **Visitor Pattern**

Visitor Pattern adalah pola desain yang memungkinkan penambahkan operasi ke objek tanpa mengubah struktur objek
tersebut. Pola ini berguna untuk memisahkan algoritma dari struktur objek yang dioperasikan.

```mermaid
classDiagram
    class Visitor {
        <<interface>>
        +visitElementA(in a : ConcreteElementA)
        +visitElementB(in b : ConcreteElementB)
    }

    class ConcreteVisitor {
        +visitElementA(in a : ConcreteElementA)
        +visitElementB(in b : ConcreteElementB)
    }

    class Element {
        <<interface>>
        +accept(in v : Visitor)
    }

    class ConcreteElementA {
        +accept(in v : Visitor)
    }

    class ConcreteElementB {
        +accept(in v : Visitor)
    }

    class Client

		direction RL
    Client --> Element
    Visitor <|-- ConcreteVisitor
    Element <|-- ConcreteElementA
    Element <|-- ConcreteElementB
    Element --> Visitor : accept
    Visitor <.. Client
```

### **Struktur Kelas Visitor Pattern**

Dalam implementasi ini, Visitor Pattern digunakan untuk menghitung area, menghitung perimeter, dan mengekspor bentuk ke
XML. `Visitor` adalah antarmuka untuk pengunjung, `AreaCalculator`, `PerimeterCalculator`, dan `XMLExportVisitor` adalah
pengunjung konkret. `Shape` adalah antarmuka untuk bentuk, dan `Circle`, `Rectangle`, serta `Triangle` adalah bentuk
konkret.

```mermaid
classDiagram
direction TB
    class Visitor {
        +visit(Circle circle) void
        +visit(Rectangle rectangle) void
        +visit(Triangle triangle) void
        +getResult() String
    }

    class AreaCalculator {
        +visit(Circle circle) void
        +visit(Rectangle rectangle) void
        +visit(Triangle triangle) void
        +getResult() String
    }

    class PerimeterCalculator {
        +visit(Circle circle) void
        +visit(Rectangle rectangle) void
        +visit(Triangle triangle) void
        +getResult() String
    }

    class XMLExportVisitor {
        +visit(Circle circle) void
        +visit(Rectangle rectangle) void
        +visit(Triangle triangle) void
        +getResult() String
    }

    class Shape {
        +accept(Visitor visitor) void
    }

    class Circle {
        +accept(Visitor visitor) void
    }

    class Rectangle {
        +accept(Visitor visitor) void
    }

    class Triangle {
        +accept(Visitor visitor) void
    }

    class Drawing {
        +add(Shape shape) void
        +accept(Visitor visitor) void
    }

    Visitor <|.. AreaCalculator
    Visitor <|.. PerimeterCalculator
    Visitor <|.. XMLExportVisitor
    Shape <|-- Circle
    Shape <|-- Rectangle
    Shape <|-- Triangle
    Drawing --> Shape : contains
    Shape --> Visitor : accepts
```

Dalam implementasinya, `Drawing` menyimpan daftar `Shape`, yang bisa berupa `Circle`, `Rectangle`, atau `Triangle`.
`AreaCalculator`, `PerimeterCalculator`, dan `XMLExportVisitor` mengunjungi setiap bentuk untuk menghitung area,
menghitung perimeter, dan mengekspor ke XML. Pendekatan ini memungkinkan penambahan operasi baru tanpa mengubah struktur
kelasnya.

---

## **Template Method Pattern**

Template Method Pattern adalah pola desain yang mendefinisikan kerangka algoritma dalam metode di kelas dasar, tetapi
memungkinkan subclass untuk mengisi langkah-langkah tertentu dari algoritma tersebut. Pola ini berguna untuk
mendefinisikan struktur umum dari algoritma sambil membiarkan detail implementasi bervariasi.

```mermaid
classDiagram
    class AbstractClass {
        +templateMethod() 
        #subMethod() 
    }

    class ConcreteClass {
        +subMethos() 
    }

    AbstractClass <|-- ConcreteClass
```

### **Struktur Kelas Template Method Pattern**

Dalam implementasi ini, Template Method Pattern digunakan untuk mendefinisikan perilaku karakter dalam permainan.
`GameCharacterAI` adalah kelas abstrak yang mendefinisikan kerangka algoritma untuk siklus perilaku karakter.
`AggressiveEnemyAI`, `SupportCharacterAI`, dan `StealthCharacterAI` adalah kelas konkret yang mengisi langkah-langkah
spesifik dari algoritma tersebut.

```mermaid
classDiagram
direction TB
    class GameCharacterAI {
        +executeTurn() void
        #evaluateThreats() void
        #choosePrimaryTarget() void
        #selectAction() void
        #performSpecialAbility() void
    }

    class AggressiveEnemyAI {
        +evaluateThreats() void
        +choosePrimaryTarget() void
        +selectAction() void
        +performSpecialAbility() void
    }

    class SupportCharacterAI {
        +evaluateThreats() void
        +choosePrimaryTarget() void
        +selectAction() void
    }

    class StealthCharacterAI {
        +evaluateThreats() void
        +choosePrimaryTarget() void
        +selectAction() void
        +performSpecialAbility() void
    }

    GameCharacterAI <|-- AggressiveEnemyAI
    GameCharacterAI <|-- SupportCharacterAI
    GameCharacterAI <|-- StealthCharacterAI
```

Dalam implementasinya, `GameCharacterAI` mendefinisikan metode `executeTurn` yang merupakan kerangka algoritma untuk
siklus perilaku karakter. Metode ini memanggil beberapa metode abstrak seperti `evaluateThreats`, `choosePrimaryTarget`,
dan `selectAction` yang diimplementasikan oleh subclass. `AggressiveEnemyAI`, `SupportCharacterAI`, dan
`StealthCharacterAI` mengisi langkah-langkah spesifik dari algoritma tersebut sesuai dengan perilaku karakter
masing-masing.

---
## **Memento Pattern**

Memento Pattern adalah pola desain yang memungkinkan Anda untuk menangkap dan mengeksternalisasi keadaan internal suatu
objek sehingga dapat dipulihkan nanti tanpa melanggar enkapsulasi. Pola ini berguna untuk mengimplementasikan fungsi
undo dan redo.

```mermaid
classDiagram
    class Memento {
        -state
    }

    class Originator {
        -state
        +setMemento(in m : Memento)
        +createMemento()
    }

    class Caretaker

    Originator --> Memento : creates/restores
    Caretaker --> Memento : stores
```

### **Struktur Kelas Memento Pattern**

Dalam implementasi ini, Memento Pattern digunakan untuk mengelola keadaan editor teks. `TextEditor` adalah kelas
originator yang membuat memento untuk menyimpan keadaannya. `EditorMemento` adalah kelas memento yang menyimpan keadaan.
`History` adalah kelas caretaker yang mengelola memento untuk operasi undo dan redo.

```mermaid
classDiagram
direction TB
    class TextEditor {
        +write(String text) void
        +delete() void
        +selectText(String text) void
        +moveCursor(int position) void
        +displayState() void
        +save() EditorMemento
        +restore(EditorMemento memento) void
    }

    class EditorMemento {
        +getSnapshotInfo() String
    }

    class History {
        +save(TextEditor editor) void
        +undo(TextEditor editor) void
        +redo(TextEditor editor) void
        +showHistory() void
    }

    class MementoPatternDemo {
        +main(String[] args) void
    }

    TextEditor --> EditorMemento : creates
    History --> EditorMemento : manages
    MementoPatternDemo --> TextEditor
    MementoPatternDemo --> History
```

Dalam implementasinya, `TextEditor` memiliki metode untuk menulis, menghapus, memilih teks, dan memindahkan kursor. Ia
dapat membuat dan memulihkan memento untuk menyimpan dan mengembalikan keadaannya. `History` mengelola memento untuk
operasi undo dan redo. `MementoPatternDemo` mendemonstrasikan penggunaan Memento Pattern dengan melakukan berbagai
operasi pada editor teks dan mengelola keadaannya menggunakan history.