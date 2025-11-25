/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto.vista;

import proyecto.*;
import proyecto.controlador.ControladorPago;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 *
 * @author USUARIO
 */
public class FrmPagos extends javax.swing.JFrame {
    
    private JTable tablaPagos;
    private DefaultTableModel modeloTabla;
    private JComboBox<Venta> cmbVenta;
    private JTextField txtMonto;
    private JComboBox<String> cmbMetodoPago;
    private JLabel lblTotalVenta, lblTotalPagado, lblSaldoPendiente;
    private JButton btnRegistrar, btnActualizar;
    private ControladorPago controlador;
    private ArregloVentas arregloVentas;
    private ArregloPagos arregloPagos;

    public FrmPagos() {
        DatosSistema datos = DatosSistema.getInstancia();
        this.arregloVentas = datos.getArregloVentas();
        this.arregloPagos = datos.getArregloPagos();
        initComponents();
        configurarControlador();
        cargarVentas();
    }

    private void initComponents() {
        setTitle("Gestión de Pagos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout(15, 15));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(new Color(245, 245, 250));

        // Panel superior - Formulario de pago
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));
        panelFormulario.setBackground(new Color(255, 255, 255));
        panelFormulario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(),
                "Registrar Pago",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                new Color(0, 51, 102)
            ),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));

        agregarCombo(panelFormulario, "Venta:", cmbVenta = new JComboBox<>());
        cmbVenta.addActionListener(e -> {
            if (controlador != null) {
                controlador.actualizarSaldo();
                actualizarTablaPagos();
            }
        });

        agregarCampo(panelFormulario, "Monto:", txtMonto = new JTextField());
        
        JLabel lblMetodo = new JLabel("Método de Pago:");
        lblMetodo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblMetodo.setForeground(new Color(60, 60, 80));
        panelFormulario.add(lblMetodo);
        panelFormulario.add(Box.createVerticalStrut(8));
        cmbMetodoPago = new JComboBox<>(new String[]{"EFECTIVO", "TARJETA", "TRANSFERENCIA"});
        cmbMetodoPago.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cmbMetodoPago.setPreferredSize(new Dimension(0, 42));
        panelFormulario.add(cmbMetodoPago);
        panelFormulario.add(Box.createVerticalStrut(25));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBotones.setBackground(new Color(255, 255, 255));
        btnRegistrar = crearBoton("Registrar Pago", new Color(0, 153, 76));
        btnActualizar = crearBoton("Actualizar", new Color(102, 102, 102));
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnActualizar);
        panelFormulario.add(panelBotones);

        // Panel de información de saldo mejorado
        JPanel panelSaldo = new JPanel(new GridLayout(3, 2, 15, 15));
        panelSaldo.setBackground(new Color(255, 255, 255));
        panelSaldo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(),
                "Información de Saldo",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                new Color(0, 51, 102)
            ),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        
        JLabel lblTotalVentaTxt = new JLabel("Total Venta:");
        lblTotalVentaTxt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblTotalVentaTxt.setForeground(new Color(60, 60, 80));
        panelSaldo.add(lblTotalVentaTxt);
        lblTotalVenta = new JLabel("S/ 0.00");
        lblTotalVenta.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblTotalVenta.setForeground(new Color(100, 100, 120));
        panelSaldo.add(lblTotalVenta);
        
        JLabel lblTotalPagadoTxt = new JLabel("Total Pagado:");
        lblTotalPagadoTxt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblTotalPagadoTxt.setForeground(new Color(60, 60, 80));
        panelSaldo.add(lblTotalPagadoTxt);
        lblTotalPagado = new JLabel("S/ 0.00");
        lblTotalPagado.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblTotalPagado.setForeground(new Color(100, 100, 120));
        panelSaldo.add(lblTotalPagado);
        
        JLabel lblSaldoPendienteTxt = new JLabel("Saldo Pendiente:");
        lblSaldoPendienteTxt.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblSaldoPendienteTxt.setForeground(new Color(60, 60, 80));
        panelSaldo.add(lblSaldoPendienteTxt);
        lblSaldoPendiente = new JLabel("S/ 0.00");
        lblSaldoPendiente.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblSaldoPendiente.setForeground(new Color(204, 0, 0));
        panelSaldo.add(lblSaldoPendiente);

        JPanel panelSuperior = new JPanel(new BorderLayout(20, 0));
        panelSuperior.setBackground(new Color(245, 245, 250));
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelSaldo, BorderLayout.EAST);
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);

        // Panel de tabla de pagos
        JPanel panelTabla = crearPanelTabla();
        panelPrincipal.add(panelTabla, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    private void agregarCombo(JPanel panel, String etiqueta, JComboBox<?> combo) {
        JLabel lbl = new JLabel(etiqueta);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lbl.setForeground(new Color(60, 60, 80));
        panel.add(lbl);
        panel.add(Box.createVerticalStrut(8));
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setForeground(Color.BLACK);
        combo.setPreferredSize(new Dimension(0, 42));
        panel.add(combo);
        panel.add(Box.createVerticalStrut(20));
    }

    private void agregarCampo(JPanel panel, String etiqueta, JTextField campo) {
        JLabel lbl = new JLabel(etiqueta);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lbl.setForeground(new Color(60, 60, 80));
        panel.add(lbl);
        panel.add(Box.createVerticalStrut(8));
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setForeground(Color.BLACK);
        campo.setPreferredSize(new Dimension(0, 42));
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 210), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        panel.add(campo);
        panel.add(Box.createVerticalStrut(20));
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(180, 48));
        btn.setBackground(color);
        // Texto negro para botones grises, blanco para los demás
        if (color.equals(new Color(102, 102, 102)) || color.equals(new Color(153, 153, 153))) {
            btn.setForeground(Color.BLACK);
        } else {
            btn.setForeground(Color.WHITE);
        }
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(),
                "Historial de Pagos",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                new Color(0, 51, 102)
            ),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        String[] columnas = {"Código", "Venta", "Monto", "Fecha", "Método"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaPagos = new JTable(modeloTabla);
        tablaPagos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaPagos.setRowHeight(35);
        tablaPagos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablaPagos.getTableHeader().setBackground(new Color(0, 51, 102));
        tablaPagos.getTableHeader().setForeground(Color.WHITE);
        tablaPagos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaPagos.setSelectionBackground(new Color(230, 240, 255));
        tablaPagos.setGridColor(new Color(220, 220, 230));
        JScrollPane scrollPane = new JScrollPane(tablaPagos);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 230), 1));
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private void configurarControlador() {
        controlador = new ControladorPago(this, arregloVentas, arregloPagos);
        btnRegistrar.addActionListener(e -> controlador.registrarPago());
        btnActualizar.addActionListener(e -> {
            controlador.actualizarSaldo();
            actualizarTablaPagos();
        });
    }

    private void cargarVentas() {
        cmbVenta.removeAllItems();
        Venta[] ventas = arregloVentas.listar();
        for (Venta v : ventas) {
            if (v != null && !v.isCancelada()) {
                cmbVenta.addItem(v);
            }
        }
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            cargarVentas(); // Recargar ventas cada vez que se muestra la ventana
        }
        super.setVisible(visible);
    }

    public void actualizarTablaPagos() {
        modeloTabla.setRowCount(0);
        Venta venta = (Venta) cmbVenta.getSelectedItem();
        if (venta != null) {
            Pago[] pagos = arregloPagos.buscarPorVenta(venta.getNumero());
            for (Pago p : pagos) {
                if (p != null) {
                    Object[] fila = {
                        p.getCodigo(),
                        p.getVenta().getNumero(),
                        "S/ " + String.format("%.2f", p.getMonto()),
                        p.getFecha(),
                        p.getMetodoPago()
                    };
                    modeloTabla.addRow(fila);
                }
            }
        }
    }

    public void mostrarSaldo(double total, double pagado, double pendiente) {
        lblTotalVenta.setText("S/ " + String.format("%.2f", total));
        lblTotalPagado.setText("S/ " + String.format("%.2f", pagado));
        lblSaldoPendiente.setText("S/ " + String.format("%.2f", pendiente));
    }

    public void actualizarSaldo() {
        Venta venta = (Venta) cmbVenta.getSelectedItem();
        if (venta != null) {
            double totalPagado = arregloPagos.calcularTotalPagado(venta.getNumero());
            double saldoPendiente = arregloPagos.calcularSaldoPendiente(venta);
            mostrarSaldo(venta.getTotal(), totalPagado, saldoPendiente);
        }
    }

    public void limpiarCampos() {
        txtMonto.setText("");
        cmbMetodoPago.setSelectedIndex(0);
        actualizarTablaPagos();
    }

    // Getters
    public JComboBox<Venta> getCmbVenta() { return cmbVenta; }
    public JTextField getTxtMonto() { return txtMonto; }
    public JComboBox<String> getCmbMetodoPago() { return cmbMetodoPago; }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

