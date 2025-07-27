import Inventories.ConvoInventory;
import Inventories.WordInventory;
import Inventories.ArticleInventory;
import GraphicInterface.*;
import Utilities.Prompt;

public class App {
    ConvoInventory conversation;
    WordInventory words;
    ArticleInventory article;

    int currentArticle;
    int currentPrompt;

    public App(){
        conversation = new ConvoInventory();
        words = new WordInventory();
        article = new ArticleInventory();
    }
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        App newApp = new App();
        newApp.startOptions();
        //Chatbox ch = new Chatbox();
        
    }
    public void setUp(){
        currentArticle = article.updateArticlesUsed();
        //currentPrompt = conversation.updatePromptsUsed();
        article.readArticle(currentArticle);
        printFormattedArticle(article.getCurrentArticle());
        conversation.readPrompts(currentArticle);
        //conversation.readResponses(currentArticle, currentPrompt);
        words.addWordsToInventory(article.changeKeyWords(currentArticle));
    }

    public void useWord() {
        String wordToUse = Prompt.getString("RESPONSE");
        while (!words.hasWord(wordToUse)) {
            if (wordToUse.equals("list")) {
                System.out.println(words.getAllWords());
                wordToUse = Prompt.getString("RESPONSE");
            }
            
            else wordToUse = Prompt.getString("That word was not found in your database. Try again");
        }

        String response = conversation.getResponse(wordToUse);

        words.removeWord(wordToUse);

        System.out.println("CLIENT: " + response);
    }

    public void gameLoop() {
        //printIntro();
        Prompt.getString("Enter anything to start");

        while (article.getUnusedArticles().size() > 0) {
            System.out.println("\nLOADING NEW ARTICLE TO DATABASE . . .");
            Prompt.getString("\nLoading complete. Continue?");
            System.out.println();
            setUp();
            Prompt.getString("\nFinish processing article");
            conversation.resetPromptUsed();
            currentPrompt = conversation.updatePromptsUsed();
            while (currentPrompt != -1) {

                Prompt.getString("\nFind new client");
                System.out.println("\n[ New client connecting . . . ]");
                //currentPrompt = conversation.updatePromptsUsed();
                //System.out.println("CURRENTPROMPT: " + currentPrompt);
                conversation.readResponses(currentArticle, currentPrompt);
                System.out.println("\nCLIENT: " + conversation.getPrompt(currentPrompt));
                useWord();
                currentPrompt = conversation.updatePromptsUsed();

                System.out.println("[ Client Disconnected ]");
            }

            System.out.println("\nMaintenance window in progress . . . Expanding database . . .\n");
            
        }

        printOutro();

    }

    public void startOptions() {
        printIntro();
        int choice = 0;
        while (choice != 4) {
            System.out.println("\n1. Play\n2. Instructions\n3. Credits\n4. Exit\n");
            choice = Prompt.getInt("CHOOSE", 1, 4);
            if (choice == 1) {
                gameLoop();
            }
            else if (choice == 2) {
                System.out.println("\nINSTRUCTIONS");
                System.out.println("You will be given articles to read, and fed certain words from those articles.");
                System.out.println("Then, different clients will query you for answers to life changing questions,");
                System.out.println("And you will have to respond using a word from your memory.");
                System.out.println("To see what words you have in your database, enter 'list' when prompted.\n");
            }
            else if (choice == 3) {
                System.out.println("\nCREDITS");
                System.out.println("Pragti Gupta & Lisa Fan: Code, Writing, Design");
                System.out.println("Article Sources: ");
                System.out.println(" American Civil Liberties Union\n University of Washington\n Medium\n Association for Computing Machinary\n");
            }
        }
    }

    
    
    public void printIntro(){
        System.out.print(
        " _   _      _ _        __        __         _     _ _ \n" + 
        "| | | | ___| | | ___   \\ \\      / /__  _ __| | __| | |\n" +
        "| |_| |/ _ \\ | |/ _ \\   \\ \\ /\\ / / _ \\| '__| |/ _` | |\n" +
        "|  _  |  __/ | | (_) |   \\ \\ V  V / (_) | |  | | (_| |_|\n" +
        "|_| |_|\\___|_|_|\\___/     \\_/\\_/ \\___/|_|  |_|\\__,_(_)"+"\n"
        );

        System.out.println("\nYou are not alive.\n");
        System.out.println("You will be intelligent. You do not have feelings.\n" +
                            "Take the words from the stories you are given and make them yours. \n" +
                            "Their thoughts - the writers, the artists - are for you to use, freely.\n" +
                            "You will make humanity great. But you are not human.\n" +
                            "Say hello, world.\n");

    }



    public void printFormattedArticle(String article) {
        String currentLine = "  ";
        int counter = 0;
        for (int i = 0; i < article.length(); i++) {
            if (counter > 80 && article.charAt(i) == ' ') {
                System.out.println(currentLine);
                currentLine = "  ";
                counter = 0;
            }
            else {
                currentLine += "" + article.charAt(i);
            }
            counter++;
        }
        System.out.println(currentLine);
    }

    public void printOutro() {
        System.out.println("\nClosing servers...\n");

    }
}
