import net.pwall.json.kotlin.codegen.gradle.JSONSchemaCodegen
import net.pwall.json.kotlin.codegen.gradle.JSONSchemaCodegenPlugin

plugins {
	kotlin("multiplatform")
	kotlin("plugin.serialization")
	id("org.jetbrains.kotlinx.kover")
	id("io.kotest.multiplatform")
	application
}

buildscript {
	dependencies {
		// https://github.com/pwall567/json-kotlin-gradle
		classpath("net.pwall.json", "json-kotlin-gradle", "0.83") {
			exclude("org.jetbrains.kotlin", "kotlin-gradle-plugin-api")
		}
	}
}

apply<JSONSchemaCodegenPlugin>()

repositories {
	mavenCentral()
	jcenter()
//	maven("https://dl.bintray.com/ricky12awesome/github")
}

group   = "nz.co.aetheric"
version = "0.1.0-SNAPSHOT"

kotlin {
	listOf( linuxX64("nix"), mingwX64("win"), macosX64("mac") )
		.forEach { it.binaries { executable {  entryPoint = "nz.co.aetheric.gsm.main"  }} }
	jvm {
		withJava()
		compilations.all {
			kotlinOptions.jvmTarget = "11"
		}
		testRuns["test"].executionTask.configure {
			useJUnitPlatform()
		}
	}
	sourceSets {
		val mockkVersion: String by project
		val kotlinCliVersion: String by project
		val kotlinJsonVersion: String by project
		val koinVersion: String by project
		val atriumVersion: String by project
		val flowExtVersion: String by project
		val coroutinesVersion: String by project
		val okioVersion: String by project
		val cliktVersion: String by project
		val kotestVersion: String by project
		val kotestKoinVersion: String by project
		val commonMain by getting {
			kotlin.srcDir("build/generated-sources/kotlin")
			dependencies {

				// https://ajalt.github.io/clikt/
				implementation("com.github.ajalt.clikt:clikt:${cliktVersion}")

				// https://github.com/Kotlin/kotlinx.serialization
				implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${kotlinJsonVersion}")

				// https://insert-koin.io
				implementation("io.insert-koin:koin-core:${koinVersion}")

				// https://square.github.io/okio/multiplatform/
				implementation("com.squareup.okio:okio:${okioVersion}")

				// https://github.com/Ricky12Awesome/json-schema-serialization
//				implementation("com.github.Ricky12Awesome:json-schema-serialization:0.6.6")

				// https://github.com/petertrr/kotlin-multiplatform-diff
				// https://docs.korge.org/krypto
				// https://github.com/z4kn4fein/kotlin-semver
				// https://github.com/Kotlin/kotlinx-datetime

			}
			configure<JSONSchemaCodegen> {
				configFile.set(file("src/commonMain/resources/codegen-config.json"))
				inputs {
					inputFile(file("src/commonMain/resources/schema"))
				}
			}
		}
		val commonTest by getting {
			dependencies {
				implementation("io.kotest:kotest-framework-engine:${kotestVersion}")
				implementation("io.kotest:kotest-assertions-core:${kotestVersion}")
				implementation("io.kotest:kotest-property:${kotestVersion}")
				implementation("io.insert-koin:koin-test:${koinVersion}") {
					exclude("junit", "junit") }
				implementation("com.squareup.okio:okio-fakefilesystem:${okioVersion}")
			}
		}
	}
}

application {
	mainClass.set("MainKt")
}
