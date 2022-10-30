package nz.co.aetheric.gsm.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Repository(

	/** The git-compatible URI to the scripts repo. */
	val source: String,

	/** The branch/tag reference to pull. */
	val reference: String = "main",

	/** A map of the scripts identified in this repository. */
	val scripts: Map<String, Script>? = emptyMap(),

) {

	@SerialName("\$schema")
	val schema = "https://aetheric.co.nz/schema/gsm/repository"

}
