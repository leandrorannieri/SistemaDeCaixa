import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class MainGUI {
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            // Crie o catálogo e o caixa
            Map<String, Produto> catalogo = new HashMap<>();
            Caixa caixa = new Caixa();

            SistemaDeCaixaGUI frame = new SistemaDeCaixaGUI(catalogo, caixa);

            setUIFont(new javax.swing.plaf.FontUIResource("Segoe UI", Font.PLAIN, 14));

            frame.getContentPane().setBackground(new Color(245, 245, 245));
            frame.setVisible(true);
        });
    }

    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }
}