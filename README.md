# Sistema de Gestión de Concesionaria de Automóviles

## 📋 Descripción del Proyecto

Sistema completo de gestión para una concesionaria de automóviles desarrollado en Java utilizando programación orientada a objetos (POO), arquitectura MVC (Modelo-Vista-Controlador) y Java Swing para la interfaz gráfica.

El sistema permite gestionar vehículos, clientes, empleados, promociones, cotizaciones, ventas, pagos, reportes y reservas de test drive, con control de acceso basado en roles (Administrador y Vendedor).

## 🎯 Características Principales

### Módulos Implementados

1. **Módulo de Personas y Seguridad**
   - Gestión de empleados (Admin/Vendedor)
   - Gestión de clientes
   - Sistema de autenticación con roles
   - Control de acceso según permisos

2. **Módulo de Vehículos**
   - CRUD completo de vehículos
   - Soporte para Sedán y SUV (herencia)
   - Control de estados (DISPONIBLE, VENDIDO, RESERVADO)
   - Gestión de precios

3. **Módulo de Promociones**
   - Promociones por porcentaje
   - Promociones con descuento fijo
   - Activación/desactivación de promociones
   - Implementación de interfaz `Descontable` para polimorfismo

4. **Módulo de Cotizaciones**
   - Generación de cotizaciones
   - Cálculo automático de precios con promociones
   - Selección de cliente, vehículo y promoción

5. **Módulo de Ventas**
   - Conversión de cotizaciones en ventas
   - Generación de comprobantes (Boleta/Factura)
   - Cambio automático de estado del vehículo a VENDIDO
   - Polimorfismo en generación de comprobantes

6. **Módulo de Pagos**
   - Registro de pagos parciales
   - Cálculo de saldo pendiente
   - Historial de pagos por venta
   - Múltiples métodos de pago (Efectivo, Tarjeta, Transferencia)

7. **Módulo de Reportes**
   - Reporte de ventas por vendedor
   - Reporte de vehículos vendidos y disponibles
   - Reporte de clientes frecuentes
   - Implementación de interfaz `Reporteable` para polimorfismo

8. **Módulo de Test Drive**
   - Agenda de test drives
   - Reserva de vehículos para pruebas
   - Control de estados de reservas
   - Gestión de disponibilidad

## 🏗️ Arquitectura del Proyecto

### Patrón de Diseño: MVC (Modelo-Vista-Controlador)

- **Modelo**: Clases de datos (`Persona`, `Vehiculo`, `Venta`, etc.) y arreglos (`ArregloVehiculos`, `ArregloClientes`, etc.)
- **Vista**: Interfaces gráficas Swing (`FrmLogin`, `FrmGestionVehiculos`, etc.)
- **Controlador**: Clases que conectan vista con modelo (`ControladorVehiculo`, `ControladorCliente`, etc.)

### Conceptos POO Aplicados

- ✅ **Herencia**: `Empleado` y `Cliente` extienden `Persona`; `Sedan` y `SUV` extienden `Vehiculo`; `Boleta` y `Factura` extienden `Comprobante`
- ✅ **Polimorfismo**: Interfaces `Descontable` y `Reporteable`; método abstracto `generarTexto()` en `Comprobante`
- ✅ **Clases Abstractas**: `Persona`, `Vehiculo`, `Comprobante`
- ✅ **Interfaces**: `Descontable`, `Reporteable`
- ✅ **Encapsulación**: Atributos privados con getters y setters
- ✅ **Singleton**: `DatosSistema` para compartir instancias entre pantallas

## 📁 Estructura del Proyecto

```
leo-java/
├── Main.java                          # Punto de entrada de la aplicación
├── DatosSistema.java                  # Singleton para datos compartidos
│
├── Modelo (Clases de Datos)
│   ├── Persona.java                   # Clase abstracta base
│   ├── Empleado.java                   # Extiende Persona
│   ├── Cliente.java                    # Extiende Persona
│   ├── Rol.java                        # Enum (ADMIN, VENDEDOR)
│   │
│   ├── Vehiculo.java                   # Clase abstracta
│   ├── Sedan.java                      # Extiende Vehiculo
│   ├── SUV.java                        # Extiende Vehiculo
│   │
│   ├── Promocion.java                  # Clase base
│   ├── PromocionPorcentaje.java        # Extiende Promocion, implementa Descontable
│   ├── PromocionFija.java              # Extiende Promocion, implementa Descontable
│   │
│   ├── Cotizacion.java
│   ├── Venta.java
│   ├── Pago.java
│   ├── Comprobante.java                # Clase abstracta
│   ├── Boleta.java                     # Extiende Comprobante
│   ├── Factura.java                    # Extiende Comprobante
│   ├── ReservaTestDrive.java
│   │
│   ├── Descontable.java                # Interfaz
│   ├── Reporteable.java                # Interfaz
│   ├── ReporteVentasPorVendedor.java   # Implementa Reporteable
│   ├── ReporteVehiculosVendidos.java   # Implementa Reporteable
│   ├── ReporteClientesFrecuentes.java  # Implementa Reporteable
│   │
│   └── Arreglo*.java                   # Arreglos estáticos para gestión
│
├── servicio/
│   └── ServicioLogin.java              # Lógica de autenticación
│
├── controlador/
│   ├── ControladorLogin.java
│   ├── ControladorVehiculo.java
│   ├── ControladorCliente.java
│   ├── ControladorEmpleado.java
│   ├── ControladorPromocion.java
│   ├── ControladorCotizacion.java
│   ├── ControladorVenta.java
│   ├── ControladorPago.java
│   ├── ControladorReporte.java
│   └── ControladorReserva.java
│
└── vista/
    ├── FrmLogin.java                   # Ventana de inicio de sesión
    ├── FrmMenuPrincipal.java           # Menú principal según rol
    ├── FrmGestionVehiculos.java        # CRUD de vehículos
    ├── FrmGestionClientes.java         # CRUD de clientes
    ├── FrmGestionEmpleados.java        # CRUD de empleados
    ├── FrmGestionPromociones.java     # CRUD de promociones
    ├── FrmCotizacion.java              # Generación de cotizaciones
    ├── FrmVenta.java                   # Registro de ventas
    ├── FrmPagos.java                   # Gestión de pagos
    ├── FrmReportes.java                # Visualización de reportes
    └── FrmReservaTestDrive.java        # Agenda de test drives
```

