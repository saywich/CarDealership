plugins {
    alias(libs.plugins.spring.boot)
}

dependencies {
    compileOnly(libs.mapstruct)
    compileOnly(libs.lombok)

    annotationProcessor(libs.mapstruct.processor)
    annotationProcessor(libs.lombok)
    annotationProcessor(libs.lombok.mapstruct.binding)

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")

    implementation(platform("org.springframework.grpc:spring-grpc-dependencies:1.0.3"))
    implementation("org.springframework.grpc:spring-grpc-server-spring-boot-starter")

    implementation(libs.keycloak)
    implementation(libs.postgresql)

    implementation(project(":shared:grpc"))
    implementation(project(":shared:domain"))
    implementation(project(":storageService:application"))
    implementation(project(":storageService:infrastructure:persistence"))

    implementation(libs.springdoc)

    testImplementation(libs.junit.jupiter)
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}