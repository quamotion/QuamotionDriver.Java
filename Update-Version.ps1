$file = Get-Item .\pom.xml
$pom = [xml](Get-Content $file)
$pom.project.version = $pom.project.version + "." + $env:APPVEYOR_BUILD_NUMBER
Write-Host "Version is now set to $pom.project.version"

$pom.Save($file.FullName)