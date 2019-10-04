package jaxb;


import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileToString {
    public static String fileToString(String path) throws IOException, URISyntaxException {
        return new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(path).toURI())), StandardCharsets.UTF_8).replace("\r", "");
    }

    public static byte[] fileToBytes(String path) throws IOException, URISyntaxException {
        return Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(path).toURI()));
    }
}