## 🚀 Cómo Ejecutar el Proyecto

### Requisitos Previos

- **Java JDK 8 o superior** instalado
- Verificar instalación:
  ```bash
  java -version
  javac -version
  ```

### Opción 1: Script Automático (Recomendado)

**Windows:**
```bash
.\compilar.bat
```

**Linux/Mac:**
```bash
chmod +x compilar.sh
./compilar.sh
```

### Opción 2: Compilación Manual

1. **Compilar:**
   ```bash
   javac -source 1.8 -target 1.8 -encoding UTF-8 -d . *.java controlador\*.java servicio\*.java vista\*.java
   ```

2. **Ejecutar:**
   ```bash
   java proyecto.Main
   ```

### Opción 3: Desde NetBeans

1. Abrir NetBeans
2. **File → Open Project**
3. Seleccionar la carpeta `leo-java`
4. Click derecho en `Main.java` → **Run File** (F6)

## 🔐 Credenciales de Acceso

### Administrador (Acceso Completo)
- **Usuario:** `admin`
- **Contraseña:** `admin123`
- **Permisos:** Acceso a todos los módulos (Empleados, Vehículos, Clientes, Promociones, Ventas, Reportes)

### Vendedor (Acceso Limitado)
- **Usuario:** `vendedor`
- **Contraseña:** `vendedor123`
- **Permisos:** Solo acceso a Clientes, Cotizaciones y Ventas

## 💾 Persistencia de Datos

**Importante:** El sistema trabaja en **memoria (RAM)** durante la ejecución. Los datos se comparten entre todas las pantallas mediante el patrón Singleton (`DatosSistema`), pero **no se guardan permanentemente**. Al cerrar el programa, todos los datos se pierden excepto los usuarios iniciales que están hardcoded.

### Datos Iniciales

Al iniciar el sistema, se crean automáticamente:
- 1 Administrador (admin/admin123)
- 1 Vendedor (vendedor/vendedor123)

## 🎨 Tecnologías Utilizadas

- **Java SE 8+**
- **Java Swing** (Interfaz gráfica)
- **Arquitectura MVC** (Modelo-Vista-Controlador)
- **Programación Orientada a Objetos** (POO)
- **Patrón Singleton** (DatosSistema)
- **Arreglos estáticos** (sin Collections dinámicas)

## 📊 Funcionalidades por Módulo

### Gestión de Vehículos
- Agregar vehículos (Sedán o SUV)
- Modificar información
- Eliminar vehículos
- Buscar por código
- Listar todos los vehículos
- Control de estados (DISPONIBLE, VENDIDO, RESERVADO)

### Gestión de Clientes
- CRUD completo de clientes
- Búsqueda por DNI
- Listado en tabla

### Gestión de Empleados
- CRUD completo (solo Admin)
- Asignación de roles
- Creación de usuarios para login

### Gestión de Promociones
- Crear promociones por porcentaje o fijas
- Activar/desactivar promociones
- Aplicar en cotizaciones

### Cotizaciones
- Seleccionar cliente, vehículo y promoción
- Cálculo automático de precio final
- Generación de cotización

### Ventas
- Convertir cotización en venta
- Generar Boleta o Factura
- Cambio automático de estado del vehículo

### Pagos
- Registrar pagos parciales
- Ver historial de pagos
- Calcular saldo pendiente

### Reportes
- Ventas por vendedor
- Vehículos vendidos vs disponibles
- Clientes frecuentes (ordenados por cantidad de compras)

### Test Drive
- Agendar test drives
- Completar o cancelar reservas
- Control de disponibilidad de vehículos

## 🔧 Solución de Problemas

### Error: "javac no se reconoce como comando"
- Instalar Java JDK
- Agregar Java al PATH del sistema

### Error: "No se puede encontrar la clase Main"
- Asegurarse de estar en la carpeta raíz del proyecto
- Verificar que Main.java esté presente

### Error de versión de Java
- El script compila con `-source 1.8 -target 1.8` para compatibilidad
- Verificar que Java Runtime sea compatible

### Los datos no se guardan entre pantallas
- Verificar que se esté usando `DatosSistema.getInstancia()`
- Asegurarse de que las pantallas compartan las mismas instancias

## 📝 Notas de Desarrollo

- **Sin Bases de Datos**: El proyecto usa arreglos estáticos en memoria
- **Sin Collections Dinámicas**: No se usan ArrayList, List, Vector o Map
- **Arquitectura MVC**: Separación clara entre Modelo, Vista y Controlador
- **POO Pura**: Aplicación de herencia, polimorfismo, clases abstractas e interfaces
- **Singleton Pattern**: DatosSistema asegura una única instancia de cada arreglo

## 👥 Autor

Proyecto desarrollado como trabajo final del curso de Programación Orientada a Objetos.

## 📄 Licencia

Este proyecto es de uso educativo.

---

**Versión:** 1.0  
**Última actualización:** 2024

