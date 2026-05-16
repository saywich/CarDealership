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

    implementation(project(":shared:domain"))
    implementation(project(":storageService:application"))
    implementation(project(":storageService:infrastructure:persistence"))

    testImplementation(platform("io.grpc:grpc-bom:1.81.0"))

    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation(project(":shared:grpc"))
    testImplementation(project(":storageService:application"))
    testImplementation(project(":orderService:application"))
    testImplementation(project(":orderService:infrastructure:grpc"))

    testImplementation("io.grpc:grpc-inprocess")
    testImplementation("io.grpc:grpc-protobuf")
    testImplementation("io.grpc:grpc-stub")
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
