@file:UseSerializers(OkioPathSerDe::class)
package nz.co.aetheric.gsm.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import nz.co.aetheric.gsm.hacks.OkioPathSerDe
import okio.Path

@Serializable
data class Script(

	/** The path within the hosting repository it can be found at. */
	val path: Path,

	/** Any files relative to the script that are required. */
	val resources: Set<Path> = emptySet(),

	/** Files that get created or read by the script during the course of its use. */
	val incidentals: Set<Path> = emptySet(),

) {

	@SerialName("\$schema")
	val schema = "https://aetheric.co.nz/schema/gsm/script"

}
