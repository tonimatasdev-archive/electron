plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

val jdaVersion: String by extra

dependencies {
    implementation("ch.qos.logback:logback-classic:1.5.6")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("net.dv8tion:JDA:$jdaVersion")
    implementation(project(":common"))
}

tasks.shadowJar {
    archiveClassifier.set("all")
    mergeServiceFiles()
    
    manifest.attributes("Main-Class" to "dev.tonimatas.electron.ServerMK")
}

tasks.build {
    dependsOn(tasks.shadowJar)
}
