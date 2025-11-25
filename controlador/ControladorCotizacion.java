/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.controlador;

import proyecto.*;
import proyecto.vista.FrmCotizacion;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author USUARIO
 */
public class ControladorCotizacion {
    
    private FrmCotizacion vista;
    private ArregloClientes arregloClientes;
    private ArregloVehiculos arregloVehiculos;
    private ArregloPromociones arregloPromociones;
    private ArregloCotizaciones arregloCotizaciones;
    private Empleado vendedor;

    public ControladorCotizacion(FrmCotizacion vista, ArregloClientes arregloClientes, 
                                  ArregloVehiculos arregloVehiculos, 
                                  ArregloPromociones arregloPromociones,
                                  ArregloCotizaciones arregloCotizaciones,
                                  Empleado vendedor) {
        this.vista = vista;
        this.arregloClientes = arregloClientes;
        this.arregloVehiculos = arregloVehiculos;
        this.arregloPromociones = arregloPromociones;
        this.arregloCotizaciones = arregloCotizaciones;
        this.vendedor = vendedor;
    }

    public void calcularPrecio() {
        Cliente cliente = (Cliente) vista.getCmbCliente().getSelectedItem();
        Vehiculo vehiculo = (Vehiculo) vista.getCmbVehiculo().getSelectedItem();
        Descontable promocion = (Descontable) vista.getCmbPromocion().getSelectedItem();

        if (cliente == null || vehiculo == null) {
            vista.mostrarError("Seleccione un cliente y un vehículo");
            return;
        }

        double precioBase = vehiculo.getPrecioBase();
        double precioFinal = precioBase;

        if (promocion != null) {
            precioFinal = promocion.calcularPrecioFinal(precioBase);
        }

        vista.mostrarPrecio(precioBase, precioFinal);
    }

    public void generarCotizacion() {
        Cliente cliente = (Cliente) vista.getCmbCliente().getSelectedItem();
        Vehiculo vehiculo = (Vehiculo) vista.getCmbVehiculo().getSelectedItem();
        Descontable promocion = (Descontable) vista.getCmbPromocion().getSelectedItem();

        if (cliente == null || vehiculo == null) {
            vista.mostrarError("Seleccione un cliente y un vehículo");
            return;
        }

        if (!vehiculo.getEstado().equals("DISPONIBLE")) {
            vista.mostrarError("El vehículo no está disponible");
            return;
        }

        String codigo = generarCodigoCotizacion();
        Cotizacion cotizacion = new Cotizacion(codigo, cliente, vendedor, vehiculo, promocion);
        arregloCotizaciones.agregar(cotizacion);

        vista.mostrarMensaje("Cotización generada: " + codigo + "\nPrecio Final: S/ " + 
                           String.format("%.2f", cotizacion.getPrecioFinal()));
        vista.limpiarCampos();
    }

    private String generarCodigoCotizacion() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String fecha = sdf.format(new Date());
        int numero = arregloCotizaciones.getContador() + 1;
        return "COT-" + fecha + "-" + String.format("%03d", numero);
    }
}

