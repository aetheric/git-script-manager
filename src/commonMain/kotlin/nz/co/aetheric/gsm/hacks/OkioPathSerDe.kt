package nz.co.aetheric.gsm.hacks

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import okio.Path
import okio.Path.Companion.toPath

@OptIn(ExperimentalSerializationApi::class)
@Serializer(Path::class)
object OkioPathSerDe : KSerializer<Path> {
	override val descriptor = PrimitiveSerialDescriptor("path", PrimitiveKind.STRING)
	override fun serialize(encoder: Encoder, value: Path) = value.toString().let(encoder::encodeString)
	override fun deserialize(decoder: Decoder) = decoder.decodeString().toPath()
}
