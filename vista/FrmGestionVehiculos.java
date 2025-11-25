/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto.vista;

import proyecto.*;
import proyecto.controlador.ControladorVehiculo;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author USUARIO
 */
public class FrmGestionVehiculos extends javax.swing.JFrame {
    
    private JTable tablaVehiculos;
    private DefaultTableModel modeloTabla;
    private JTextField txtCodigo, txtMarca, txtModelo, txtColor, txtAnio, txtPrecioBase;
    private JTextField txtNumPuertas, txtTraccion;
    private JComboBox<String> cmbTipo;
    private JButton btnAgregar, btnModificar, btnEliminar, btnBuscar, btnLimpiar;
    private ControladorVehiculo controlador;
    private ArregloVehiculos arregloVehiculos;

    public FrmGestionVehiculos() {
        arregloVehiculos = DatosSistema.getInstancia().getArregloVehiculos();
        initComponents();
        configurarControlador();
        actualizarTabla();
    }

    private void initComponents() {
        setTitle("Gestión de Vehículos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout(5, 5));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelPrincipal.setBackground(new Color(245, 245, 250));

        // Panel de formulario (izquierda) con scroll
        JPanel panelFormulario = crearPanelFormulario();
        JScrollPane scrollFormulario = new JScrollPane(panelFormulario);
        scrollFormulario.setBorder(BorderFactory.createEmptyBorder());
        scrollFormulario.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollFormulario.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollFormulario.setPreferredSize(new Dimension(360, 600));
        panelPrincipal.add(scrollFormulario, BorderLayout.WEST);

        // Panel de tabla (centro)
        JPanel panelTabla = crearPanelTabla();
        panelPrincipal.add(panelTabla, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(),
                "Datos del Vehículo",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 14),
                new Color(0, 51, 102)
            ),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        // Configuración para que el scroll funcione correctamente
        panel.setPreferredSize(new Dimension(340, 1100));
        panel.setMaximumSize(new Dimension(340, Integer.MAX_VALUE));
        panel.setMinimumSize(new Dimension(340, 200));

        // Código
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblCodigo.setForeground(new Color(60, 60, 80));
        panel.add(lblCodigo);
        panel.add(Box.createVerticalStrut(5));
        txtCodigo = new JTextField();
        txtCodigo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCodigo.setForeground(Color.BLACK);
        txtCodigo.setPreferredSize(new Dimension(0, 38));
        txtCodigo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 210), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        panel.add(txtCodigo);
        panel.add(Box.createVerticalStrut(15));

        // Tipo
        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblTipo.setForeground(new Color(60, 60, 80));
        panel.add(lblTipo);
        panel.add(Box.createVerticalStrut(5));
        cmbTipo = new JComboBox<>(new String[]{"Sedán", "SUV"});
        cmbTipo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cmbTipo.setForeground(Color.BLACK);
        cmbTipo.setPreferredSize(new Dimension(0, 38));
        cmbTipo.addActionListener(e -> actualizarCamposEspecificos());
        panel.add(cmbTipo);
        panel.add(Box.createVerticalStrut(15));

