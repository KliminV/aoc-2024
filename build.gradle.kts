plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.allopen") version "2.0.21"
    id("org.jetbrains.kotlinx.benchmark") version "0.4.13"
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.13")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(22)
}

benchmark {
    targets {
        register("main")
    }
}

allOpen {
    annotation("org.openjdk.jmh.annotations.State")
}