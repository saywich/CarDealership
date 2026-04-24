plugins {
    id("java")
    alias(libs.plugins.spring.dm)
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(libs.mapstruct)
    compileOnly(libs.lombok)

    annotationProcessor(libs.mapstruct.processor)
    annotationProcessor(libs.lombok)
    annotationProcessor(libs.lombok.mapstruct.binding)

    implementation(libs.spring.context)
    implementation("org.springframework.boot:spring-boot-starter-kafka")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.core:jackson-databind")

    implementation(project(":shared:domain"))
    implementation(project(":shared:events"))

    implementation(project(":storageService:domain"))
}
