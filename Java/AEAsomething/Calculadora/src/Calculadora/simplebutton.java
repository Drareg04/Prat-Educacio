package Calculadora;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class simplebutton extends JFrame{
    public simplebutton(){
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Button Example");
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(500,500));
        setMaximumSize(new Dimension(500,500));
        button();
    }
    private void button(){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);

        JLabel button = new JLabel();
        ImageIcon imageButton = new ImageIcon("C:/Users/Gerard/Documents/Prat/Prat Educació/Java/AEAsomething/Calculadora/src/img/button.png");
        button.setBounds(200, 200, 100, 40);
        button.setIcon(new ImageIcon(imageButton.getImage().getScaledInstance(100,40, Image.SCALE_SMOOTH)));

        JLabel etiqueta = new JLabel("Click", SwingConstants.CENTER);
        etiqueta.setBounds(200, 200, 100, 40);
        
        panel.add(etiqueta);
        panel.add(button);
    }
}