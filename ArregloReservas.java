/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

/**
 *
 * @author USUARIO
 */
public class ArregloReservas {
    
    private ReservaTestDrive[] reservas;
    private int contador;

    public ArregloReservas(int tamanio) {
        reservas = new ReservaTestDrive[tamanio];
        contador = 0;
    }

    public void agregar(ReservaTestDrive r) {
        if (contador < reservas.length) {
            reservas[contador] = r;
            contador++;
        }
    }

    public ReservaTestDrive buscar(String codigo) {
        for (int i = 0; i < contador; i++) {
            if (reservas[i].getCodigo().equals(codigo)) {
                return reservas[i];
            }
        }
        return null;
    }

    public void eliminar(String codigo) {
        for (int i = 0; i < contador; i++) {
            if (reservas[i].getCodigo().equals(codigo)) {
                // Desplazar elementos hacia la izquierda
                for (int j = i; j < contador - 1; j++) {
                    reservas[j] = reservas[j + 1];
                }
                reservas[contador - 1] = null;
                contador--;
                break;
            }
        }
    }

    public void modificar(ReservaTestDrive r) {
        for (int i = 0; i < contador; i++) {
            if (reservas[i].getCodigo().equals(r.getCodigo())) {
                reservas[i] = r;
                break;
            }
        }
    }

    public ReservaTestDrive[] listar() {
        ReservaTestDrive[] lista = new ReservaTestDrive[contador];
        for (int i = 0; i < contador; i++) {
            lista[i] = reservas[i];
        }
        return lista;
    }

    public ReservaTestDrive[] buscarPorCliente(String dniCliente) {
        ReservaTestDrive[] resultado = new ReservaTestDrive[contador];
        int indice = 0;
        for (int i = 0; i < contador; i++) {
            if (reservas[i].getCliente().getDni().equals(dniCliente)) {
                resultado[indice] = reservas[i];
                indice++;
            }
        }
        ReservaTestDrive[] listaFinal = new ReservaTestDrive[indice];
        for (int i = 0; i < indice; i++) {
            listaFinal[i] = resultado[i];
        }
        return listaFinal;
    }

    public int getContador() {
        return contador;
    }
}

