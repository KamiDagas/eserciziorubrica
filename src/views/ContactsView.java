package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import entity.Persona;

public class ContactsView extends JFrame {

	private static final long serialVersionUID = 1L;
	private Controller controller;
	private JTable table;
	private JScrollPane scrollPane;
	private Persona[] persone;
	private DefaultTableModel model;
	

	public ContactsView() {
		setSize(new Dimension(400, 400));
		setResizable(false);
		getContentPane().setLayout(null);
		setTitle("I tuoi contatti");
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 146, 39);
		toolBar.setFloatable(false);
		getContentPane().add(toolBar);
		
 		JButton addButton = new JButton();
 		addButton.setIcon(new ImageIcon(new ImageIcon(ContactsView.class.getResource("/resources/icons/add.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
		addButton.setMargin(new Insets(0,10,0,10));
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.createButtonPressed();
			}
			
		});
 		toolBar.add(addButton);
		
		JButton editButton = new JButton();
		editButton.setIcon(new ImageIcon(new ImageIcon(ContactsView.class.getResource("/resources/icons/pencil.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
		editButton.setMargin(new Insets(0,0,0,10));
		editButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() == -1)
					JOptionPane.showMessageDialog(null,"Seleziona prima un contatto","Errore",JOptionPane.ERROR_MESSAGE);
				else {
					Persona p = persone[table.getSelectedRow()];
					controller.editButtonPressed(p);
				}
			}

		});
		toolBar.add(editButton);
		
		JButton deleteButton = new JButton();
		deleteButton.setIcon(new ImageIcon(new ImageIcon(ContactsView.class.getResource("/resources/icons/bin.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
		deleteButton.setMargin(new Insets(0,0,0,10));
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() == -1)
					JOptionPane.showMessageDialog(null,"Seleziona prima un contatto","Errore",JOptionPane.ERROR_MESSAGE);
				else {
					Persona p = persone[table.getSelectedRow()];
					if(JOptionPane.showConfirmDialog(null,"Eliminare la persona "+ p.getNome() +" "+ p.getCognome() +"?","Errore",JOptionPane.YES_NO_OPTION) == 0) {
						if (controller.confirmDeleteContactButtonPressed(p.getId())) {
							Persona[] tempPersone = new Persona[persone.length-1];
							int tempIndex = 0;
							for(int i = 0; i < persone.length; i++) {
								if(persone[i].getId() != p.getId())
									tempPersone[tempIndex++] = persone[i];
							}
							persone = tempPersone;
							
							updateContactsTable();
						}
							
					}
				}
			}

		});
		toolBar.add(deleteButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 50, 300, 300);
		getContentPane().add(scrollPane);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}
	
	public ContactsView(Controller controller, Persona[] persone) {
		this();
		this.controller = controller;
		this.persone = persone;
		table = new JTable();
		table.setBackground(Color.white);
		scrollPane.setViewportView(table);
		model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
		
		updateContactsTable();
	}
	
	
	private void updateContactsTable() {
		String[][] tableData = new String[persone.length][3];
		
		for(int i = 0; i < persone.length; i++) {
			tableData[i][0] = persone[i].getNome();
			tableData[i][1] = persone[i].getCognome();
			tableData[i][2] = persone[i].getTelefono();
		}
		
		model.setDataVector(tableData, new String[] {"Nome","Cognome","Indirizzo"});
		model.fireTableDataChanged();
		
	}
}
