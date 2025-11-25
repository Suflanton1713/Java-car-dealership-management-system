/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

/**
 *
 * @author USUARIO
 */
public class PromocionFija extends Promocion implements Descontable {
    private double descuentoFijo;

    public PromocionFija(String codigo, String descripcion, double descuentoFijo, boolean activa) {
        super(codigo, descripcion, activa);
        this.descuentoFijo = descuentoFijo;
    }

    @Override
    public double calcularPrecioFinal(double precioBase) {
        return precioBase - descuentoFijo;
    }

    public double getDescuentoFijo() {
        return descuentoFijo;
    }

    public void setDescuentoFijo(double descuentoFijo) {
        this.descuentoFijo = descuentoFijo;
    }
}

