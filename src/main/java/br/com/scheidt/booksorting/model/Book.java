package br.com.scheidt.booksorting.model;

/**
 * @author Marcelo Scheidt
 * 
 * Book model class.
 */
public class Book implements Comparable<Book>{

    private String title;
    private String author;
    private Integer edition;

    /**
     * Default constructor.
     */
    public Book() {

    }

    /**
     * Overload constructor
     * 
     * @param title The title of the book.
     * @param author The author of the book.
     * @param edition The edition year of the book.
     */
    public Book(String title, String author, Integer edition) {
        this.title = title;
        this.author = author;
        this.edition = edition;
    }
    
    @Override
    public int compareTo(Book other) {
        return BookComparator.compare(this, other);
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * @param author
     *            the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the edition
     */
    public Integer getEdition() {
        return this.edition;
    }

    /**
     * @param edition
     *            the edition to set
     */
    public void setEdition(Integer edition) {
        this.edition = edition;
    }
}
