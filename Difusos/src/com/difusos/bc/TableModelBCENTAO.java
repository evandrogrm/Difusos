package com.difusos.bc;

import javax.swing.table.AbstractTableModel;

public class TableModelBCENTAO extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private String[] columnNames = {"Variável Objetivo","Resultado"};
	private Object[][] data = {{"",""}};
	
	public int getColumnCount() {
        return 2;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }
    
    public boolean isCellEditable(int row, int col) {
        return true;
    }
    
    public void setValueAt(Object value, int row, int col) {
    	data[row][col] = value;
    }

}
