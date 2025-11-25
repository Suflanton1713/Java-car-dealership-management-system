/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

/**
 *
 * @author USUARIO
 */
public class Empleado extends Persona {
    
    private String usuario;
    private String clave;
    private Rol rol;

    public Empleado(String dni, String nombres, String apellidoPaterno, String apellidoMaterno, String telefono, String correo, String usuario, String clave, Rol rol) {
        super(dni, nombres, apellidoPaterno, apellidoMaterno, telefono, correo);
        this.usuario = usuario;
        this.clave = clave;
        this.rol = rol;
    }

    @Override
    public String tipoPersona() {
        return "Empleado";
    }

    // Getters y Setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}

