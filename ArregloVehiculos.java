/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

/**
 *
 * @author USUARIO
 */
public class ArregloVehiculos {

    private Vehiculo[] vehiculos;
    private int contador;

    public ArregloVehiculos(int tamanio) {
        vehiculos = new Vehiculo[tamanio];
        contador = 0;
    }

    public void agregar(Vehiculo v) {
        vehiculos[contador] = v;
        contador++;
    }

    public Vehiculo buscarPorCodigo(String codigo) {
        for (int i = 0; i < contador; i++) {
            if (vehiculos[i].codigo.equals(codigo)) {
                return vehiculos[i];
            }
        }
        return null;
    }

    public Vehiculo[] listar() {
        Vehiculo[] lista = new Vehiculo[contador];
        for (int i = 0; i < contador; i++) {
            lista[i] = vehiculos[i];
        }
        return lista;
    }

    public void eliminar(String codigo) {
        for (int i = 0; i < contador; i++) {
            if (vehiculos[i].codigo.equals(codigo)) {
                // Desplazar elementos hacia la izquierda
                for (int j = i; j < contador - 1; j++) {
                    vehiculos[j] = vehiculos[j + 1];
                }
                vehiculos[contador - 1] = null;
                contador--;
                break;
            }
        }
    }

    public void modificar(Vehiculo v) {
        for (int i = 0; i < contador; i++) {
            if (vehiculos[i].codigo.equals(v.codigo)) {
                vehiculos[i] = v;
                break;
            }
        }
    }

    public int getContador() {
        return contador;
    }
}