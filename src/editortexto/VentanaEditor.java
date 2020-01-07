/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editortexto;

import java.awt.BorderLayout;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Usuario DAM 2
 */
public class VentanaEditor extends JFrame implements ActionListener, ChangeListener,ListSelectionListener {

    JMenuBar barra;
    JMenu archivo, edicion, estiloFuente;
    JMenuItem nuevo, abrir, cerrar, guardar, guardarComo, imprimir;
    JMenuItem copiar, pegar, cortar, normal, bold, cursiva;
    JMenuItem copiar1, pegar1, cortar1, normal1, bold1, cursiva1;
    JPanel panelSuperior, panelCentral;
    JButton bNuevo, bAbrir, bGuardar, bCopiar, bCortar, bPegar, bBold;
    Container container;
    JLabel tamanioL, tipoLetraL;
    ImageIcon iNuevo;
    JTextArea texto;
    JSpinner tamanio;
    JComboBox tipoLetra;
    DefaultComboBoxModel modeloTipoLetra;
    SpinnerNumberModel modeloNumeros;
    JList lista;
    File dir = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Desktop");
    String[] arrayDir = dir.list();
    JPopupMenu menuPopUp;
    VentanaEditor ventanaEditor;
    final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

    public void initGUI() {
        instancias();
        configurarMenu();
        configurarPanel();
        configurarPopUp();
        rellenarLetras();
        acciones();
        this.setSize(new Dimension(900, 500));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Procesador de textos");
        this.setVisible(true);
    }

