# ShoppingCart

ShoppingCart — это демо приложение на основе **Jetpack Compose** для управления корзиной покупок и отправки заказов на
сервер. Проект демонстрирует современную архитектуру Android-приложений с использованием **Hilt** для внедрения
зависимостей, **Retrofit** для сетевого взаимодействия, **Detekt** для анализа качества кода и **Kotlin Coroutines** для
асинхронных операций.

---

## Функциональность

- **Корзина покупок**: Управление товарами в корзине с возможностью увеличения/уменьшения количества.
- **Отправка заказа**: Отправка тестового заказа на сервер с помощью API.
- **Интеграция с API**: Взаимодействие с бэкендом через Retrofit.
- **Интеграция Hilt**: Использование Hilt для внедрения зависимостей.
- **Анализ кода**: Интеграция Detekt для проверки качества кода.
- **Тестирование**: Поддержка тестов с использованием JUnit и Espresso.

---

## Стек технологий

- **Язык**: Kotlin (JVM 21)
- **UI**: Jetpack Compose
- **DI**: Dagger Hilt
- **Сетевое взаимодействие**: Retrofit, OkHttp
- **Анализ кода**: Detekt
- **Загрузка изображений**: Coil
- **Асинхронные операции**: Kotlin Coroutines
- **Тестирование**: JUnit, Espresso

---

## Установка и запуск

1. Склонируйте репозиторий:

   ```bash
    git clone https://github.com/username/shoppingcart.git
   ```
2. Перейдите в папку проекта:

   ```bash
    cd shoppingcart
   ```
3. Откройте проект в Android Studio.
4. Убедитесь, что у вас установлена JDK 21.
5. Запустите проект на эмуляторе или реальном устройстве.

---

## Структура проекта

- **MainActivity:** Главная активность, которая запускает экран корзины.
- **CartScreen:** Экран корзины, отображает список товаров, общую стоимость и кнопку отправки заказа.
- **ViewModel**:
    - **CartViewModel**: Управляет состоянием корзины и отправкой заказа.
- **Domain**:
    - **UseCase**: Содержит бизнес-логику приложения (GetCartItemsUseCase, UpdateCartItemUseCase, DeleteCartUseCase,
      SendTestOrderUseCase).
- **Data**:
    - **CartRepository**: Управляет данными корзины.
    - **ApiService**: Интерфейс для взаимодействия с API.
- **DI**:
    - Использование Hilt для внедрения зависимостей через @HiltViewModel и @AndroidEntryPoint.

---

## Анализ кода с Detekt

Для анализа качества кода выполните команду:

   ```bash
    ./gradlew detekt
   ```

Результаты анализа будут сохранены в папке build/reports/detekt.

---

## API Интеграция

Приложение взаимодействует с локальным сервером по адресу http://localhost:8081. Для отправки тестового заказа
используется эндпоинт `/api/send-test-order`.