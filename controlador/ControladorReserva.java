/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.controlador;

import proyecto.*;
import proyecto.vista.FrmReservaTestDrive;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author USUARIO
 */
public class ControladorReserva {
    
    private FrmReservaTestDrive vista;
    private ArregloReservas arregloReservas;
    private ArregloClientes arregloClientes;
    private ArregloVehiculos arregloVehiculos;

    public ControladorReserva(FrmReservaTestDrive vista, ArregloReservas arregloReservas,
                              ArregloClientes arregloClientes, ArregloVehiculos arregloVehiculos) {
        this.vista = vista;
        this.arregloReservas = arregloReservas;
        this.arregloClientes = arregloClientes;
        this.arregloVehiculos = arregloVehiculos;
    }

    public void agendar() {
        Cliente cliente = (Cliente) vista.getCmbCliente().getSelectedItem();
        Vehiculo vehiculo = (Vehiculo) vista.getCmbVehiculo().getSelectedItem();
        String fecha = vista.getTxtFecha().getText();
        String hora = vista.getTxtHora().getText();

        if (cliente == null || vehiculo == null) {
            vista.mostrarError("Seleccione un cliente y un vehículo");
            return;
        }

        if (fecha.isEmpty() || hora.isEmpty()) {
            vista.mostrarError("Complete la fecha y hora");
            return;
        }

        if (!vehiculo.getEstado().equals("DISPONIBLE")) {
            vista.mostrarError("El vehículo no está disponible para test drive");
            return;
        }

        String codigo = generarCodigoReserva();
        ReservaTestDrive reserva = new ReservaTestDrive(codigo, cliente, vehiculo, fecha, hora);
        
        // Cambiar estado del vehículo a RESERVADO
        vehiculo.setEstado("RESERVADO");
        
        arregloReservas.agregar(reserva);
        vista.mostrarMensaje("Test drive agendado: " + codigo);
        vista.limpiarCampos();
        vista.actualizarTabla();
    }

    private String generarCodigoReserva() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String fecha = sdf.format(new Date());
        int numero = arregloReservas.getContador() + 1;
        return "TD-" + fecha + "-" + String.format("%03d", numero);
    }

    public void completar() {
        ReservaTestDrive reserva = (ReservaTestDrive) vista.getCmbReserva().getSelectedItem();
        if (reserva == null) {
            vista.mostrarError("Seleccione una reserva");
            return;
        }

        reserva.setEstado("COMPLETADA");
        reserva.getVehiculo().setEstado("DISPONIBLE");
        arregloReservas.modificar(reserva);
        vista.mostrarMensaje("Test drive completado");
        vista.actualizarTabla();
    }

    public void cancelar() {
        ReservaTestDrive reserva = (ReservaTestDrive) vista.getCmbReserva().getSelectedItem();
        if (reserva == null) {
            vista.mostrarError("Seleccione una reserva");
            return;
        }

        reserva.setEstado("CANCELADA");
        reserva.getVehiculo().setEstado("DISPONIBLE");
        arregloReservas.modificar(reserva);
        vista.mostrarMensaje("Test drive cancelado");
        vista.actualizarTabla();
    }
}

