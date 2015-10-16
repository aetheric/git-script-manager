$thispath = Split-Path -Parent $MyInvocation.MyCommand.Path
echo "Script running from $thispath"

$binpath = Join-Path -Path $thispath -ChildPath bin
$scriptpath = [Environment]::GetEnvironmentVariable('home', 'Machine')
$scriptpath = Join-Path -Path $scriptpath -ChildPath "/.git/script-bin"
$addpath = "$binpath;$scriptpath"

$oldpath = [Environment]::GetEnvironmentVariable('path', 'Machine')
if ($oldpath -like "*$addpath*") {
    echo "Path already contains $addpath"

} else {

	$newpath = "$oldpath;$addpath"

	echo "Adding $addpath to Path"
	[Environment]::SetEnvironmentVariable('path', $newpath, 'Machine')

	$newpath = [Environment]::GetEnvironmentVariable('path', 'Machine')
	echo "Path updated to be $newpath"

}

