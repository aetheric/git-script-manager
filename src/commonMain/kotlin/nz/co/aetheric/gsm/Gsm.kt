package nz.co.aetheric.gsm

import kotlinx.cli.ArgParser
import kotlinx.cli.ExperimentalCli

@OptIn(ExperimentalCli::class)
class Gsm : ArgParser("gsm") {
	init {
		subcommands(
			GsmRepo(),
		)
	}
}
