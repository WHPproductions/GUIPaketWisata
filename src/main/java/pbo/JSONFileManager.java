package pbo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONFileManager {
    private static String FILE_PATH = "C:\\Users\\USER\\Desktop\\PBO\\GUIPaketWisata\\GUIPaketWisata\\src\\main\\data base\\";
    private static String FILE_NAME = "users.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        // Enable pretty printing for JSON files
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public static String getFilePath() {
        return FILE_PATH;
    }
    public static String getFileName() {
        return FILE_NAME;
    }
    public static void setFilePath(String filePath) {
        FILE_PATH = filePath;
    }
    public static void setFileName(String fileName) {
        FILE_NAME = fileName;
    }
    public static String getSaveLoadFilePath() {
        return FILE_PATH + FILE_NAME;
    }

    // Generic method to read JSON file and convert to List of objects
    // Parameter: clazz - The class type of the objects in the JSON file
    public static <T> List<T> readFromJSON(Class<T> clazz) throws IOException {
        File file = new File(getSaveLoadFilePath());

        // Create directory if it doesn't exist
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        // Return empty list if file doesn't exist
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try {
            return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            throw new IOException("Error reading JSON file: " + e.getMessage(), e);
        }
    }

    // Generic method to read JSON file with TypeReference (for complex types)
    public static <T> T readFromJSON(TypeReference<T> typeReference) throws IOException {
        File file = new File(getSaveLoadFilePath());

        // Create directory if it doesn't exist
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        // Return null if file doesn't exist
        if (!file.exists()) {
            return null;
        }

        try {
            return mapper.readValue(file, typeReference);
        } catch (IOException e) {
            throw new IOException("Error reading JSON file: " + e.getMessage(), e);
        }
    }

    // Generic method to write List of objects to JSON file
    public static <T> void writeToJSON(List<T> data) throws IOException {
        File file = new File(getSaveLoadFilePath());

        // Create directory if it doesn't exist
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try {
            mapper.writeValue(file, data);
        } catch (IOException e) {
            throw new IOException("Error writing to JSON file: " + e.getMessage(), e);
        }
    }

    // Generic method to write any object to JSON file
    public static <T> void writeObjectToJSON(T data) throws IOException {
        File file = new File(getSaveLoadFilePath());

        // Create directory if it doesn't exist
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try {
            mapper.writeValue(file, data);
        } catch (IOException e) {
            throw new IOException("Error writing to JSON file: " + e.getMessage(), e);
        }
    }

//    // Add single item to existing JSON array
//    public static <T> void appendToJSON(T newItem, Class<T> clazz) throws IOException {
//        List<T> existingData = readFromJSON(clazz);
//        existingData.add(newItem);
//        writeToJSON(existingData);
//    }
//
//    // Update item in JSON array based on condition
//    public static <T> boolean updateInJSON(T updatedItem, Class<T> clazz, ItemMatcher<T> matcher) throws IOException {
//        List<T> data = readFromJSON(clazz);
//        boolean found = false;
//
//        for (int i = 0; i < data.size(); i++) {
//            if (matcher.matches(data.get(i))) {
//                data.set(i, updatedItem);
//                found = true;
//                break;
//            }
//        }
//
//        if (found) {
//            writeToJSON(data);
//        }
//
//        return found;
//    }
//
//    // Delete item from JSON array based on condition
//    public static <T> boolean deleteFromJSON(Class<T> clazz, ItemMatcher<T> matcher) throws IOException {
//        List<T> data = readFromJSON(clazz);
//        boolean removed = data.removeIf(matcher::matches);
//
//        if (removed) {
//            writeToJSON(data);
//        }
//
//        return removed;
//    }
//
//    // Check if file exists
//    public static boolean fileExists() {
//        File file = new File(getSaveLoadFilePath());
//        return file.exists();
//    }
//
//    // Delete the JSON file
//    public static boolean deleteFile() {
//        File file = new File(getSaveLoadFilePath());
//        return file.exists() && file.delete();
//    }
//
//    // Functional interface for matching items
//    @FunctionalInterface
//    public interface ItemMatcher<T> {
//        boolean matches(T item);
//    }
}
