import groovy.util.Node
import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    java
    `java-library`
//    id("net.minecrell.licenser") version "0.3"
    `maven-publish`
}

group = "eu.mikroskeem"
version = "1.18"
description = "Minecraft LegacyLauncher - mikroskeem's fork"

val joptSimpleVersion = "5.0.4"
val asmVersion = "6.2.1"
val slf4jVersion = "1.8.0-beta2"
val checkerQualVersion = "2.5.4"

val gradleWrapperVersion = "4.6"

val lwtsVersion = "1.1.0-SNAPSHOT"
val shurikenVersion = "0.0.1-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()

    maven("https://repo.wut.ee/repository/mikroskeem-repo")
    maven("https://repo.spongepowered.org/maven")
}

dependencies {
    api("net.sf.jopt-simple:jopt-simple:$joptSimpleVersion")
    api("org.ow2.asm:asm:$asmVersion")
    api("org.slf4j:slf4j-api:$slf4jVersion")
    api("org.checkerframework:checker-qual:$checkerQualVersion")

    testImplementation("org.spongepowered:lwts:$lwtsVersion") {
        exclude(group = "net.minecraft", module = "launchwrapper")
    }
    testImplementation("org.slf4j:slf4j-simple:$slf4jVersion")
}

val test by tasks.getting(Test::class) {
    systemProperty("lwts.tweaker", "eu.mikroskeem.test.launchwrapper.tweaker.TestTweaker")
    systemProperty("legacy.debugClassLoading", "true")
    systemProperty("legacy.debugClassLoadingFiner", "true")
    systemProperty("org.slf4j.simpleLogger.defaultLogLevel", "trace")

    // Set working directory
    workingDir = this.temporaryDir

    // Show output
    testLogging {
        showStandardStreams = true
        exceptionFormat = TestExceptionFormat.FULL
    }

    // Verbose
    beforeTest(closureOf<Any> { logger.lifecycle("Running test: $this") })
}

//license {
//    header = rootProject.file("etc/HEADER")
//    filter.include("**/*.java")
//
    // Because org.spongepowered.asm.launch.MixinBootstrap does this:
    // if(MixinBootstrap.findInStackTrace("net.minecraft.launchwrapper.Launch", "launch") > 132) {
    //     *we are not in pre-init some mixins may be skipped blah blah*
//    filter.exclude("net/minecraft/launchwrapper/Launch.java")
//}

val javadoc by tasks.getting(Javadoc::class)

val javadocJar by tasks.creating(Jar::class) {
    dependsOn(javadoc)
    classifier = "javadoc"
    from(javadoc.destinationDir)
}

val wrapper by tasks.creating(Wrapper::class) {
    gradleVersion = gradleWrapperVersion
    distributionUrl = "https://services.gradle.org/distributions/gradle-$gradleVersion-all.zip"
}

fun XmlProvider.builder(builder: GroovyBuilderScope.() -> Unit) {
    (asNode().children().last() as Node).plus(delegateClosureOf<Any> {
        withGroovyBuilder(builder)
    })
}
