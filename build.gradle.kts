import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
val brotliVersion = "1.6.0"
val operatingSystem: OperatingSystem = org.gradle.nativeplatform.platform.internal.DefaultNativePlatform.getCurrentOperatingSystem()
plugins {
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.serialization") version "1.6.0"
}

group = "top.wsure.bililiver"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api("top.wsure.guild:wsure-guild-common:1.0-SNAPSHOT")
    api("com.aayushatharva.brotli4j:brotli4j:$brotliVersion")
    api(
        "com.aayushatharva.brotli4j:native-${
            if (operatingSystem.isWindows) "windows-x86_64"
            else if (operatingSystem.isMacOsX) "osx-x86_64"
            else if (operatingSystem.isLinux)
                if (org.gradle.nativeplatform.platform.internal.DefaultNativePlatform.getCurrentArchitecture().isArm) "linux-aarch64"
                else "native-linux-x86_64"
            else ""
        }:$brotliVersion"
    )
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}