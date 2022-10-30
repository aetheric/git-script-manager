@file:UseSerializers(OkioPathSerDe::class)
package nz.co.aetheric.gsm.data

import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import nz.co.aetheric.gsm.hacks.OkioPathSerDe
import okio.FileSystem
import okio.Path
import okio.Path.Companion.toPath

/**
 * Configuration for the Git Script Manager application.
 */
@Serializable
data class Settings(

	/** Where the contents of the script repositories get put */
	val cacheDir: Path? = null,

	/** The directory that the scripts get exposed on. */
	val binDir: Path? = null,

	/** The list of repositories being sourced. */
	val repositories: Map<String, Repository> = mapOf(),

) {

	@SerialName("\$schema")
	val schema = "https://aetheric.co.nz/schema/gsm/settings"

	/**
	 * Saves the [Settings] to the most appropriate of the provided locations given
	 * as JSON.
	 */
	fun save(files: FileSystem, vararg locations: String) {

		// Figure out where to actually save the file.
		val target = locations
			.map { it.toPath(true) }
			.run { firstOrNull(files::exists) ?: first() }

		// Ensure all the needed directories exist.
		files.createDirectories(target.parent!!)

		// Serialise to JSON and write to file.
		val json = Json.encodeToString(this)
		files.write(target) { writeUtf8(json) }

	}

	companion object {

		/**
		 * Attempts to load a [Settings] instance from the first given path that
		 * actually has a file, otherwise it just returns a default settings
		 * instance.
		 */
		fun load(files: FileSystem, vararg locations: String): Settings {

			// Figure out where to load the file from, if even possible.
			val source = locations
				.map { it.toPath(true) }
				.firstOrNull(files::exists)

			// If there's no existing source, just return a default instance.
			source ?: return Settings()

			// Load and deserialise the settings file.
			val json = files.read(source) { readUtf8() }
			return Json.Default.decodeFromString(json)

		}

	}

}
