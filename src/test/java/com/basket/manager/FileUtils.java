package com.basket.manager;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Objects;

public class FileUtils {
    public static File getFile(String fileName) {
        ClassLoader classLoader = FileUtils.class.getClassLoader();
        try {
            return new File(Objects.requireNonNull(classLoader.getResource(fileName)).toURI());
        } catch (URISyntaxException e) {
            return null;
        }
    }
}
