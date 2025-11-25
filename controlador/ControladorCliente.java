/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.controlador;

import proyecto.*;
import proyecto.vista.FrmGestionClientes;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class ControladorCliente {
    
    private FrmGestionClientes vista;
    private ArregloClientes arregloClientes;

    public ControladorCliente(FrmGestionClientes vista, ArregloClientes arregloClientes) {
        this.vista = vista;
        this.arregloClientes = arregloClientes;
    }

    public void agregar() {
        try {
            String dni = vista.getTxtDni().getText();
            String nombres = vista.getTxtNombres().getText();
            String apellidoPaterno = vista.getTxtApellidoPaterno().getText();
            String apellidoMaterno = vista.getTxtApellidoMaterno().getText();
            String telefono = vista.getTxtTelefono().getText();
            String correo = vista.getTxtCorreo().getText();
            String direccion = vista.getTxtDireccion().getText();

            if (dni.isEmpty() || nombres.isEmpty() || apellidoPaterno.isEmpty()) {
                vista.mostrarError("Complete los campos obligatorios");
                return;
            }

            if (arregloClientes.buscar(dni) != null) {
                vista.mostrarError("Ya existe un cliente con ese DNI");
                return;
            }

            Cliente cliente = new Cliente(dni, nombres, apellidoPaterno, apellidoMaterno, telefono, correo, direccion);
            arregloClientes.agregar(cliente);
            vista.mostrarMensaje("Cliente agregado correctamente");
            vista.limpiarCampos();
            vista.actualizarTabla();
        } catch (Exception e) {
            vista.mostrarError("Error al agregar cliente: " + e.getMessage());
        }
    }

    public void modificar() {
        try {
            String dni = vista.getTxtDni().getText();
            Cliente cliente = arregloClientes.buscar(dni);

            if (cliente == null) {
                vista.mostrarError("Cliente no encontrado");
                return;
            }

            cliente.setNombres(vista.getTxtNombres().getText());
            cliente.setApellidoPaterno(vista.getTxtApellidoPaterno().getText());
            cliente.setApellidoMaterno(vista.getTxtApellidoMaterno().getText());
            cliente.setTelefono(vista.getTxtTelefono().getText());
            cliente.setCorreo(vista.getTxtCorreo().getText());
            cliente.setDireccion(vista.getTxtDireccion().getText());

            arregloClientes.modificar(cliente);
            vista.mostrarMensaje("Cliente modificado correctamente");
            vista.limpiarCampos();
            vista.actualizarTabla();
        } catch (Exception e) {
            vista.mostrarError("Error al modificar cliente: " + e.getMessage());
        }
    }

    public void eliminar() {
        String dni = vista.getTxtDni().getText();
        if (dni.isEmpty()) {
            vista.mostrarError("Ingrese el DNI del cliente a eliminar");
            return;
        }

        int opcion = vista.confirmarEliminacion();
        if (opcion == JOptionPane.YES_OPTION) {
            arregloClientes.eliminar(dni);
            vista.mostrarMensaje("Cliente eliminado correctamente");
            vista.limpiarCampos();
            vista.actualizarTabla();
        }
    }

    public void buscar() {
        String dni = vista.getTxtDni().getText();
        if (dni.isEmpty()) {
            vista.mostrarError("Ingrese el DNI del cliente");
            return;
        }

        Cliente cliente = arregloClientes.buscar(dni);
        if (cliente != null) {
            vista.cargarDatos(cliente);
        } else {
            vista.mostrarError("Cliente no encontrado");
        }
    }

    public void listar() {
        vista.actualizarTabla();
    }
}

