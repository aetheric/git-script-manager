package nz.co.aetheric.gsm.cmds

import com.github.ajalt.clikt.core.MissingArgument
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.koin.KoinExtension
import io.kotest.matchers.shouldBe
import nz.co.aetheric.gsm.data.ConfigScope
import okio.Path.Companion.toPath
import okio.fakefilesystem.FakeFileSystem
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import kotlin.test.assertFalse

// https://ajalt.github.io/clikt/advanced/#testing-your-clikt-cli
class GsmRepoSetTest : DescribeSpec(), KoinComponent {

	override fun isolationMode() = IsolationMode.InstancePerTest
	override fun extensions() = listOf(
		KoinExtension(module {
			singleOf(::GsmRepoSet)
			singleOf(::FakeFileSystem)
		}),
	)

	init {
		val files: FakeFileSystem by inject()
		val target: GsmRepoSet by inject()
		val configPath = ConfigScope.USER.locations[0].toPath(true)

		beforeAny {
		}

		afterAny {
			files.checkNoOpenFiles()
		}

		describe("no_arguments") {
			it("should_fail_validation") {
				shouldThrow<MissingArgument> {
					target.parse(listOf())
				}
			}
		}

		describe("minimal_arguments") {
			val testArgs = listOf("butts", "https://github.com/tzrlk/git-utils.git")

			describe("a_config_file_doesnt_already_exist") {

				beforeEach {
					assertFalse(files.exists(configPath), "should_start_with_no_config_file")
				}

				it("should_create_the_config_file_with_expected_content") {
					target.parse(testArgs)
					files.exists(configPath) shouldBe true
					files.read(configPath) { readUtf8() } shouldBe "{}"
				}

			}
		}

	}
}
