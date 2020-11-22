package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import it.unibo.oop.lab.mvcio.Controller;
import it.unibo.oop.lab.mvcio.SimpleGUI;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    
    private final JFrame frame = new JFrame("My first Java graphical interface");

    public static void main(final String[] args) {
        final SimpleGUIWithFileChooser simp = new SimpleGUIWithFileChooser();
        simp.frame.setVisible(true);
    }
    
    public SimpleGUIWithFileChooser() {
        
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new JPanel());
        frame.getContentPane().setLayout(new BorderLayout());
        final JPanel secPan = new JPanel(new BorderLayout());
        final JTextArea txtArea2 = new JTextArea();
        final JButton browse = new JButton("Browse...");
        secPan.add(txtArea2, BorderLayout.CENTER);
        secPan.add(browse, BorderLayout.LINE_END);
        frame.getContentPane().add(secPan, BorderLayout.NORTH);
        final JTextArea txtArea = new JTextArea();
        final JButton save = new JButton("Save");
        frame.getContentPane().add(txtArea);
        frame.getContentPane().add(save, BorderLayout.SOUTH);
        txtArea2.setEditable(false);
        final Controller cont = new Controller();
        txtArea2.setText(cont.getPath());
        
        save.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    cont.writeOnFile(txtArea.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        browse.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser menu = new JFileChooser();
                final int result = menu.showSaveDialog(menu);
                if (result == JFileChooser.APPROVE_OPTION) {
                    cont.setFile(menu.getSelectedFile());
                    txtArea2.setText(cont.getPath());
                } else {
                    if (result != JFileChooser.CANCEL_OPTION) {
                        JOptionPane.showMessageDialog(menu, "An error has occurred...");
                    }
                }
            }
        });
    }
    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */

}
