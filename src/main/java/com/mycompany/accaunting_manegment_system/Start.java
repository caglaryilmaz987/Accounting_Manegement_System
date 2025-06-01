/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.accaunting_manegment_system;

import Classes.ThemeManager;
import Classes.Hasher;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author cagla
 */
public class Start {

    public static void main(String[] args) {
        // Using the Hasher class for encryption or other operations
        Hasher hash = new Hasher();
        hash.main(args);  // Receiving external command line arguments

        // Getting and applying the saved theme of the application (default could be "light")
        String savedTheme = ThemeManager.getSavedTheme();
        ThemeManager.applyTheme(savedTheme);

        // Starting the main GUI screen on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            new Starting_Screen().setVisible(true);
        });

    }
}
