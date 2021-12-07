import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
val brotliVersion = "1.6.0"
val operatingSystem: OperatingSystem = org.gradle.nativeplatform.platform.internal.DefaultNativePlatform.getCurrentOperatingSystem()
plugins {
    kotlin("jvm") version "1.6.0"
    application
//    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.0"
    kotlin("plugin.serialization") version "1.6.0"
}

group = "top.wsure.bililiver"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.aayushatharva.brotli4j:brotli4j:$brotliVersion")
    implementation(
        "com.aayushatharva.brotli4j:native-${
            if (operatingSystem.isWindows) "windows-x86_64"
            else if (operatingSystem.isMacOsX) "osx-x86_64"
            else if (operatingSystem.isLinux)
                if (org.gradle.nativeplatform.platform.internal.DefaultNativePlatform.getCurrentArchitecture().isArm) "linux-aarch64"
                else "native-linux-x86_64"
            else ""
        }:$brotliVersion"
    )
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.5.2-native-mt")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
    implementation("com.squareup.okhttp3:okhttp:4.9.2")
    implementation("org.slf4j:slf4j-api:1.7.32")
    implementation("ch.qos.logback:logback-core:1.2.6")
    implementation("ch.qos.logback:logback-classic:1.2.6")
    implementation(kotlin("reflect"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}