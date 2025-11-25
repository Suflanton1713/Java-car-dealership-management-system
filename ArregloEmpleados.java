/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

/**
 *
 * @author USUARIO
 */
public class ArregloEmpleados {
    
    private Empleado[] empleados;
    private int contador;

    public ArregloEmpleados(int tamanio) {
        empleados = new Empleado[tamanio];
        contador = 0;
        // Datos hardcoded para pruebas
        inicializarDatos();
    }

    private void inicializarDatos() {
        // Admin por defecto
        Empleado admin = new Empleado(
            "12345678", 
            "Juan", 
            "Pérez", 
            "García", 
            "987654321", 
            "admin@concesionaria.com",
            "admin",
            "admin123",
            Rol.ADMIN
        );
        agregar(admin);

        // Vendedor por defecto
        Empleado vendedor = new Empleado(
            "87654321", 
            "María", 
            "López", 
            "Sánchez", 
            "987654322", 
            "vendedor@concesionaria.com",
            "vendedor",
            "vendedor123",
            Rol.VENDEDOR
        );
        agregar(vendedor);
    }

    public void agregar(Empleado e) {
        if (contador < empleados.length) {
            empleados[contador] = e;
            contador++;
        }
    }

    public Empleado buscar(String dni) {
        for (int i = 0; i < contador; i++) {
            if (empleados[i].getDni().equals(dni)) {
                return empleados[i];
            }
        }
        return null;
    }

    public void eliminar(String dni) {
        for (int i = 0; i < contador; i++) {
            if (empleados[i].getDni().equals(dni)) {
                // Desplazar elementos hacia la izquierda
                for (int j = i; j < contador - 1; j++) {
                    empleados[j] = empleados[j + 1];
                }
                empleados[contador - 1] = null;
                contador--;
                break;
            }
        }
    }

    public void modificar(Empleado e) {
        for (int i = 0; i < contador; i++) {
            if (empleados[i].getDni().equals(e.getDni())) {
                empleados[i] = e;
                break;
            }
        }
    }

    public Empleado[] listar() {
        Empleado[] lista = new Empleado[contador];
        for (int i = 0; i < contador; i++) {
            lista[i] = empleados[i];
        }
        return lista;
    }

    public Empleado validarLogin(String usuario, String clave) {
        for (int i = 0; i < contador; i++) {
            if (empleados[i].getUsuario().equals(usuario) && 
                empleados[i].getClave().equals(clave)) {
                return empleados[i];
            }
        }
        return null;
    }

    public int getContador() {
        return contador;
    }
}

