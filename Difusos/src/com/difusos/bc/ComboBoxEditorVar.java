package com.difusos.bc;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class ComboBoxEditorVar extends AbstractCellEditor implements TableCellEditor {
	
	private static final long serialVersionUID = 1L;
	private JComboBox<Object> field;
	private String[] values = { "", "" };

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		field = new JComboBox<Object>(values);
		field.setSelectedItem(value); // Deixa o editor pré-selecionado com o valor da célula
		return field;
	}

	@Override
	public Object getCellEditorValue() {
		return field.getSelectedItem().toString();
	}
	
}