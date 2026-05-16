import com.google.protobuf.gradle.id

plugins {
    id("com.google.protobuf") version "0.9.6"
}

dependencies {
    implementation(platform("io.grpc:grpc-bom:1.81.0"))

    implementation("io.grpc:grpc-protobuf")
    implementation("io.grpc:grpc-stub")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.25.8"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.81.0"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
            }
        }
    }
}