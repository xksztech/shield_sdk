@echo off
setlocal enabledelayedexpansion

REM 设置 AAR 文件所在目录
set AAR_DIR=dfshield\libs

REM 设置 Maven 参数
set GROUP_ID=com.xksztech
set VERSION=1.0.0

REM 进入指定目录
cd /d %~dp0

REM 遍历目录中所有 AAR 文件
for %%f in (%AAR_DIR%\*.aar) do (
    set FILE_PATH=%%f
    for %%i in (%%~nf) do (
        set ARTIFACT_ID=%%~nxi
        echo Installing !FILE_PATH! as artifactId=%%~ni
        mvn install:install-file ^
            -Dfile=!FILE_PATH! ^
            -DgroupId=%GROUP_ID% ^
            -DartifactId=%%~ni ^
            -Dversion=%VERSION% ^
            -Dpackaging=aar
        echo.
    )
)

echo All AARs installed to local Maven repository.
pause