    private void instancias() {
        container = this.getContentPane();
        barra = new JMenuBar();
        archivo = new JMenu("Archivo");
        edicion = new JMenu("Edicion");
        estiloFuente = new JMenu("Estilo de fuente ");
        nuevo = new JMenuItem("Nuevo", new ImageIcon(getClass().getResource("../recursos/new.png")));
        //nuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.ALT_DOWN_MASK));
        //nuevo.setToolTipText("Pulsar Ctl + 1");
        abrir = new JMenuItem("Abrir", new ImageIcon(getClass().getResource("../recursos/open.png")));
        //abrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.ALT_DOWN_MASK));
        //abrir.setToolTipText("Pulsar Ctl + 2");
        cerrar = new JMenuItem("Cerrar", new ImageIcon(getClass().getResource("../recursos/close.png")));
        //cerrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.ALT_DOWN_MASK));
        //cerrar.setToolTipText("Pulsar Ctl + 3");
        guardar = new JMenuItem("Guardar", new ImageIcon(getClass().getResource("../recursos/save.png")));
        //guardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.ALT_DOWN_MASK));
        //guardar.setToolTipText("Pulsar Ctl + 4");
        guardarComo = new JMenuItem("Guardar como...");
        //guardarComo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, InputEvent.ALT_DOWN_MASK));
        //guardarComo.setToolTipText("Pulsar Ctl + 5");
        imprimir = new JMenuItem("Imprimir");
        //imprimir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_6, InputEvent.ALT_DOWN_MASK));
        //imprimir.setToolTipText("Pulsar Ctl + 6");
        copiar = new JMenuItem("Copiar", new ImageIcon(getClass().getResource("../recursos/copy.png")));
        //copiar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.ALT_DOWN_MASK));
        //copiar.setToolTipText("Pulsar Ctl + 1");
        cortar = new JMenuItem("Cortar", new ImageIcon(getClass().getResource("../recursos/cut.png")));
        //cortar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.ALT_DOWN_MASK));
        //cortar.setToolTipText("Pulsar Ctl + 2");
        pegar = new JMenuItem("Pegar", new ImageIcon(getClass().getResource("../recursos/paste.png")));
        //pegar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.ALT_DOWN_MASK));
        //pegar.setToolTipText("Pulsar Ctl + 3");
        normal = new JMenuItem("Normal", new ImageIcon(getClass().getResource("../recursos/normal.png")));
        //normal.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.ALT_DOWN_MASK));
        //normal.setToolTipText("Pulsar Ctl + 4");
        bold = new JMenuItem("Bold", new ImageIcon(getClass().getResource("../recursos/bold.png")));
        //bold.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, InputEvent.ALT_DOWN_MASK));
        //bold.setToolTipText("Pulsar Ctl + 5");
        cursiva = new JMenuItem("Cursiva", new ImageIcon(getClass().getResource("../recursos/italic.png")));
        //cursiva.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_6, InputEvent.ALT_DOWN_MASK));
        //cursiva.setToolTipText("Pulsar Ctl + 6");        
        copiar1 = new JMenuItem("Copiar", new ImageIcon(getClass().getResource("../recursos/copy.png")));
        cortar1 = new JMenuItem("Cortar", new ImageIcon(getClass().getResource("../recursos/cut.png")));
        pegar1 = new JMenuItem("Pegar", new ImageIcon(getClass().getResource("../recursos/paste.png")));
        normal1 = new JMenuItem("Normal", new ImageIcon(getClass().getResource("../recursos/normal.png")));
        bold1 = new JMenuItem("Bold", new ImageIcon(getClass().getResource("../recursos/bold.png")));
        cursiva1 = new JMenuItem("Cursiva", new ImageIcon(getClass().getResource("../recursos/italic.png")));
        panelSuperior = new JPanel();
        panelCentral = new JPanel();
        bNuevo = new JButton(new ImageIcon(getClass().getResource("../recursos/new.png")));
        bAbrir = new JButton(new ImageIcon(getClass().getResource("../recursos/open.png")));
        bGuardar = new JButton(new ImageIcon(getClass().getResource("../recursos/save.png")));
        bCopiar = new JButton(new ImageIcon(getClass().getResource("../recursos/copy.png")));
        bCortar = new JButton(new ImageIcon(getClass().getResource("../recursos/cut.png")));
        bPegar = new JButton(new ImageIcon(getClass().getResource("../recursos/paste.png")));
        bBold = new JButton(new ImageIcon(getClass().getResource("../recursos/bold.png")));
        tamanioL = new JLabel("Tamaño de letra");
        tipoLetraL = new JLabel("Tipo de letra");
        texto = new JTextArea();
        modeloNumeros = new SpinnerNumberModel(5, 5, 72, 1);
        tamanio = new JSpinner(modeloNumeros);
        modeloTipoLetra = new DefaultComboBoxModel();
        tipoLetra = new JComboBox(modeloTipoLetra);
        lista = new JList(arrayDir);
        ventanaEditor = this;
        menuPopUp = new JPopupMenu();

    }

    private void configurarPanel() {
        container.setLayout(new BorderLayout());
        container.add(configurarSuperior(), BorderLayout.NORTH);
        container.add(configurarCentral(), BorderLayout.CENTER);
    }

    private void configurarMenu() {

        archivo.add(nuevo);
        archivo.add(abrir);
        archivo.add(cerrar);
        archivo.add(guardar);
        archivo.add(guardarComo);
        archivo.addSeparator();
        archivo.add(imprimir);
        edicion.add(copiar);
        edicion.add(cortar);
        edicion.add(pegar);
        edicion.addSeparator();
        edicion.add(estiloFuente);
        estiloFuente.add(bold);
        estiloFuente.add(normal);
        estiloFuente.add(cursiva);
        barra.add(archivo);
        barra.add(edicion);

        this.setJMenuBar(barra);
    }

    private JPanel configurarCentral() {
        panelCentral.setLayout(new BorderLayout());
        panelCentral.add(new JScrollPane(texto), BorderLayout.CENTER);
        panelCentral.add(lista, BorderLayout.EAST);
        return panelCentral;
    }

