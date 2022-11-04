package nz.co.aetheric

import nz.co.aetheric.cli.CliCmd

open class TestCommand : CliCmd(
	name = "test",
	desc = "Tests subcommand functionality.",
)
