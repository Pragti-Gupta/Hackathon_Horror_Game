package GraphicInterface;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel; 
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent; 

public class ArticlePopup extends JPanel {
    JTextPane articleText;
    JLabel articleHeader;
    JButton closeArticleButton;
    String articleWords, articleTitle;
    
    public ArticlePopup(String aText, String title) {
        setLayout(null);
        setBackground(new Color(100, 100, 100));
        //setVisible(true);
        articleText = new JTextPane(); articleText.setText(aText);
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
        
        //articleText.setLineWrap(true);
        //articleText.setWrapStyleWord(true);
        articleText.setEditable(false);
        articleHeader.setBounds(20, 20, 500, 30);
        articleText.setBounds(20, 20, 460, 420);
        closeArticleButton.setBounds(20, 460, 150, 30);
        closeArticleButton.setFocusable(true);
        closeArticleButton.setEnabled(true);
        closeArticleButton.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) // goes to menu
                {
                    setVisible(false);
                }
            });
        
        closeArticleButton.setVisible(true);

    }

    public void waitUntilClosed() {
        while (isVisible()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
}

    public void updateArticle(String newTitle, String newArticle) {
        articleText.setText(newArticle);
    }

    
}