    private JPanel configurarSuperior() {
        panelSuperior.setLayout(new FlowLayout());
        panelSuperior.add(bNuevo);
        panelSuperior.add(bAbrir);
        panelSuperior.add(bGuardar);
        panelSuperior.add(bCopiar);
        panelSuperior.add(bCortar);
        panelSuperior.add(bPegar);
        panelSuperior.add(bBold);
        panelSuperior.add(tamanioL);
        panelSuperior.add(tamanio);
        panelSuperior.add(tipoLetraL);
        panelSuperior.add(tipoLetra);
        return panelSuperior;
    }

    private void configurarPopUp() {
        menuPopUp.add(copiar1, new ImageIcon(getClass().getResource("../recursos/copy.png")));
        menuPopUp.add(cortar1, new ImageIcon(getClass().getResource("../recursos/cut.png")));
        menuPopUp.add(pegar1, new ImageIcon(getClass().getResource("../recursos/paste.png")));
        menuPopUp.addSeparator();
        menuPopUp.add(normal1, new ImageIcon(getClass().getResource("../recursos/normal.png")));
        menuPopUp.add(bold1, new ImageIcon(getClass().getResource("../recursos/bold.png")));
        menuPopUp.add(cursiva1, new ImageIcon(getClass().getResource("../recursos/italic.png")));
    }

    private void rellenarLetras() {
        Font[] fuentes = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getAllFonts();

        for (Font item : fuentes) {
            modeloTipoLetra.addElement(item.getName());
        }
    }

    private void copiar() {
        String seleccionado = texto.getSelectedText();
        StringSelection selection = new StringSelection(seleccionado);
        clipboard.setContents(selection, selection);
    }

    private void cortar() {
        String seleccionado = texto.getSelectedText();
        StringSelection selection = new StringSelection(seleccionado);
        clipboard.setContents(selection, selection);
        texto.replaceRange("", texto.getSelectionStart(), texto.getSelectionEnd());
    }

