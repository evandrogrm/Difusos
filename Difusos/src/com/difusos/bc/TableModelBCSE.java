package com.difusos.bc;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelBCSE extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private String[] columnNames = {"Variável","Concentração","Termo","TP Condição"};
	private List<Condicao> data = new ArrayList<>();
	
	public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
    	/**TODO APLICAR A EXPLICAÇÂO NO FORUM GUJ
    	 * http://www.guj.com.br/t/abstracttablemodel-celleditor-jtable-com-combobox-resolvido/234024/2
    	 */
    	switch(col){
    		case 0:
    		  return data.get(row).getComboVariaveis().toString();
    		case 1:
    		  return data.get(row).getConcentracao().toString();
    		case 2:
    		  return data.get(row).getComboTermos();
    		case 3:
    		  return data.get(row).getTP().toString();
    	}
    	JOptionPane.showMessageDialog(null, "DATA NULL");
    	return null;
    }
    
    public boolean isCellEditable(int row, int col) {
        return true;
    }
    
    public void addRow(Condicao c){
    	this.data.add(c);
    	this.fireTableDataChanged();
    }

}
