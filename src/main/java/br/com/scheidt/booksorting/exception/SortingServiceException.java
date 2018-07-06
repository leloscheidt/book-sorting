package br.com.scheidt.booksorting.exception;

/**
 * Exception class.
 * 
 * @author Marcelo Scheidt
 */
public class SortingServiceException extends Exception {

    private static final long serialVersionUID = -3425118739707388455L;

    /**
     * Default constructor.
     * 
     * @param message The error message.
     */
    public SortingServiceException(String message) {
        super(message);
    }
}
