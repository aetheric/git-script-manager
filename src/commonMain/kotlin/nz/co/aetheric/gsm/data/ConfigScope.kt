package nz.co.aetheric.gsm.data

import nz.co.aetheric.gsm.homeDir
import nz.co.aetheric.gsm.xdgConfigDir

enum class ConfigScope(
	vararg val locations: String,

) {
	USER(
		"$xdgConfigDir/gsmcfg.json",
		"$homeDir/.gsmcfg",
	),
	LOCAL(
		".config/gsmcfg.json",
		".gsmcfg",
	);
}
