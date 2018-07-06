package br.com.scheidt.booksorting.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import br.com.scheidt.booksorting.configuration.Configuration;
import br.com.scheidt.booksorting.exception.SortingServiceException;
import br.com.scheidt.booksorting.model.Book;
import br.com.scheidt.booksorting.model.BookComparator;

/**
 * Sorting service class used to sort a set of books with the given sorting parameters.
 * 
 * @author Marcelo Scheidt
 */
public class BookSortingService {
    
    private Properties properties;

    /**
     * Sort the given set of books with the current sorting properties. <br>
     * If none property is found, the service will sort using the default class comparator.
     * 
     * @param books The set of books to sort.
     * 
     * @return The sorted set by the given sorting properties.
     * 
     * @throws SortingServiceException if given set is null. 
     */
    public List<Book> sort(List<Book> books) throws SortingServiceException {
        
        if(books == null) {
            throw new SortingServiceException("The given set is null");
        
        } else if(books.isEmpty()) {
            return books;
        
        } else {
            if(this.properties == null) {
                this.properties = Configuration.getProperties();
            }
            
            MultiComparator<Book> chain = this.getSortParameters();

            if(chain.size() > 0) {
                 Collections.sort(books, chain);
            
            } else {
                Collections.sort(books);
            }
            
            return books;
        }
    }

    /**
     * Return the comparator chain according to the sorting properties.
     * 
     * @return The comparator chain according to the sorting properties.
     */
    private MultiComparator<Book> getSortParameters() {
        MultiComparator<Book> chain = new MultiComparator<Book>();
        Enumeration<Object> keys = this.properties.keys();
        
        while(keys.hasMoreElements()) {
            
            String key = (String) keys.nextElement();
            String value = this.properties.getProperty(key);
            
            if(value != null && !value.trim().isEmpty()) {
                Comparator<Book> comparator = this.getComparator(key);
                
                if(comparator != null) {
                    chain.addComparator(comparator, this.getDirection(value));
                }
            }
        }
        
        return chain;
    }

    /**
     * Check if the given direction is descending. 
     * 
     * @param value The direction to check.
     * 
     * @return True if the given value is descending. False otherwise.
     */
    private boolean getDirection(String value) {
        return value.toLowerCase().equals("descending");
    }

    /**
     * Return the comparator for the given attribute.
     * 
     * @param attribute The attribute to compare.
     * 
     * @return The comparator for the given attribute.
     */
    private Comparator<Book> getComparator(String attribute) {
        return BookComparator.compareBy(attribute);
    }

    /**
     * @return the properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * @param properties the properties to set
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
