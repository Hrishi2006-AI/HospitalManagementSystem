package hospital;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataStore {
    private final File dataDirectory;

    public DataStore(String directory) {
        dataDirectory = new File(directory);
        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs();
        }
    }

    public <T extends Serializable> void save(List<T> data, String filename) {
        File file = new File(dataDirectory, filename);
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file))) {
            output.writeObject(new ArrayList<>(data));
        } catch (IOException e) {
            throw new RuntimeException("Could not save " + filename, e);
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends Serializable> List<T> load(String filename) {
        File file = new File(dataDirectory, filename);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
            return (List<T>) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Warning: Could not load " + filename + ". Starting with empty data.");
            return new ArrayList<>();
        }
    }
}
