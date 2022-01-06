package ru.job4j.dream;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class PropertyManufacture {

    public String getDir() throws Exception {
        File file = new File("c:/projects/job4j_dreamjob/src/main/resources/config.properties");
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        return properties.getProperty("directory");
    }
}
