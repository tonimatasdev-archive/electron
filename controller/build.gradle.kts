plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2"
    application
}

dependencies {
    implementation(project(":common"))
}

application {
    mainClass = "dev.tonimatas.discordmk.ControllerMK"
}