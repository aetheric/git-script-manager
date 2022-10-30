//package nz.co.aetheric.gsm.cmds
//
//import com.github.ajalt.clikt.core.CliktCommand
//import com.github.ajalt.clikt.core.PrintHelpMessage
//import com.github.ajalt.clikt.core.subcommands
//import io.mockk.mockkClass
//import okio.fakefilesystem.FakeFileSystem
//
//// https://ajalt.github.io/clikt/advanced/#testing-your-clikt-cli
//@DisplayName("GsmRepo")
//class GsmRepoTest {
//	val target = GsmRepo()
//	val files = target.currentContext.findOrSetObject { FakeFileSystem() }
//
//	@AfterEach
//	fun tearDown() {
//		files.checkNoOpenFiles()
//	}
//
//	@Test
//	@DisplayName("when given no arguments")
//	fun testParseNothing() {
//		assertThrows<PrintHelpMessage>("should print the help message.") {
//			target.parse(listOf())
//		}
//	}
//
//	@Test
//	@DisplayName("when given a subcommand")
//	fun testParseRepo() {
//		val subcommandName = "repo"
//		val subcommand = mockkClass(CliktCommand::class, relaxed = true)
//		assertThrows<PrintHelpMessage>("should print the help message.") {
//			target.subcommands(subcommand)
//				.parse(listOf(subcommandName))
//		}
//	}
//
//}