        // Marca
        JLabel lblMarca = new JLabel("Marca:");
        lblMarca.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblMarca.setForeground(new Color(60, 60, 80));
        panel.add(lblMarca);
        panel.add(Box.createVerticalStrut(5));
        txtMarca = new JTextField();
        txtMarca.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtMarca.setForeground(Color.BLACK);
        txtMarca.setPreferredSize(new Dimension(0, 38));
        txtMarca.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 210), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        panel.add(txtMarca);
        panel.add(Box.createVerticalStrut(15));

        // Modelo
        JLabel lblModelo = new JLabel("Modelo:");
        lblModelo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblModelo.setForeground(new Color(60, 60, 80));
        panel.add(lblModelo);
        panel.add(Box.createVerticalStrut(5));
        txtModelo = new JTextField();
        txtModelo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtModelo.setForeground(Color.BLACK);
        txtModelo.setPreferredSize(new Dimension(0, 38));
        txtModelo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 210), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        panel.add(txtModelo);
        panel.add(Box.createVerticalStrut(15));

        // Color
        JLabel lblColor = new JLabel("Color:");
        lblColor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblColor.setForeground(new Color(60, 60, 80));
        panel.add(lblColor);
        panel.add(Box.createVerticalStrut(5));
        txtColor = new JTextField();
        txtColor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtColor.setForeground(Color.BLACK);
        txtColor.setPreferredSize(new Dimension(0, 38));
        txtColor.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 210), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        panel.add(txtColor);
        panel.add(Box.createVerticalStrut(15));

        // Año
        JLabel lblAnio = new JLabel("Año:");
        lblAnio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblAnio.setForeground(new Color(60, 60, 80));
        panel.add(lblAnio);
        panel.add(Box.createVerticalStrut(5));
        txtAnio = new JTextField();
        txtAnio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtAnio.setForeground(Color.BLACK);
        txtAnio.setPreferredSize(new Dimension(0, 38));
        txtAnio.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 210), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        panel.add(txtAnio);
        panel.add(Box.createVerticalStrut(15));

        // Precio Base
        JLabel lblPrecio = new JLabel("Precio Base:");
        lblPrecio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPrecio.setForeground(new Color(60, 60, 80));
        panel.add(lblPrecio);
        panel.add(Box.createVerticalStrut(5));
        txtPrecioBase = new JTextField();
        txtPrecioBase.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPrecioBase.setForeground(Color.BLACK);
        txtPrecioBase.setPreferredSize(new Dimension(0, 38));
        txtPrecioBase.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 210), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        panel.add(txtPrecioBase);
        panel.add(Box.createVerticalStrut(15));

        // Campos específicos
        JLabel lblPuertas = new JLabel("Número de Puertas (Sedán):");
        lblPuertas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPuertas.setForeground(new Color(60, 60, 80));
        panel.add(lblPuertas);
        panel.add(Box.createVerticalStrut(5));
        txtNumPuertas = new JTextField();
        txtNumPuertas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtNumPuertas.setForeground(Color.BLACK);
        txtNumPuertas.setPreferredSize(new Dimension(0, 38));
        txtNumPuertas.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 210), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        panel.add(txtNumPuertas);
        panel.add(Box.createVerticalStrut(15));

        JLabel lblTraccion = new JLabel("Tracción (SUV):");
        lblTraccion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblTraccion.setForeground(new Color(60, 60, 80));
        panel.add(lblTraccion);
        panel.add(Box.createVerticalStrut(5));
        txtTraccion = new JTextField();
        txtTraccion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtTraccion.setForeground(Color.BLACK);
        txtTraccion.setPreferredSize(new Dimension(0, 38));
        txtTraccion.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 210), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        panel.add(txtTraccion);
        panel.add(Box.createVerticalStrut(25));

        // Botones mejorados
        JPanel panelBotones = new JPanel(new GridLayout(5, 1, 10, 10));
        panelBotones.setBackground(new Color(255, 255, 255));
        
        btnAgregar = crearBoton("Agregar", new Color(0, 153, 76));
        btnModificar = crearBoton("Modificar", new Color(0, 102, 204));
        btnEliminar = crearBoton("Eliminar", new Color(204, 0, 0));
        btnBuscar = crearBoton("Buscar", new Color(102, 102, 102));
        btnLimpiar = crearBoton("Limpiar", new Color(153, 153, 153));

        btnAgregar.addActionListener(e -> controlador.agregar());
        btnModificar.addActionListener(e -> controlador.modificar());
        btnEliminar.addActionListener(e -> controlador.eliminar());
        btnBuscar.addActionListener(e -> controlador.buscar());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnLimpiar);

        panel.add(panelBotones);

        actualizarCamposEspecificos();
        return panel;
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

    private void actualizarCamposEspecificos() {
        String tipo = (String) cmbTipo.getSelectedItem();
        txtNumPuertas.setEnabled(tipo.equals("Sedán"));
        txtTraccion.setEnabled(tipo.equals("SUV"));
    }

    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(),
                "Lista de Vehículos",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                new Color(0, 51, 102)
            ),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        String[] columnas = {"Código", "Tipo", "Marca", "Modelo", "Color", "Año", "Precio Base", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaVehiculos = new JTable(modeloTabla);
        tablaVehiculos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaVehiculos.setRowHeight(35);
        tablaVehiculos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablaVehiculos.getTableHeader().setBackground(new Color(0, 51, 102));
        tablaVehiculos.getTableHeader().setForeground(Color.WHITE);
        tablaVehiculos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaVehiculos.setSelectionBackground(new Color(230, 240, 255));
        tablaVehiculos.setGridColor(new Color(220, 220, 230));
        tablaVehiculos.getSelectionModel().addListSelectionListener(e -> {
            int fila = tablaVehiculos.getSelectedRow();
            if (fila >= 0) {
                String codigo = (String) modeloTabla.getValueAt(fila, 0);
                Vehiculo v = arregloVehiculos.buscarPorCodigo(codigo);
                if (v != null) {
                    cargarDatos(v);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tablaVehiculos);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 230), 1));
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void configurarControlador() {
        controlador = new ControladorVehiculo(this, arregloVehiculos);
    }

    public void actualizarTabla() {
        modeloTabla.setRowCount(0);
        Vehiculo[] vehiculos = arregloVehiculos.listar();
        for (Vehiculo v : vehiculos) {
            if (v != null) {
                Object[] fila = {
                    v.getCodigo(),
                    v.tipoVehiculo(),
                    v.getMarca(),
                    v.getModelo(),
                    v.getColor(),
                    v.getAnio(),
                    v.getPrecioBase(),
                    v.getEstado()
                };
                modeloTabla.addRow(fila);
            }
        }
    }

    public void cargarDatos(Vehiculo v) {
        txtCodigo.setText(v.getCodigo());
        txtMarca.setText(v.getMarca());
        txtModelo.setText(v.getModelo());
        txtColor.setText(v.getColor());
        txtAnio.setText(String.valueOf(v.getAnio()));
        txtPrecioBase.setText(String.valueOf(v.getPrecioBase()));

        if (v instanceof Sedan) {
            cmbTipo.setSelectedItem("Sedán");
            txtNumPuertas.setText(String.valueOf(((Sedan) v).getNumPuertas()));
        } else if (v instanceof SUV) {
            cmbTipo.setSelectedItem("SUV");
            txtTraccion.setText(((SUV) v).getTraccion());
        }
        actualizarCamposEspecificos();
    }

    public void limpiarCampos() {
        txtCodigo.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
        txtColor.setText("");
        txtAnio.setText("");
        txtPrecioBase.setText("");
        txtNumPuertas.setText("");
        txtTraccion.setText("");
        cmbTipo.setSelectedIndex(0);
        tablaVehiculos.clearSelection();
    }

    // Getters
    public JTextField getTxtCodigo() { return txtCodigo; }
    public JTextField getTxtMarca() { return txtMarca; }
    public JTextField getTxtModelo() { return txtModelo; }
    public JTextField getTxtColor() { return txtColor; }
    public JTextField getTxtAnio() { return txtAnio; }
    public JTextField getTxtPrecioBase() { return txtPrecioBase; }
    public JTextField getTxtNumPuertas() { return txtNumPuertas; }
    public JTextField getTxtTraccion() { return txtTraccion; }
    public JComboBox<String> getCmbTipo() { return cmbTipo; }

    // Métodos de utilidad
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public int confirmarEliminacion() {
        return JOptionPane.showConfirmDialog(this, 
            "¿Está seguro que desea eliminar este vehículo?", 
            "Confirmar", 
            JOptionPane.YES_NO_OPTION);
    }
}

