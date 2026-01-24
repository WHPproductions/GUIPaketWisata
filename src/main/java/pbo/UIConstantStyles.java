package pbo;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UIConstantStyles {
    public static final Color DARK_BLUE = new Color(0, 0, 139);
    public static final Color LIGHT_BLUE = new Color(2, 150, 184);
    public static final Color DARK_RED = new Color(139, 0, 0);
    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color GRAY = new Color(128, 128, 128);
    public static final Color LIGHT_GRAY = new Color(211, 211, 211);

    public static final Font DEFAULT_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font DEFAULT_BOLD_FONT = new Font("Segoe UI", Font.BOLD, 14);

    public static final EmptyBorder MAIN_PANEL_PADDING = new EmptyBorder(20, 20, 20, 20);

    public static final CompoundBorder TEXT_FIELD_BORDER = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIConstantStyles.LIGHT_GRAY, 2),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
    );
}
