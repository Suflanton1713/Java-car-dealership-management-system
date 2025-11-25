@echo off
echo Compilando proyecto...
echo.
javac -source 1.8 -target 1.8 -encoding UTF-8 -d . *.java controlador\*.java servicio\*.java vista\*.java
if %errorlevel% == 0 (
    echo.
    echo ========================================
    echo Compilacion exitosa!
    echo ========================================
    echo.
    echo Ejecutando aplicacion...
    echo.
    java proyecto.Main
) else (
    echo.
    echo ========================================
    echo Error en la compilacion!
    echo ========================================
    pause
)

