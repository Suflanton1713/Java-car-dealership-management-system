/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto.vista;

import proyecto.*;
import proyecto.controlador.ControladorEmpleado;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 *
 * @author USUARIO
 */
public class FrmGestionEmpleados extends javax.swing.JFrame {
    
    private JTable tablaEmpleados;
    private DefaultTableModel modeloTabla;
    private JTextField txtDni, txtNombres, txtApellidoPaterno, txtApellidoMaterno;
    private JTextField txtTelefono, txtCorreo, txtUsuario;
    private JPasswordField txtClave;
    private JComboBox<Rol> cmbRol;
    private JButton btnAgregar, btnModificar, btnEliminar, btnBuscar, btnLimpiar;
    private ControladorEmpleado controlador;
    private ArregloEmpleados arregloEmpleados;

    public FrmGestionEmpleados() {
        arregloEmpleados = DatosSistema.getInstancia().getArregloEmpleados();
        initComponents();
        configurarControlador();
        actualizarTabla();
    }

    private void initComponents() {
        setTitle("Gestión de Empleados");
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
                "Datos del Empleado",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 14),
                new Color(0, 51, 102)
            ),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        // Configuración para que el scroll funcione correctamente
        panel.setPreferredSize(new Dimension(340, 1000));
        panel.setMaximumSize(new Dimension(340, Integer.MAX_VALUE));
        panel.setMinimumSize(new Dimension(340, 200));

        agregarCampo(panel, "DNI:", txtDni = new JTextField());
        agregarCampo(panel, "Nombres:", txtNombres = new JTextField());
        agregarCampo(panel, "Apellido Paterno:", txtApellidoPaterno = new JTextField());
        agregarCampo(panel, "Apellido Materno:", txtApellidoMaterno = new JTextField());
        agregarCampo(panel, "Teléfono:", txtTelefono = new JTextField());
        agregarCampo(panel, "Correo:", txtCorreo = new JTextField());
        agregarCampo(panel, "Usuario:", txtUsuario = new JTextField());
        
        JLabel lblClave = new JLabel("Contraseña:");
        lblClave.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblClave.setForeground(new Color(60, 60, 80));
        panel.add(lblClave);
        panel.add(Box.createVerticalStrut(5));
        txtClave = new JPasswordField();
        txtClave.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtClave.setForeground(Color.BLACK);
        txtClave.setPreferredSize(new Dimension(0, 38));
        txtClave.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 210), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        panel.add(txtClave);
        panel.add(Box.createVerticalStrut(15));

        JLabel lblRol = new JLabel("Rol:");
        lblRol.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblRol.setForeground(new Color(60, 60, 80));
        panel.add(lblRol);
        panel.add(Box.createVerticalStrut(5));
        cmbRol = new JComboBox<>(Rol.values());
        cmbRol.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cmbRol.setForeground(Color.BLACK);
        cmbRol.setPreferredSize(new Dimension(0, 38));
        panel.add(cmbRol);
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

    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(),
                "Lista de Empleados",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                new Color(0, 51, 102)
            ),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        String[] columnas = {"DNI", "Nombres", "Apellidos", "Usuario", "Rol"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaEmpleados = new JTable(modeloTabla);
        tablaEmpleados.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaEmpleados.setRowHeight(35);
        tablaEmpleados.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablaEmpleados.getTableHeader().setBackground(new Color(0, 51, 102));
        tablaEmpleados.getTableHeader().setForeground(Color.WHITE);
        tablaEmpleados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaEmpleados.setSelectionBackground(new Color(230, 240, 255));
        tablaEmpleados.setGridColor(new Color(220, 220, 230));
        tablaEmpleados.getSelectionModel().addListSelectionListener(e -> {
            int fila = tablaEmpleados.getSelectedRow();
            if (fila >= 0) {
                String dni = (String) modeloTabla.getValueAt(fila, 0);
                Empleado emp = arregloEmpleados.buscar(dni);
                if (emp != null) {
                    cargarDatos(emp);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tablaEmpleados);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 230), 1));
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private void configurarControlador() {
        controlador = new ControladorEmpleado(this, arregloEmpleados);
    }

    public void actualizarTabla() {
        modeloTabla.setRowCount(0);
        Empleado[] empleados = arregloEmpleados.listar();
        for (Empleado e : empleados) {
            if (e != null) {
                Object[] fila = {
                    e.getDni(),
                    e.getNombres(),
                    e.getApellidoPaterno() + " " + e.getApellidoMaterno(),
                    e.getUsuario(),
                    e.getRol()
                };
                modeloTabla.addRow(fila);
            }
        }
    }

    public void cargarDatos(Empleado e) {
        txtDni.setText(e.getDni());
        txtNombres.setText(e.getNombres());
        txtApellidoPaterno.setText(e.getApellidoPaterno());
        txtApellidoMaterno.setText(e.getApellidoMaterno());
        txtTelefono.setText(e.getTelefono());
        txtCorreo.setText(e.getCorreo());
        txtUsuario.setText(e.getUsuario());
        txtClave.setText("");
        cmbRol.setSelectedItem(e.getRol());
    }

    public void limpiarCampos() {
        txtDni.setText("");
        txtNombres.setText("");
        txtApellidoPaterno.setText("");
        txtApellidoMaterno.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtUsuario.setText("");
        txtClave.setText("");
        cmbRol.setSelectedIndex(0);
        tablaEmpleados.clearSelection();
    }

    // Getters
    public JTextField getTxtDni() { return txtDni; }
    public JTextField getTxtNombres() { return txtNombres; }
    public JTextField getTxtApellidoPaterno() { return txtApellidoPaterno; }
    public JTextField getTxtApellidoMaterno() { return txtApellidoMaterno; }
    public JTextField getTxtTelefono() { return txtTelefono; }
    public JTextField getTxtCorreo() { return txtCorreo; }
    public JTextField getTxtUsuario() { return txtUsuario; }
    public JPasswordField getTxtClave() { return txtClave; }
    public JComboBox<Rol> getCmbRol() { return cmbRol; }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public int confirmarEliminacion() {
        return JOptionPane.showConfirmDialog(this, 
            "¿Está seguro que desea eliminar este empleado?", 
            "Confirmar", 
            JOptionPane.YES_NO_OPTION);
    }
}

