/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto.vista;

import proyecto.ArregloEmpleados;
import proyecto.DatosSistema;
import proyecto.controlador.ControladorLogin;
import proyecto.servicio.ServicioLogin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author USUARIO
 */
public class FrmLogin extends javax.swing.JFrame {
    
    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JButton btnIngresar;
    private ControladorLogin controlador;

    public FrmLogin() {
        initComponents();
        configurarControlador();
    }

    private void initComponents() {
        setTitle("Sistema de Concesionaria - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Panel principal con fondo elegante
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(new Color(245, 245, 250));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(50, 60, 50, 60));

        // Panel de título mejorado
        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(new Color(245, 245, 250));
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
        
        JLabel lblTitulo = new JLabel("CONCESIONARIA DE AUTOMÓVILES", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(0, 51, 102));
        panelTitulo.add(lblTitulo, BorderLayout.CENTER);
        
        JLabel lblSubtitulo = new JLabel("Sistema de Gestión", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSubtitulo.setForeground(new Color(100, 100, 120));
        lblSubtitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        panelTitulo.add(lblSubtitulo, BorderLayout.SOUTH);
        
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);

        // Panel de formulario con mejor diseño
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridBagLayout());
        panelFormulario.setBackground(new Color(255, 255, 255));
        panelFormulario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 230), 1),
            BorderFactory.createEmptyBorder(40, 50, 40, 50)
        ));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 15, 20, 15);
        gbc.anchor = GridBagConstraints.WEST;

        // Usuario
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblUsuario.setForeground(new Color(60, 60, 80));
        panelFormulario.add(lblUsuario, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.ipadx = 10;
        txtUsuario = new JTextField(25);
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsuario.setForeground(Color.BLACK);
        txtUsuario.setPreferredSize(new Dimension(300, 40));
        txtUsuario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 210), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        panelFormulario.add(txtUsuario, gbc);

        // Clave
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.ipadx = 0;
        JLabel lblClave = new JLabel("Contraseña:");
        lblClave.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblClave.setForeground(new Color(60, 60, 80));
        panelFormulario.add(lblClave, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.ipadx = 10;
        txtClave = new JPasswordField(25);
        txtClave.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtClave.setForeground(Color.BLACK);
        txtClave.setPreferredSize(new Dimension(300, 40));
        txtClave.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 210), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        panelFormulario.add(txtClave, gbc);

        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);

        // Panel de botones mejorado
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelBotones.setBackground(new Color(245, 245, 250));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

        btnIngresar = new JButton("Ingresar");
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnIngresar.setPreferredSize(new Dimension(200, 50));
        btnIngresar.setBackground(new Color(0, 102, 204));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFocusPainted(false);
        btnIngresar.setBorderPainted(false);
        btnIngresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controlador != null) {
                    controlador.iniciarSesion();
                }
            }
        });

        panelBotones.add(btnIngresar);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);
        setSize(600, 500);
    }

    private void configurarControlador() {
        ArregloEmpleados arregloEmpleados = DatosSistema.getInstancia().getArregloEmpleados();
        ServicioLogin servicio = new ServicioLogin(arregloEmpleados);
        controlador = new ControladorLogin(this, servicio);
    }

    // Getters
    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public JPasswordField getTxtClave() {
        return txtClave;
    }

    // Métodos de utilidad
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void limpiarCampos() {
        txtUsuario.setText("");
        txtClave.setText("");
        txtUsuario.requestFocus();
    }

    public void ocultar() {
        this.setVisible(false);
    }
}

