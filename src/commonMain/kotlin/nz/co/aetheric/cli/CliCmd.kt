package nz.co.aetheric.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.PrintHelpMessage
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent

abstract class CliCmd(
	name: String,
	desc: String,
	note: String = "",
	tags: Map<String, String> = emptyMap(),
	opts: Set<Opt> = emptySet(),

) : CliktCommand(
	name     = name,
	help     = desc,
	epilog   = note,
	helpTags = tags,

	autoCompleteEnvvar = "", // ??

	printHelpOnEmptyArgs      = Opt.ARGS_REQUIRED  in opts,
	allowMultipleSubcommands  = Opt.SUBS_MULTIPLE  in opts,
	hidden                    = Opt.HIDDEN         in opts,
	invokeWithoutSubcommand   = Opt.SUBS_REQUIRED !in opts,
	treatUnknownOptionsAsArgs = Opt.ARGS_TOLERANT  in opts,

), KoinComponent {

	/** Default action for commands is to print help, unless overridden. **/
	override fun run(): Unit = throw PrintHelpMessage(this, false)

	enum class Opt {
		ARGS_REQUIRED,
		ARGS_TOLERANT,
		SUBS_MULTIPLE,
		SUBS_REQUIRED,
		HIDDEN,
	}

}
