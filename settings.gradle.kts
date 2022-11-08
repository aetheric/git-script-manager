
rootProject.name = "git-script-manager"

pluginManagement {
	repositories {
		gradlePluginPortal()
		mavenCentral()
	}
}

fun VersionCatalogBuilder.libraries(version: String, group: String, artifacts: Set<String>) = artifacts
	.map { library(it, group, it) }
	.forEach { it.versionRef(version) }

dependencyResolutionManagement {
	versionCatalogs {
		create("libs") {
			version("mockk", "1.13.1")
			version("kotlin-cli", "0.3.5")
			version("kotlin-json", "1.4.0")
			version("atrium", "0.16.0")
			version("kotlin-logging", "2.1.20")
			version("flow-ext", "0.4.0")
			version("coroutines", "1.6.0")

			plugin("kover", "org.jetbrains.kotlinx.kover").version("0.6.0")

			libraries(version("kotlin", "1.7.20"), "org.jetbrains.kotlin", emptySet())
			plugin("kotlin-multiplatform", "org.jetbrains.kotlin.multiplatform").versionRef("kotlin")
			plugin("kotlin-serialization", "org.jetbrains.kotlin.plugin.serialization").versionRef("kotlin")

			libraries(version("kotest", "5.5.3"), "io.kotest", setOf(
				"kotest-framework-engine",
				"kotest-assertions-core",
				"kotest-property",
				"kotest-runner-junit5",
			))
			plugin("kotest-multiplatform", "io.kotest.multiplatform").versionRef("kotest")

			// https://insert-koin.io
			libraries(version("koin", "3.2.2"), "io.insert-koin", setOf(
				"koin-core",
				"koin-test",
			))

			libraries(version("kotest-extensions-koin", "1.1.0"), "io.kotest.extensions", setOf(
				"kotest-extensions-koin",
			))

			// https://square.github.io/okio/multiplatform/
			libraries(version("okio", "3.2.0"), "com.squareup.okio", setOf(
				"okio",
				"okio-fakefilesystem",
			))

			// https://ajalt.github.io/clikt/
			library("clikt", "com.github.ajalt.clikt", "clikt").version("3.5.0")

			// https://github.com/Kotlin/kotlinx.serialization
			library("kotlinx-serialization-json","org.jetbrains.kotlinx", "kotlinx-serialization-json").version("1.4.0")

			// https://github.com/Ricky12Awesome/json-schema-serialization
			library("json-schema-serialization", "com.github.Ricky12Awesome", "json-schema-serialization").version("0.6.6")

			// https://github.com/petertrr/kotlin-multiplatform-diff
			// https://docs.korge.org/krypto
			// https://github.com/z4kn4fein/kotlin-semver
			// https://github.com/Kotlin/kotlinx-datetime

		}
	}
}

