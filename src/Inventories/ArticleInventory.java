package Inventories;

import Utilities.FileUtils;
import java.util.Scanner;
import java.util.ArrayList;

public class ArticleInventory {
    String article;
    ArrayList<Integer> articlesNotUsed;
    ArrayList<String> keyWords;
    public ArticleInventory() {
        articlesNotUsed = new ArrayList<Integer>();
        articlesNotUsed.add(1);
        articlesNotUsed.add(2);
        keyWords = new ArrayList<String>();
        article = "";
    }

    public void readArticle(int articleNum){
        article = "";
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

    public ArrayList<String> changeKeyWords(int articleNum){
        if(articleNum == 1){
            keyWords = new ArrayList<String>();
            keyWords.add("softer");
            keyWords.add("caregiver");
            keyWords.add("male");
            keyWords.add("white");
        }

        if(articleNum == 2){
            keyWords = new ArrayList<String>();
            keyWords.add("no");
            keyWords.add("parasocial");
            keyWords.add("socialize");
        }
        return keyWords;
    }

    public int updateArticlesUsed(){
        if(articlesNotUsed.size()>0){
            int choose = (int)(Math.random()*articlesNotUsed.size());
            int articleNum = articlesNotUsed.get(choose);
            articlesNotUsed.remove(choose);
            return articleNum;
        }
        else{
            return -1;
        }
    }

    public ArrayList<Integer> getUnusedArticles() {
        return articlesNotUsed;
    }
    
}