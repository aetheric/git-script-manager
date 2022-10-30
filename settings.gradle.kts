
rootProject.name = "git-script-manager"

pluginManagement {
	plugins {

		val kotlinVersion: String by settings
		kotlin("multiplatform") version kotlinVersion
		kotlin("plugin.serialization") version kotlinVersion

		val koverVersion: String by settings
		id("org.jetbrains.kotlinx.kover") version koverVersion

		val kotestVersion: String by settings
		id("io.kotest.multiplatform") version kotestVersion

	}
	repositories {
		gradlePluginPortal()
		mavenCentral()
	}
}
