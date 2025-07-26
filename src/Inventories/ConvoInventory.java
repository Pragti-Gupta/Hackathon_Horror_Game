package Inventories;

import java.util.HashMap;
import java.util.ArrayList;

class ConvoInventory {
    ArrayList<String> availablePrompts;
    HashMap<String, String> responses;

    public ConvoInventory() {
        availablePrompts = new ArrayList<String>();
        responses = new HashMap<String, String>();
    }

    public void readPrompts(int articleNum) {

    }

    public void readResponses(int articleNum, int promptNum) {

    }

    public String getResponse(String input) {
        return "";
    }

    public String getPrompt(int promptNum) {
        return availablePrompts.get(promptNum);
    }
}