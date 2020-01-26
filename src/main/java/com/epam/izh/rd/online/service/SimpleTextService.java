package com.epam.izh.rd.online.service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.regex.Pattern;

public class SimpleTextService implements TextService {

    /**
     * Реализовать функционал удаления строки из другой строки.
     *
     * Например для базовой строки "Hello, hello, hello, how low?" и строки для удаления ", he"
     * метод вернет "Hellollollo, how low?"
     *
     * @param base - базовая строка с текстом
     * @param remove - строка которую необходимо удалить
     */
    @Override
    public String removeString(String base, String remove) {
        if (base.length() != 0) {
            return base.replaceAll(remove, "");//TODO
        }else return base;
    }

    /**
     * Реализовать функционал проверки на то, что строка заканчивается знаком вопроса.
     *
     * Например для строки "Hello, hello, hello, how low?" метод вернет true
     * Например для строки "Hello, hello, hello!" метод вернет false
     */
    @Override
    public boolean isQuestionString(String text) {
        return text.endsWith("?"); //TODO
    }

    /**
     * Реализовать функционал соединения переданных строк.
     *
     * Например для параметров {"Smells", " ", "Like", " ", "Teen", " ", "Spirit"}
     * метод вернет "Smells Like Teen Spirit"
     */
    @Override
    public String concatenate(String... elements) {
        String text="";
        for (String element: elements) {
            text+=element;
        }
        return text.replaceAll(", ",""); //TODO
    }

    /**
     * Реализовать функционал изменения регистра в вид лесенки.
     * Возвращаемый текст должен начинаться с прописного регистра.
     *
     * Например для строки "Load Up On Guns And Bring Your Friends"
     * метод вернет "lOaD Up oN GuNs aNd bRiNg yOuR FrIeNdS".
     */
    @Override
    public String toJumpCase(String text) {

        String newText="";
        if(text.length()!=0) {
            if (Character.isUpperCase(text.charAt(0))) {
                newText += text.substring(0, 1).toLowerCase();

            } else {
                newText += text.substring(0, 1).toUpperCase();
            }

            for (int i = 1; i < text.length(); i++) {
                if (newText.charAt(i - 1) == ' ' && Character.isUpperCase(newText.charAt((i - 2)))) {
                    newText += text.substring(i, (i + 1)).toUpperCase();
                } else if (newText.charAt(i - 1) == ' ' && Character.isLowerCase(newText.charAt((i - 2)))) {
                    newText += text.substring(i, (i + 1)).toLowerCase();
                } else if (Character.isUpperCase(newText.charAt((i - 1)))) {
                    newText += text.substring(i, (i + 1)).toLowerCase();

                } else {
                    newText += text.substring(i, (i + 1)).toUpperCase();
                }
            }
            return newText; //TODO
        }
        return newText;
    }

    /**
     * Метод определяет, является ли строка палиндромом.
     *
     * Палиндром - строка, которая одинаково читается слева направо и справа налево.
     *
     * Например для строки "а роза упала на лапу Азора" вернется true, а для "я не палиндром" false
     */
    @Override
    public boolean isPalindrome(String string) {
        if(string.length()!=0){
        String newText=string.replaceAll(" ","");
        StringBuffer text=new StringBuffer(newText).reverse();
        String nText=text.toString();
        for (int i=0;i<newText.length();i++) {
            if (Character.toLowerCase(newText.charAt(i)) !=Character.toLowerCase(text.charAt(i))) {
                return false;// TODO
            }
        }
        return true;
        }
        return false;
    }
}
