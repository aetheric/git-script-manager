package nz.co.aetheric.gsm.cmds

import com.github.ajalt.clikt.core.PrintHelpMessage
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.koin.KoinExtension
import nz.co.aetheric.TestCommand
import okio.fakefilesystem.FakeFileSystem
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

// https://ajalt.github.io/clikt/api/clikt/com.github.ajalt.clikt.core/-context/-builder/envvar-reader.html
// https://ajalt.github.io/clikt/advanced/#testing-your-clikt-cli
class GsmTests : DescribeSpec(), KoinComponent {

	override fun isolationMode() = IsolationMode.InstancePerTest
	override fun extensions() = listOf(
		KoinExtension(module {
			singleOf(::GsmRepo)
			singleOf(::FakeFileSystem)
		}),
	)

	init {
		val files: FakeFileSystem by inject()

		beforeAny {
		}

		afterAny {
			files.checkNoOpenFiles()
		}

		describe("a_Gsm_cmd_instance") {
			val target: Gsm by inject()

			describe("given_no_arguments") {
				it("should_print_the_help_message") {
					shouldThrow<PrintHelpMessage> {
						target.parse(listOf())
					}
				}
			}

			describe("given_a_subcommand") {
				val testCmd: TestCommand by inject()
				it("should_invoke_the_subcommand") {
					shouldThrow<PrintHelpMessage> {
						target.parse(listOf(testCmd.commandName))
					}
				}
			}

		}

	}

}
