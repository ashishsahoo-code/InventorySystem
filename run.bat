@echo off
setlocal

:: JavaFX SDK paths
set FX_LIB=lib\javafx-sdk-24\lib
set FX_BIN=lib\javafx-sdk-24\bin

:: Classpath: your libraries and compiled output
set CP=lib\sqlite-jdbc.jar;lib\itext-2.1.7.jar;out

:: JavaFX VM options
set JAVA_OPTS=--add-opens=javafx.graphics/javafx.stage=ALL-UNNAMED ^
 -Dprism.order=sw -Dprism.verbose=true ^
 -Djava.library.path=%FX_BIN%

:: Clean and compile
if exist out rmdir /s /q out
mkdir out

echo === Compiling Java files... ===
javac -d out -cp "%CP%" --module-path "%FX_LIB%" --add-modules javafx.controls,javafx.fxml ^
 src\InventorySystem\Main.java ^
 src\controller\*.java ^
 src\model\*.java ^
 src\util\*.java ^
 src\view\*.java

if %errorlevel% neq 0 (
    echo Compilation failed.
    pause
    exit /b
)

echo.
echo === Running application... ===
java %JAVA_OPTS% -cp "%CP%" --module-path "%FX_LIB%" --add-modules javafx.controls,javafx.fxml InventorySystem.Main

pause
