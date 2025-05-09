pluginManagement {
    repositories {
//        maven("https://maven.aliyun.com/repository/public")
//        maven("https://maven.aliyun.com/repository/central")
//        maven("https://maven.aliyun.com/repository/google")
//        maven("https://maven.aliyun.com/repository/gradle-plugin")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        kotlin("jvm").version(extra["kotlin.version"] as String)
        id("org.jetbrains.compose").version(extra["compose.version"] as String)
        id("org.jetbrains.kotlin.plugin.compose").version(extra["kotlin.version"] as String)
    }
}

rootProject.name = "stickynotes"
