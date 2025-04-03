# Base image ที่ใช้ JDK
FROM eclipse-temurin:17-jdk

# สร้าง working directory
WORKDIR /app

# คัดลอก source code ทั้งหมด
COPY . .

# รันคำสั่ง build jar ตอน deploy
RUN ./mvnw clean package -DskipTests

# รันแอป
ENTRYPOINT ["java", "-jar", "target/myapp-0.0.1-SNAPSHOT.jar"]
