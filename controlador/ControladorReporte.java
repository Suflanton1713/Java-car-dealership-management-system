/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.controlador;

import proyecto.*;
import proyecto.vista.FrmReportes;

/**
 *
 * @author USUARIO
 */
public class ControladorReporte {
    
    private FrmReportes vista;
    private ArregloVentas arregloVentas;
    private ArregloVehiculos arregloVehiculos;
    private ArregloClientes arregloClientes;
    private ArregloEmpleados arregloEmpleados;

    public ControladorReporte(FrmReportes vista, ArregloVentas arregloVentas, 
                             ArregloVehiculos arregloVehiculos, 
                             ArregloClientes arregloClientes,
                             ArregloEmpleados arregloEmpleados) {
        this.vista = vista;
        this.arregloVentas = arregloVentas;
        this.arregloVehiculos = arregloVehiculos;
        this.arregloClientes = arregloClientes;
        this.arregloEmpleados = arregloEmpleados;
    }

    public void generarReporteVentas() {
        ReporteVentasPorVendedor reporte = new ReporteVentasPorVendedor(arregloVentas, arregloEmpleados);
        vista.mostrarReporte(reporte.generarReporte());
    }

    public void generarReporteVehiculos() {
        ReporteVehiculosVendidos reporte = new ReporteVehiculosVendidos(arregloVentas, arregloVehiculos);
        vista.mostrarReporte(reporte.generarReporte());
    }

    public void generarReporteClientes() {
        ReporteClientesFrecuentes reporte = new ReporteClientesFrecuentes(arregloVentas, arregloClientes);
        vista.mostrarReporte(reporte.generarReporte());
    }
}

