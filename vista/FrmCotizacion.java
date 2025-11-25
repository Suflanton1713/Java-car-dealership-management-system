/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto.vista;

import proyecto.*;
import proyecto.controlador.ControladorCotizacion;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author USUARIO
 */
public class FrmCotizacion extends javax.swing.JFrame {
    
    private JComboBox<Cliente> cmbCliente;
    private JComboBox<Vehiculo> cmbVehiculo;
    private JComboBox<Descontable> cmbPromocion;
    private JLabel lblPrecioBase, lblPrecioFinal;
    private JButton btnCalcular, btnGenerar;
    private ControladorCotizacion controlador;
    private ArregloClientes arregloClientes;
    private ArregloVehiculos arregloVehiculos;
    private ArregloPromociones arregloPromociones;
    private ArregloCotizaciones arregloCotizaciones;
    private Empleado vendedor;

    public FrmCotizacion(Empleado vendedor) {
        this.vendedor = vendedor;
        DatosSistema datos = DatosSistema.getInstancia();
        this.arregloClientes = datos.getArregloClientes();
        this.arregloVehiculos = datos.getArregloVehiculos();
        this.arregloPromociones = datos.getArregloPromociones();
        this.arregloCotizaciones = datos.getArregloCotizaciones();
        initComponents();
        configurarControlador();
        cargarCombos();
    }

    private void initComponents() {
        setTitle("Generar Cotización");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout(15, 15));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        panelPrincipal.setBackground(new Color(245, 245, 250));

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));
        panelFormulario.setBackground(new Color(255, 255, 255));
        panelFormulario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(),
                "Datos de la Cotización",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 18),
                new Color(0, 51, 102)
            ),
            BorderFactory.createEmptyBorder(40, 50, 40, 50)
        ));

        agregarCombo(panelFormulario, "Cliente:", cmbCliente = new JComboBox<>());
        agregarCombo(panelFormulario, "Vehículo:", cmbVehiculo = new JComboBox<>());
        agregarCombo(panelFormulario, "Promoción (Opcional):", cmbPromocion = new JComboBox<>());

        panelFormulario.add(Box.createVerticalStrut(30));

        JPanel panelPrecios = new JPanel(new GridLayout(2, 2, 15, 15));
        panelPrecios.setBackground(new Color(255, 255, 255));
        panelPrecios.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 230), 1),
            BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));
        
        JLabel lblPrecioBaseTxt = new JLabel("Precio Base:");
        lblPrecioBaseTxt.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblPrecioBaseTxt.setForeground(new Color(60, 60, 80));
        panelPrecios.add(lblPrecioBaseTxt);
        
        lblPrecioBase = new JLabel("S/ 0.00");
        lblPrecioBase.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblPrecioBase.setForeground(new Color(100, 100, 120));
        panelPrecios.add(lblPrecioBase);
        
        JLabel lblPrecioFinalTxt = new JLabel("Precio Final:");
        lblPrecioFinalTxt.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblPrecioFinalTxt.setForeground(new Color(60, 60, 80));
        panelPrecios.add(lblPrecioFinalTxt);
        
        lblPrecioFinal = new JLabel("S/ 0.00");
        lblPrecioFinal.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblPrecioFinal.setForeground(new Color(0, 153, 76));
        panelPrecios.add(lblPrecioFinal);
        
        panelFormulario.add(panelPrecios);
        panelFormulario.add(Box.createVerticalStrut(30));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelBotones.setBackground(new Color(255, 255, 255));
        
        btnCalcular = crearBoton("Calcular Precio", new Color(102, 102, 102));
        btnGenerar = crearBoton("Generar Cotización", new Color(0, 102, 204));
        
        panelBotones.add(btnCalcular);
        panelBotones.add(btnGenerar);
        panelFormulario.add(panelBotones);

        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
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

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setPreferredSize(new Dimension(200, 50));
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

    private void configurarControlador() {
        controlador = new ControladorCotizacion(this, arregloClientes, arregloVehiculos, 
                                                 arregloPromociones, arregloCotizaciones, vendedor);
        btnCalcular.addActionListener(e -> controlador.calcularPrecio());
        btnGenerar.addActionListener(e -> controlador.generarCotizacion());
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
            if (v != null && v.getEstado().equals("DISPONIBLE")) {
                cmbVehiculo.addItem(v);
            }
        }

        cmbPromocion.removeAllItems();
        cmbPromocion.addItem(null); // Opción sin promoción
        Promocion[] promociones = arregloPromociones.listarActivas();
        for (Promocion p : promociones) {
            if (p != null && p instanceof Descontable) {
                cmbPromocion.addItem((Descontable) p);
            }
        }
    }

    public void mostrarPrecio(double precioBase, double precioFinal) {
        lblPrecioBase.setText("S/ " + String.format("%.2f", precioBase));
        lblPrecioFinal.setText("S/ " + String.format("%.2f", precioFinal));
    }

    public void limpiarCampos() {
        cmbCliente.setSelectedIndex(-1);
        cmbVehiculo.setSelectedIndex(-1);
        cmbPromocion.setSelectedIndex(0);
        lblPrecioBase.setText("S/ 0.00");
        lblPrecioFinal.setText("S/ 0.00");
        cargarCombos();
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            cargarCombos(); // Recargar combos cada vez que se muestra la ventana
        }
        super.setVisible(visible);
    }

    // Getters
    public JComboBox<Cliente> getCmbCliente() { return cmbCliente; }
    public JComboBox<Vehiculo> getCmbVehiculo() { return cmbVehiculo; }
    public JComboBox<Descontable> getCmbPromocion() { return cmbPromocion; }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

