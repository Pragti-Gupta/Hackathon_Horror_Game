package GraphicInterface;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel; 
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent; 

public class ArticlePopup extends JPanel {
    JTextArea articleText;
    JLabel articleHeader;
    JButton closeArticleButton;
    String articleWords, articleTitle;
    
    public ArticlePopup(String aText, String title) {
        setLayout(null);
        setVisible(true);
        articleText = new JTextArea(aText);
        articleHeader = new JLabel(title);
        articleWords = aText;
        articleTitle = title;
        closeArticleButton = new JButton("Finish article");
        setUpLayout();
    }

    public void setUpLayout(){
        add(articleHeader);
        add(articleText);
        add(closeArticleButton);
        articleText.setLineWrap(true);
        articleText.setWrapStyleWord(true);
        articleText.setEditable(false);
        articleHeader.setBounds(20, 20, 400, 30);
        articleText.setBounds(20, 60, 750, 100);
        closeArticleButton.setBounds(20, 180, 150, 30);
        closeArticleButton.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) // goes to menu
                {
                    setVisible(false);
                }
            });
    
    }

    
}