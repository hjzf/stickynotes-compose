import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

group = "com.stickynotes"
version = "1.0.10"

repositories {
//    maven("https://maven.aliyun.com/repository/public")
//    maven("https://maven.aliyun.com/repository/central")
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
//    maven("https://maven.aliyun.com/repository/google")
//    maven("https://maven.aliyun.com/repository/gradle-plugin")
    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    implementation("org.apache.commons:commons-text:1.12.0")
}

compose.desktop {
    application {
        mainClass = "MainKt"
        buildTypes.release.proguard {
            isEnabled = false
            // configurationFiles.from("proguard-rules.pro")
        }
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "stickynotes"
            packageVersion = "1.0.10"
            description = "stickynotes"
            vendor = "Sticky Notes"
            macOS {
                iconFile.set(project.file("icon.icns"))
            }
            windows {
                iconFile.set(project.file("icon.ico"))
                perUserInstall = true
                dirChooser = true
                shortcut = true
                menuGroup = "stickynotes"
            }
            linux {
                iconFile.set(project.file("icon.png"))
            }
        }
    }
}
