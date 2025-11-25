/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.controlador;

import proyecto.Empleado;
import proyecto.servicio.ServicioLogin;
import proyecto.vista.FrmLogin;
import proyecto.vista.FrmMenuPrincipal;

/**
 *
 * @author USUARIO
 */
public class ControladorLogin {
    
    private FrmLogin vista;
    private ServicioLogin servicio;

    public ControladorLogin(FrmLogin vista, ServicioLogin servicio) {
        this.vista = vista;
        this.servicio = servicio;
    }

    public void iniciarSesion() {
        String usuario = vista.getTxtUsuario().getText();
        String clave = new String(vista.getTxtClave().getPassword());

        if (usuario.isEmpty() || clave.isEmpty()) {
            vista.mostrarMensaje("Por favor, complete todos los campos");
            return;
        }

        Empleado empleado = servicio.validar(usuario, clave);

        if (empleado != null) {
            vista.ocultar();
            FrmMenuPrincipal menu = new FrmMenuPrincipal(empleado);
            menu.setVisible(true);
        } else {
            vista.mostrarMensaje("Usuario o contraseña incorrectos");
            vista.limpiarCampos();
        }
    }
}

