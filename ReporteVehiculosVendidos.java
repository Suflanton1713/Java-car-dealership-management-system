/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

/**
 *
 * @author USUARIO
 */
public class ReporteVehiculosVendidos implements Reporteable {
    
    private ArregloVentas arregloVentas;
    private ArregloVehiculos arregloVehiculos;

    public ReporteVehiculosVendidos(ArregloVentas arregloVentas, ArregloVehiculos arregloVehiculos) {
        this.arregloVentas = arregloVentas;
        this.arregloVehiculos = arregloVehiculos;
    }

    @Override
    public String generarReporte() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("========================================\n");
        reporte.append("   REPORTE DE VEHÍCULOS VENDIDOS\n");
        reporte.append("========================================\n\n");

        Vehiculo[] vehiculos = arregloVehiculos.listar();
        int vendidos = 0;
        int disponibles = 0;
        double totalVendido = 0;

        reporte.append("VEHÍCULOS VENDIDOS:\n");
        reporte.append("----------------------------------------\n");

        for (Vehiculo v : vehiculos) {
            if (v != null) {
                if (v.getEstado().equals("VENDIDO")) {
                    vendidos++;
                    totalVendido += v.getPrecioBase();
                    reporte.append("Código: ").append(v.getCodigo()).append("\n");
                    reporte.append("Marca: ").append(v.getMarca()).append("\n");
                    reporte.append("Modelo: ").append(v.getModelo()).append("\n");
                    reporte.append("Año: ").append(v.getAnio()).append("\n");
                    reporte.append("Precio Base: S/ ").append(String.format("%.2f", v.getPrecioBase())).append("\n");
                    reporte.append("----------------------------------------\n");
                } else if (v.getEstado().equals("DISPONIBLE")) {
                    disponibles++;
                }
            }
        }

        reporte.append("\nRESUMEN:\n");
        reporte.append("----------------------------------------\n");
        reporte.append("Total Vendidos: ").append(vendidos).append("\n");
        reporte.append("Total Disponibles: ").append(disponibles).append("\n");
        reporte.append("Total en Ventas: S/ ").append(String.format("%.2f", totalVendido)).append("\n");
        reporte.append("\n========================================\n");
        return reporte.toString();
    }
}

