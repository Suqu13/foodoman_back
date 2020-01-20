import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    jacoco
    id("org.springframework.boot") version "2.2.2.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    id("org.sonarqube") version "2.7"
    id("org.jetbrains.dokka") version "0.10.0"
    kotlin("jvm") version "1.3.61"
    kotlin("plugin.spring") version "1.3.61"
    kotlin("kapt") version "1.3.61"

}

group = "garstka.jakub"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

val developmentOnly by configurations.creating
configurations {
    runtimeClasspath {
        extendsFrom(developmentOnly)
    }
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation("io.springfox:springfox-swagger2:2.7.0")
    implementation("io.springfox:springfox-swagger-ui:2.7.0")
    implementation("org.mapstruct:mapstruct:1.3.1.Final")
    kapt("org.mapstruct:mapstruct-processor:1.3.1.Final")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("mysql:mysql-connector-java")
    testImplementation("io.mockk:mockk:1.9.3")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

sonarqube {
    properties {
        property("sonar.host.url", "http://localhost:9000")
        property("sonar.sources", "src/main/")
        property("sonar.tests", "src/test/")
        property("sonar.exclusions", "src/generated/")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/jacoco/jacocoTestReport.xml")
        property("sonar.junit.reportsPath","build/test-results/test")
        property("sonar.core.codeCoveragePlugin","jacoco")
        property("sonar.verbose", "true")
        property("sonar.binaries" ,"build/classes/kotlin")
        property("sonar.java.binaries" ,"build/classes/java, build/classes/kotlin")
        property("sonar.dynamicAnalysis", "reuseReports")
    }
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        xml.destination = file("${buildDir}/jacoco/jacocoTestReport.xml")
        csv.isEnabled = false
        html.destination = file("${buildDir}/jacocoHtml")
    }
}
jacoco {
    toolVersion = "0.8.5"
    reportsDir = file("$buildDir/customJacocoReportDir")
}

tasks.named("sonarqube") {
    dependsOn(tasks.named("jacocoTestReport"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks {
    val dokka by getting(DokkaTask::class) {
        outputDirectory = "$buildDir/dokka"
        outputFormat = "html"
    }
}