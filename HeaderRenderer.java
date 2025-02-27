import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class HeaderRenderer extends DefaultTableCellRenderer {
    public final JComboBox<String> month;

    HeaderRenderer(JComboBox<String> month) {
        this.month = month;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return month;
    }
}
