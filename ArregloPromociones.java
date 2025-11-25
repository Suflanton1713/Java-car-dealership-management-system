/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

/**
 *
 * @author USUARIO
 */
public class ArregloPromociones {

    private Promocion[] promociones;
    private int contador;

    public ArregloPromociones(int tamanio) {
        promociones = new Promocion[tamanio];
        contador = 0;
    }

    public void agregar(Promocion p) {
        promociones[contador] = p;
        contador++;
    }

    public Promocion buscarPorCodigo(String codigo) {
        for (int i = 0; i < contador; i++) {
            if (promociones[i].codigo.equals(codigo)) {
                return promociones[i];
            }
        }
        return null;
    }

    public Promocion[] listar() {
        Promocion[] lista = new Promocion[contador];
        for (int i = 0; i < contador; i++) {
            lista[i] = promociones[i];
        }
        return lista;
    }

    public Promocion[] listarActivas() {
        Promocion[] resultado = new Promocion[contador];
        int indice = 0;
        for (int i = 0; i < contador; i++) {
            if (promociones[i].activa) {
                resultado[indice] = promociones[i];
                indice++;
            }
        }
        Promocion[] listaFinal = new Promocion[indice];
        for (int i = 0; i < indice; i++) {
            listaFinal[i] = resultado[i];
        }
        return listaFinal;
    }

    public void eliminar(String codigo) {
        for (int i = 0; i < contador; i++) {
            if (promociones[i].codigo.equals(codigo)) {
                // Desplazar elementos hacia la izquierda
                for (int j = i; j < contador - 1; j++) {
                    promociones[j] = promociones[j + 1];
                }
                promociones[contador - 1] = null;
                contador--;
                break;
            }
        }
    }

    public void modificar(Promocion p) {
        for (int i = 0; i < contador; i++) {
            if (promociones[i].codigo.equals(p.codigo)) {
                promociones[i] = p;
                break;
            }
        }
    }

    public int getContador() {
        return contador;
    }
}