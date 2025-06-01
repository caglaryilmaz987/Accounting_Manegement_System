/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.util.prefs.Preferences;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author cagla
 */
public class ThemeManager {
    // Key used to store the theme preference

    private static final String THEME_KEY = "theme";
// Using Preferences to store user-specific settings
    private static final Preferences prefs = Preferences.userRoot().node("MyAppTheme");

    public static void applyTheme(String theme) {
        try {
            // If the theme is dark, apply FlatDarkLaf
            if ("dark".equalsIgnoreCase(theme)) {
                UIManager.setLookAndFeel(new FlatDarkLaf());
                // Update the saved theme
                prefs.put(THEME_KEY, "dark");
            } else {
                // Otherwise, apply FlatLightLaf
                UIManager.setLookAndFeel(new FlatLightLaf());
                // Update the saved theme
                prefs.put(THEME_KEY, "light");
            }

            // Apply the updated look and feel to all open windows using foreach
            for (java.awt.Window window : java.awt.Window.getWindows()) {
                SwingUtilities.updateComponentTreeUI(window); // update UI
                window.pack(); // resize the window
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getSavedTheme() {
        return prefs.get(THEME_KEY, "light"); // default is light
    }

}
