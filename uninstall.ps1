$thispath = Split-Path -Parent $MyInvocation.MyCommand.Path
echo "Script running from $thispath"

$binpath = Join-Path -Path $thispath -ChildPath bin
$scriptpath = [Environment]::GetEnvironmentVariable('home', 'Machine')
$scriptpath = Join-Path -Path $scriptpath -ChildPath "/.git/script-bin"
$rempath = "$binpath;$scriptpath"

$oldpath = [Environment]::GetEnvironmentVariable('path', 'Machine')
if ($oldpath -like "*$rempath*") {
	echo "Removing $rempath from Path"

	$newpath = $oldpath.replace(";$rempath", '')

	[Environment]::SetEnvironmentVariable('path', $newpath, 'Machine')

	$newpath = [Environment]::GetEnvironmentVariable('path', 'Machine')
	echo "Path updated to be $newpath"

} else {
    echo "Path doesn't contain $rempath"

}

