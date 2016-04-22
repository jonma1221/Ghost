package com.google.engedu.ghost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class SimpleDictionary implements GhostDictionary {
    private ArrayList<String> words;
    Random rand = new Random();
    public SimpleDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        words = new ArrayList<>();
        String line = null;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >= MIN_WORD_LENGTH)
              words.add(line.trim());
        }
    }

    @Override
    public boolean isWord(String word) {
        return words.contains(word);
    }

    @Override
    public String getAnyWordStartingWith(String prefix) {
        String validWord = "";
        if(prefix.isEmpty()){
            int i = rand.nextInt(words.size());
            validWord = words.get(i);
        }
        else{
            validWord = binarySearch(prefix);
        }
        return validWord;
    }

    private String binarySearch(String prefix) {
        int low = 0;
        int high = words.size();

        while(low < high){
            int mid = low + high / 2;
            int compare = words.get(mid).compareToIgnoreCase(prefix);
            if(words.get(mid).substring(0,prefix.length()).equals(prefix)){
                return words.get(mid);
            }
            else if(compare < 0){
                low = mid + 1 ;
            }
            else if(compare > 0){
                high = mid - 1 ;
            }
        }
        return null;
    }

    @Override
    public String getGoodWordStartingWith(String prefix) {
        return null;
    }
}
