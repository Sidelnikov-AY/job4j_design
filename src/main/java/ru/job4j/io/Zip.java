package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(String.valueOf(target))))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(String.valueOf(source)));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(String.valueOf(source)))) {
                zip.write(out.readAllBytes());
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(Path source, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(String.valueOf(target))))) {
            zip.putNextEntry(new ZipEntry(String.valueOf(source)));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(String.valueOf(source)))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        final Map<String, String> values = new HashMap<>();
        for (String arg: args) {
            String[] temp = arg.split("=");
            if (temp.length != 2 || temp[0].isEmpty() || temp[1].isEmpty() || !temp[0].startsWith("-")) {
                  throw new IllegalArgumentException("error when setting parameters");
            }
            values.put(temp[0].substring(1), temp[1]);
        }
        File source = new File(String.valueOf(Path.of(values.get("d"))));
        if (source.isDirectory()) {
            List<Path> sources = Search.search(Path.of(values.get("d")),
                    p -> !p.toFile().getName().endsWith(values.get("e")));
            packFiles(sources, Path.of(values.get("o")));
        }
        else {
            System.out.println("source directory does not exist");
        }
    }
}