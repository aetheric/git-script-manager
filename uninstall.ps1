$thispath = Split-Path -Parent $MyInvocation.MyCommand.Path
echo "Script running from $thispath"

$binpath = Join-Path -Path $thispath -ChildPath bin

$oldpath = [Environment]::GetEnvironmentVariable('path', 'Machine')
if ($oldpath -like "*$binpath*") {
	echo "Removing $binpath from Path"

	$newpath = $oldpath.replace(";$binpath", '')

	[Environment]::SetEnvironmentVariable('path', $newpath, 'Machine')

	$newpath = [Environment]::GetEnvironmentVariable('path', 'Machine')
	echo "Path updated to be $newpath"

} else {
    echo "Path doesn't contain $binpath"

}

