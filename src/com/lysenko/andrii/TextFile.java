package com.lysenko.andrii;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TextFile {
    public static Set<String> fileDistinctWords(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line = null;
        BufferedReader in = new BufferedReader(
                new FileReader(filePath));
        try {
            while ((line = in.readLine()) != null) {
                sb.append(line + " ");
            }
        } finally {
            in.close();
        }
        String[] arr = sb.toString().split("[ .,:;]");
        Set<String> set = new HashSet<String>();
        for (String s : arr) {
            if (!s.equals("")) {
                set.add(s.toLowerCase());
            }
        }
        return set;
    }

    public static void main(String[] args) throws IOException {
        Set<String> set = fileDistinctWords("D:\\file.txt");
        for (String s : set) {
            System.out.println(s);
        }
    }
}
