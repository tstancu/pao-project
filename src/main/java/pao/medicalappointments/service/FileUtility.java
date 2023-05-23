package pao.medicalappointments.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtility {
    private static final FileUtility instance = new FileUtility();

    private FileUtility() {
        // Private constructor to prevent instantiation from outside the class
    }

    public static FileUtility getInstance() {
        return instance;
    }

    public List<String[]> readCSV(String filePath) {
        List<String[]> data = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void writeCSV(String filePath, List<String[]> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] values : data) {
                String line = String.join(",", values);
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendLineToCSV(String filePath, String[] values) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String line = String.join(",", values);
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
