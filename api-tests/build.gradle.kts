plugins {
    java
}

dependencies {
    implementation(project(":common"))

    testImplementation("org.junit.jupiter:junit-jupiter:5.12.2")
    testImplementation("io.rest-assured:rest-assured:5.5.1")
    testImplementation("org.assertj:assertj-core:3.27.3")

    testImplementation("io.qameta.allure:allure-junit5:2.29.1")
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
    description = "Run Smoke API tests"

    useJUnitPlatform {
        includeTags("smoke")
    }

    testClassesDirs = sourceSets.test.get().output.classesDirs
    classpath = sourceSets.test.get().runtimeClasspath

    testLogging {
        showStandardStreams = true
    }
}

val testJar by tasks.registering(Jar::class) {
    archiveClassifier.set("tests")
    from(sourceSets.test.get().output)
}

configurations {
    create("testArtifacts") {
        isCanBeConsumed = true
        isCanBeResolved = false
    }
}

artifacts {
    add("testArtifacts", testJar)
}