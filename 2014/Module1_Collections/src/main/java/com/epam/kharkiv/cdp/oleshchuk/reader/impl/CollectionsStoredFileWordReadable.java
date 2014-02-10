package com.epam.kharkiv.cdp.oleshchuk.reader.impl;

import com.epam.kharkiv.cdp.oleshchuk.reader.FileWordReadable;

import java.io.*;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CollectionsStoredFileWordReadable implements FileWordReadable {

    private static final String WORD_REGEX = "[\\w]+";
    private Collection<String> sourceCollection;

    public CollectionsStoredFileWordReadable(Collection<String> sourceCollection) {
        this.sourceCollection = sourceCollection;
    }

    @Override
    public Collection<String> getWordsFromFile(InputStream stream) throws IOException {
        if (stream == null ) {
            throw new FileNotFoundException("File is not exists");
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        String line;
        while ( (line = br.readLine()) != null) {
            separateLineIntoWords(line);
        }
        return sourceCollection;
    }

    private void separateLineIntoWords(String line) {
        Pattern pattern = Pattern.compile(WORD_REGEX);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            sourceCollection.add(matcher.group());
        }
    }
}
