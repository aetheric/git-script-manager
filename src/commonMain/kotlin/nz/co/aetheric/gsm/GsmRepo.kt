package nz.co.aetheric.gsm

import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand

@OptIn(ExperimentalCli::class)
class GsmRepo : Subcommand("repo", "Script repository management.") {
	init {
		subcommands(
			GsmRepoAdd(),
		)
	}

	override fun execute() {
		TODO("Not yet implemented")
	}

}
