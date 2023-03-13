package helpers;

import javax.swing.*;


/**
 * Factory that generates label, field, and password components.
 */
public class ComponentFactory {
    /**
     * Generate Label component
     * @param componentLabel Name of the label.
     * @param bounds Bounds of the label.
     * @return JLabel
     */
    public static JLabel generateLabelComponent(String componentLabel, Bounds bounds) {
        JLabel label = new JLabel(componentLabel);
        label.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        return label;
    }

    /**
     * Generate JField component.
     * @param bounds Bounds of the field.
     * @return JField
     */
    public static JTextField generateFieldComponent(Bounds bounds) {
        JTextField text = new JTextField(20);
        text.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        return text;
    }

    /**
     * Generate JPassword Field component.
     * @param bounds Bounds of the field.
     * @return JPasswordField
     */
    public static JPasswordField generatePasswordFieldComponent(Bounds bounds) {
        JPasswordField password = new JPasswordField(20);
        password.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        return password;
    }

}
