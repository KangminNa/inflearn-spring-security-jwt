import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.9.22"
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation
    implementation("org.springframework.boot:spring-boot-starter-validation:3.2.2")


}

/*
* allOpen과 noArg는 Kotlin에서 제공하는 Gradle 플러그인들입니다.
* 이들은 Spring Boot와 같은 Java 프레임워크를 Kotlin에서 더 효율적으로 사용하기 위해 필요한 설정들입니다.
*
allOpen
* Kotlin은 기본적으로 모든 클래스와 메소드를 'final'로 처리합니다.
* 즉, 이들은 상속이나 오버라이딩이 불가능합니다. 이는 Kotlin의 안전성을 높이는 특징 중 하나입니다.
* 그러나 이러한 특징은 Spring과 같은 프레임워크에서 문제가 될 수 있습니다.
* 왜냐하면, Spring에는 프록시 기반의 AOP(Aspect-Oriented Programming)가 있고, 이는 런타임 시에 클래스를 상속받아서 동작하기 때문입니다.
* 따라서 allOpen 플러그인은 이런 문제를 해결하기 위해 도입되었습니다. allOpen 플러그인은 특정 어노테이션이 붙은 클래스에 대해 'final' 제한을 해제하고,
* 이를 'open' 상태로 만듭니다.
* 따라서 해당 클래스는 상속이 가능해집니다. 위의 코드에서는 @Entity 어노테이션이 붙은 클래스를 'open'으로 만들도록 설정하고 있습니다.
*
noArg
* Kotlin은 기본 생성자가 없는 클래스를 선언할 수 있습니다.
* 하지만, JPA(Java Persistence API)와 같은 라이브러리에서는 기본 생성자가 필요합니다.
* 왜냐하면, JPA는 엔티티 객체를 생성할 때 리플렉션을 사용하는데, 이때 기본 생성자가 필요하기 때문입니다.
* 따라서 noArg 플러그인은 이런 문제를 해결하기 위해 도입되었습니다.
* noArg 플러그인은 특정 어노테이션이 붙은 클래스에 기본 생성자를 자동으로 추가합니다.
* 이렇게 하면, JPA와 같은 라이브러리에서 문제 없이 사용할 수 있습니다.
*
*
* */
allOpen {
    annotation("jakarta.persistence.Entity")
}
noArg {
    annotation("jakarta.persistence.Entity")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
