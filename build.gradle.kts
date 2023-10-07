import info.solidsoft.gradle.pitest.PitestPluginExtension

plugins {
    kotlin("jvm") version "1.8.0"
    id("info.solidsoft.pitest") version "1.9.11"
    application
}

group = "dev.diegofernando"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    pitest("org.pitest:pitest-junit5-plugin:1.1.2")
}

tasks.named<Test>("test"){
    useJUnitPlatform()
    testLogging {
        events = setOf(
            org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
        )
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        showStandardStreams = true
    }
}

plugins.withId("info.solidsoft.pitest") {
    configure<PitestPluginExtension> {
        verbose.set(false)
        testPlugin.set("junit5")
        outputFormats.set(setOf("HTML"))
    }
}