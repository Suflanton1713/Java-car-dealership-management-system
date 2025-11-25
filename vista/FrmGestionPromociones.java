/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto.vista;

import proyecto.*;
import proyecto.controlador.ControladorPromocion;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 *
 * @author USUARIO
 */
public class FrmGestionPromociones extends javax.swing.JFrame {
    
    private JTable tablaPromociones;
    private DefaultTableModel modeloTabla;
    private JTextField txtCodigo, txtDescripcion, txtValor;
    private JComboBox<String> cmbTipo;
    private JCheckBox chkActiva;
    private JButton btnAgregar, btnModificar, btnEliminar, btnBuscar, btnLimpiar;
    private ControladorPromocion controlador;
    private ArregloPromociones arregloPromociones;

    public FrmGestionPromociones() {
        arregloPromociones = DatosSistema.getInstancia().getArregloPromociones();
        initComponents();
        configurarControlador();
        actualizarTabla();
    }

    private void initComponents() {
        setTitle("Gestión de Promociones");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout(5, 5));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelPrincipal.setBackground(new Color(245, 245, 250));

        JPanel panelFormulario = crearPanelFormulario();
        JScrollPane scrollFormulario = new JScrollPane(panelFormulario);
        scrollFormulario.setBorder(BorderFactory.createEmptyBorder());
        scrollFormulario.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollFormulario.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollFormulario.setPreferredSize(new Dimension(360, 600));
        panelPrincipal.add(scrollFormulario, BorderLayout.WEST);

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
                "Datos de la Promoción",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 14),
                new Color(0, 51, 102)
            ),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        // Configuración para que el scroll funcione correctamente
        panel.setPreferredSize(new Dimension(340, 900));
        panel.setMaximumSize(new Dimension(340, Integer.MAX_VALUE));
        panel.setMinimumSize(new Dimension(340, 200));

        agregarCampo(panel, "Código:", txtCodigo = new JTextField());
        agregarCampo(panel, "Descripción:", txtDescripcion = new JTextField());
        
        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblTipo.setForeground(new Color(60, 60, 80));
        panel.add(lblTipo);
        panel.add(Box.createVerticalStrut(5));
        cmbTipo = new JComboBox<>(new String[]{"Por Porcentaje", "Fija"});
        cmbTipo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cmbTipo.setForeground(Color.BLACK);
        cmbTipo.setPreferredSize(new Dimension(0, 38));
        cmbTipo.addActionListener(e -> actualizarEtiquetaValor());
        panel.add(cmbTipo);
        panel.add(Box.createVerticalStrut(15));

        agregarCampo(panel, "Valor (% o S/):", txtValor = new JTextField());
        
        JLabel lblActiva = new JLabel("Activa:");
        lblActiva.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblActiva.setForeground(new Color(60, 60, 80));
        panel.add(lblActiva);
        panel.add(Box.createVerticalStrut(5));
        chkActiva = new JCheckBox();
        chkActiva.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chkActiva.setSelected(true);
        panel.add(chkActiva);
        panel.add(Box.createVerticalStrut(25));

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
        return panel;
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

    private void actualizarEtiquetaValor() {
        // Se puede mejorar con un JLabel dinámico
    }

    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(),
                "Lista de Promociones",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                new Color(0, 51, 102)
            ),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        String[] columnas = {"Código", "Descripción", "Tipo", "Valor", "Activa"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaPromociones = new JTable(modeloTabla);
        tablaPromociones.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaPromociones.setRowHeight(35);
        tablaPromociones.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablaPromociones.getTableHeader().setBackground(new Color(0, 51, 102));
        tablaPromociones.getTableHeader().setForeground(Color.WHITE);
        tablaPromociones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaPromociones.setSelectionBackground(new Color(230, 240, 255));
        tablaPromociones.setGridColor(new Color(220, 220, 230));
        tablaPromociones.getSelectionModel().addListSelectionListener(e -> {
            int fila = tablaPromociones.getSelectedRow();
            if (fila >= 0) {
                String codigo = (String) modeloTabla.getValueAt(fila, 0);
                Promocion p = arregloPromociones.buscarPorCodigo(codigo);
                if (p != null) {
                    cargarDatos(p);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tablaPromociones);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 230), 1));
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private void configurarControlador() {
        controlador = new ControladorPromocion(this, arregloPromociones);
    }

    public void actualizarTabla() {
        modeloTabla.setRowCount(0);
        Promocion[] promociones = arregloPromociones.listar();
        for (Promocion p : promociones) {
            if (p != null) {
                String tipo = p instanceof PromocionPorcentaje ? "Porcentaje" : "Fija";
                String valor = "";
                if (p instanceof PromocionPorcentaje) {
                    valor = ((PromocionPorcentaje) p).getPorcentajeDescuento() + "%";
                } else if (p instanceof PromocionFija) {
                    valor = "S/ " + ((PromocionFija) p).getDescuentoFijo();
                }
                Object[] fila = {
                    p.getCodigo(),
                    p.getDescripcion(),
                    tipo,
                    valor,
                    p.isActiva() ? "Sí" : "No"
                };
                modeloTabla.addRow(fila);
            }
        }
    }

    public void cargarDatos(Promocion p) {
        txtCodigo.setText(p.getCodigo());
        txtDescripcion.setText(p.getDescripcion());
        chkActiva.setSelected(p.isActiva());

        if (p instanceof PromocionPorcentaje) {
            cmbTipo.setSelectedItem("Por Porcentaje");
            txtValor.setText(String.valueOf(((PromocionPorcentaje) p).getPorcentajeDescuento()));
        } else if (p instanceof PromocionFija) {
            cmbTipo.setSelectedItem("Fija");
            txtValor.setText(String.valueOf(((PromocionFija) p).getDescuentoFijo()));
        }
    }

    public void limpiarCampos() {
        txtCodigo.setText("");
        txtDescripcion.setText("");
        txtValor.setText("");
        cmbTipo.setSelectedIndex(0);
        chkActiva.setSelected(true);
        tablaPromociones.clearSelection();
    }

    // Getters
    public JTextField getTxtCodigo() { return txtCodigo; }
    public JTextField getTxtDescripcion() { return txtDescripcion; }
    public JTextField getTxtValor() { return txtValor; }
    public JComboBox<String> getCmbTipo() { return cmbTipo; }
    public JCheckBox getChkActiva() { return chkActiva; }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public int confirmarEliminacion() {
        return JOptionPane.showConfirmDialog(this, 
            "¿Está seguro que desea eliminar esta promoción?", 
            "Confirmar", 
            JOptionPane.YES_NO_OPTION);
    }
}

