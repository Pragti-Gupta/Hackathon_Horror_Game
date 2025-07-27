package Inventories;

import java.util.HashMap;
import java.util.ArrayList;
import Utilities.FileUtils;
import java.util.Scanner;

public class ConvoInventory {
    ArrayList<String> availablePrompts;
    HashMap<String, String> responses;


    public ConvoInventory() {
        availablePrompts = new ArrayList<String>();
        responses = new HashMap<String, String>();
    }


    public void readPrompts(int articleNum) {
        Scanner promptsReader = FileUtils.openToRead("src/TextFiles/prompts.txt");
        String check = "";
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
                            responseString.substring(responseString.indexOf(":") + 1));
        }

        //System.out.println(responses);
    }

    public String getResponse(String input) {
        return "";
    }

    public String getPrompt(int promptNum) {
        return availablePrompts.get(promptNum-1);
    }
    
}