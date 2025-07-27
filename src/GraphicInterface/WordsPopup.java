package GraphicInterface;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel; 
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent; 
import Inventories.WordInventory;
import java.util.ArrayList;

public class WordsPopup extends JPanel{
    JButton cancelButton;
    JButton confirmButton;
    JTextPane wordList;
    JTextField userInput;
    JLabel errorLabel;
    WordInventory wordInventory;
    Chatbox chatbox;
    private boolean waitingForWord = false;
    WordsPopup wp = this;
    
    public WordsPopup(WordInventory wi, Chatbox chbox) {
        setLayout(null);
        setVisible(true);
        cancelButton = new JButton("Cancel");
        confirmButton = new JButton("Confirm");
        wordList = new JTextPane();
        userInput = new JTextField();
        wordInventory = wi;
        chatbox = chbox;
        setupLayout();
    }

    public void setupLayout() {
        add(wordList);
        add(userInput);
        add(cancelButton);
        add(confirmButton);
        wordList.setContentType("text/html");
        //wordList.setText("fjksld kldsjf <b>dklfjdl</b> kdlfjsd dklsjflsdk dlf sdkjfdlkfjsdlkf djflskfjdkf nothing beats a jet 2 holiday and now you can get 50 pounds off PER PERSON!!!");
        wordList.setEditable(false);
        wordList.setBounds(20, 20, 300, 100);
        userInput.setBounds(20, 130, 300, 30);
        cancelButton.setBounds(20, 180, 140, 30);
        confirmButton.setBounds(180, 180, 140, 30);
        cancelButton.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) // goes to menu
                {
                    setVisible(false);
                    waitingForWord = false;
                }
            });
        confirmButton.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) // goes to menu
                {
                    String usedWord = useWordFromPopup();
                    if (usedWord != null) {
                        setVisible(false);
                        chatbox.printResponse(usedWord);
                         waitingForWord = false;
                    }
                    else JOptionPane.showMessageDialog(wp, "Word not found in database. Try again.");
                    
                }
            });
        
    }

    public void updateWords(ArrayList<String> allWords) {
        //JOptionPane.showMessageDialog(this, "Word not found in database.");
        String allWordsString = "";
        for (String i : allWords) {
            allWordsString += i + "\n";
        }
        wordList.setText(allWordsString);
    }

    public String useWordFromPopup() {
        String currentInput = userInput.getText();
        if (wordInventory.hasWord(currentInput)) {
            wordInventory.removeWord(currentInput);
            return currentInput;
        }
        return null;
    }


    public void waitUntilConfirmed() {
    waitingForWord = true;
    while (waitingForWord) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            break;
        }
    }
}

}