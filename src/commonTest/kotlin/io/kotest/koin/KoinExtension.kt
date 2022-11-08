package io.kotest.koin

import io.kotest.core.listeners.AfterSpecListener
import io.kotest.core.listeners.BeforeSpecListener
import io.kotest.core.listeners.PrepareSpecListener
import io.kotest.core.spec.Spec
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import kotlin.reflect.KClass

class KoinExtension(
	private vararg val modules: Module,

) : PrepareSpecListener, BeforeSpecListener, AfterSpecListener {

	// Making sure that koin isn't running before the spec runs.
	override suspend fun prepareSpec(kclass: KClass<out Spec>) {
		stopKoin()
	}

	// Run koin for the spec scope.
	override suspend fun beforeSpec(spec: Spec) {
		startKoin {
			modules(modules.toList())
		}
	}

	// Stop the spec after the spec is done.
	override suspend fun afterSpec(spec: Spec) {
		stopKoin()
	}

}
