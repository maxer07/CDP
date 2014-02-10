package com.epam.kharkiv.cdp.oleshchuk.util.impl;

import com.epam.kharkiv.cdp.oleshchuk.util.WordSearchable;

import java.util.Collection;

public class CollectionsWordSearcher implements WordSearchable {

    private Collection<String> sourceCollection;


    public CollectionsWordSearcher(Collection<String> sourceCollection) {
        this.sourceCollection = sourceCollection;
    }

    @Override
    public Collection<String> getAllUniqueWords() {
        return null;
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
