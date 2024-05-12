plugins {
    java
}

val projectVersion: String by extra
val jdaVersion: String by extra

base.archivesName.set("electron")

allprojects {
    apply(plugin = "java")
    
    java {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    
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