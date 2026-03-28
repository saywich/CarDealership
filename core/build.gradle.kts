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

    implementation(libs.spring.context)

    implementation(project(":domain"))
}
