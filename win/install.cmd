@ECHO OFF

SET binpath=%~dp0bin

SET pswrap="& {Start-Process PowerShell -ArgumentList '-NoProfile -ExecutionPolicy Bypass -File ""./install.ps1""' -Verb RunAs}"
powershell -NoProfile -ExecutionPolicy Bypass -Command %pswrap%

