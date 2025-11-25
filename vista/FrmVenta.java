/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto.vista;

import proyecto.*;
import proyecto.controlador.ControladorVenta;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author USUARIO
 */
public class FrmVenta extends javax.swing.JFrame {
    
    private JComboBox<Cotizacion> cmbCotizacion;
    private JTextArea txtComprobante;
    private JButton btnRegistrar, btnGenerarBoleta, btnGenerarFactura;
    private ControladorVenta controlador;
    private ArregloCotizaciones arregloCotizaciones;
    private ArregloVentas arregloVentas;
    private Venta ventaActual;

    public FrmVenta() {
        DatosSistema datos = DatosSistema.getInstancia();
        this.arregloCotizaciones = datos.getArregloCotizaciones();
        this.arregloVentas = datos.getArregloVentas();
        initComponents();
        configurarControlador();
        actualizarLista();
    }

    private void initComponents() {
        setTitle("Registrar Venta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout(15, 15));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        panelPrincipal.setBackground(new Color(245, 245, 250));

        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));
        panelSuperior.setBackground(new Color(255, 255, 255));
        panelSuperior.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(),
                "Seleccionar Cotización",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                new Color(0, 51, 102)
            ),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));

        JLabel lblCotizacion = new JLabel("Cotización:");
        lblCotizacion.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblCotizacion.setForeground(new Color(60, 60, 80));
        panelSuperior.add(lblCotizacion);
        panelSuperior.add(Box.createVerticalStrut(10));
        cmbCotizacion = new JComboBox<>();
        cmbCotizacion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cmbCotizacion.setForeground(Color.BLACK);
        cmbCotizacion.setPreferredSize(new Dimension(0, 42));
        panelSuperior.add(cmbCotizacion);
        panelSuperior.add(Box.createVerticalStrut(25));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBotones.setBackground(new Color(255, 255, 255));
        
        btnRegistrar = crearBoton("Registrar Venta", new Color(0, 153, 76));
        btnGenerarBoleta = crearBoton("Generar Boleta", new Color(0, 102, 204));
        btnGenerarFactura = crearBoton("Generar Factura", new Color(153, 51, 0));
        
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnGenerarBoleta);
        panelBotones.add(btnGenerarFactura);
        panelSuperior.add(panelBotones);

        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);

        JPanel panelComprobante = new JPanel(new BorderLayout());
        panelComprobante.setBackground(new Color(255, 255, 255));
        panelComprobante.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(),
                "Comprobante",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                new Color(0, 51, 102)
            ),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        txtComprobante = new JTextArea(20, 60);
        txtComprobante.setEditable(false);
        txtComprobante.setFont(new Font("Courier New", Font.PLAIN, 13));
        txtComprobante.setForeground(Color.BLACK);
        txtComprobante.setBackground(new Color(250, 250, 255));
        txtComprobante.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 230), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JScrollPane scrollPane = new JScrollPane(txtComprobante);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 230), 1));
        panelComprobante.add(scrollPane, BorderLayout.CENTER);
        panelPrincipal.add(panelComprobante, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(180, 48));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void configurarControlador() {
        controlador = new ControladorVenta(this, arregloCotizaciones, arregloVentas);
        btnRegistrar.addActionListener(e -> controlador.registrarVenta());
        btnGenerarBoleta.addActionListener(e -> generarBoleta());
        btnGenerarFactura.addActionListener(e -> generarFactura());
    }

    public void actualizarLista() {
        cmbCotizacion.removeAllItems();
        Cotizacion[] cotizaciones = arregloCotizaciones.listar();
        for (Cotizacion c : cotizaciones) {
            if (c != null && c.getVehiculo().getEstado().equals("DISPONIBLE")) {
                cmbCotizacion.addItem(c);
            }
        }
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            actualizarLista(); // Recargar lista cada vez que se muestra la ventana
        }
        super.setVisible(visible);
    }

    public void mostrarComprobante(Venta venta) {
        this.ventaActual = venta;
        generarBoleta(); // Por defecto muestra boleta
    }

    private void generarBoleta() {
        if (ventaActual == null) {
            mostrarError("Primero debe registrar una venta");
            return;
        }

        Boleta boleta = new Boleta("B001", ventaActual.getNumero(), ventaActual);
        txtComprobante.setText(boleta.generarTexto());
    }

    private void generarFactura() {
        if (ventaActual == null) {
            mostrarError("Primero debe registrar una venta");
            return;
        }

        Factura factura = new Factura("F001", ventaActual.getNumero(), ventaActual, "20123456789");
        txtComprobante.setText(factura.generarTexto());
    }

    // Getters
    public JComboBox<Cotizacion> getCmbCotizacion() { return cmbCotizacion; }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

