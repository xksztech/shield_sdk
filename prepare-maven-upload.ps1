# 设置模块路径
$groupIdPath = "com\xksztech\shieldsdk\1.0.0"
$baseDir = "$PSScriptRoot\dfshield\build\tmp\repo\$groupIdPath"

# 检查路径
if (!(Test-Path $baseDir)) {
    Write-Error "构建输出路径不存在：$baseDir"
    exit 1
}

Write-Host "正在生成 MD5 和 SHA1 校验文件..."

# 生成 .md5 和 .sha1
Get-ChildItem "$baseDir\*" -Include *.pom,*.aar,*.jar,*.asc | ForEach-Object {
    $file = $_.FullName
    $md5 = Get-FileHash $file -Algorithm MD5
    $sha1 = Get-FileHash $file -Algorithm SHA1
    Set-Content "$file.md5" $md5.Hash
    Set-Content "$file.sha1" $sha1.Hash
    Write-Host "✔️ 已处理：" $_.Name
}

# 进入 repo 根目录打包为 zip
$repoRoot = "$PSScriptRoot\dfshield\build\tmp\repo"
$zipOutput = "$PSScriptRoot\dfshield\build\distributions\shieldsdk-1.0.0-upload.zip"

# 创建目标目录
New-Item -ItemType Directory -Force -Path (Split-Path $zipOutput) | Out-Null

# 打包 zip（保留目录结构）
Add-Type -Assembly 'System.IO.Compression.FileSystem'
[System.IO.Compression.ZipFile]::CreateFromDirectory($repoRoot, $zipOutput)

Write-Host "`n✅ 已成功打包为 zip 文件：$zipOutput"
Write-Host "请在浏览器中访问：https://s01.oss.sonatype.org -> Staging Upload 上传该 zip 包。"
