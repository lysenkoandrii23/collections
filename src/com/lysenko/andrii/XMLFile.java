package com.lysenko.andrii;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;

public class XMLFile {
    public static void main(String[] args) throws Exception {
        Collection<Book> books = new ArrayList<Book>();
        parseBooks("D:\\my.xml", books);
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public static void parseBooks(String filePath, Collection<Book> books) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(filePath));
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();

        NodeList nodelist = doc.getElementsByTagName("book");
        int nOfBooks = nodelist.getLength();
        String xpathStr;
        for (int i = 1; i <= nOfBooks; i++) {
            xpathStr = "//test/book[" + i + "]/title/text()";
            String title = xpath.evaluate(xpathStr, doc);
            xpathStr = "//test/book[" + i + "]/author/text()";
            String author = xpath.evaluate(xpathStr, doc);
            xpathStr = "//test/book[" + i + "]/publisher/text()";
            String publisherName = xpath.evaluate(xpathStr, doc);
            xpathStr = "//test/book[" + i + "]/publisher/@date";
            String dateText = xpath.evaluate(xpathStr, doc);
            Publisher publisher = new Publisher(publisherName, dateText);
            books.add(new Book(title, author, publisher));
        }
    }
}

class Book {
    private String title;
    private String author;
    private Publisher publisher;

    public Book(String title, String author, Publisher publisher) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    public String toString() {
        return "book " + title + " written by " + author + ", " + publisher;
    }
}

class Publisher {
    private String name;
    private Date date;
    private SimpleDateFormat sdf = new SimpleDateFormat ("dd-MM-yyyy");

    public Publisher(String name, String dateText) throws Exception{
        this.name = name;
        this.date = sdf.parse(dateText);
    }

    public String toString() {
        return "publisher " + name + ", published " + sdf.format(date);
    }
}