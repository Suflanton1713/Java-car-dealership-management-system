/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

/**
 *
 * @author USUARIO
 */
public class ArregloClientes {
    
    private Cliente[] clientes;
    private int contador;

    public ArregloClientes(int tamanio) {
        clientes = new Cliente[tamanio];
        contador = 0;
    }

    public void agregar(Cliente c) {
        if (contador < clientes.length) {
            clientes[contador] = c;
            contador++;
        }
    }

    public Cliente buscar(String dni) {
        for (int i = 0; i < contador; i++) {
            if (clientes[i].getDni().equals(dni)) {
                return clientes[i];
            }
        }
        return null;
    }

    public void eliminar(String dni) {
        for (int i = 0; i < contador; i++) {
            if (clientes[i].getDni().equals(dni)) {
                // Desplazar elementos hacia la izquierda
                for (int j = i; j < contador - 1; j++) {
                    clientes[j] = clientes[j + 1];
                }
                clientes[contador - 1] = null;
                contador--;
                break;
            }
        }
    }

    public void modificar(Cliente c) {
        for (int i = 0; i < contador; i++) {
            if (clientes[i].getDni().equals(c.getDni())) {
                clientes[i] = c;
                break;
            }
        }
    }

    public Cliente[] listar() {
        Cliente[] lista = new Cliente[contador];
        for (int i = 0; i < contador; i++) {
            lista[i] = clientes[i];
        }
        return lista;
    }

    public int getContador() {
        return contador;
    }
}

