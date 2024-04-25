package burp.ui.renderer;

import burp.util.UiUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * @author： shaun
 * @create： 2024/3/27 21:30
 * @description：TODO
 */
public class HavingImportantRenderer extends DefaultTableCellRenderer {

    public HavingImportantRenderer() {
        setHorizontalAlignment(CENTER); // 设置居中
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // 设置文本为空，因为我们只显示图标
        setText("");

        // 根据单元格值设置相应图标
        if (value instanceof Boolean) {
            if ((Boolean) value) {
                setIcon(UiUtils.getImageIcon("/icon/importantButtonIcon.png", 15, 15));
            } else {
                setIcon(UiUtils.getImageIcon("/icon/normalIcon.png", 15, 15));
            }
        } else {
            setIcon(null); // 如果值不是布尔类型，则不显示图标
        }

        return this;
    }
}
