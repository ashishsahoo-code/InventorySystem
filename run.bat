@echo off
echo === Compiling Java files... ===
javac -cp ".;lib/*;lib/javafx-sdk-24/lib/*" -d bin src/**/*.java

echo.
echo === Running application... ===
java -cp ".;bin;lib/*;lib/javafx-sdk-24/lib/*" ^
--module-path lib/javafx-sdk-24/lib ^
--add-modules javafx.controls,javafx.fxml ^
InventorySystem.Main

pause
