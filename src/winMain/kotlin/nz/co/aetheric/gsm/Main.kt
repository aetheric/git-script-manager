package nz.co.aetheric.gsm

import kotlinx.cinterop.toKString
import okio.FileSystem
import platform.posix.getenv

actual fun getFileSystem(): FileSystem
	= FileSystem.SYSTEM

actual val homeDir: String
	get() = getenv("HOME")?.toKString()
		?: getenv("USERPROFILE")?.toKString()
		?: "~"

actual val xdgConfigDir: String
	get() = getenv("XDG_CONFIG_DIR")?.toKString()
		?: "$homeDir/.config/gsmcfg"

actual fun main(args: Array<String>) = start(args)
