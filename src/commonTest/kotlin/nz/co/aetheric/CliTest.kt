package nz.co.aetheric

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.koin.KoinExtension
import org.koin.core.component.KoinComponent
import org.koin.core.module.Module

abstract class CliTest(
	protected val module: Module,

) : BehaviorSpec(), KoinComponent {

	override fun isolationMode() = IsolationMode.InstancePerTest
	override fun extensions() = listOf(
		KoinExtension(module),
	)

}
