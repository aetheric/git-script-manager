$thispath = Split-Path -Parent $MyInvocation.MyCommand.Path
echo "Script running from $thispath"

$binpath = Join-Path -Path $thispath -ChildPath bin

$oldpath = [Environment]::GetEnvironmentVariable('path', 'Machine')
if ($oldpath -like "*$binpath*") {
    echo "Path already contains $binpath"

} else {

	echo "Adding $binpath to Path"
	[Environment]::SetEnvironmentVariable('path', $oldpath + ';' + $binpath, 'Machine')

	$newpath = [Environment]::GetEnvironmentVariable('path', 'Machine')
	echo "Path updated to be $newpath"

}

