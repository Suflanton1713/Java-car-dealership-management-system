/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.controlador;

import proyecto.*;
import proyecto.vista.FrmGestionPromociones;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class ControladorPromocion {
    
    private FrmGestionPromociones vista;
    private ArregloPromociones arregloPromociones;

    public ControladorPromocion(FrmGestionPromociones vista, ArregloPromociones arregloPromociones) {
        this.vista = vista;
        this.arregloPromociones = arregloPromociones;
    }

    public void agregar() {
        try {
            String codigo = vista.getTxtCodigo().getText();
            String descripcion = vista.getTxtDescripcion().getText();
            String tipo = (String) vista.getCmbTipo().getSelectedItem();
            boolean activa = vista.getChkActiva().isSelected();

            if (codigo.isEmpty() || descripcion.isEmpty()) {
                vista.mostrarError("Complete los campos obligatorios");
                return;
            }

            if (arregloPromociones.buscarPorCodigo(codigo) != null) {
                vista.mostrarError("Ya existe una promoción con ese código");
                return;
            }

            Promocion promocion;
            if (tipo.equals("Por Porcentaje")) {
                double porcentaje = Double.parseDouble(vista.getTxtValor().getText());
                promocion = new PromocionPorcentaje(codigo, descripcion, porcentaje, activa);
            } else {
                double descuentoFijo = Double.parseDouble(vista.getTxtValor().getText());
                promocion = new PromocionFija(codigo, descripcion, descuentoFijo, activa);
            }

            arregloPromociones.agregar(promocion);
            vista.mostrarMensaje("Promoción agregada correctamente");
            vista.limpiarCampos();
            vista.actualizarTabla();
        } catch (NumberFormatException e) {
            vista.mostrarError("Error en el valor numérico");
        } catch (Exception e) {
            vista.mostrarError("Error al agregar promoción: " + e.getMessage());
        }
    }

    public void modificar() {
        try {
            String codigo = vista.getTxtCodigo().getText();
            Promocion promocion = arregloPromociones.buscarPorCodigo(codigo);

            if (promocion == null) {
                vista.mostrarError("Promoción no encontrada");
                return;
            }

            promocion.setDescripcion(vista.getTxtDescripcion().getText());
            promocion.setActiva(vista.getChkActiva().isSelected());

            if (promocion instanceof PromocionPorcentaje) {
                double porcentaje = Double.parseDouble(vista.getTxtValor().getText());
                ((PromocionPorcentaje) promocion).setPorcentajeDescuento(porcentaje);
            } else if (promocion instanceof PromocionFija) {
                double descuentoFijo = Double.parseDouble(vista.getTxtValor().getText());
                ((PromocionFija) promocion).setDescuentoFijo(descuentoFijo);
            }

            arregloPromociones.modificar(promocion);
            vista.mostrarMensaje("Promoción modificada correctamente");
            vista.limpiarCampos();
            vista.actualizarTabla();
        } catch (NumberFormatException e) {
            vista.mostrarError("Error en el valor numérico");
        } catch (Exception e) {
            vista.mostrarError("Error al modificar promoción: " + e.getMessage());
        }
    }

    public void eliminar() {
        String codigo = vista.getTxtCodigo().getText();
        if (codigo.isEmpty()) {
            vista.mostrarError("Ingrese el código de la promoción a eliminar");
            return;
        }

        int opcion = vista.confirmarEliminacion();
        if (opcion == JOptionPane.YES_OPTION) {
            arregloPromociones.eliminar(codigo);
            vista.mostrarMensaje("Promoción eliminada correctamente");
            vista.limpiarCampos();
            vista.actualizarTabla();
        }
    }

    public void buscar() {
        String codigo = vista.getTxtCodigo().getText();
        if (codigo.isEmpty()) {
            vista.mostrarError("Ingrese el código de la promoción");
            return;
        }

        Promocion promocion = arregloPromociones.buscarPorCodigo(codigo);
        if (promocion != null) {
            vista.cargarDatos(promocion);
        } else {
            vista.mostrarError("Promoción no encontrada");
        }
    }
}

