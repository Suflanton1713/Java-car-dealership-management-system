/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

/**
 *
 * @author USUARIO
 */
public class ArregloVentas {
    
    private Venta[] ventas;
    private int contador;

    public ArregloVentas(int tamanio) {
        ventas = new Venta[tamanio];
        contador = 0;
    }

    public void agregar(Venta v) {
        if (contador < ventas.length) {
            ventas[contador] = v;
            contador++;
        }
    }

    public Venta buscar(String numero) {
        for (int i = 0; i < contador; i++) {
            if (ventas[i].getNumero().equals(numero)) {
                return ventas[i];
            }
        }
        return null;
    }

    public void eliminar(String numero) {
        for (int i = 0; i < contador; i++) {
            if (ventas[i].getNumero().equals(numero)) {
                // Desplazar elementos hacia la izquierda
                for (int j = i; j < contador - 1; j++) {
                    ventas[j] = ventas[j + 1];
                }
                ventas[contador - 1] = null;
                contador--;
                break;
            }
        }
    }

    public void modificar(Venta v) {
        for (int i = 0; i < contador; i++) {
            if (ventas[i].getNumero().equals(v.getNumero())) {
                ventas[i] = v;
                break;
            }
        }
    }

    public Venta[] listar() {
        Venta[] lista = new Venta[contador];
        for (int i = 0; i < contador; i++) {
            lista[i] = ventas[i];
        }
        return lista;
    }

    // Buscar ventas por cliente
    public Venta[] buscarPorCliente(String dniCliente) {
        Venta[] resultado = new Venta[contador];
        int indice = 0;
        for (int i = 0; i < contador; i++) {
            if (ventas[i].getCotizacion().getCliente().getDni().equals(dniCliente)) {
                resultado[indice] = ventas[i];
                indice++;
            }
        }
        Venta[] listaFinal = new Venta[indice];
        for (int i = 0; i < indice; i++) {
            listaFinal[i] = resultado[i];
        }
        return listaFinal;
    }

    // Buscar ventas por vendedor
    public Venta[] buscarPorVendedor(String dniVendedor) {
        Venta[] resultado = new Venta[contador];
        int indice = 0;
        for (int i = 0; i < contador; i++) {
            if (ventas[i].getCotizacion().getVendedor().getDni().equals(dniVendedor)) {
                resultado[indice] = ventas[i];
                indice++;
            }
        }
        Venta[] listaFinal = new Venta[indice];
        for (int i = 0; i < indice; i++) {
            listaFinal[i] = resultado[i];
        }
        return listaFinal;
    }

    public int getContador() {
        return contador;
    }
}

