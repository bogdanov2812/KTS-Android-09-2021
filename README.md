# Выпускной проект по курсу "Начинающий Android-разработчик" от студии KTS
### Использованные технологии:
+ Платформа - Android
+ Язык - Kotlin
+ Авторизация - AppAuth for Android
+ Архитектура – MVVM
+ Сеть - Retrofit + OkHttp
+ БД - Room
+ DI - Koin
+ Архитектура - Clean Architecture
+ VCS - Git
## Основные функции:
### Приветственный экран
+ Реализован при помощи ViewPager2
+ Отображается только при первом входе в приложение

![Alt text](https://github.com/bogdanov2812/Screenshots/blob/master/Mobile_apps/KTS/Splash1.png)
![Alt text](https://github.com/bogdanov2812/Screenshots/blob/master/Mobile_apps/KTS/Splash2.png)
![Alt text](https://github.com/bogdanov2812/Screenshots/blob/master/Mobile_apps/KTS/Splash3.png)

### Вход в приложение
![Alt text](https://github.com/bogdanov2812/Screenshots/blob/master/Mobile_apps/KTS/Login.png)

### Авторизация
Запрос доступа к функциям изменения аккаунта через приложение

![Alt text](https://github.com/bogdanov2812/Screenshots/blob/master/Mobile_apps/KTS/Auth.png)

### Список тренировок
+ Реализован с помощью RecyclerView
+ Есть функция кэширования

![Alt text](https://github.com/bogdanov2812/Screenshots/blob/master/Mobile_apps/KTS/Workouts.png)

### Добавление тренировки
Выбор даты тренировки реализован с помощью DatePicker и TimePicker

![Alt text](https://github.com/bogdanov2812/Screenshots/blob/master/Mobile_apps/KTS/Add_workout.png)

### Профиль пользователя
+ Работа с аватарками пользователя при помощи Glide
+ Данные пользователя также кэшируются и доступные без подключения к интернету

![Alt text](https://github.com/bogdanov2812/Screenshots/blob/master/Mobile_apps/KTS/Profile.png)

### Редактирование профиля
Реализован кастомный AlertDialog

![Alt text](https://github.com/bogdanov2812/Screenshots/blob/master/Mobile_apps/KTS/Edit_profile.png)

### Выход из аккаунта
При выходе очищаются все закэшированные данные и происходит переход на экран входа

![Alt text](https://github.com/bogdanov2812/Screenshots/blob/master/Mobile_apps/KTS/Logout.png)
