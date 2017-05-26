package util;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class ColorirEsperados extends DefaultTableCellRenderer {

    private static final long   serialVersionUID    = 1L;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Color c = Color.WHITE;
        Color f = Color.BLACK;
        Object text = table.getValueAt(row, 3);
       
        if("Perdido".equals(text.toString())){
            c = Color.RED;
            f = Color.WHITE;
        }else
            c = Color.WHITE;
        if(isSelected)
            c = Color.LIGHT_GRAY;
        
        label.setBackground(c);
        label.setForeground(f);
        return label;
    }
}