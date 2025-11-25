/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

/**
 *
 * @author USUARIO
 */
public class Factura extends Comprobante {

    private String ruc; // RUC de la empresa

    public Factura(String serie, String numero, Venta venta, String ruc) {
        super(serie, numero, venta);
        this.ruc = ruc;
    }

    @Override
    public String generarTexto() {
        StringBuilder texto = new StringBuilder();
        texto.append("========================================\n");
        texto.append("           FACTURA DE VENTA\n");
        texto.append("========================================\n");
        texto.append("Serie: ").append(serie).append("\n");
        texto.append("Número: ").append(numero).append("\n");
        texto.append("RUC: ").append(ruc).append("\n");
        texto.append("Fecha: ").append(venta.getFecha()).append("\n");
        texto.append("----------------------------------------\n");
        texto.append("CLIENTE:\n");
        texto.append("DNI: ").append(venta.getCotizacion().getCliente().getDni()).append("\n");
        texto.append("Nombre: ").append(venta.getCotizacion().getCliente().nombreCompleto()).append("\n");
        texto.append("Dirección: ").append(venta.getCotizacion().getCliente().getDireccion()).append("\n");
        texto.append("----------------------------------------\n");
        texto.append("VENDEDOR:\n");
        texto.append("Nombre: ").append(venta.getCotizacion().getVendedor().nombreCompleto()).append("\n");
        texto.append("----------------------------------------\n");
        texto.append("VEHÍCULO:\n");
        texto.append("Código: ").append(venta.getCotizacion().getVehiculo().getCodigo()).append("\n");
        texto.append("Marca: ").append(venta.getCotizacion().getVehiculo().getMarca()).append("\n");
        texto.append("Modelo: ").append(venta.getCotizacion().getVehiculo().getModelo()).append("\n");
        texto.append("Año: ").append(venta.getCotizacion().getVehiculo().getAnio()).append("\n");
        texto.append("Color: ").append(venta.getCotizacion().getVehiculo().getColor()).append("\n");
        texto.append("----------------------------------------\n");
        texto.append("SUBTOTAL: S/ ").append(String.format("%.2f", venta.getTotal())).append("\n");
        texto.append("IGV (18%): S/ ").append(String.format("%.2f", venta.getTotal() * 0.18)).append("\n");
        texto.append("TOTAL: S/ ").append(String.format("%.2f", venta.getTotal() * 1.18)).append("\n");
        texto.append("========================================\n");
        return texto.toString();
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
}

