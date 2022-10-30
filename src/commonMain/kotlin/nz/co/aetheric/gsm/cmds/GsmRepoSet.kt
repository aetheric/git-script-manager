package nz.co.aetheric.gsm.cmds

import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import nz.co.aetheric.cli.CliCmd
import nz.co.aetheric.gsm.data.ConfigScope
import nz.co.aetheric.gsm.data.Repository
import nz.co.aetheric.gsm.data.Settings
import okio.FileSystem
import org.koin.core.component.inject

// "add", "Add/update a git repository for tracking scripts on."
class GsmRepoSet : CliCmd(
	name = "set",
	desc = "Adds/updates/removes a script repository from tracking."

) {

	private val alias: String by argument(
		help = "What name to file the repository under.",
	)

	private val source: String by argument(
		help = "A git-compatible URI to the repository.",
	)

	private val force: Boolean by option(
		help = "Don't prompt to overwrite existing entries.",
	).flag("--force")

	private val local: Boolean by option(
		help = "Whether config should be saved locally.",
	).flag("--local")

	private val files : FileSystem by inject()

	override fun run() {

		val userSettings = Settings.load(files, *ConfigScope.USER.locations)
		val localSettings = Settings.load(files, *ConfigScope.LOCAL.locations)

		val repo = localSettings.repositories[alias]
			?: userSettings.repositories[alias]

		val targetScope = if (local) ConfigScope.LOCAL else ConfigScope.USER
		val dstSettings = ( if (local) localSettings else userSettings )

		// Check if the entry already exist.
		if (repo == null) {
			echo("Adding new entry.")
			dstSettings.copy(
				repositories = dstSettings.repositories
					.plus(alias to Repository(source))
			).save(files, *targetScope.locations)
			return
		}

		// If entries are equal, print a warning and exit.
		if (repo.source == source) {
			echo("No need to change sources.", err = true)
			return
		}

		// Otherwise, prompt to overwrite
		if (!force) {
			val result = prompt("Overwrite '${repo.source}'? (Y/n)",
				default = "Y", showDefault = false)?.lowercase()
			if (result != "y" && result != "yes") {
				echo("Aborting operation.", err = true)
				return
			}
		}

		val dstRepos = dstSettings.repositories
		val dstRepo = dstRepos[alias]?.copy(source = source)
			?: Repository(source)

		echo("Overwriting existing entry.", err = true)
		dstSettings.copy(
			repositories = dstRepos.plus(alias to dstRepo)
		).save(files, *targetScope.locations)

	}
}
