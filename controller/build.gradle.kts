plugins {
    id("io.spring.dependency-management") version "1.1.4"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.springframework.boot") version "3.2.5"
    application
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":common"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

application {
    mainClass = "dev.tonimatas.electron.ControllerMK"
}

tasks.withType<Test> {
    useJUnitPlatform()
}
