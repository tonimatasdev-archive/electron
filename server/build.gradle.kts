plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
    mavenLocal()
}

dependencies {
    implementation("dev.discordmk:quark:1.0.0")
    implementation("ch.qos.logback:logback-classic:1.5.6")
    implementation(project(":common"))
}

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
