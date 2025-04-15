# 📨 Message Cluster (Producer / Consumer / RabbitMQ on Kubernetes)

Цей проєкт демонструє мікросервісну архітектуру на базі **Spring Boot**, **RabbitMQ** та **Kubernetes** (Minikube). Складається з трьох сервісів:

- 🟢 **Producer** – надсилає випадкові повідомлення до черги щосекунди
- 🕣 **Consumer** – авторизує користувача та відображає повідомлення
- 🐰 **RabbitMQ** – брокер повідомлень

---

## 🔧 Що потрібно встановити

### 📌 1. Java

- Java 21 (для білду Gradle)
- Java 24 (лише для Docker-образу)

> Рекомендується використовувати [SDKMAN](https://sdkman.io/)

```bash
sdk install java 21.0.3-tem
sdk install java 24.0.1-tem
sdk use java 21.0.3-tem
```

---

### 📌 2. Інші інструменти

- Gradle (або використай `./gradlew`)
- Docker
- Minikube (локальний Kubernetes):
  ```bash
  brew install minikube
  minikube start --driver=docker
  ```
- kubectl:
  ```bash
  brew install kubectl
  ```

---

## 🚀 Як запустити кластер

```bash
./run-cluster.sh
```

Скрипт:

1. Збирає JAR-файли `producer` та `consumer`
2. Створює Docker-образи
3. Деплоїть `rabbitmq`, `producer`, `consumer` у Kubernetes
4. Відкриває браузер з веб-інтерфейсом `consumer`

---

## 🌐 Доступ до інтерфейсу

Після запуску скрипта автоматично відкриється посилання:

```
http://localhost:<порт>
```

Введи:

- **Логін:** `user`
- **Пароль:** `password`

---

## 🔄 Що відбувається

- Producer надсилає випадкові повідомлення до RabbitMQ щосекунди
- Consumer читає з черги `demo-queue` та показує ці повідомлення після авторизації

---

## 🧪 API для тестування

- Перегляд повідомлень у форматі JSON:
  ```
  GET /api/messages
  ```
  приклад:
  ```bash
  curl http://localhost:<порт>/api/messages
  ```

- Надіслати повідомлення вручну:
  ```bash
  curl -X POST "http://<MINIKUBE_IP>:<PRODUCER_PORT>/api/messages?text=ManualTest"
  ```

---

## 📦 Kubernetes YAML-файли

У папці `k8s/`:

- `rabbitmq.yaml` – брокер RabbitMQ + UI (порт 15672)
- `producer-deployment.yaml` – сервіс генерації повідомлень
- `consumer-deployment.yaml` – веб-інтерфейс + читач черги

---

## 📂 Структура проєкту

```
message-cluster/
├── consumer/
│   ├── src/
│   ├── Dockerfile
│   └── build.gradle
├── producer/
│   ├── src/
│   ├── Dockerfile
│   └── build.gradle
├── k8s/
│   ├── rabbitmq.yaml
│   ├── producer-deployment.yaml
│   └── consumer-deployment.yaml
├── run-cluster.sh
└── README.md
```

---

## 🧻 Діагностика

### 🔍 Подивитись статуси pod'ів

```bash
kubectl get pods
```

### 🔍 Логи consumer/producer

```bash
kubectl logs deployment/consumer
kubectl logs deployment/producer
```

### 🔍 RabbitMQ Admin панель

```url
http://localhost:<PORT>
```

Логін/пароль: `guest` / `guest`

---

## 📬 Автор

Створено як демонстраційний кластер для мікросервісної архітектури на Spring Boot 3.2, Java 21/24, Gradle 8.13, RabbitMQ, Kubernetes (Minikube).
