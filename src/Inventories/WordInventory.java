package Inventories;

import java.util.ArrayList;

public class WordInventory {
    private ArrayList<String> words;

    /** Sets up empty ArrayList */
    public WordInventory() {
        words = new ArrayList<String>();
    }

    public void addWordsToInventory(ArrayList<String> keyWords){
        for (String i : keyWords) {
            words.add(i);
        }
    }

    public ArrayList<String> getAllWords() {
        return words;
    }

    public boolean hasWord(String checkFor) {
        return getWordIndex(checkFor) != -1;
    }

    public int getWordIndex(String checkFor) {
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).equals(checkFor)) return i;
        }
        return -1;
    }

    public void addWord(String toAdd) {
        words.add(toAdd);
    }

    public boolean removeWord(String toRemove) {
        int toRemoveIndex = getWordIndex(toRemove);

        if (toRemoveIndex != -1) {
            words.remove(toRemoveIndex);
            return true;
        }

        return false;
    }

    public void emptyInventory() {
        words = new ArrayList<String>();
    }
}