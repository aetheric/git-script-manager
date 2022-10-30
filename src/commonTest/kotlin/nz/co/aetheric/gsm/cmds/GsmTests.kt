package nz.co.aetheric.gsm.cmds

import com.github.ajalt.clikt.core.PrintHelpMessage
import io.kotest.assertions.throwables.shouldThrow
import nz.co.aetheric.CliTest
import nz.co.aetheric.cli.CliCmd
import okio.fakefilesystem.FakeFileSystem
import org.koin.core.component.inject
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

// https://ajalt.github.io/clikt/api/clikt/com.github.ajalt.clikt.core/-context/-builder/envvar-reader.html
// https://ajalt.github.io/clikt/advanced/#testing-your-clikt-cli
class GsmTests : CliTest(module {
	singleOf(::Gsm)
	singleOf(::TestCommand)
	singleOf(::FakeFileSystem)

}) {

	init {
		val files: FakeFileSystem by inject()

		beforeAny {
		}

		afterAny {
			files.checkNoOpenFiles()
		}

		Given("a Gsm cmd instance") {
			val target: Gsm by inject()
			When("given no arguments") {
				Then("the help message should be printed.") {
					shouldThrow<PrintHelpMessage> {
						target.parse(listOf())
					}
				}
			}

			When("given a subcommand") {
				val testCmd: TestCommand by inject()
				Then("the subcommand should be invoked") {
					shouldThrow<PrintHelpMessage> {
						target.parse(listOf(testCmd.commandName))
					}
				}
			}

		}

	}

	class TestCommand : CliCmd("test", "Tests subcommand functionality.")

}
