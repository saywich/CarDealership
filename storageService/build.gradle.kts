import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

plugins {
    alias(libs.plugins.spring.dm) apply false
}

val bootVersion = libs.plugins.spring.boot.get().version

subprojects {
    apply {
        plugin("java")
        plugin("io.spring.dependency-management")
    }

    extensions.configure<DependencyManagementExtension> {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:$bootVersion")
        }
    }

    repositories {
        mavenCentral()
    }
}