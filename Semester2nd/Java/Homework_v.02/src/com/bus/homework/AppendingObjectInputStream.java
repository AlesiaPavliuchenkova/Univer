package com.bus.homework;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * Created by apavliuchenkova on 13/06/2017.
 */
public class AppendingObjectInputStream extends ObjectInputStream {

    public AppendingObjectInputStream(InputStream in) throws IOException {
        super(in);
    }

    protected AppendingObjectInputStream() throws IOException, SecurityException {
    }

    @Override
    protected void readStreamHeader() throws IOException {
        // do not read a header
    }
}
