package com.epam.kharkiv.cdp.oleshchuk.reader;

import java.io.IOException;
import java.io.InputStream;

public interface FileWordReadable {

    Object getWordsFromFile(InputStream stream) throws IOException;

}
