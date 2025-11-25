/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

import proyecto.Vehiculo;

/**
 *
 * @author USUARIO
 */
public class SUV extends Vehiculo {
    private String traccion;

    public SUV(String codigo, String marca, String modelo, String color, int anio, double precioBase, String traccion) {
        super(codigo, marca, modelo, color, anio, precioBase);
        this.traccion = traccion;
    }

    @Override
    public String tipoVehiculo() {
        return "SUV";
    }

    public String getTraccion() {
        return traccion;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }
}

