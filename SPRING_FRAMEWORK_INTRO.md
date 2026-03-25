# Spring Framework Introduction

Muc tieu tai lieu:
- Hieu Spring ra doi de giai quyet van de gi
- Nam duoc SOLID trong boi canh Java backend
- Hieu Inversion of Control (IoC)
- Hieu Dependency Injection (DI)
- Phan biet Spring Framework va Spring Boot
- Nhan biet cac design pattern pho bien trong he sinh thai Spring

---

## 1. Problems Spring Solves (Spring giai quyet van de gi?)

Truoc khi co Spring, code Java enterprise thuong gap:

1. Boilerplate qua nhieu
- Cau hinh XML/rang buoc thu cong rat dai
- Tu wiring object bang tay

2. Tight coupling (phu thuoc chat)
- Class A tu tao Class B bang `new`
- Kho test, kho thay the implementation

3. Kho test
- Business logic bi dinh vao infrastructure (DB, message queue, framework code)
- Unit test phu thuoc moi truong that

4. Quan ly transaction, security, lifecycle phuc tap
- Lap lai logic transaction/security o nhieu noi

5. Cau hinh phan tan, khong nhat quan
- Kho bao tri khi ung dung lon dan

Spring giai quyet bang cach:
- Cung cap IoC container de quan ly object
- Inject dependency thay vi tu `new`
- Tach concern (AOP, transaction, security)
- Chuan hoa cau hinh va mo hinh lap trinh

---

## 2. SOLID Principles (trong boi canh Spring)

### S - Single Responsibility Principle
Mot class nen co 1 ly do de thay doi.

Vi du:
- `UserService`: xu ly nghiep vu user
- `UserRepository`: truy cap du lieu user
Khong tron nghiep vu va persistence vao cung 1 class.

### O - Open/Closed Principle
Mo rong duoc, nhung khong sua code cu qua nhieu.

Vi du:
- Dinh nghia interface `PaymentService`
- Them `MomoPaymentService`, `VnPayPaymentService` ma khong sua controller

### L - Liskov Substitution Principle
Class con thay duoc class cha ma khong lam sai hanh vi.

Vi du:
- `JpaUserRepository` co the thay `UserRepository` interface
- Consumer van chay dung

### I - Interface Segregation Principle
Khong ep class phai implement method no khong can.

Vi du:
- Tach `ReadableRepository<T>` va `WritableRepository<T>`
- Service chi can doc thi inject interface doc

### D - Dependency Inversion Principle
Phu thuoc vao abstraction (interface), khong phu thuoc implementation.

Vi du:
- `OrderService` phu thuoc `PaymentGateway` (interface)
- Spring inject implementation thuc te khi runtime

---

## 3. Inversion of Control (IoC)

IoC = dao nguoc quyen kiem soat viec tao va quan ly object.

Khong dung IoC:
```java
public class OrderService {
    private final EmailSender sender = new SmtpEmailSender();
}
```

Co IoC:
```java
@Service
public class OrderService {
    private final EmailSender sender;

    public OrderService(EmailSender sender) {
        this.sender = sender;
    }
}
```

Y nghia:
- `OrderService` khong tu tao `SmtpEmailSender`
- Container cua Spring tao bean va cap vao

Loi ich:
- Giam coupling
- De test (inject mock)
- De mo rong

---

## 4. Dependency Injection (DI)

DI la cach cu the de thuc hien IoC: dependency duoc "tiem" vao class.

Cac kieu DI:

1. Constructor Injection (khuyen nghi)
```java
@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }
}
```

2. Setter Injection
```java
@Service
public class UserService {
    private UserRepository repo;

    public void setRepo(UserRepository repo) {
        this.repo = repo;
    }
}
```

3. Field Injection (khong khuyen nghi)
```java
@Autowired
private UserRepository repo;
```

Tai sao constructor injection tot hon:
- Bat buoc dependency ngay khi tao object
- De viet test
- De giu object immutable

---

## 5. Spring Framework vs Spring Boot

### Spring Framework
- La nen tang goc (core ecosystem)
- Cho ban toan quyen cau hinh
- Co the can nhieu setup hon

### Spring Boot
- Xay tren Spring Framework
- "Convention over configuration"
- Auto-configuration
- Starter dependencies (`spring-boot-starter-web`, ...)
- Embedded server (Tomcat/Jetty)
- Actuator, production-ready features

Tom tat:
- Spring Framework: linh hoat cao, setup thu cong nhieu hon
- Spring Boot: phat trien nhanh, it boilerplate, la lua chon pho bien cho app moi

---

## 6. Design Patterns trong Spring

### 1) Singleton
- Mac dinh moi bean Spring la singleton scope
- 1 bean duoc tao va tai su dung trong container

### 2) Factory
- IoC container dong vai tro factory tao bean
- `BeanFactory`, `ApplicationContext`

### 3) Proxy
- Dung cho AOP, transaction, security
- Vi du `@Transactional` thuong duoc ap dung thong qua proxy

### 4) Template Method
- Spring cung cap cac template class
- Vi du: `JdbcTemplate`, `RestTemplate`
- Ban chi truyen phan logic can tuy bien

### 5) Observer (event-driven)
- `ApplicationEventPublisher` va listener
- Tach nhe coupling giua thanh phan phat su kien va xu ly

### 6) MVC Pattern
- Phan tach `Model`, `View`, `Controller`
- Trong REST, Controller tiep nhan request va goi service

---

## 7. Mini mental model de nho nhanh

1. Spring giai quyet: coupling cao + cau hinh phuc tap + kho test
2. SOLID la nguyen tac thiet ke de code ben vung
3. IoC: container quan ly object thay ban
4. DI: cach container cung cap dependency cho class
5. Spring Boot: cach nhanh nhat de xay app Spring hien dai
6. Pattern trong Spring: Singleton, Factory, Proxy, Template, Observer, MVC

---

## 8. Goi y hoc tiep theo

1. Tao project Spring Boot dau tien
- Them `spring-boot-starter-web`
- Viet endpoint `/hello`

2. Them layer ro rang
- `controller` -> `service` -> `repository`

3. Thu DI + test
- Inject interface
- Viet unit test dung mock implementation

4. Thu `@Transactional` va quan sat hanh vi rollback

5. Thu event
- Publish event sau khi tao order
- Listener gui email/thong bao
