plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

val projectVersion: String by extra
val jdaVersion: String by extra

base.archivesName.set("electron")

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

version = projectVersion
group = "dev.tonimatas"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("dev.discordmk:quark:1.0.0")
    implementation("net.dv8tion:JDA:$jdaVersion")
    implementation("ch.qos.logback:logback-classic:1.5.6")
    
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

java.withSourcesJar()

tasks.shadowJar {
    archiveClassifier.set("")
    mergeServiceFiles()

    manifest.attributes("Main-Class" to "dev.tonimatas.electron.ServerMK")
}

tasks.jar {
    archiveClassifier.set("plain")
}

tasks.create("exportJar") {
    dependsOn(tasks.shadowJar)
}
