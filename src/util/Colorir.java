/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author gusta
 */
public class Colorir implements TableCellRenderer {

  public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    ((JLabel) renderer).setOpaque(true);
    Color foreground, background;
    
    
    /*if (isSelected) {
      foreground = Color.BLACK;
      background = Color.LIGHT_GRAY;
    }else if (row <= 5) {
        foreground = Color.BLACK;
        Color c = new Color(0,255, 0);
        background = c;
      } else if(row <= 10){
        foreground = Color.white;
        Color c = new Color(255, 250, 0);
        foreground = Color.BLACK;
        background = c;
        
      }else{
          background = Color.white;
          foreground = Color.black;
      }*/
    
    background = Color.RED;
    foreground = Color.BLACK;
    
    renderer.setForeground(foreground);
    renderer.setBackground(background);
    return renderer;
  }
}
