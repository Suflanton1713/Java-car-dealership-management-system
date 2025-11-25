/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

/**
 *
 * @author USUARIO
 */
public class ReporteClientesFrecuentes implements Reporteable {
    
    private ArregloVentas arregloVentas;
    private ArregloClientes arregloClientes;

    public ReporteClientesFrecuentes(ArregloVentas arregloVentas, ArregloClientes arregloClientes) {
        this.arregloVentas = arregloVentas;
        this.arregloClientes = arregloClientes;
    }

    @Override
    public String generarReporte() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("========================================\n");
        reporte.append("   REPORTE DE CLIENTES FRECUENTES\n");
        reporte.append("========================================\n\n");

        Cliente[] clientes = arregloClientes.listar();
        
        // Ordenar clientes por cantidad de compras (bubble sort simple)
        Cliente[] clientesOrdenados = new Cliente[clientes.length];
        int[] cantidadCompras = new int[clientes.length];
        int indice = 0;

        for (Cliente c : clientes) {
            if (c != null) {
                Venta[] ventas = arregloVentas.buscarPorCliente(c.getDni());
                int cantidad = 0;
                for (Venta v : ventas) {
                    if (v != null && !v.isCancelada()) {
                        cantidad++;
                    }
                }
                if (cantidad > 0) {
                    clientesOrdenados[indice] = c;
                    cantidadCompras[indice] = cantidad;
                    indice++;
                }
            }
        }

        // Bubble sort
        for (int i = 0; i < indice - 1; i++) {
            for (int j = 0; j < indice - i - 1; j++) {
                if (cantidadCompras[j] < cantidadCompras[j + 1]) {
                    // Intercambiar clientes
                    Cliente temp = clientesOrdenados[j];
                    clientesOrdenados[j] = clientesOrdenados[j + 1];
                    clientesOrdenados[j + 1] = temp;
                    // Intercambiar cantidades
                    int tempCant = cantidadCompras[j];
                    cantidadCompras[j] = cantidadCompras[j + 1];
                    cantidadCompras[j + 1] = tempCant;
                }
            }
        }

        reporte.append("CLIENTES FRECUENTES (ordenados por cantidad de compras):\n");
        reporte.append("----------------------------------------\n");

        for (int i = 0; i < indice; i++) {
            Cliente c = clientesOrdenados[i];
            Venta[] ventas = arregloVentas.buscarPorCliente(c.getDni());
            double totalGastado = 0;
            for (Venta v : ventas) {
                if (v != null && !v.isCancelada()) {
                    totalGastado += v.getTotal();
                }
            }

            reporte.append((i + 1)).append(". ").append(c.nombreCompleto()).append("\n");
            reporte.append("   DNI: ").append(c.getDni()).append("\n");
            reporte.append("   Compras: ").append(cantidadCompras[i]).append("\n");
            reporte.append("   Total Gastado: S/ ").append(String.format("%.2f", totalGastado)).append("\n");
            reporte.append("----------------------------------------\n");
        }

        reporte.append("\n========================================\n");
        return reporte.toString();
    }
}

