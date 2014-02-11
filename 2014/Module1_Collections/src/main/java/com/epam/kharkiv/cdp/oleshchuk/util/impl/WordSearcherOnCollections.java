package com.epam.kharkiv.cdp.oleshchuk.util.impl;

import com.epam.kharkiv.cdp.oleshchuk.util.WordSearchable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WordSearcherOnCollections implements WordSearchable {

    private Collection<String> sourceCollection;


    public WordSearcherOnCollections(Collection<String> sourceCollection) {
        this.sourceCollection = sourceCollection;
    }

    @Override
    public int getAllUniqueWords() {
        if (sourceCollection instanceof Set) {
            System.out.print("This is set. So, all words are unique in it. ");
            return sourceCollection.size();
        }
        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        for (String word : sourceCollection) {
           Integer count = wordMap.get(word);
           count = (count == null) ? 0 : count;
           wordMap.put(word, ++count);
        }
        int count = 0;
        for (Integer wordFreq :  wordMap.values()) {
            if (wordFreq.equals(1)) ++count;
        }
        return count;
    }

    @Override
    public int countWord(String word) {
        int counter = 0;
        for (String entry : sourceCollection) {
            if (entry.equals(word)) {
                ++counter;
            }
        }
        return counter;
    }
}
