package com.epam.kharkiv.cdp.oleshchuk.main;

import com.epam.kharkiv.cdp.oleshchuk.reader.impl.FileWordReaderOnCollections;
import com.epam.kharkiv.cdp.oleshchuk.util.impl.WordSearcherOnCollections;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        String word = args[0];
        if (word == null || word.isEmpty()) {
           throw new RuntimeException("You should write some word");
        }
        ArrayList<String> listOfWords = new ArrayList<>();
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("textFiles/warAndPeace.txt");
        FileWordReaderOnCollections reader = new FileWordReaderOnCollections(listOfWords);
        reader.getWordsFromFile(inputStream);
        WordSearcherOnCollections searcher = new WordSearcherOnCollections(listOfWords);
        int countOfInputWord = searcher.countWord(word);
        int countOfUniqueWord = searcher.getAllUniqueWords();
        System.out.println(String.format("Count of word '%s' - %d", word, countOfInputWord));
        System.out.println(String.format("Count of unique words - %d", countOfUniqueWord));
    }




}
