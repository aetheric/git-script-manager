package nz.co.aetheric.gsm.cmds

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import nz.co.aetheric.cli.CliCmd
import org.koin.core.KoinApplication
import org.koin.core.component.inject
import org.koin.core.qualifier.Qualifier

class Gsm : CliCmd(
	name = "gsm",
	desc = "Manages use of scripts stored in git repos.",
	opts = setOf(Opt.SUBS_REQUIRED)
) {

	// Subcommand injection.
	private val cmdRepo: GsmRepo by inject()

	init {
		subcommands(
			cmdRepo,
		)
	}




}
