/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

/**
 * Clase singleton para compartir instancias de arreglos entre todas las pantallas
 * 
 * @author USUARIO
 */
public class DatosSistema {
    
    private static DatosSistema instancia;
    
    // Arreglos compartidos
    private ArregloEmpleados arregloEmpleados;
    private ArregloClientes arregloClientes;
    private ArregloVehiculos arregloVehiculos;
    private ArregloPromociones arregloPromociones;
    private ArregloCotizaciones arregloCotizaciones;
    private ArregloVentas arregloVentas;
    private ArregloPagos arregloPagos;
    private ArregloReservas arregloReservas;

    private DatosSistema() {
        // Inicializar todos los arreglos
        arregloEmpleados = new ArregloEmpleados(100);
        arregloClientes = new ArregloClientes(100);
        arregloVehiculos = new ArregloVehiculos(100);
        arregloPromociones = new ArregloPromociones(100);
        arregloCotizaciones = new ArregloCotizaciones(100);
        arregloVentas = new ArregloVentas(100);
        arregloPagos = new ArregloPagos(100);
        arregloReservas = new ArregloReservas(100);
    }

    public static DatosSistema getInstancia() {
        if (instancia == null) {
            instancia = new DatosSistema();
        }
        return instancia;
    }

    // Getters para acceder a los arreglos
    public ArregloEmpleados getArregloEmpleados() {
        return arregloEmpleados;
    }

    public ArregloClientes getArregloClientes() {
        return arregloClientes;
    }

    public ArregloVehiculos getArregloVehiculos() {
        return arregloVehiculos;
    }

    public ArregloPromociones getArregloPromociones() {
        return arregloPromociones;
    }

    public ArregloCotizaciones getArregloCotizaciones() {
        return arregloCotizaciones;
    }

    public ArregloVentas getArregloVentas() {
        return arregloVentas;
    }

    public ArregloPagos getArregloPagos() {
        return arregloPagos;
    }

    public ArregloReservas getArregloReservas() {
        return arregloReservas;
    }
}

