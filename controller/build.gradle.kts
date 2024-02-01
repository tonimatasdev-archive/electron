plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2"
    application
}

val jdaVersion: String by extra

dependencies {
}

application {
    mainClass = "dev.tonimatas.discordmk.Main"
}