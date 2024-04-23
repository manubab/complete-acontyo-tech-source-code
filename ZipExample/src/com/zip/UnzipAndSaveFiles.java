package com.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipAndSaveFiles {

    public static void main(String[] args) {
        String zipFilePath = "C:\\Users\\talar\\Downloads\\foldermanu\\test.zip";
        String destFolder = "C:\\Users\\talar\\Downloads\\manuunzipfolder";

        try {
            unzipAndSave(zipFilePath, destFolder);
            System.out.println("Files successfully unzipped and saved to destination folder.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void unzipAndSave(String zipFilePath, String destFolder) throws IOException {
        byte[] buffer = new byte[1024];
        File folder = new File(destFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                String fileName = zipEntry.getName();
                File newFile = new File(destFolder + File.separator + fileName);

                if (zipEntry.isDirectory()) {
                    newFile.mkdirs();
                } else {
                    File parent = newFile.getParentFile();
                    if (!parent.exists()) {
                        parent.mkdirs();
                    }
                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
            }
        }
    }
}
