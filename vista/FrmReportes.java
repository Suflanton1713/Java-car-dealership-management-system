/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto.vista;

import proyecto.*;
import proyecto.controlador.ControladorReporte;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author USUARIO
 */
public class FrmReportes extends javax.swing.JFrame {
    
    private JTextArea txtReporte;
    private JButton btnReporteVentas, btnReporteVehiculos, btnReporteClientes;
    private JButton btnLimpiar;
    private ControladorReporte controlador;
    private ArregloVentas arregloVentas;
    private ArregloVehiculos arregloVehiculos;
    private ArregloClientes arregloClientes;
    private ArregloEmpleados arregloEmpleados;

    public FrmReportes() {
        DatosSistema datos = DatosSistema.getInstancia();
        this.arregloVentas = datos.getArregloVentas();
        this.arregloVehiculos = datos.getArregloVehiculos();
        this.arregloClientes = datos.getArregloClientes();
        this.arregloEmpleados = datos.getArregloEmpleados();
        initComponents();
        configurarControlador();
    }

    private void initComponents() {
        setTitle("Reportes del Sistema");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout(15, 15));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        panelPrincipal.setBackground(new Color(245, 245, 250));

        // Panel de botones mejorado
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBotones.setBackground(new Color(255, 255, 255));
        panelBotones.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(),
                "Seleccionar Reporte",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                new Color(0, 51, 102)
            ),
            BorderFactory.createEmptyBorder(25, 30, 25, 30)
        ));

        btnReporteVentas = crearBoton("Ventas por Vendedor", new Color(0, 102, 204));
        btnReporteVehiculos = crearBoton("Vehículos Vendidos", new Color(0, 102, 204));
        btnReporteClientes = crearBoton("Clientes Frecuentes", new Color(0, 102, 204));
        btnLimpiar = crearBoton("Limpiar", new Color(153, 153, 153));

        panelBotones.add(btnReporteVentas);
        panelBotones.add(btnReporteVehiculos);
        panelBotones.add(btnReporteClientes);
        panelBotones.add(btnLimpiar);

        panelPrincipal.add(panelBotones, BorderLayout.NORTH);

        // Panel de texto del reporte mejorado
        JPanel panelReporte = new JPanel(new BorderLayout());
        panelReporte.setBackground(new Color(255, 255, 255));
        panelReporte.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(),
                "Reporte",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                new Color(0, 51, 102)
            ),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        txtReporte = new JTextArea(25, 70);
        txtReporte.setEditable(false);
        txtReporte.setFont(new Font("Courier New", Font.PLAIN, 13));
        txtReporte.setForeground(Color.BLACK);
        txtReporte.setBackground(new Color(250, 250, 255));
        txtReporte.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 230), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JScrollPane scrollPane = new JScrollPane(txtReporte);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 230), 1));
        panelReporte.add(scrollPane, BorderLayout.CENTER);

        panelPrincipal.add(panelReporte, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
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
        controlador = new ControladorReporte(this, arregloVentas, arregloVehiculos, 
                                            arregloClientes, arregloEmpleados);
        
        btnReporteVentas.addActionListener(e -> controlador.generarReporteVentas());
        btnReporteVehiculos.addActionListener(e -> controlador.generarReporteVehiculos());
        btnReporteClientes.addActionListener(e -> controlador.generarReporteClientes());
        btnLimpiar.addActionListener(e -> txtReporte.setText(""));
    }

    public void mostrarReporte(String reporte) {
        txtReporte.setText(reporte);
    }

    public ControladorReporte getControlador() {
        return controlador;
    }
}

