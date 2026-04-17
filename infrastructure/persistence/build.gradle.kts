plugins {
    alias(libs.plugins.spring.dm)
}

dependencyManagement {
    imports {
        val bootVersion = libs.plugins.spring.boot.get().version

        mavenBom("org.springframework.boot:spring-boot-dependencies:$bootVersion")
    }
}

dependencies {
    compileOnly(libs.lombok)
    compileOnly(libs.mapstruct)
    annotationProcessor(libs.mapstruct.processor)
    annotationProcessor(libs.lombok)
    annotationProcessor(libs.lombok.mapstruct.binding)

    implementation(libs.postgresql)
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-test")
    implementation(project(":domain"))
    implementation(project(":core"))

    testImplementation(libs.mapstruct)
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    testLogging.showStandardStreams = true
}
