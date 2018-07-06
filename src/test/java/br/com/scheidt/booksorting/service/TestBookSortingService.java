package br.com.scheidt.booksorting.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import br.com.scheidt.booksorting.configuration.LinkedProperties;
import br.com.scheidt.booksorting.exception.SortingServiceException;
import br.com.scheidt.booksorting.model.Book;

/**
 * Test class for BookSortingService.
 * 
 * @author Marcelo Scheidt
 */
public class TestBookSortingService {
    
    private static final Book BOOK_1 = new Book("Java How to Program", "Deitel & Deitel", 2007);
    private static final Book BOOK_2 = new Book("Patterns of Enterprise Application Architecture", "Martin Fowler", 2002);
    private static final Book BOOK_3 = new Book("Head First Design Patterns", "Elisabeth Freeman", 2004);
    private static final Book BOOK_4 = new Book("Internet & World Wide Web: How to Program", "Deitel & Deitel", 2007);
    
    private BookSortingService service;
    private List<Book> books;
    
    /**
     * Execute before each test in the class. <br>
     * Initialize the set of book to be sent for sorting and initialize a new sorting service.
     */
    @Before
    public void setUp() {
        this.books = new ArrayList<Book>();
        this.books.add(BOOK_1);
        this.books.add(BOOK_2);
        this.books.add(BOOK_3);
        this.books.add(BOOK_4);
        
        this.service = new BookSortingService();
    }
    
    /**
     * Test the sort method with null value. <br> Should throw SortingServiceException.
     * 
     * @throws SortingServiceException if set is null.
     */
    @Test(expected = SortingServiceException.class)
    public void testSortWithNullValue() throws SortingServiceException {
        this.service.sort(null);
    }
    
    /**
     * Test the sort method with an empty set. <br> Should return a empty set.
     * 
     * @throws SortingServiceException if set is null.
     */
    @Test
    public void testSortWithEmptySet() throws SortingServiceException {
        List<Book> result = this.service.sort(new ArrayList<Book>());
        assertTrue(result.isEmpty());
    }
    
    /**
     * Test the sort method with the follow condition: <br>
     * - <b>Ascending title</b>.<br>
     * 
     * Should return one set with the same order of the expected variable.
     * 
     * @throws SortingServiceException if set is null.
     */
    @Test
    public void testSortWithAscendingTitle() throws SortingServiceException {
        Book[] expected = {BOOK_3, BOOK_4, BOOK_1, BOOK_2};
        
        Properties properties = new LinkedProperties();
        properties.put("title", "ascending");
        this.service.setProperties(properties);
        
        List<Book> result = this.service.sort(this.books);
        
        assertEquals(expected[0], result.get(0));
        assertEquals(expected[1], result.get(1));
        assertEquals(expected[2], result.get(2));
        assertEquals(expected[3], result.get(3));
    }
    
    /**
     * Test the sort method with the follow condition: <br>
     * - <b>Ascending author</b>.<br>
     * - <b>Descending title</b>.<br>
     * 
     * Should return one set with the same order of the expected variable.
     * 
     * @throws SortingServiceException if set is null.
     */
    @Test
    public void testSortWithAscendingAuthorAndDescendingTitle() throws SortingServiceException {
        Book[] expected = {BOOK_1, BOOK_4, BOOK_3, BOOK_2};
        
        Properties properties = new LinkedProperties();
        properties.put("author", "ascending");
        properties.put("title", "descending");
        this.service.setProperties(properties);
        
        List<Book> result = this.service.sort(this.books);
        
        assertEquals(expected[0], result.get(0));
        assertEquals(expected[1], result.get(1));
        assertEquals(expected[2], result.get(2));
        assertEquals(expected[3], result.get(3));
    }
    
    /**
     * Test the sort method with the follow condition: <br>
     * - <b>Descending edition</b>.<br>
     * - <b>Descending author</b>.<br>
     * - <b>Ascending title</b>.<br>
     * 
     * Should return one set with the same order of the expected variable.
     * 
     * @throws SortingServiceException if set is null.
     */
    @Test
    public void testSortWithDescendingEditionAndAuthorAndAscendingTitle() throws SortingServiceException {
        Book[] expected = {BOOK_4, BOOK_1, BOOK_3, BOOK_2};
        
        Properties properties = new LinkedProperties();
        properties.put("edition", "descending");
        properties.put("author", "descending");
        properties.put("title", "ascending");
        this.service.setProperties(properties);
        
        List<Book> result = this.service.sort(this.books);
        
        assertEquals(expected[0], result.get(0));
        assertEquals(expected[1], result.get(1));
        assertEquals(expected[2], result.get(2));
        assertEquals(expected[3], result.get(3));
    }
}
