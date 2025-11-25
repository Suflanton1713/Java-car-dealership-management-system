/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto.vista;

import proyecto.Empleado;
import proyecto.Rol;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author USUARIO
 */
public class FrmMenuPrincipal extends javax.swing.JFrame {
    
    private Empleado empleadoLogueado;
    private JMenuBar menuBar;
    private JMenu menuGestion, menuVentas, menuReportes, menuSalir;
    private JMenuItem itemEmpleados, itemVehiculos, itemClientes, itemPromociones;
    private JMenuItem itemCotizaciones, itemVentas, itemPagos;
    private JMenuItem itemReporteVentas, itemReporteVehiculos, itemReporteClientes;
    private JMenuItem itemSalir, itemTestDrive;

    public FrmMenuPrincipal(Empleado empleado) {
        this.empleadoLogueado = empleado;
        initComponents();
        configurarMenuSegunRol();
    }

    private void initComponents() {
        setTitle("Sistema de Concesionaria - Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 750);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Panel principal con información del usuario
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(new Color(245, 245, 250));

        // Panel superior con información del usuario mejorado
        JPanel panelUsuario = new JPanel();
        panelUsuario.setLayout(new BorderLayout());
        panelUsuario.setBackground(new Color(0, 51, 102));
        panelUsuario.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        JLabel lblBienvenido = new JLabel("Bienvenido: " + empleadoLogueado.nombreCompleto() + 
                                         " (" + empleadoLogueado.getRol() + ")");
        lblBienvenido.setForeground(Color.WHITE);
        lblBienvenido.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panelUsuario.add(lblBienvenido, BorderLayout.WEST);

        JLabel lblFecha = new JLabel(new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(new java.util.Date()));
        lblFecha.setForeground(new Color(200, 200, 220));
        lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panelUsuario.add(lblFecha, BorderLayout.EAST);

        panelPrincipal.add(panelUsuario, BorderLayout.NORTH);

        // Panel central mejorado
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        panelCentral.setBackground(new Color(245, 245, 250));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(80, 80, 80, 80));

        JLabel lblTitulo = new JLabel("SISTEMA DE GESTIÓN DE CONCESIONARIA", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblTitulo.setForeground(new Color(0, 51, 102));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panelCentral.add(lblTitulo, BorderLayout.NORTH);

        JLabel lblInstrucciones = new JLabel("Use el menú superior para acceder a las diferentes opciones del sistema", 
                                             SwingConstants.CENTER);
        lblInstrucciones.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblInstrucciones.setForeground(new Color(100, 100, 120));
        lblInstrucciones.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        panelCentral.add(lblInstrucciones, BorderLayout.CENTER);

        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

        add(panelPrincipal);

        // Crear menú
        crearMenu();
    }

    private void crearMenu() {
        menuBar = new JMenuBar();
        menuBar.setBackground(new Color(250, 250, 255));
        menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 51, 102)));

        // Menú Gestión
        menuGestion = new JMenu("Gestión");
        menuGestion.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        itemEmpleados = new JMenuItem("Empleados");
        itemVehiculos = new JMenuItem("Vehículos");
        itemClientes = new JMenuItem("Clientes");
        itemPromociones = new JMenuItem("Promociones");
        itemTestDrive = new JMenuItem("Test Drive");

        menuGestion.add(itemEmpleados);
        menuGestion.add(itemVehiculos);
        menuGestion.add(itemClientes);
        menuGestion.add(itemPromociones);
        menuGestion.addSeparator();
        menuGestion.add(itemTestDrive);

        // Menú Ventas
        menuVentas = new JMenu("Ventas");
        menuVentas.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        itemCotizaciones = new JMenuItem("Cotizaciones");
        itemVentas = new JMenuItem("Registrar Venta");
        itemPagos = new JMenuItem("Pagos");

        menuVentas.add(itemCotizaciones);
        menuVentas.add(itemVentas);
        menuVentas.add(itemPagos);

        // Menú Reportes
        menuReportes = new JMenu("Reportes");
        menuReportes.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        itemReporteVentas = new JMenuItem("Ventas por Vendedor");
        itemReporteVehiculos = new JMenuItem("Vehículos Vendidos");
        itemReporteClientes = new JMenuItem("Clientes Frecuentes");

        menuReportes.add(itemReporteVentas);
        menuReportes.add(itemReporteVehiculos);
        menuReportes.add(itemReporteClientes);

        // Menú Salir
        menuSalir = new JMenu("Salir");
        menuSalir.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        itemSalir = new JMenuItem("Cerrar Sesión");
        itemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });

        menuSalir.add(itemSalir);

        // Agregar menús a la barra
        menuBar.add(menuGestion);
        menuBar.add(menuVentas);
        menuBar.add(menuReportes);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(menuSalir);

        setJMenuBar(menuBar);

        // Agregar listeners temporales (se implementarán en PASO 4)
        agregarListenersTemporales();
    }

    private void configurarMenuSegunRol() {
        if (empleadoLogueado.getRol() == Rol.VENDEDOR) {
            // Vendedor: solo acceso a Clientes, Cotizaciones y Ventas
            itemEmpleados.setEnabled(false);
            itemPromociones.setEnabled(false);
            itemReporteVentas.setEnabled(false);
            itemReporteVehiculos.setEnabled(false);
            itemReporteClientes.setEnabled(false);
        }
        // Admin: acceso completo (todos los items habilitados por defecto)
    }

    private void agregarListenersTemporales() {
        itemEmpleados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Solo Admin puede acceder (ya está deshabilitado para Vendedor)
                new FrmGestionEmpleados().setVisible(true);
            }
        });

        itemVehiculos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrmGestionVehiculos().setVisible(true);
            }
        });

        itemClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrmGestionClientes().setVisible(true);
            }
        });

        itemTestDrive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrmReservaTestDrive().setVisible(true);
            }
        });

        itemPromociones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrmGestionPromociones().setVisible(true);
            }
        });

        itemCotizaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrmCotizacion(empleadoLogueado).setVisible(true);
            }
        });

        itemVentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrmVenta().setVisible(true);
            }
        });

        itemPagos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrmPagos().setVisible(true);
            }
        });

        itemReporteVentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmReportes reportes = new FrmReportes();
                reportes.setVisible(true);
                reportes.getControlador().generarReporteVentas();
            }
        });

        itemReporteVehiculos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmReportes reportes = new FrmReportes();
                reportes.setVisible(true);
                reportes.getControlador().generarReporteVehiculos();
            }
        });

        itemReporteClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmReportes reportes = new FrmReportes();
                reportes.setVisible(true);
                reportes.getControlador().generarReporteClientes();
            }
        });
    }

    private void cerrarSesion() {
        int opcion = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro que desea cerrar sesión?",
            "Confirmar",
            JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {
            this.dispose();
            FrmLogin login = new FrmLogin();
            login.setVisible(true);
        }
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public Empleado getEmpleadoLogueado() {
        return empleadoLogueado;
    }
}

