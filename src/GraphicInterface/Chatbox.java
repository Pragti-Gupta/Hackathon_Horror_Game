package GraphicInterface;

import javax.swing.JFrame;

public class Chatbox extends JFrame {
    public Chatbox() {
        setSize(800, 600);
        setResizable(true); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //add(new ArticlePopup("and now you can get 50 pounds off per person!!!!!!!!!!!! that's 200 pounds off for a family of four", "NOTHING BEATS A JET TWO HOLIDAY" 
                  //                                      ));

        add(new WordsPopup());
        setVisible(true);
        
    }
}