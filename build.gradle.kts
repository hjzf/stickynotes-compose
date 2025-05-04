import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

group = "com.stickynotes"
version = "1.0.12"

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
    implementation("org.apache.lucene:lucene-core:10.1.0")
    implementation("org.apache.lucene:lucene-analysis-common:10.1.0")
    implementation("org.apache.lucene:lucene-queryparser:10.1.0")
    implementation("cn.shenyanchao.ik-analyzer:ik-analyzer:9.0.0")
    implementation("org.slf4j:slf4j-api:2.0.17")
    implementation("ch.qos.logback:logback-classic:1.5.17")
    testImplementation(kotlin("test"))
}

compose.desktop {
    application {
        mainClass = "MainKt"
        buildTypes.release.proguard {
            isEnabled = false
            version = "7.7.0"
            configurationFiles.from("proguard-rules.pro")
        }
        nativeDistributions {
            modules(
                "java.base",
                "java.datatransfer",
                "java.desktop",
                "java.logging",
                "java.prefs",
                "java.xml",
                "java.naming", // for logback
                "jdk.incubator.vector", // for lucene
                "jdk.crypto.ec"
            )
            targetFormats(TargetFormat.AppImage, TargetFormat.Msi)
            packageName = "stickynotes"
            packageVersion = "1.0.12"
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
                console = false
            }
            linux {
                iconFile.set(project.file("icon.png"))
            }
        }
    }
}
