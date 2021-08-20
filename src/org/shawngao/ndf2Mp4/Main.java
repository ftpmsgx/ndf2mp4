package org.shawngao.ndf2Mp4;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * @author ShawnGao
 * @date 2021-8-20
 * @deprecated 'yo yo Lu mi' Desktop NDF file to MP4 file.(Max file size is 2GB)
 */

public class Main {

    /**
     * Display the maximum number of lines
     */
    private static final int MAX = 8;

    /**
     * ndf convert to mp4 file
     * @param fileData  file data array
     * @param fileName  file path and file name string
     */
    private static void ndf2Mp4(byte[] fileData, String fileName) {
        byte[] finalDataArray = new byte[fileData.length - 2];
        System.arraycopy(fileData, 2, finalDataArray, 0, fileData.length - 2);
        try {
            Files.write(Paths.get(fileName + ".mp4"), finalDataArray);
            System.out.println("Conversion complete !");
            System.out.println("File path: " + fileName + ".mp4");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * open this ndf file and display the first eight lines of the hexadecimal data of the file
     * @param fileName  file path and file name string
     */
    private static void openFileWithHex(String fileName) {
        try {
            Path path = Paths.get(fileName);
            byte[] fileData = Files.readAllBytes(path);
            int rowLength = 0;
            int row = 0;
            System.out.println("This file size is " + new File(fileName).length() / 1024 / 1024 + "MB");
            for (byte data: fileData) {
                if (row != MAX) {
                    System.out.printf("%02X ", data);
                    rowLength ++;
                    if (rowLength == 16) {
                        System.out.println();
                        rowLength = 0;
                        row ++;
                    }
                }
            }
            System.out.println("Here only output the first 8 lines of hexadecimal data of the file......");
            System.out.println("Start converting this file......");
            ndf2Mp4(fileData, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * main method
     * @param args  There is nothing to explain
     */
    public static void main(String[] args) {
        String fileName;
        Scanner sc = new Scanner(System.in);
        System.out.print("Please input NDF file name and path(E.g: D:/directory/xxx.ndf)(Max file size is 2GB):");
        fileName = sc.nextLine();
        System.out.println("Opening this file...");
        openFileWithHex(fileName);
    }
}
