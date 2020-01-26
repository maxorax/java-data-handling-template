package com.epam.izh.rd.online.repository;

import javafx.css.PseudoClass;
import org.omg.CORBA.Environment;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sun.deploy.*;
import sun.tools.java.*;
import org.omg.CORBA.*;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */

private long count=0l;
    @Override
    public long countFilesInDirectory(String path) {


       File dir = new File("src/main/resources/"+path);
        if (dir.exists() && dir.isDirectory()) {
            File[] dirList = dir.listFiles();
            if(dirList!=null) {
                for (File file : dirList) {
                    if (!file.isDirectory()&&file.isFile()){
                        count++;
                        }
                    if (file.isDirectory()){
                        countFilesInDirectory(file.getPath().replaceAll("src/main/resources/",""));
                    }
                }
            }
        }

        return count;
    }






    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    private long dirCount=1;
    @Override
    public long countDirsInDirectory(String path) {
        File dir = new File("src/main/resources/"+path);
        if (dir.exists() && dir.isDirectory()) {
            File[] dirList = dir.listFiles();
            if(dirList!=null) {
                for (File file : dirList) {
                    if (file.isDirectory()) {
                        dirCount++;
                        countDirsInDirectory(file.getPath().replaceAll("src/main/resources/", ""));
                    }
                }
            }
        }


        return dirCount;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        File folder = new File(from);
        File[] listOfFiles=folder.listFiles();
        Path destDir=Paths.get(to);
        if (listOfFiles != null)
            for (File file : listOfFiles) {
                try {
                    Files.copy(file.toPath(), destDir.resolve(file.getName()), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        return;
    }

    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     */
    @Override
    public boolean createFile(String path, String name)  {
        File newPath=new File("target/classes/"+path);
        newPath.mkdir();
        try {
            File file=new File(newPath+"/"+name);
               return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        try {
            FileReader reader= new FileReader("src/main/resources/"+fileName);
            BufferedReader bufferedReader=new BufferedReader(reader);

            return bufferedReader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