    private void pegar() {
        Transferable transferable = clipboard.getContents(clipboard);

        try {
            if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                String s = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                texto.replaceSelection(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void bold() {

        int tamanioI = Integer.valueOf(tamanio.getModel().getValue().toString());
        String letraS = tipoLetra.getSelectedItem().toString();

        Font font = new Font(letraS, 2, tamanioI);

        texto.setFont(font);

    }

    private void normal() {

        int tamanioI = Integer.valueOf(tamanio.getModel().getValue().toString());
        String letraS = tipoLetra.getSelectedItem().toString();

        Font font = new Font(letraS, 0, tamanioI);

        texto.setFont(font);

    }

    private void cursiva() {

        int tamanioI = Integer.valueOf(tamanio.getModel().getValue().toString());
        String letraS = tipoLetra.getSelectedItem().toString();

        Font font = new Font(letraS, 1, tamanioI);

        texto.setFont(font);

    }

    private void cerrar() {
        int opcion = JOptionPane.showConfirmDialog(VentanaEditor.this, "¿Desea cerrar el proyecto?", "Cerrar", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            texto.setText("");
        }

    }

    private void acciones() {
        texto.addMouseListener(new ManejoRaton());
        bAbrir.addActionListener(this);
        abrir.addActionListener(this);
        bCopiar.addActionListener(this);
        bCortar.addActionListener(this);
        bPegar.addActionListener(this);
        copiar1.addActionListener(this);
        pegar1.addActionListener(this);
        cortar1.addActionListener(this);
        copiar.addActionListener(this);
        pegar.addActionListener(this);
        cortar.addActionListener(this);
        bold1.addActionListener(this);
        bBold.addActionListener(this);
        normal1.addActionListener(this);
        cursiva1.addActionListener(this);
        bold.addActionListener(this);
        normal.addActionListener(this);
        cursiva.addActionListener(this);
        bNuevo.addActionListener(this);
        nuevo.addActionListener(this);
        bGuardar.addActionListener(this);
        guardar.addActionListener(this);
        lista.addListSelectionListener(this);
        tamanio.addChangeListener(this);
        cerrar.addActionListener(this);
    }

    private void crearArchivo() throws IOException {
        File f = new File("..\\EditordeTexto\\src\\Archivos\\fichero.txt");
        //File tempFile = File.createTempFile("fichero", ".txt");

        f.createNewFile();

        /*if (tempFile.exists()) {
            JOptionPane.showMessageDialog(VentanaEditor.this, "Fichero creado correctamente", "Añadir fichero", JOptionPane.INFORMATION_MESSAGE, null);
        } else {
            JOptionPane.showMessageDialog(VentanaEditor.this, "El fichero no se creo correctamente", "Error fichero", JOptionPane.WARNING_MESSAGE, null);
        }*/
        if (f.exists()) {
            JOptionPane.showMessageDialog(VentanaEditor.this, "Fichero creado correctamente", "Añadir fichero", JOptionPane.INFORMATION_MESSAGE, null);
        } else {
            JOptionPane.showMessageDialog(VentanaEditor.this, "El fichero no se creo correctamente", "Error fichero", JOptionPane.WARNING_MESSAGE, null);
        }
    }

    /*private void guardararchivo() {

        FileChooser dialogoFichero = new FileChooser();
        dialogoFichero.setTitle("Selecciona un fichero");
//File fAbrir = dialogoFichero.showOpenDialog(null);
//labelFichero.setText(f.getAbsolutePath());
        File fGuardar = dialogoFichero.showSaveDialog(null);

        if (fGuardar != null) {
            try {
                FileWriter fileWriter = null;
                fileWriter = new FileWriter(fGuardar);
                fileWriter.close();
            } catch (IOException ex) {
                System.out.println("Excepción capturada");
            }
        }
    }*/
    private void guardarArchivo() {
        JFileChooser fileChoser = new JFileChooser();
        fileChoser.showSaveDialog(this);
        File archivoF = fileChoser.getSelectedFile();
        try {
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(archivoF);
            BufferedWriter salida = new BufferedWriter(fileWriter);
            salida.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void abrirArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        int i = fileChooser.showOpenDialog(this);
        if (i == JFileChooser.APPROVE_OPTION) {
            String f = fileChooser.getSelectedFile().getName();
            String ex = f.substring(f.indexOf(".") + 1);
            System.out.println(ex);
        } else if (i == JFileChooser.CANCEL_OPTION) {

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bAbrir || e.getSource() == abrir) {
            abrirArchivo();
        } else if (e.getSource() == bCopiar) {
            copiar();
            //texto.copy();
        } else if (e.getSource() == bCortar) {
            cortar();
            //texto.cut();
        } else if (e.getSource() == bPegar) {
            pegar();
            //texto.paste();
        } else if (e.getSource() == copiar1 || e.getSource() == copiar) {
            copiar();
        } else if (e.getSource() == cortar1 || e.getSource() == cortar) {
            cortar();
        } else if (e.getSource() == pegar1 || e.getSource() == pegar) {
            pegar();
        } else if (e.getSource() == bold1 || e.getSource() == bold) {
            bold();
        } else if (e.getSource() == cursiva1 || e.getSource() == cursiva) {
            cursiva();
        } else if (e.getSource() == normal1 || e.getSource() == normal) {
            normal();
        } else if (e.getSource() == bBold) {
            bold();
        } else if (e.getSource() == bNuevo || e.getSource() == nuevo) {
            try {
                crearArchivo();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == bGuardar || e.getSource() == guardar) {
            guardarArchivo();
        } else if (e.getSource() == cerrar) {
            cerrar();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == tamanio) {
            normal();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        String seleccion = String.valueOf(lista.getSelectedValue());
        File fileSeleccioanda = new File(dir.getAbsolutePath() + "\\" + seleccion);

        if (!Desktop.isDesktopSupported()) {
            System.out.println("Desktop no está soportado");
        }

        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(fileSeleccioanda);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    class ManejoRaton extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);

            if (e.getButton() == MouseEvent.BUTTON3) {
                menuPopUp.show(ventanaEditor, e.getX(), e.getY());
            }

        }
    }
}
