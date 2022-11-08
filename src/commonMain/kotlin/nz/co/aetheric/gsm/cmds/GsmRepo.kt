package nz.co.aetheric.gsm.cmds

import com.github.ajalt.clikt.core.subcommands
import nz.co.aetheric.cli.CliCmd
import org.koin.core.component.inject
import org.koin.core.qualifier.named

class GsmRepo : CliCmd(
	name  = "repo",
	desc  = "Script repository management.",
	opts = setOf(Opt.ARGS_REQUIRED),
) {

	// Subcommand injection.
	private val cmdSet by inject<CliCmd>(named("gsm_repo_set"))

	init {
		subcommands(
			cmdSet,
		)
	}

}
