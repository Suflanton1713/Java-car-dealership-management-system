/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.controlador;

import proyecto.*;
import proyecto.vista.FrmPagos;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author USUARIO
 */
public class ControladorPago {
    
    private FrmPagos vista;
    private ArregloVentas arregloVentas;
    private ArregloPagos arregloPagos;

    public ControladorPago(FrmPagos vista, ArregloVentas arregloVentas, ArregloPagos arregloPagos) {
        this.vista = vista;
        this.arregloVentas = arregloVentas;
        this.arregloPagos = arregloPagos;
    }

    public void registrarPago() {
        Venta venta = (Venta) vista.getCmbVenta().getSelectedItem();
        if (venta == null) {
            vista.mostrarError("Seleccione una venta");
            return;
        }

        try {
            double monto = Double.parseDouble(vista.getTxtMonto().getText());
            String metodoPago = (String) vista.getCmbMetodoPago().getSelectedItem();

            if (monto <= 0) {
                vista.mostrarError("El monto debe ser mayor a cero");
                return;
            }

            double saldoPendiente = arregloPagos.calcularSaldoPendiente(venta);
            if (monto > saldoPendiente) {
                vista.mostrarError("El monto excede el saldo pendiente: S/ " + String.format("%.2f", saldoPendiente));
                return;
            }

            String codigo = generarCodigoPago();
            String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            
            Pago pago = new Pago(codigo, venta, monto, fecha, metodoPago);
            arregloPagos.agregar(pago);

            vista.mostrarMensaje("Pago registrado correctamente");
            vista.actualizarSaldo();
            vista.limpiarCampos();
        } catch (NumberFormatException e) {
            vista.mostrarError("Ingrese un monto válido");
        } catch (Exception e) {
            vista.mostrarError("Error al registrar pago: " + e.getMessage());
        }
    }

    private String generarCodigoPago() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String fecha = sdf.format(new Date());
        int numero = arregloPagos.getContador() + 1;
        return "PAG-" + fecha + "-" + String.format("%03d", numero);
    }

    public void actualizarSaldo() {
        Venta venta = (Venta) vista.getCmbVenta().getSelectedItem();
        if (venta != null) {
            double totalPagado = arregloPagos.calcularTotalPagado(venta.getNumero());
            double saldoPendiente = arregloPagos.calcularSaldoPendiente(venta);
            vista.mostrarSaldo(venta.getTotal(), totalPagado, saldoPendiente);
        }
    }
}

