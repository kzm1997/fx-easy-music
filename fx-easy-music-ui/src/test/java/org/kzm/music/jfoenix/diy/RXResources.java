package org.kzm.music.jfoenix.diy;

import java.net.URL;

public class RXResources {

    public static URL load(String path) {
        return RXResources.class.getResource(path);
    }

    private RXResources() {
    }
}
