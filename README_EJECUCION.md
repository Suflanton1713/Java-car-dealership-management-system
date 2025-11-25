# Cómo Ejecutar el Sistema de Concesionaria

## Opción 1: Desde NetBeans (Recomendado)

1. **Abrir NetBeans**
2. **File → Open Project** (o Ctrl+Shift+O)
3. Seleccionar la carpeta `leo-java`
4. NetBeans detectará automáticamente los archivos Java
5. **Click derecho en `Main.java` → Run File** (o presionar F6)
6. O hacer **Run → Run Project** (F6)

## Opción 2: Desde Línea de Comandos (Windows)

### Método Rápido:
1. Abrir PowerShell o CMD en la carpeta del proyecto
2. Ejecutar:
```bash
compilar.bat
```

### Método Manual:
1. Abrir PowerShell o CMD en la carpeta del proyecto
2. Compilar todos los archivos:
```bash
javac -encoding UTF-8 -d . *.java controlador\*.java servicio\*.java vista\*.java
```

3. Ejecutar:
```bash
java proyecto.Main
```

## Opción 3: Desde Línea de Comandos (Linux/Mac)

1. Abrir Terminal en la carpeta del proyecto
2. Dar permisos de ejecución:
```bash
chmod +x compilar.sh
```

3. Ejecutar:
```bash
./compilar.sh
```

O manualmente:
```bash
javac -encoding UTF-8 -d . *.java controlador/*.java servicio/*.java vista/*.java
java proyecto.Main
```

## Requisitos

- **Java JDK 8 o superior** instalado
- Verificar instalación:
```bash
java -version
javac -version
```

## Credenciales de Prueba

Al ejecutar, aparecerá la ventana de Login. Usa estas credenciales:

### Administrador:
- **Usuario:** `admin`
- **Contraseña:** `admin123`

### Vendedor:
- **Usuario:** `vendedor`
- **Contraseña:** `vendedor123`

## Solución de Problemas

### Error: "javac no se reconoce como comando"
- Instalar Java JDK
- Agregar Java al PATH del sistema

### Error: "No se puede encontrar la clase Main"
- Asegurarse de estar en la carpeta raíz del proyecto
- Verificar que Main.java esté en la carpeta raíz

### Error de compilación por encoding
- El script usa `-encoding UTF-8` para evitar problemas con caracteres especiales

## Estructura del Proyecto

```
leo-java/
├── Main.java (punto de entrada)
├── *.java (clases del modelo)
├── controlador/ (controladores MVC)
├── servicio/ (lógica de negocio)
└── vista/ (interfaces gráficas)
```

