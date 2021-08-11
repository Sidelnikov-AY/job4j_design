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
        ArgsName argsName = ArgsName.of(args);
        File source = new File(String.valueOf(Path.of(argsName.get("d"))));
        if (source.isDirectory()) {
            List<Path> sources = Search.search(Path.of(argsName.get("d")),
                    p -> !p.toFile().getName().endsWith(argsName.get("e")));
            packFiles(sources, Path.of(argsName.get("o")));
        }
        else {
            System.out.println("source directory does not exist");
        }
    }
}