package nz.co.aetheric.gsm.cmds

import com.github.ajalt.clikt.core.PrintHelpMessage
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.koin.KoinExtension
import nz.co.aetheric.TestCommand
import nz.co.aetheric.cli.CliCmd
import org.koin.core.component.inject
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.named
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.test.KoinTest

// https://ajalt.github.io/clikt/api/clikt/com.github.ajalt.clikt.core/-context/-builder/envvar-reader.html
// https://ajalt.github.io/clikt/advanced/#testing-your-clikt-cli
class GsmTests : DescribeSpec(), KoinTest {

	override fun extensions() = listOf(
		KoinExtension(module {
			singleOf(::Gsm)         { bind<CliCmd>(); named("gsm") }
			singleOf(::TestCommand) { bind<CliCmd>(); named("gsm_repo") }
		}),
	)

	val target: Gsm by inject(named("gsm"))
	val testCmd: TestCommand by inject(named("gsm_repo"))

	init {

		describe("a_Gsm_cmd_instance") {

			describe("given_no_arguments") {
				it("should_print_the_help_message") {
					shouldThrow<PrintHelpMessage> {
						target.parse(listOf())
					}
				}
			}

			describe("given_a_subcommand") {
				it("should_invoke_the_subcommand") {
					shouldThrow<PrintHelpMessage> {
						target.parse(listOf(testCmd.commandName))
					}
				}
			}

		}

	}

}
