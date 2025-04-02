# ใช้ Java 17 JDK base image
FROM eclipse-temurin:17-jdk

# กำหนด working directory
WORKDIR /app

# คัดลอกไฟล์ JAR จาก target เข้า container
COPY target/*.jar app.jar

# คำสั่งที่ใช้รัน Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
