package nz.co.aetheric.gsm.cmds

import nz.co.aetheric.cli.CliCmd
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.named
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val commands = module {
	singleOf(::Gsm)        { bind<CliCmd>(); named("gsm") }
	singleOf(::GsmRepo)    { bind<CliCmd>(); named("gsm_repo") }
	singleOf(::GsmRepoSet) { bind<CliCmd>(); named("gsm_repo_set") }
}
