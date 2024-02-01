plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2"
    application
}

val jdaVersion: String by extra

dependencies {
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("net.dv8tion:JDA:$jdaVersion")
    implementation(project(":api"))
}

application {
    mainClass = "dev.tonimatas.discordmk.Main"
}