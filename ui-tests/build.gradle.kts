plugins {
    java
}

dependencies {
    implementation(project(":common"))

    testImplementation(project(
        mapOf(
            "path" to ":api-tests",
            "configuration" to "testArtifacts"
        )
    ))

    testImplementation("org.junit.jupiter:junit-jupiter:5.12.2")
    testImplementation("org.assertj:assertj-core:3.27.3")
    testImplementation("io.rest-assured:rest-assured:5.5.1")

    testImplementation("org.seleniumhq.selenium:selenium-java:4.35.0")
    testImplementation("com.codeborne:selenide:7.9.3")

    testImplementation("io.qameta.allure:allure-junit5:2.29.1")
    testImplementation("io.qameta.allure:allure-selenide:2.29.1")
    testImplementation("io.qameta.allure:allure-rest-assured:2.29.1")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        showStandardStreams = true
    }
}

tasks.register<Test>("smokeTest") {
    group = "verification"
    description = "Run Smoke UI tests"

    useJUnitPlatform {
        includeTags("smoke")
    }

    testClassesDirs = sourceSets.test.get().output.classesDirs
    classpath = sourceSets.test.get().runtimeClasspath

    testLogging {
        showStandardStreams = true
    }
}