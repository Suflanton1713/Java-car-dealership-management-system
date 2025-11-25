/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

/**
 *
 * @author USUARIO
 */
public class Venta {
    
    private String numero;
    private Cotizacion cotizacion;
    private double total;
    private String fecha;
    private boolean cancelada;

    public Venta(String numero, Cotizacion cotizacion, String fecha) {
        this.numero = numero;
        this.cotizacion = cotizacion;
        this.total = cotizacion.getPrecioFinal();
        this.fecha = fecha;
        this.cancelada = false;
        // Cambiar el estado del vehículo a VENDIDO
        if (cotizacion.getVehiculo() != null) {
            cotizacion.getVehiculo().setEstado("VENDIDO");
        }
    }

    // Getters y Setters
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }

    @Override
    public String toString() {
        return numero + " - " + cotizacion.getCliente().nombreCompleto() + " - S/ " + String.format("%.2f", total);
    }
}

