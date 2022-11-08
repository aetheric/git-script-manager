package nz.co.aetheric

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.config.LogLevel
import io.kotest.core.spec.IsolationMode

class TestConfig : AbstractProjectConfig() {

	override val isolationMode: IsolationMode
			= IsolationMode.InstancePerTest

	override val logLevel: LogLevel
			= LogLevel.Debug

}
