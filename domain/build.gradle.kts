plugins {
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)

    testCompileOnly(libs.lombok)
    testAnnotationProcessor(libs.lombok)

    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    testLogging.showStandardStreams = true
}

tasks.register<Test>("integrationTest") {
    useJUnitPlatform()
    testLogging.showStandardStreams = true
}
