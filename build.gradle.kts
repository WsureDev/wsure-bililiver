import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
val brotliVersion = "0.2.0"
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
    api("com.nixxcode.jvmbrotli:jvmbrotli:$brotliVersion")
    api("com.nixxcode.jvmbrotli:jvmbrotli-win32-x86:$brotliVersion")
    api("com.nixxcode.jvmbrotli:jvmbrotli-win32-x86-amd64:$brotliVersion")
    api("com.nixxcode.jvmbrotli:jvmbrotli-darwin-x86-amd64:$brotliVersion")
    api("com.nixxcode.jvmbrotli:jvmbrotli-linux-x86-amd64:$brotliVersion")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}