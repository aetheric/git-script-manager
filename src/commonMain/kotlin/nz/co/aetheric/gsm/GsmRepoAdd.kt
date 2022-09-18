package nz.co.aetheric.gsm

import kotlinx.cli.ArgType
import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand

@OptIn(ExperimentalCli::class)
class GsmRepoAdd : Subcommand("add", "Add a git repository for tracking scripts on.") {
	val alias  by argument(ArgType.String, description = "What name to file the repository under.")
	val source by argument(ArgType.String, description = "A git-compatible URI to the repository.")
	override fun execute() {
		TODO("Not yet implemented")
	}
}
