plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.12.2")

    // Rest Assured — наше предыдущее ДЗ
    testImplementation("io.rest-assured:rest-assured:5.5.1")

    // AssertJ — наше предыдущее ДЗ
    testImplementation("org.assertj:assertj-core:3.27.3")

    // Selenium
    testImplementation("org.seleniumhq.selenium:selenium-java:4.35.0")

    // Selenide
    testImplementation("com.codeborne:selenide:7.9.3")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        showStandardStreams = true
    }
}

tasks.register<Test>("apiTest") {
    group = "verification"
    description = "Runs API autotests"

    useJUnitPlatform {
        includeTags("api")
    }

    testClassesDirs = sourceSets.test.get().output.classesDirs
    classpath = sourceSets.test.get().runtimeClasspath
}