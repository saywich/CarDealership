base {
    archivesName.set("order-grpc")
}

dependencies {
    compileOnly(libs.lombok)
    compileOnly(libs.mapstruct)
    annotationProcessor(libs.mapstruct.processor)
    annotationProcessor(libs.lombok)
    annotationProcessor(libs.lombok.mapstruct.binding)

    implementation(platform("org.springframework.grpc:spring-grpc-dependencies:1.0.3"))

    implementation("org.springframework.grpc:spring-grpc-client-spring-boot-starter")

    implementation(project(":shared:grpc"))
    implementation(project(":shared:domain"))
    implementation(project(":orderService:application"))
}