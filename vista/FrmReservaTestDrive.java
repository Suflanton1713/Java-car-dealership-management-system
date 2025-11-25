/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto.vista;

import proyecto.*;
import proyecto.controlador.ControladorReserva;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 *
 * @author USUARIO
 */
public class FrmReservaTestDrive extends javax.swing.JFrame {
    
    private JTable tablaReservas;
    private DefaultTableModel modeloTabla;
    private JComboBox<Cliente> cmbCliente;
    private JComboBox<Vehiculo> cmbVehiculo;
    private JTextField txtFecha, txtHora;
    private JComboBox<ReservaTestDrive> cmbReserva;
    private JButton btnAgendar, btnCompletar, btnCancelar, btnLimpiar;
    private ControladorReserva controlador;
    private ArregloReservas arregloReservas;
    private ArregloClientes arregloClientes;
    private ArregloVehiculos arregloVehiculos;

    public FrmReservaTestDrive() {
        DatosSistema datos = DatosSistema.getInstancia();
        this.arregloReservas = datos.getArregloReservas();
        this.arregloClientes = datos.getArregloClientes();
        this.arregloVehiculos = datos.getArregloVehiculos();
        initComponents();
        configurarControlador();
        cargarCombos();
        actualizarTabla();
    }

    private void initComponents() {
        setTitle("Agenda de Test Drive");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout(5, 5));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelPrincipal.setBackground(new Color(245, 245, 250));

