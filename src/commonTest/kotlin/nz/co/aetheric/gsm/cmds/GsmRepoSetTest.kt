//package nz.co.aetheric.gsm.cmds
//
//import com.github.ajalt.clikt.core.MissingArgument
//import nz.co.aetheric.gsm.data.ConfigScope
//import okio.Path.Companion.toPath
//import okio.fakefilesystem.FakeFileSystem
//import org.junit.jupiter.api.*
//import org.junit.jupiter.api.extension.RegisterExtension
//import org.koin.core.module.dsl.singleOf
//import org.koin.dsl.module
//import org.koin.test.KoinTest
//import org.koin.test.inject
//import org.koin.test.junit5.KoinTestExtension
//import kotlin.test.assertEquals
//import kotlin.test.assertFalse
//import kotlin.test.assertTrue
//
//// https://ajalt.github.io/clikt/advanced/#testing-your-clikt-cli
//@DisplayName("GsmRepoSet")
//class GsmRepoSetTest : KoinTest {
//
//	@JvmField
//	@RegisterExtension
//	val koinExt = KoinTestExtension.create {
//		modules(module {
//			singleOf(::FakeFileSystem)
//			singleOf(::GsmRepoSet)
//		})
//	}
//
//	private val files: FakeFileSystem by inject()
//	private val target: GsmRepoSet by inject()
//
//	@AfterEach
//	fun tearDown() {
//		files.checkNoOpenFiles()
//	}
//
//	@Test
//	@DisplayName("when passed no arguments")
//	fun testParseNothing() {
//		assertThrows<MissingArgument>("should fail validation.") {
//			target.parse(listOf())
//		}
//	}
//
//	@Test
//	@DisplayName("when passed minimal arguments")
//	fun testParseRepo() {
//		val configPath = ConfigScope.USER.locations[0].toPath(true)
//		assertFalse(files.exists(configPath), "should start with no config file.")
//		target.parse(listOf("butts", "https://github.com/tzrlk/git-utils.git"))
//		assertTrue(files.exists(configPath), "should have created a config file.")
//		assertEquals("{}", files.read(configPath) { readUtf8() }, "should have the expected output")
//	}
//
//}
