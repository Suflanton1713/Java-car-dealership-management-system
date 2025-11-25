/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

/**
 *
 * @author USUARIO
 */
public abstract class Vehiculo {


    protected String codigo;
    protected String marca;
    protected String modelo;
    protected String color;
    protected int anio;
    protected double precioBase;
    protected String estado; // DISPONIBLE, VENDIDO, RESERVADO

    public Vehiculo(String codigo, String marca, String modelo, String color, int anio, double precioBase) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.anio = anio;
        this.precioBase = precioBase;
        this.estado = "DISPONIBLE"; // Por defecto disponible
    }

    public String mostrarDatos() {
        return codigo + " - " + marca + " " + modelo;
    }

    public abstract String tipoVehiculo();

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return codigo + " - " + marca + " " + modelo + " (" + estado + ")";
    }
}


