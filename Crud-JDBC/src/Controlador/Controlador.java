package Controlador;

import Modelo.Estudiante;
import Modelo.EstudianteDAO;
import Vista.vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Controlador implements ActionListener {

    EstudianteDAO dao = new EstudianteDAO();
    Estudiante estudiante = new Estudiante();
    vista vista = new vista();
    DefaultTableModel modelo = new DefaultTableModel();

    public Controlador(vista v) {
        this.vista = v;
        this.vista.btnListar.addActionListener(this);
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnBorrar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnNuevo.addActionListener(this);

    }

    void nuevo() {
        vista.txtId.setText("");
        vista.txtNom.setText("");
        vista.txtCorreo.setText("");
        vista.txtTel.setText("");
    }

    void limpiarTabla() {
        for (int i = 0; i < vista.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void delete() {
        int fila = vista.tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "seleccione un registro");

        } else {
            int id = Integer.parseInt((String) vista.tabla.getValueAt(fila, 0).toString());
            dao.delete(id);
            JOptionPane.showMessageDialog(vista, "Estudiante eliminado");
        }
        limpiarTabla();
    }

    public void add() {
        String nom = vista.txtNom.getText();
        String correo = vista.txtCorreo.getText();
        String tel = vista.txtTel.getText();
        estudiante.setNom(nom);
        estudiante.setCorreo(correo);
        estudiante.setTelefono(tel);
        int r = dao.agregar(estudiante);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "si lo agrege");
        } else {
            JOptionPane.showMessageDialog(vista, "no lo puede agregar");

        }
        limpiarTabla();
    }

    public void actualizar() {
        if (vista.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "seleccione primero");
        } else {
            int id = Integer.parseInt(vista.txtId.getText());
            String nom = vista.txtNom.getText();
            String correo = vista.txtCorreo.getText();
            String tel = vista.txtTel.getText();
            estudiante.setId(id);
            estudiante.setNom(nom);
            estudiante.setCorreo(correo);
            estudiante.setTelefono(tel);
            int r = dao.actualizar(estudiante);
            if (r == 1) {
                JOptionPane.showMessageDialog(vista, "si lo actualice");
            } else {
                JOptionPane.showMessageDialog(vista, "no lo pude actualizar");

            }
        }
        limpiarTabla();
    }

    public void listar(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Estudiante> lista = dao.listar();
        Object[] objeto = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getNom();
            objeto[2] = lista.get(i).getCorreo();
            objeto[3] = lista.get(i).getTelefono();
            modelo.addRow(objeto);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnListar) {
            limpiarTabla();
            listar(vista.tabla);
            nuevo();
        }
        if (e.getSource() == vista.btnAgregar) {
            add();
            listar(vista.tabla);
            nuevo();

        }
        if (e.getSource() == vista.btnEditar) {
            int fila = vista.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "seleccione uno primero ");
            } else {
                int id = Integer.parseInt((String) vista.tabla.getValueAt(fila, 0).toString());
                String nom = (String) vista.tabla.getValueAt(fila, 1);
                String Correo = (String) vista.tabla.getValueAt(fila, 2);
                String telefono = (String) vista.tabla.getValueAt(fila, 3);
                vista.txtId.setText("" + id);
                vista.txtNom.setText(nom);
                vista.txtCorreo.setText(Correo);
                vista.txtTel.setText(telefono);

            }
        }
        if (e.getSource() == vista.btnActualizar) {
            actualizar();
            listar(vista.tabla);
            nuevo();
        }
        if (e.getSource() == vista.btnBorrar) {
            delete();
            listar(vista.tabla);
            nuevo();

        }
        if (e.getSource() == vista.btnNuevo) {
            nuevo();
        }
    }

}
