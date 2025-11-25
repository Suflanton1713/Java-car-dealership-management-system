/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

/**
 *
 * @author USUARIO
 */
public class ArregloPagos {
    
    private Pago[] pagos;
    private int contador;

    public ArregloPagos(int tamanio) {
        pagos = new Pago[tamanio];
        contador = 0;
    }

    public void agregar(Pago p) {
        if (contador < pagos.length) {
            pagos[contador] = p;
            contador++;
        }
    }

    public Pago buscar(String codigo) {
        for (int i = 0; i < contador; i++) {
            if (pagos[i].getCodigo().equals(codigo)) {
                return pagos[i];
            }
        }
        return null;
    }

    public void eliminar(String codigo) {
        for (int i = 0; i < contador; i++) {
            if (pagos[i].getCodigo().equals(codigo)) {
                // Desplazar elementos hacia la izquierda
                for (int j = i; j < contador - 1; j++) {
                    pagos[j] = pagos[j + 1];
                }
                pagos[contador - 1] = null;
                contador--;
                break;
            }
        }
    }

    public void modificar(Pago p) {
        for (int i = 0; i < contador; i++) {
            if (pagos[i].getCodigo().equals(p.getCodigo())) {
                pagos[i] = p;
                break;
            }
        }
    }

    public Pago[] listar() {
        Pago[] lista = new Pago[contador];
        for (int i = 0; i < contador; i++) {
            lista[i] = pagos[i];
        }
        return lista;
    }

    // Buscar pagos por número de venta
    public Pago[] buscarPorVenta(String numeroVenta) {
        Pago[] resultado = new Pago[contador];
        int indice = 0;
        for (int i = 0; i < contador; i++) {
            if (pagos[i].getVenta().getNumero().equals(numeroVenta)) {
                resultado[indice] = pagos[i];
                indice++;
            }
        }
        Pago[] listaFinal = new Pago[indice];
        for (int i = 0; i < indice; i++) {
            listaFinal[i] = resultado[i];
        }
        return listaFinal;
    }

    // Calcular el total pagado de una venta
    public double calcularTotalPagado(String numeroVenta) {
        double total = 0;
        for (int i = 0; i < contador; i++) {
            if (pagos[i].getVenta().getNumero().equals(numeroVenta)) {
                total += pagos[i].getMonto();
            }
        }
        return total;
    }

    // Calcular el saldo pendiente de una venta
    public double calcularSaldoPendiente(Venta venta) {
        double totalPagado = calcularTotalPagado(venta.getNumero());
        return venta.getTotal() - totalPagado;
    }

    public int getContador() {
        return contador;
    }
}

