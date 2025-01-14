# Используем образ gradle+java для сборки
FROM gradle:jdk17 AS build_k8s_demo

WORKDIR /app

# Копируем gradle файлы
#COPY gradle/ gradle/
COPY build.gradle build.gradle
COPY settings.gradle settings.gradle
#COPY gradlew gradlew

# Копируем исходный код
COPY src/ src/

# Сборка JAR
RUN gradle clean bootJar

# Используем образ JRE для запуска приложения
FROM openjdk:17 AS runtime_k8s_demo

WORKDIR /app

EXPOSE 8080

# Копируем скомпилированный JAR файл
COPY --from=build_k8s_demo /app/build/libs/k8s.jar k8s.jar

# Запускаем приложение, указывая путь к скомпилированному JAR
CMD ["java", "-jar", "k8s.jar"]