        // Panel de formulario
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));
        panelFormulario.setBackground(new Color(255, 255, 255));
        panelFormulario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(),
                "Agendar Test Drive",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                new Color(0, 51, 102)
            ),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        // Configuración para que el scroll funcione correctamente
        panelFormulario.setPreferredSize(new Dimension(340, 900));
        panelFormulario.setMaximumSize(new Dimension(340, Integer.MAX_VALUE));
        panelFormulario.setMinimumSize(new Dimension(340, 200));

        agregarCombo(panelFormulario, "Cliente:", cmbCliente = new JComboBox<>());
        agregarCombo(panelFormulario, "Vehículo:", cmbVehiculo = new JComboBox<>());
        agregarCampo(panelFormulario, "Fecha (dd/mm/yyyy):", txtFecha = new JTextField());
        agregarCampo(panelFormulario, "Hora (hh:mm):", txtHora = new JTextField());

        panelFormulario.add(Box.createVerticalStrut(20));

        JPanel panelBotones = new JPanel(new GridLayout(4, 1, 10, 10));
        panelBotones.setBackground(new Color(255, 255, 255));
        
        btnAgendar = crearBoton("Agendar", new Color(0, 153, 76));
        btnCompletar = crearBoton("Completar", new Color(0, 102, 204));
        btnCancelar = crearBoton("Cancelar", new Color(204, 0, 0));
        btnLimpiar = crearBoton("Limpiar", new Color(153, 153, 153));

        panelBotones.add(btnAgendar);
        panelBotones.add(btnCompletar);
        panelBotones.add(btnCancelar);
        panelBotones.add(btnLimpiar);

        panelFormulario.add(panelBotones);
        panelFormulario.add(Box.createVerticalStrut(25));

        JLabel lblReserva = new JLabel("Reserva (para completar/cancelar):");
        lblReserva.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblReserva.setForeground(new Color(60, 60, 80));
        panelFormulario.add(lblReserva);
        panelFormulario.add(Box.createVerticalStrut(8));
        cmbReserva = new JComboBox<>();
        cmbReserva.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cmbReserva.setForeground(Color.BLACK);
        cmbReserva.setPreferredSize(new Dimension(0, 42));
        panelFormulario.add(cmbReserva);

        JScrollPane scrollFormulario = new JScrollPane(panelFormulario);
        scrollFormulario.setBorder(BorderFactory.createEmptyBorder());
        scrollFormulario.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollFormulario.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollFormulario.setPreferredSize(new Dimension(360, 600));
        panelPrincipal.add(scrollFormulario, BorderLayout.WEST);

        // Panel de tabla
        JPanel panelTabla = crearPanelTabla();
        panelPrincipal.add(panelTabla, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    private void agregarCombo(JPanel panel, String etiqueta, JComboBox<?> combo) {
        JLabel lbl = new JLabel(etiqueta);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setForeground(new Color(60, 60, 80));
        panel.add(lbl);
        panel.add(Box.createVerticalStrut(5));
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setForeground(Color.BLACK);
        combo.setPreferredSize(new Dimension(0, 38));
        panel.add(combo);
        panel.add(Box.createVerticalStrut(15));
    }

    private void agregarCampo(JPanel panel, String etiqueta, JTextField campo) {
        JLabel lbl = new JLabel(etiqueta);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setForeground(new Color(60, 60, 80));
        panel.add(lbl);
        panel.add(Box.createVerticalStrut(5));
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setForeground(Color.BLACK);
        campo.setPreferredSize(new Dimension(0, 38));
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 210), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        panel.add(campo);
        panel.add(Box.createVerticalStrut(15));
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(0, 45));
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
                "Reservas de Test Drive",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                new Color(0, 51, 102)
            ),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        String[] columnas = {"Código", "Cliente", "Vehículo", "Fecha", "Hora", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaReservas = new JTable(modeloTabla);
        tablaReservas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaReservas.setRowHeight(35);
        tablaReservas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablaReservas.getTableHeader().setBackground(new Color(0, 51, 102));
        tablaReservas.getTableHeader().setForeground(Color.WHITE);
        tablaReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaReservas.setSelectionBackground(new Color(230, 240, 255));
        tablaReservas.setGridColor(new Color(220, 220, 230));
        JScrollPane scrollPane = new JScrollPane(tablaReservas);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 230), 1));
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private void configurarControlador() {
        controlador = new ControladorReserva(this, arregloReservas, arregloClientes, arregloVehiculos);
        btnAgendar.addActionListener(e -> controlador.agendar());
        btnCompletar.addActionListener(e -> controlador.completar());
        btnCancelar.addActionListener(e -> controlador.cancelar());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void cargarCombos() {
        cmbCliente.removeAllItems();
        Cliente[] clientes = arregloClientes.listar();
        for (Cliente c : clientes) {
            if (c != null) {
                cmbCliente.addItem(c);
            }
        }

        cmbVehiculo.removeAllItems();
        Vehiculo[] vehiculos = arregloVehiculos.listar();
        for (Vehiculo v : vehiculos) {
            if (v != null && (v.getEstado().equals("DISPONIBLE") || v.getEstado().equals("RESERVADO"))) {
                cmbVehiculo.addItem(v);
            }
        }

        cmbReserva.removeAllItems();
        ReservaTestDrive[] reservas = arregloReservas.listar();
        for (ReservaTestDrive r : reservas) {
            if (r != null && r.getEstado().equals("PROGRAMADA")) {
                cmbReserva.addItem(r);
            }
        }
    }

    public void actualizarTabla() {
        modeloTabla.setRowCount(0);
        ReservaTestDrive[] reservas = arregloReservas.listar();
        for (ReservaTestDrive r : reservas) {
            if (r != null) {
                Object[] fila = {
                    r.getCodigo(),
                    r.getCliente().nombreCompleto(),
                    r.getVehiculo().getMarca() + " " + r.getVehiculo().getModelo(),
                    r.getFecha(),
                    r.getHora(),
                    r.getEstado()
                };
                modeloTabla.addRow(fila);
            }
        }
        cargarCombos();
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            cargarCombos(); // Recargar combos cada vez que se muestra la ventana
            actualizarTabla(); // Recargar tabla
        }
        super.setVisible(visible);
    }

    public void limpiarCampos() {
        txtFecha.setText("");
        txtHora.setText("");
        cmbCliente.setSelectedIndex(-1);
        cmbVehiculo.setSelectedIndex(-1);
        cmbReserva.setSelectedIndex(-1);
    }

    // Getters
    public JComboBox<Cliente> getCmbCliente() { return cmbCliente; }
    public JComboBox<Vehiculo> getCmbVehiculo() { return cmbVehiculo; }
    public JTextField getTxtFecha() { return txtFecha; }
    public JTextField getTxtHora() { return txtHora; }
    public JComboBox<ReservaTestDrive> getCmbReserva() { return cmbReserva; }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

