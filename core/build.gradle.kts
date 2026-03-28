plugins {
    id("java")
    alias(libs.plugins.spring.dm)
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    annotationProcessor(libs.mapstruct)

    implementation(libs.spring.context)

    implementation(project(":domain"))
}
