package Inventories;

import java.util.HashMap;
import java.util.ArrayList;
import Utilities.FileUtils;
import java.util.Scanner;

public class ConvoInventory {
    ArrayList<String> availablePrompts;
    ArrayList<Integer> promptsNotUsed;
    HashMap<String, String> responses;


    public ConvoInventory() {
        availablePrompts = new ArrayList<String>();
        responses = new HashMap<String, String>();
        promptsNotUsed = new ArrayList<Integer>();
    }


    public void readPrompts(int articleNum) {
        Scanner promptsReader = FileUtils.openToRead("src/TextFiles/prompts.txt");
        String check = "";
        availablePrompts = new ArrayList<String>();
        check = "PROMPTS" + articleNum;
        while(promptsReader.hasNextLine()){
            if(promptsReader.nextLine().equals(check)){
                String responseOne = promptsReader.nextLine();
                String responseTwo = promptsReader.nextLine();
                availablePrompts.add(responseOne.substring(responseOne.indexOf(" ")+1));
                availablePrompts.add(responseTwo.substring(responseTwo.indexOf(" ")+1));
            }
        }
    }

    public void readResponses(int articleNum, int promptNum) {
        Scanner responseReader = FileUtils.openToRead("src/TextFiles/responses.txt");
        responses = new HashMap<String, String>();

        // go to specific article
        String articleHeader = "RESPONSES" + articleNum;
        String promptHeader = "" + promptNum;

        String responseString = "";
        // go to ARTICLE
        while (responseReader.hasNextLine() && !responseString.equals(articleHeader)) {
            responseString = responseReader.nextLine();
        }
        // go to PROMPT
        while (responseReader.hasNextLine() && !responseString.equals(promptHeader)) {
            responseString = responseReader.nextLine();
        }
        // read out the stuff
        while (responseReader.hasNextLine()) {
            responseString = responseReader.nextLine();
            if (responseString.indexOf("~") != -1) break;
            responses.put(responseString.substring(0, responseString.indexOf(":")),
                            responseString.substring(responseString.indexOf(":") + 2));
        }

        //System.out.println(responses);
    }

    public String getResponse(String input) {
        return responses.get(input);
    }

    public String getPrompt(int promptNum) {
        return availablePrompts.get(promptNum-1);
    }
    public void resetPromptUsed(){
        promptsNotUsed = new ArrayList<Integer>();
        promptsNotUsed.add(1);
        promptsNotUsed.add(2);
    }
    public int updatePromptsUsed(){
        if(promptsNotUsed.size()>0){
            int choose = (int)(Math.random()*promptsNotUsed.size());
            int promptNum = promptsNotUsed.get(choose);
            promptsNotUsed.remove(choose);
            return promptNum;
        }
        else{
            return -1;
        }
    }
    
}