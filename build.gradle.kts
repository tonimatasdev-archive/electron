plugins {
    java
}

val projectVersion: String by extra
val jdaVersion: String by extra

base.archivesName.set("DiscordMK")

allprojects {
    apply(plugin = "java")
    
    java.setSourceCompatibility(17)
    java.setTargetCompatibility(17)
    
    version = projectVersion
    group = "dev.tonimatas"

    repositories {
        mavenCentral()
    }
    
    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    java.withSourcesJar()
}


subprojects {
    if (project.name == "controller") return@subprojects
    
    dependencies {
        implementation("net.dv8tion:JDA:$jdaVersion")
    }
}