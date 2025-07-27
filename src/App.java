import Inventories.ConvoInventory;
import Inventories.WordInventory;
import Inventories.ArticleInventory;
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
        newApp.gameLoop();
    }
    public void setUp(){
        currentArticle = article.updateArticlesUsed();
        //currentPrompt = conversation.updatePromptsUsed();
        article.readArticle(currentArticle);
        conversation.readPrompts(currentArticle);
        //conversation.readResponses(currentArticle, currentPrompt);
        words.addWordsToInventory(article.changeKeyWords(currentArticle));
    }

    public void useWord() {
        String wordToUse = Prompt.getString("What words will you process?");
        while (!words.hasWord(wordToUse)) {
            if (wordToUse.equals("list")) {
                System.out.println(words.getAllWords());
                wordToUse = Prompt.getString("What words will you process?");
            }
            
            else wordToUse = Prompt.getString("That word was not found in your database. Try again");
        }

        String response = conversation.getResponse(wordToUse);

        words.removeWord(wordToUse);

        System.out.println(response);
    }

    public void gameLoop() {
        printIntro();
        while (article.getUnusedArticles().size() > 0) {
            setUp();
            conversation.resetPromptUsed();
            currentPrompt = conversation.updatePromptsUsed();
            while (currentPrompt != -1) {
                //currentPrompt = conversation.updatePromptsUsed();
                System.out.println("CURRENTPROMPT: " + currentPrompt);
                conversation.readResponses(currentArticle, currentPrompt);
                System.out.println(conversation.getPrompt(currentPrompt));
                useWord();
                currentPrompt = conversation.updatePromptsUsed();
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
    }
}
