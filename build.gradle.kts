plugins {
    base
}

group = "org.example"
version = "1.0-SNAPSHOT"

subprojects {
    group = rootProject.group
    version = rootProject.version

    repositories {
        mavenCentral()
    }
}

tasks.register("smokeApi") {
    group = "verification"
    description = "Запуск Smoke API-тестов"
    dependsOn(":api-tests:smokeTest")
}

tasks.register("smokeUi") {
    group = "verification"
    description = "Запуск Smoke UI-тестов"
    dependsOn(":ui-tests:smokeTest")
}