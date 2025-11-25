/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.controlador;

import proyecto.*;
import proyecto.vista.FrmGestionVehiculos;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class ControladorVehiculo {
    
    private FrmGestionVehiculos vista;
    private ArregloVehiculos arregloVehiculos;

    public ControladorVehiculo(FrmGestionVehiculos vista, ArregloVehiculos arregloVehiculos) {
        this.vista = vista;
        this.arregloVehiculos = arregloVehiculos;
    }

    public void agregar() {
        try {
            String codigo = vista.getTxtCodigo().getText();
            String marca = vista.getTxtMarca().getText();
            String modelo = vista.getTxtModelo().getText();
            String color = vista.getTxtColor().getText();
            int anio = Integer.parseInt(vista.getTxtAnio().getText());
            double precioBase = Double.parseDouble(vista.getTxtPrecioBase().getText());
            String tipo = (String) vista.getCmbTipo().getSelectedItem();

            if (codigo.isEmpty() || marca.isEmpty() || modelo.isEmpty()) {
                vista.mostrarMensaje("Complete todos los campos obligatorios");
                return;
            }

            if (arregloVehiculos.buscarPorCodigo(codigo) != null) {
                vista.mostrarError("Ya existe un vehículo con ese código");
                return;
            }

            Vehiculo vehiculo;
            if (tipo.equals("Sedán")) {
                int numPuertas = Integer.parseInt(vista.getTxtNumPuertas().getText());
                vehiculo = new Sedan(codigo, marca, modelo, color, anio, precioBase, numPuertas);
            } else {
                String traccion = vista.getTxtTraccion().getText();
                vehiculo = new SUV(codigo, marca, modelo, color, anio, precioBase, traccion);
            }

            arregloVehiculos.agregar(vehiculo);
            vista.mostrarMensaje("Vehículo agregado correctamente");
            vista.limpiarCampos();
            vista.actualizarTabla();
        } catch (NumberFormatException e) {
            vista.mostrarError("Error en los datos numéricos");
        } catch (Exception e) {
            vista.mostrarError("Error al agregar vehículo: " + e.getMessage());
        }
    }

    public void modificar() {
        try {
            String codigo = vista.getTxtCodigo().getText();
            Vehiculo vehiculo = arregloVehiculos.buscarPorCodigo(codigo);

            if (vehiculo == null) {
                vista.mostrarError("Vehículo no encontrado");
                return;
            }

            vehiculo.setMarca(vista.getTxtMarca().getText());
            vehiculo.setModelo(vista.getTxtModelo().getText());
            vehiculo.setColor(vista.getTxtColor().getText());
            vehiculo.setAnio(Integer.parseInt(vista.getTxtAnio().getText()));
            vehiculo.setPrecioBase(Double.parseDouble(vista.getTxtPrecioBase().getText()));

            arregloVehiculos.modificar(vehiculo);
            vista.mostrarMensaje("Vehículo modificado correctamente");
            vista.limpiarCampos();
            vista.actualizarTabla();
        } catch (NumberFormatException e) {
            vista.mostrarError("Error en los datos numéricos");
        } catch (Exception e) {
            vista.mostrarError("Error al modificar vehículo: " + e.getMessage());
        }
    }

    public void eliminar() {
        String codigo = vista.getTxtCodigo().getText();
        if (codigo.isEmpty()) {
            vista.mostrarError("Ingrese el código del vehículo a eliminar");
            return;
        }

        int opcion = vista.confirmarEliminacion();
        if (opcion == JOptionPane.YES_OPTION) {
            arregloVehiculos.eliminar(codigo);
            vista.mostrarMensaje("Vehículo eliminado correctamente");
            vista.limpiarCampos();
            vista.actualizarTabla();
        }
    }

    public void buscar() {
        String codigo = vista.getTxtCodigo().getText();
        if (codigo.isEmpty()) {
            vista.mostrarError("Ingrese el código del vehículo");
            return;
        }

        Vehiculo vehiculo = arregloVehiculos.buscarPorCodigo(codigo);
        if (vehiculo != null) {
            vista.cargarDatos(vehiculo);
        } else {
            vista.mostrarError("Vehículo no encontrado");
        }
    }

    public void listar() {
        vista.actualizarTabla();
    }
}

