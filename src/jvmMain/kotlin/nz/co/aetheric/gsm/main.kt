package nz.co.aetheric.gsm

import okio.FileSystem
import java.lang.System.getenv

actual fun getFileSystem(): FileSystem
	= FileSystem.SYSTEM

actual val homeDir: String
	get() = getenv("HOME")
		?: getenv("USERPROFILE")
		?: "~"

actual val xdgConfigDir: String
	get() = getenv("XDG_CONFIG_DIR")
		?: "$homeDir/.config/gsmcfg"

actual fun main(args: Array<String>) = start(args)
