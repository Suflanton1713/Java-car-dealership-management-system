/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

/**
 *
 * @author USUARIO
 */
public class ReporteVentasPorVendedor implements Reporteable {
    
    private ArregloVentas arregloVentas;
    private ArregloEmpleados arregloEmpleados;

    public ReporteVentasPorVendedor(ArregloVentas arregloVentas, ArregloEmpleados arregloEmpleados) {
        this.arregloVentas = arregloVentas;
        this.arregloEmpleados = arregloEmpleados;
    }

    @Override
    public String generarReporte() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("========================================\n");
        reporte.append("   REPORTE DE VENTAS POR VENDEDOR\n");
        reporte.append("========================================\n\n");

        Empleado[] empleados = arregloEmpleados.listar();
        
        for (Empleado emp : empleados) {
            if (emp != null && emp.getRol() == Rol.VENDEDOR) {
                Venta[] ventas = arregloVentas.buscarPorVendedor(emp.getDni());
                double totalVentas = 0;
                int cantidadVentas = 0;

                for (Venta v : ventas) {
                    if (v != null && !v.isCancelada()) {
                        totalVentas += v.getTotal();
                        cantidadVentas++;
                    }
                }

                if (cantidadVentas > 0) {
                    reporte.append("Vendedor: ").append(emp.nombreCompleto()).append("\n");
                    reporte.append("DNI: ").append(emp.getDni()).append("\n");
                    reporte.append("Cantidad de Ventas: ").append(cantidadVentas).append("\n");
                    reporte.append("Total Vendido: S/ ").append(String.format("%.2f", totalVentas)).append("\n");
                    reporte.append("----------------------------------------\n");
                }
            }
        }

        reporte.append("\n========================================\n");
        return reporte.toString();
    }
}

