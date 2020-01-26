package com.epam.izh.rd.online.service;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpService implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData() {
        try {
            FileReader reader=new FileReader("src/main/resources/sensitive_data.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line=bufferedReader.readLine() ;
            String[] res=line.split(" ");
            String newLine="";
            int count=0;
            for (int i = 0; i < res.length ; i++) {
                if(res[i].matches("\\d{4}")){
                    count++;
                    switch (count){
                        case 2:
                        case 3:
                        case 6:
                        case 7: {
                            res[i]="****";
                            break;
                        }
                        default: break;
                    }

                }
                newLine+=(res[i]+" ");
            }




            return newLine.trim();


        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        return null;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {

        try {
            FileReader reader=new FileReader("src/main/resources/sensitive_data.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line=bufferedReader.readLine() ;
            String[] res=line.split(" ");
            String newLine="";
            int count=0;
            for (int i = 0; i < res.length ; i++) {
                if(res[i].matches("\\$+\\{+.*")){
                    if(count==0){
                        count++;
                        res[(i)] = String.valueOf((int) paymentAmount);
                    }else{
                        res[(i)] = String.valueOf((int) balance);
                    }

                    }

                newLine+=(res[i]+" ");
                }

            return newLine.trim();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
