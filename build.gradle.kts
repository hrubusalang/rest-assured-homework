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

    testImplementation("io.rest-assured:rest-assured:5.5.1")

    testImplementation("org.assertj:assertj-core:3.27.3")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
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