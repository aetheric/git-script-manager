package nz.co.aetheric.gsm.cmds

import com.github.ajalt.clikt.core.MissingArgument
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.koin.KoinExtension
import io.kotest.matchers.shouldBe
import nz.co.aetheric.gsm.data.ConfigScope
import okio.FileSystem
import okio.Path.Companion.toPath
import okio.fakefilesystem.FakeFileSystem
import org.koin.core.component.inject
import org.koin.core.module.dsl.*
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.test.KoinTest
import kotlin.test.assertFalse

// https://ajalt.github.io/clikt/advanced/#testing-your-clikt-cli
class GsmRepoSetTests : DescribeSpec(), KoinTest {

	override fun extensions() = listOf(
		KoinExtension(module {
			singleOf(::GsmRepoSet) { named("gsm_repo_set") }
			single { FakeFileSystem() } withOptions {
				bind<FileSystem>()
			}
		}),
	)

	val target: GsmRepoSet by inject(named("gsm_repo_set"))
	val files: FakeFileSystem by inject()

	init {
		val configPath = ConfigScope.USER.locations[0].toPath(true)

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
			val repoAlias = "butts"
			val repoUrl   = "https://github.com/tzrlk/git-utils.git"

			describe("a_config_file_doesnt_already_exist") {

				beforeEach {
					assertFalse(files.exists(configPath), "should_start_with_no_config_file")
				}

				it("should_create_the_config_file_with_expected_content") {
					target.parse(listOf(repoAlias, repoUrl))
					files.exists(configPath) shouldBe true
					files.read(configPath) { readUtf8() } shouldBe "{\"repositories\":{\"${repoAlias}\":{\"source\":\"${repoUrl}\"}}}"
				}

			}
		}

	}
}
