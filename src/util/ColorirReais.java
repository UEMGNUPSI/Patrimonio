package util;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class ColorirReais extends DefaultTableCellRenderer {

    private static final long   serialVersionUID    = 1L;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Color c = Color.GREEN;
        Object text = table.getValueAt(row, 3);
       
        if(isSelected)
            c = Color.LIGHT_GRAY;
        
        label.setBackground(c);
        return label;
    }
}