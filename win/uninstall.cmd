@ECHO OFF

SET pswrap="& {Start-Process PowerShell -ArgumentList '-NoProfile -ExecutionPolicy Bypass -File ""%~dp0/uninstall.ps1""' -Verb RunAs}"
powershell -NoProfile -ExecutionPolicy Bypass -Command %pswrap%

