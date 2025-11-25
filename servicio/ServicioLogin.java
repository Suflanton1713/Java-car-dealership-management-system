/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.servicio;

import proyecto.ArregloEmpleados;
import proyecto.Empleado;

/**
 *
 * @author USUARIO
 */
public class ServicioLogin {
    
    private ArregloEmpleados arregloEmpleados;

    public ServicioLogin(ArregloEmpleados arregloEmpleados) {
        this.arregloEmpleados = arregloEmpleados;
    }

    public Empleado validar(String usuario, String clave) {
        return arregloEmpleados.validarLogin(usuario, clave);
    }
}

