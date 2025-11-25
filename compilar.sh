#!/bin/bash
echo "Compilando proyecto..."
javac -encoding UTF-8 -d . *.java controlador/*.java servicio/*.java vista/*.java
if [ $? -eq 0 ]; then
    echo "Compilación exitosa!"
    echo ""
    echo "Ejecutando aplicación..."
    java proyecto.Main
else
    echo "Error en la compilación!"
fi

