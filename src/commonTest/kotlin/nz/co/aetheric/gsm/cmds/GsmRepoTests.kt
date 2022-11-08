package nz.co.aetheric.gsm.cmds

import com.github.ajalt.clikt.core.PrintHelpMessage
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.koin.KoinExtension
import nz.co.aetheric.TestCommand
import nz.co.aetheric.cli.CliCmd
import okio.FileSystem
import okio.fakefilesystem.FakeFileSystem
import org.koin.core.component.inject
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.named
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.withOptions
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.test.KoinTest

// https://ajalt.github.io/clikt/advanced/#testing-your-clikt-cli
class GsmRepoTests : DescribeSpec(), KoinTest {

	override fun extensions() = listOf(
		KoinExtension(module {
			singleOf(::GsmRepo)     { bind<CliCmd>(); named("gsm_repo") }
			singleOf(::TestCommand) { bind<CliCmd>(); named("gsm_repo_set") }
		}),
	)

	val target: GsmRepo by inject(named("gsm_repo"))
	val testCmd: TestCommand by inject(named("gsm_repo_set"))

	init {

		describe("a_GsmRepo_cmd_instance") {

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
