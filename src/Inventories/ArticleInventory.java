package Inventories;

import Utilities.FileUtils;
import java.util.Scanner;

public class ArticleInventory {
    String article;

    public ArticleInventory() {
        article = "";
    }

    public void readArticle(int articleNum){
        Scanner articleReader = FileUtils.openToRead("src/TextFiles/articles.txt");
        String check = "ARTICLE"+articleNum;
        while(articleReader.hasNextLine()){
            if(articleReader.nextLine().equals(check)){
                String add = articleReader.nextLine();
                while(articleReader.hasNextLine() && add.indexOf("~") < 0){
                    article+=add+"\n";
                    add = articleReader.nextLine();
                }
            }
        }
        System.out.print(article);
    }
    
}