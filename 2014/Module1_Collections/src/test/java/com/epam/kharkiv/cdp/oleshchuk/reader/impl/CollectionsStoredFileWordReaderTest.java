package com.epam.kharkiv.cdp.oleshchuk.reader.impl;

import com.epam.kharkiv.cdp.oleshchuk.util.impl.WordSearcherOnCollections;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class CollectionsStoredFileWordReaderTest {

    public static final String SEPARATOR = "-------------------------------------------------------";

    @Test
    public void testTimeOfArrayList() throws IOException {
        System.out.println(SEPARATOR);
        ArrayList arrayList = (ArrayList) testTimeOfReadFileWithCollection(new ArrayList<String>());
        testTimeOfCountWordsWithCollection(arrayList, "the");
        System.out.println("\n\n\n");
    }

    @Test
    public void testTimeOfLinkedList() throws IOException {
        System.out.println(SEPARATOR);
        LinkedList<String> linkedList = (LinkedList<String>) testTimeOfReadFileWithCollection(new LinkedList<String>());
        testTimeOfCountWordsWithCollection(linkedList, "the");
        System.out.println("\n\n\n");
    }

    @Test
    public void testTimeOfHashSet() throws IOException {
        System.out.println(SEPARATOR);
        HashSet<String> hashSet = (HashSet<String>) testTimeOfReadFileWithCollection(new HashSet<String>());
        testTimeOfCountWordsWithCollection(hashSet, "the");
        System.out.println("\n\n\n");
    }

    @Test
    public void testTimeOfLinkedHashSet() throws IOException {
        System.out.println(SEPARATOR);
        LinkedHashSet<String> linkedHashSet = (LinkedHashSet<String>) testTimeOfReadFileWithCollection(new LinkedHashSet<String>());
        testTimeOfCountWordsWithCollection(linkedHashSet, "the");
        System.out.println("\n\n\n");
    }

    @Test
    public void testTimeOfTreeSet() throws IOException {
        System.out.println(SEPARATOR);
        TreeSet<String> treeSet = (TreeSet<String>) testTimeOfReadFileWithCollection(new TreeSet<String>());
        testTimeOfCountWordsWithCollection(treeSet, "the");
        System.out.println("\n\n\n");
    }


    private Collection<String> testTimeOfReadFileWithCollection(Collection<String> collection) throws IOException {
        FileWordReaderOnCollections fileWordReadable = new FileWordReaderOnCollections(collection);
        InputStream inputStream =
                getClass().getClassLoader().getResourceAsStream("textFiles/warAndPeace.txt");
        long start = System.currentTimeMillis();
        collection = fileWordReadable.getWordsFromFile(inputStream);
        long difference = System.currentTimeMillis() - start;
        System.out.println(String.format("%s read file 'warAdnPeace.txt' finished in %d ms.", collection.getClass().getSimpleName(), difference));
        return collection;
    }

    private void testTimeOfCountWordsWithCollection(Collection<String> collection, String word) {
        WordSearcherOnCollections searcher = new WordSearcherOnCollections(collection);
        long start = System.currentTimeMillis();
        int count = searcher.countWord(word);
        long difference = System.currentTimeMillis() - start;
        System.out.println(String.format("%s found word '%s' %d time(s) in %d ms.", collection.getClass().getSimpleName(),
                word, count, difference));
        start = System.currentTimeMillis();
        int countOfUnique = searcher.getAllUniqueWords();
        difference = System.currentTimeMillis() - start;
        System.out.println(String.format("%s found %d unique words in %d ms.", collection.getClass().getSimpleName(),
                countOfUnique, difference));
    }
}
