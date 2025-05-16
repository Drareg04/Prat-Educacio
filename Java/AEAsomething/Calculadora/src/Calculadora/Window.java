package Calculadora;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Window extends JFrame{
    public Window(){
        setSize(600,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Calculator");
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(200,200));
        this.getContentPane().setBackground(Color.BLACK);
        component();
    }
    private void component(){
        JPanel panel = new JPanel();

        panel.setLayout(null);
        panel.setBackground(Color.BLACK);
        this.getContentPane().add(panel);

        JLabel etiqueta = new JLabel("I'm a Gnome, and you've been gnooooomed", SwingConstants.CENTER);
        etiqueta.setForeground(Color.BLACK);
        etiqueta.setBounds(10,10,600,50);
        etiqueta.setOpaque(true);
        etiqueta.setBackground(Color.MAGENTA);
        etiqueta.setFont(new Font("chiller",0,40));
        panel.add(etiqueta);


        JLabel etiqueta2 = new JLabel(new ImageIcon("C:/Users/Gerard/Documents/Prat/Prat Educació/Java/AEAsomething/Calculadora/src/img/gnome.jpg"));
        etiqueta2.setBounds(10, 80, 458, 458);
        panel.add(etiqueta2);
    }   
}