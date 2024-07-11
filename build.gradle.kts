plugins {
	java
	id("org.springframework.boot") version "3.3.1"
	id("io.spring.dependency-management") version "1.1.5"
}

group = "br.com.estudos"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

val flywayVersion = "10.1.0"
val postgresVersion = "42.7.0"

dependencies {
	runtimeOnly("org.postgresql:postgresql:$postgresVersion")
	runtimeOnly("org.flywaydb:flyway-database-postgresql:$flywayVersion")

	implementation("org.flywaydb:flyway-core:$flywayVersion")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")

	compileOnly("org.projectlombok:lombok")

	annotationProcessor("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
