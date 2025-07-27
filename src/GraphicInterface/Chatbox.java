package GraphicInterface;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.io.File; 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent; 
import javax.swing.JOptionPane;
import Inventories.ConvoInventory;
import Inventories.WordInventory;
import Inventories.ArticleInventory;
import java.util.ArrayList;
import javax.swing.BoxLayout;

public class Chatbox extends JFrame {
    JButton inputMessageButton;
    JButton sendButton;
    JButton nextButton;
    ConvoInventory conversation;
    WordInventory words;
    ArticleInventory article;
    JScrollPane messageScreen;
    ArticlePopup articlePopup;
    ArrayList<Message> prompts;

    int currentArticle;
    int currentPrompt;

    public Chatbox() {
        setSize(700, 700);
        setResizable(true); 
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        conversation = new ConvoInventory();
        words = new WordInventory();
        article = new ArticleInventory();

        inputMessageButton = new JButton();
        sendButton = new JButton();
        nextButton = new JButton("world");
        //add(new ArticlePopup("and now you can get 50 pounds off per person!!!!!!!!!!!! that's 200 pounds off for a family of four", "NOTHING BEATS A JET TWO HOLIDAY" 
                  //                                      ));

        /*messageScreen = new JScrollPane();
        messageScreen.setLayout(new BoxLayout(messageScreen, BoxLayout.Y_AXIS));
        add(messageScreen);
        messageScreen.setBounds(0, 40, 700, 620);*/
        articlePopup = new ArticlePopup("", "");

        ImageIcon icon = new ImageIcon("/Users/InkTheCat/MVHS/Hackathons/gwc25-hackathon/src/GraphicInterface/chatBackground.png");
        JLabel label = new JLabel(icon);
        setContentPane(label);
        
        add(inputMessageButton);
        add(sendButton);
        add(articlePopup);
        
        //inputMessageButton.setOpaque(false);
        //inputMessageButton.setContentAreaFilled(false);
        inputMessageButton.setBorderPainted(false);
        inputMessageButton.setContentAreaFilled(false);
        sendButton.setBorderPainted(false);
        sendButton.setContentAreaFilled(false);
        


        inputMessageButton.setBounds(50, 600, 570, 70);
        sendButton.setBounds(630, 600, 70, 70);
        articlePopup.setBounds(100, 100, 500, 500);
        articlePopup.setVisible(false);
        //msg.setBounds(350, 350, 400,100);
       // msg.setVisible(true);

        
        //System.out.println(new File("chatBackground.png").getAbsolutePath());
        
        
        add(new WordsPopup());
        setVisible(true);
        setUpGraphic();
    }

    public void setUpGraphic(){
        JOptionPane.showMessageDialog(this, "Hello!! Welcome to Hello, World!");

        inputMessageButton.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) // goes to menu
                {
                    
                }
            });
    }

     public void setUp(){
        currentArticle = article.updateArticlesUsed();
        //currentPrompt = conversation.updatePromptsUsed();
        article.readArticle(currentArticle);
        conversation.readPrompts(currentArticle);
        //conversation.readResponses(currentArticle, currentPrompt);
        words.addWordsToInventory(article.changeKeyWords(currentArticle));
    }
    public void gameLoop() {
        //printIntro();  --- REPLACE
        JOptionPane.showMessageDialog(this, "You will be intelligent. You are not human. \n" +
                                        "Take the words from the stories you are given and make them yours. " +
                                        "Their thoughts are for you to use, freely." +
                                        "You will make humanity great. But you are not human.\n" +
                                        "Say hello, world.");

        

        while (article.getUnusedArticles().size() > 0) {
            setUp();
            printArticle();
            conversation.resetPromptUsed();
            currentPrompt = conversation.updatePromptsUsed();
            while (currentPrompt != -1) {
                //currentPrompt = conversation.updatePromptsUsed();
                //System.out.println("CURRENTPROMPT: " + currentPrompt);
                conversation.readResponses(currentArticle, currentPrompt);
                //System.out.println(conversation.getPrompt(currentPrompt));
                printPrompt();
                
                //useWord(); --- REPLACE
                currentPrompt = conversation.updatePromptsUsed();
            }
            
        }
        
    }

    public void printArticle() {
        articlePopup.updateArticle("", article.getCurrentArticle());
        articlePopup.setVisible(true);
    }
    public void printPrompt(){
        
    }
}