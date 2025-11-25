/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

/**
 *
 * @author USUARIO
 */
public class ArregloCotizaciones {
    
    private Cotizacion[] cotizaciones;
    private int contador;

    public ArregloCotizaciones(int tamanio) {
        cotizaciones = new Cotizacion[tamanio];
        contador = 0;
    }

    public void agregar(Cotizacion c) {
        if (contador < cotizaciones.length) {
            cotizaciones[contador] = c;
            contador++;
        }
    }

    public Cotizacion buscar(String codigo) {
        for (int i = 0; i < contador; i++) {
            if (cotizaciones[i].getCodigo().equals(codigo)) {
                return cotizaciones[i];
            }
        }
        return null;
    }

    public Cotizacion[] listar() {
        Cotizacion[] lista = new Cotizacion[contador];
        for (int i = 0; i < contador; i++) {
            lista[i] = cotizaciones[i];
        }
        return lista;
    }

    public Cotizacion[] buscarPorCliente(String dniCliente) {
        Cotizacion[] resultado = new Cotizacion[contador];
        int indice = 0;
        for (int i = 0; i < contador; i++) {
            if (cotizaciones[i].getCliente().getDni().equals(dniCliente)) {
                resultado[indice] = cotizaciones[i];
                indice++;
            }
        }
        Cotizacion[] listaFinal = new Cotizacion[indice];
        for (int i = 0; i < indice; i++) {
            listaFinal[i] = resultado[i];
        }
        return listaFinal;
    }

    public int getContador() {
        return contador;
    }
}

