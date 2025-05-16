package Calculadora;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class twobuttons extends JFrame{
    public twobuttons(){
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("El mejor titulo");
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
        button.setBounds(110, 200, 100, 40);
        button.setIcon(new ImageIcon(imageButton.getImage().getScaledInstance(100,40, Image.SCALE_SMOOTH)));

        JLabel redbutton = new JLabel();
        ImageIcon imageredButton = new ImageIcon("C:/Users/Gerard/Documents/Prat/Prat Educació/Java/AEAsomething/Calculadora/src/img/redbutton.png");
        redbutton.setBounds(110, 200, 100, 40);
        redbutton.setIcon(new ImageIcon(imageredButton.getImage().getScaledInstance(100,40, Image.SCALE_SMOOTH)));

        JLabel bluebutton = new JLabel();
        ImageIcon imageblueButton = new ImageIcon("C:/Users/Gerard/Documents/Prat/Prat Educació/Java/AEAsomething/Calculadora/src/img/bluebutton.png");
        bluebutton.setBounds(110, 100, 100, 40);
        bluebutton.setIcon(new ImageIcon(imageblueButton.getImage().getScaledInstance(100,40, Image.SCALE_SMOOTH)));
        panel.add(bluebutton);

        panel.add(redbutton);
        panel.add(button);
    }
}