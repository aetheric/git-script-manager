import net.pwall.json.kotlin.codegen.gradle.JSONSchemaCodegen
import net.pwall.json.kotlin.codegen.gradle.JSONSchemaCodegenPlugin

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
	alias(libs.plugins.kotlin.multiplatform)
	alias(libs.plugins.kotlin.serialization)
	alias(libs.plugins.kotest.multiplatform)
	alias(libs.plugins.kover)
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
		testRuns["test"].executionTask.configure {
			useJUnitPlatform()
		}
	}
	sourceSets {
		val commonMain by getting {
			kotlin.srcDir("build/generated-sources/kotlin")
			dependencies {
				implementation(libs.clikt)
				implementation(libs.kotlinx.serialization.json)
				implementation(libs.koin.core)
				implementation(libs.okio)
//				implementation(libs.json.schema.serialization)
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
				implementation(libs.kotest.framework.engine)
				implementation(libs.kotest.assertions.core)
				implementation(libs.kotest.property)
				implementation(libs.koin.test)
//				implementation(libs.kotest.extensions.koin)
				implementation(libs.okio.fakefilesystem)
			}
		}
		val jvmTest by getting {
			dependencies {
				implementation(libs.kotest.runner.junit5)
			}
		}
	}
}

application {
	mainClass.set("MainKt")
}
