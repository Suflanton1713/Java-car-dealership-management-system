/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

/**
 *
 * @author USUARIO
 */
public abstract class Comprobante {
    
    protected String serie;
    protected String numero;
    protected Venta venta;

    public Comprobante(String serie, String numero, Venta venta) {
        this.serie = serie;
        this.numero = numero;
        this.venta = venta;
    }

    public abstract String generarTexto();

    // Getters y Setters
    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}

