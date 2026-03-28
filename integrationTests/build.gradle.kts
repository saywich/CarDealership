plugins {
    id("java")
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dm)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.liquibase)
    implementation("org.springframework.boot:spring-boot-starter-web")

    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    annotationProcessor("org.testcontainers:junit-jupiter")

    implementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation(project(":domain"))
    implementation(project(":application"))
    implementation(project(":core"))
    implementation(project(":infrastructure:persistence"))

    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation(libs.testcontainers)
    testImplementation(libs.testcontainerspg)
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    testLogging.showStandardStreams = true
}

//tasks.register<Test>("integrationTest") {
//    useJUnitPlatform()
//    testLogging.showStandardStreams = true
//}