package com.zip;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FolderToZipConverter {

    public static void main(String[] args) {
        String sourceFolderPath = "C:\\Users\\talar\\Downloads\\test";
        String zipFilePath = "C:\\Users\\talar\\Downloads\\foldermanu\\test.zip";

        try {
            zipFolder(sourceFolderPath, zipFilePath);
            System.out.println("Folder successfully compressed to zip file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zipFolder(String sourceFolderPath, String zipFilePath) throws IOException {
        FileOutputStream fos = new FileOutputStream(zipFilePath);
        ZipOutputStream zos = new ZipOutputStream(fos);

        zipFolder(sourceFolderPath, sourceFolderPath, zos);

        zos.close();
        fos.close();
    }

    private static void zipFolder(String sourceFolderPath, String folderToZip, ZipOutputStream zos) throws IOException {
        File folder = new File(folderToZip);
        for (String fileName : folder.list()) {
            if (folderToZip.equals(sourceFolderPath)) {
                addFileToZip(folderToZip + File.separator + fileName, fileName, zos);
            } else {
                addFileToZip(folderToZip + File.separator + fileName, folderToZip.substring(sourceFolderPath.length() + 1) + File.separator + fileName, zos);
            }
        }
    }

    private static void addFileToZip(String filePath, String entryName, ZipOutputStream zos) throws IOException {
        File file = new File(filePath);
        if (file.isDirectory()) {
            zipFolder(filePath, entryName, zos);
        } else {
            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(filePath);
            zos.putNextEntry(new ZipEntry(entryName));
            int length;
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }
            zos.closeEntry();
            fis.close();
        }
    }
}
