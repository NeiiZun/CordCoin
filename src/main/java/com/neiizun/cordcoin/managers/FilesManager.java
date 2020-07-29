package com.neiizun.cordcoin.managers;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FilesManager {
    private final Map<String, File> files = new HashMap();

    public FilesManager() {
    }

    public File getFile(String fileName) {
        for (Map.Entry<String, File> entry : files.entrySet()) {
            if (entry.getKey().equals(fileName)) {
                return entry.getValue();
            }
        }

        return null;
    }

    public File createFile(String filePath, String fileName) {
        File file = new File(filePath);

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.files.put(fileName, file);
        return file;
    }

    public void saveText(String fileName, String text) {
        try {
            FileWriter fileWriter = new FileWriter(this.files.get(fileName));
            fileWriter.write(text);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getContent(String fileName) {
        File file = this.files.get(fileName);

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
