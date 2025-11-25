/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.controlador;

import proyecto.*;
import proyecto.vista.FrmVenta;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author USUARIO
 */
public class ControladorVenta {
    
    private FrmVenta vista;
    private ArregloCotizaciones arregloCotizaciones;
    private ArregloVentas arregloVentas;

    public ControladorVenta(FrmVenta vista, ArregloCotizaciones arregloCotizaciones, 
                            ArregloVentas arregloVentas) {
        this.vista = vista;
        this.arregloCotizaciones = arregloCotizaciones;
        this.arregloVentas = arregloVentas;
    }

    public void registrarVenta() {
        Cotizacion cotizacion = (Cotizacion) vista.getCmbCotizacion().getSelectedItem();
        
        if (cotizacion == null) {
            vista.mostrarError("Seleccione una cotización");
            return;
        }

        if (!cotizacion.getVehiculo().getEstado().equals("DISPONIBLE")) {
            vista.mostrarError("El vehículo ya fue vendido");
            return;
        }

        String numeroVenta = generarNumeroVenta();
        String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        
        Venta venta = new Venta(numeroVenta, cotizacion, fecha);
        arregloVentas.agregar(venta);

        vista.mostrarMensaje("Venta registrada: " + numeroVenta);
        vista.mostrarComprobante(venta);
        vista.actualizarLista();
    }

    private String generarNumeroVenta() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String fecha = sdf.format(new Date());
        int numero = arregloVentas.getContador();
        return "VEN-" + fecha + "-" + String.format("%03d", numero);
    }
}

