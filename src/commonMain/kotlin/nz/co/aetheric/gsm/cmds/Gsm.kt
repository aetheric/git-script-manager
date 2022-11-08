package nz.co.aetheric.gsm.cmds

import com.github.ajalt.clikt.core.subcommands
import nz.co.aetheric.cli.CliCmd
import org.koin.core.component.inject
import org.koin.core.qualifier.named

class Gsm : CliCmd(
	name = "gsm",
	desc = "Manages use of scripts stored in git repos.",
	opts = setOf(Opt.SUBS_REQUIRED)
) {

	// Subcommand injection.
	private val cmdRepo by inject<CliCmd>(named("gsm_repo"))

	init {
		subcommands(
			cmdRepo,
		)
	}

}
