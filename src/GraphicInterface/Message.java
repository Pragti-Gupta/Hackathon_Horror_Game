package GraphicInterface;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel; 
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Dimension;

public class Message extends JPanel{
    String message;
    JTextArea area;
    JLabel background;
    int maxLen = 400;
    //int width, height;
    public Message(String text) {
        setLayout(null);
        setVisible(true);

        message = text;
        area = new JTextArea(message);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);
        area.setOpaque(false);

        int width = 300;
        FontMetrics fm = area.getFontMetrics(area.getFont());
        int lineHeight = fm.getHeight();
        int estimatedLines = (int) Math.ceil((double) message.length() * 7 / width); 
        int height = estimatedLines * lineHeight + 20;

        area.setBounds(30, 20, width, height);

        ImageIcon icon = new ImageIcon("/Users/InkTheCat/MVHS/Hackathons/gwc25-hackathon/src/GraphicInterface/textBox.png");
        Image scaled = icon.getImage().getScaledInstance(width + 40, height + 40, Image.SCALE_SMOOTH);
        background = new JLabel(new ImageIcon(scaled));
        background.setBounds(0, 0, width + 40, height + 40);
        background.setLayout(null);

        background.add(area);
        add(background);
        setOpaque(false);
        setPreferredSize(new Dimension(width + 40, height + 40));
    }
    /*public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }*/

}