package nz.co.aetheric.gsm.cmds

import com.github.ajalt.clikt.core.subcommands
import nz.co.aetheric.cli.CliCmd
import org.koin.core.component.inject

class GsmRepo : CliCmd(
	name  = "repo",
	desc  = "Script repository management.",
	opts = setOf(Opt.ARGS_REQUIRED),
) {

	// Subcommand injection.
	private val cmdSet: GsmRepoSet by inject()

	init {
		subcommands(
			cmdSet,
		)
	}

}
