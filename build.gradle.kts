import net.pwall.json.kotlin.codegen.gradle.JSONSchemaCodegen
import net.pwall.json.kotlin.codegen.gradle.JSONSchemaCodegenPlugin

plugins {
	kotlin("multiplatform") version "1.7.10"
	kotlin("plugin.serialization") version "1.7.10"
	id("org.jetbrains.kotlinx.kover") version "0.6.0"
	application
}

group   = "nz.co.aetheric"
version = "0.1.0-SNAPSHOT"

buildscript {
	repositories {
		mavenCentral()
	}
	dependencies {
		classpath("net.pwall.json:json-kotlin-gradle:0.81")
	}
}

apply<JSONSchemaCodegenPlugin>()

repositories {
	mavenCentral()
}

kotlin {
	linuxX64("nix") {
		binaries {
			executable {
				entryPoint = "main"
			}
		}
	}
	mingwX64("win") {
		binaries {
			executable {
				entryPoint = "main"
			}
		}
	}
	jvm {
		compilations.all {
			kotlinOptions.jvmTarget = "11"
		}
		withJava()
		testRuns["test"].executionTask.configure {
			useJUnitPlatform()
		}
	}
	macosX64("mac") {
		binaries {
			executable {
				entryPoint = "main"
			}
		}
	}
	sourceSets {
		val commonMain by getting {
			kotlin.srcDir("build/generated-sources/kotlin")
			dependencies {

				// https://github.com/Kotlin/kotlinx-cli
				implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.5")

				// https://github.com/Kotlin/kotlinx.serialization
				implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")

			}
			configure<JSONSchemaCodegen> {
				configFile.set(file("src/commonMain/resources/codegen-config.json"))
				inputs {
					inputFile(file("src/commonMain/resources/schema"))
				}
			}
		}
		val commonTest by getting
		val nixMain by getting
		val nixTest by getting
		val winMain by getting
		val winTest by getting
		val jvmMain by getting
		val jvmTest by getting {
			dependencies {
				implementation(kotlin("test"))
			}
		}
		val macMain by getting
		val macTest by getting
	}
}

application {
	mainClass.set("MainKt")
}
