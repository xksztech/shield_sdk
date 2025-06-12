# 路径
$modulePath = "com/xksztech/shieldsdk/1.0.0"
$sourceDir = "$PSScriptRoot\dfshield\build\tmp\repo\$modulePath"
$zipOutput = "$PSScriptRoot\shieldsdk-1.0.0-upload.zip"

# 先清除多余校验文件
Get-ChildItem -Path $sourceDir -File | Where-Object {
    $_.Extension -notin @(".aar", ".pom", ".jar", ".asc", ".md5", ".sha1")
} | Remove-Item -Force

# 正确生成 .md5 / .sha1（仅一层）
Get-ChildItem -Path $sourceDir -File | ForEach-Object {
    $file = $_.FullName
    $basename = $_.Name

    if (-not (Test-Path "$file.md5")) {
        $hash = Get-FileHash -Path $file -Algorithm MD5
        $hash.Hash.ToLower() | Out-File "$file.md5" -Encoding ascii
    }

    if (-not (Test-Path "$file.sha1")) {
        $hash = Get-FileHash -Path $file -Algorithm SHA1
        $hash.Hash.ToLower() | Out-File "$file.sha1" -Encoding ascii
    }
}

# 打包为 zip（保留目录结构）
Compress-Archive -Path "$PSScriptRoot\dfshield\build\tmp\repo\com" -DestinationPath $zipOutput -Force

Write-Output "`n✅ ZIP 包已生成：$zipOutput"
