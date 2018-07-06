package br.com.scheidt.booksorting.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Class with the comparators of Book.
 * 
 * @author Marcelo Scheidt
 */
public class BookComparator {
    
    private static Comparator<Book> titleComparator;
    private static Comparator<Book> authorComparator;
    private static Comparator<Book> editionComparator;
    
    private static Map<String, Comparator<Book>> comparators;
    
    /**
     * Static block used to create the comparators and add them to one map by attribute.
     */
    static {
        comparators = new HashMap<String, Comparator<Book>>();
        
        titleComparator = new Comparator<Book>() {
            
            @Override
            public int compare(Book one, Book two) {
                return one.getTitle().compareTo(two.getTitle());
            }
        };
        
        authorComparator = new Comparator<Book>() {
            
            @Override
            public int compare(Book one, Book two) {
                return one.getAuthor().compareTo(two.getAuthor());
            }
        };
        
        editionComparator = new Comparator<Book>() {
            
            @Override
            public int compare(Book one, Book two) {
                return one.getEdition().compareTo(two.getEdition());
            }
        };
        
        comparators.put("title", titleComparator);
        comparators.put("author", authorComparator);
        comparators.put("edition", editionComparator);
    }

    /**
     * Return one comparator for the given attribute if exists in map.
     * 
     * @param attribute The attribute.
     * 
     * @return The comparator.
     */
    public static Comparator<Book> compareBy(String attribute) {
        return comparators.get(attribute);
    }

    /**
     * Make the comparison using the title comparator.
     * 
     * @param book The book.
     * @param other The other book.
     * 
     * @return one of -1, 0, or 1.
     */
    public static int compare(Book book, Book other) {
        return titleComparator.compare(book, other);
    }
}
