package org.demchuko;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Utils {

    @SneakyThrows
   public  static List<String> getData(String filename) {
        return Files.readAllLines(Path.of(Utils.class.getClassLoader().getResource(filename).toURI()));
    }


}
