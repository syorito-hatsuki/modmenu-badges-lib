import com.modrinth.minotaur.TaskModrinthUpload

plugins {
    id("fabric-loom")
    id("com.modrinth.minotaur")
}

base {
    val archivesBaseName: String by project
    archivesName.set(archivesBaseName)
}

val javaVersion = JavaVersion.VERSION_21

val modVersion: String by project
version = modVersion

val mavenGroup: String by project
group = mavenGroup

repositories {
    maven("https://api.modrinth.com/maven")
}

dependencies {
    val minecraftVersion: String by project
    minecraft("com.mojang", "minecraft", minecraftVersion)

    val yarnMappings: String by project
    mappings("net.fabricmc", "yarn", yarnMappings, null, "v2")

    val loaderVersion: String by project
    modImplementation("net.fabricmc", "fabric-loader", loaderVersion)

    val fabricVersion: String by project
    modImplementation("net.fabricmc.fabric-api", "fabric-api", fabricVersion)

    val modmenuVersion: String by project
    modImplementation("maven.modrinth", "modmenu", modmenuVersion)
}

modrinth {
    token.set(System.getenv("MODRINTH_TOKEN"))
    projectId.set("modmenu-badges-lib")
    versionName.set("ModMenu Badges Lib $modVersion")
    versionNumber.set(modVersion)
    versionType.set("release")
    uploadFile.set(tasks.remapJar)
    additionalFiles.add(tasks.remapSourcesJar)
    gameVersions.addAll(
        "1.21.6",
        "1.21.7",
        "1.21.8",
        "1.21.9",
        "1.21.10",
        "1.21.11",
    )
    loaders.add("fabric")
    changelog.set(rootProject.file("CHANGELOG.md").readText())
    dependencies {
        required.project(
            "modmenu",
            "fabric-api"
        )
    }
}

tasks {

    named("modrinth").configure {
        @Suppress("UnstableApiUsage") doLast {
            (this@configure as TaskModrinthUpload).uploadInfo?.let {
                "https://modrinth.com/mod/modmenu-badges-lib/version/${it.id}".apply {
                    println(this)
                    rootProject.file("build/modrinth_url.txt").writeText(this)
                }
            } ?: return@doLast
        }
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(javaVersion.toString()))
        }
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
        withSourcesJar()
    }

    processResources {
        filesMatching("fabric.mod.json") {
            expand(mutableMapOf("version" to project.version))
        }
    }
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        sourceCompatibility = javaVersion.toString()
        targetCompatibility = javaVersion.toString()
        options.release.set(javaVersion.toString().toInt())
    }
}
