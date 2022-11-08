package nz.co.aetheric.gsm

import nz.co.aetheric.gsm.cmds.Gsm
import nz.co.aetheric.gsm.cmds.GsmRepo
import nz.co.aetheric.gsm.cmds.GsmRepoSet
import nz.co.aetheric.gsm.cmds.commands
import okio.FileSystem
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.named
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

// No FileSystem available in common.
expect fun getFileSystem(): FileSystem

// Leave these here as fallbacks for now.
expect val homeDir: String
expect val xdgConfigDir: String

expect fun main(args: Array<String>)

fun start(args: Array<String>) {

	// Configure the application component assembly
	val app = startKoin {
		modules(commands)
		module {
			singleOf(::getFileSystem)
		}
	}

	// execure top-level parsing.
	app.koin.get<Gsm>().main(args)

}
