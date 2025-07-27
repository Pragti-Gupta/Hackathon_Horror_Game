package GraphicInterface;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
import javax.swing.Box;
import java.awt.Color;

public class Chatbox extends JFrame {
    JButton inputMessageButton;
    JButton sendButton;
    JButton nextButton;
    ConvoInventory conversation;
    WordInventory words;
    ArticleInventory article;
    JScrollPane scrollingScreen;
    ArticlePopup articlePopup;
    ArrayList<Message> prompts;
    JPanel messageScreen;
    WordsPopup wordsPopup;
   

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
        prompts = new ArrayList<Message>();
        articlePopup = new ArticlePopup("", "");

        ImageIcon icon = new ImageIcon("/Users/InkTheCat/MVHS/Hackathons/gwc25-hackathon/src/GraphicInterface/chatBackground.png");
        JLabel label = new JLabel(icon);
        setContentPane(label);
        
        add(inputMessageButton);
        add(sendButton);
        
        
        //inputMessageButton.setOpaque(false);
        //inputMessageButton.setContentAreaFilled(false);
        inputMessageButton.setBorderPainted(false);
        inputMessageButton.setContentAreaFilled(false);
        sendButton.setBorderPainted(false);
        sendButton.setContentAreaFilled(false);
        


        inputMessageButton.setBounds(50, 600, 570, 70);
        sendButton.setBounds(630, 600, 70, 70);
        
        //msg.setBounds(350, 350, 400,100);
       // msg.setVisible(true);

        
        //System.out.println(new File("chatBackground.png").getAbsolutePath());
        messageScreen = new JPanel();
        messageScreen.setLayout(new BoxLayout(messageScreen, BoxLayout.Y_AXIS));
        //messageScreen.setBackground(Color.BLUE);

       // JLabel testLabel = new JLabel("Test Message");
       // testLabel.setOpaque(true);
        //testLabel.setBackground(Color.YELLOW);
       // messageScreen.add(testLabel);

        scrollingScreen = new JScrollPane(messageScreen);
        scrollingScreen.setBounds(0, 40, 700, 560);
        //scrollingScreen.setBackground(Color.PINK);

        messageScreen.revalidate();
        messageScreen.repaint();

        add(scrollingScreen);
        scrollingScreen.setVisible(true);
        wordsPopup = new WordsPopup(words, this);
        add(wordsPopup);
        wordsPopup.setVisible(false);
        setVisible(true);

        add(articlePopup);
        articlePopup.setBounds(100, 70, 500, 500);
        //articlePopup.setVisible(false);
        //Message msg = new Message("Hey there hi hello hola");
       // prompts.add(msg);
       // messageScreen.add(msg);  // add at bottom
       
        //printPrompt("Hey there bestieeee");
        //printResponse("its a me a mario oooohhha hhhh");

        gameLoop();

        //setUp();
        //printArticle();
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
                                        "Take the words from the stories you are given and make them yours. \n" +
                                        "Their thoughts are for you to use, freely.\n" +
                                        "You will make humanity great. But you are not human.\n" +
                                        "Say hello, world.");

        

        while (article.getUnusedArticles().size() > 0) {
            setUp();
            System.out.println("right before print article: " + article.getCurrentArticle() + "is article value");
            printArticle();
            articlePopup.waitUntilClosed();
            conversation.resetPromptUsed();
            currentPrompt = conversation.updatePromptsUsed();
            while (currentPrompt != -1) {
                //currentPrompt = conversation.updatePromptsUsed();
                //System.out.println("CURRENTPROMPT: " + currentPrompt);
                conversation.readResponses(currentArticle, currentPrompt);
                //System.out.println(conversation.getPrompt(currentPrompt));
                printPrompt(conversation.getPrompt(currentPrompt));
                getUserWord();
                //words.removeWord(wordToUse);
                //printResponse(wordToUse);
                //printPrompt(conversation.getResponse(wordToUse));

                //useWord(); --- REPLACE
                currentPrompt = conversation.updatePromptsUsed();
            }
            
        }
        
    }


    public void getUserWord(){
        wordsPopup.updateWords(words.getAllWords());
        wordsPopup.setVisible(true);
        wordsPopup.waitUntilConfirmed();
    }
    
    public void printArticle() {
        articlePopup.revalidate();
        articlePopup.repaint();
        System.out.println("PRINTING ARTICLE");
        System.out.println("current article should look like this: " + article.getCurrentArticle());
        articlePopup.updateArticle("", article.getCurrentArticle());
        setComponentZOrder(articlePopup, 0);
        articlePopup.setVisible(true);
        articlePopup.repaint();
        articlePopup.revalidate();
        articlePopup.requestFocusInWindow();
        articlePopup.setEnabled(true);

    }
    public void printPrompt(String prompt){
        prompts.add(new Message(prompt));
        messageScreen.add(prompts.get(prompts.size() - 1));
        messageScreen.revalidate();
        messageScreen.repaint();
    }

    public void printResponse(String response){
        Message msg = new Message(response);

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));
        wrapper.setOpaque(false);
        wrapper.add(Box.createHorizontalGlue());
        wrapper.add(Box.createHorizontalStrut(350)); 
        wrapper.add(msg);

        messageScreen.add(wrapper);
        messageScreen.revalidate();
        messageScreen.repaint();
        printPrompt(conversation.getResponse(response));
    }

    public WordInventory getWordInventory() {
        return words;
    }
}