plugins {
    id("java")
    alias(libs.plugins.spring.dm)
}

dependencyManagement {
    imports {
        val bootVersion = libs.plugins.spring.boot.get().version

        mavenBom("org.springframework.boot:spring-boot-dependencies:$bootVersion")
    }
}

repositories {
    mavenCentral()
}
dependencies {
    implementation(libs.liquibase)

    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    annotationProcessor("org.testcontainers:junit-jupiter")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation(project(":domain"))
    implementation(project(":core"))
    implementation(project(":infrastructure:persistence"))

    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation(libs.testcontainers)
    testImplementation(libs.testcontainerspg)
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
}

tasks.named<Test>("test") {
    enabled = false
}

tasks.register<Test>("integrationTest") {
    useJUnitPlatform()
    testLogging.showStandardStreams = true

    val testSourceSet = sourceSets.test.get()
    testClassesDirs = testSourceSet.output.classesDirs
    classpath = testSourceSet.runtimeClasspath

    dependsOn(tasks.testClasses)
}
