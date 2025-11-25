/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.controlador;

import proyecto.*;
import proyecto.vista.FrmGestionEmpleados;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class ControladorEmpleado {
    
    private FrmGestionEmpleados vista;
    private ArregloEmpleados arregloEmpleados;

    public ControladorEmpleado(FrmGestionEmpleados vista, ArregloEmpleados arregloEmpleados) {
        this.vista = vista;
        this.arregloEmpleados = arregloEmpleados;
    }

    public void agregar() {
        try {
            String dni = vista.getTxtDni().getText();
            String nombres = vista.getTxtNombres().getText();
            String apellidoPaterno = vista.getTxtApellidoPaterno().getText();
            String apellidoMaterno = vista.getTxtApellidoMaterno().getText();
            String telefono = vista.getTxtTelefono().getText();
            String correo = vista.getTxtCorreo().getText();
            String usuario = vista.getTxtUsuario().getText();
            String clave = new String(vista.getTxtClave().getPassword());
            Rol rol = (Rol) vista.getCmbRol().getSelectedItem();

            if (dni.isEmpty() || nombres.isEmpty() || usuario.isEmpty() || clave.isEmpty()) {
                vista.mostrarError("Complete los campos obligatorios");
                return;
            }

            if (arregloEmpleados.buscar(dni) != null) {
                vista.mostrarError("Ya existe un empleado con ese DNI");
                return;
            }

            Empleado empleado = new Empleado(dni, nombres, apellidoPaterno, apellidoMaterno, 
                                            telefono, correo, usuario, clave, rol);
            arregloEmpleados.agregar(empleado);
            vista.mostrarMensaje("Empleado agregado correctamente");
            vista.limpiarCampos();
            vista.actualizarTabla();
        } catch (Exception e) {
            vista.mostrarError("Error al agregar empleado: " + e.getMessage());
        }
    }

    public void modificar() {
        try {
            String dni = vista.getTxtDni().getText();
            Empleado empleado = arregloEmpleados.buscar(dni);

            if (empleado == null) {
                vista.mostrarError("Empleado no encontrado");
                return;
            }

            empleado.setNombres(vista.getTxtNombres().getText());
            empleado.setApellidoPaterno(vista.getTxtApellidoPaterno().getText());
            empleado.setApellidoMaterno(vista.getTxtApellidoMaterno().getText());
            empleado.setTelefono(vista.getTxtTelefono().getText());
            empleado.setCorreo(vista.getTxtCorreo().getText());
            empleado.setUsuario(vista.getTxtUsuario().getText());
            String nuevaClave = new String(vista.getTxtClave().getPassword());
            if (!nuevaClave.isEmpty()) {
                empleado.setClave(nuevaClave);
            }
            empleado.setRol((Rol) vista.getCmbRol().getSelectedItem());

            arregloEmpleados.modificar(empleado);
            vista.mostrarMensaje("Empleado modificado correctamente");
            vista.limpiarCampos();
            vista.actualizarTabla();
        } catch (Exception e) {
            vista.mostrarError("Error al modificar empleado: " + e.getMessage());
        }
    }

    public void eliminar() {
        String dni = vista.getTxtDni().getText();
        if (dni.isEmpty()) {
            vista.mostrarError("Ingrese el DNI del empleado a eliminar");
            return;
        }

        int opcion = vista.confirmarEliminacion();
        if (opcion == JOptionPane.YES_OPTION) {
            arregloEmpleados.eliminar(dni);
            vista.mostrarMensaje("Empleado eliminado correctamente");
            vista.limpiarCampos();
            vista.actualizarTabla();
        }
    }

    public void buscar() {
        String dni = vista.getTxtDni().getText();
        if (dni.isEmpty()) {
            vista.mostrarError("Ingrese el DNI del empleado");
            return;
        }

        Empleado empleado = arregloEmpleados.buscar(dni);
        if (empleado != null) {
            vista.cargarDatos(empleado);
        } else {
            vista.mostrarError("Empleado no encontrado");
        }
    }
}

