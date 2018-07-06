package br.com.scheidt.booksorting.configuration;

import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Extension of the class Properties for guarantee the order of the properties. <br><br>
 * 
 * Only the needed methods for the sorting service were overridden. <br>
 * To use this class in production, other methods should be overridden too. Like remove(), clear(), putAll()...
 * 
 * @author Marcelo Scheidt
 */
public class LinkedProperties extends Properties {
    
    private static final long serialVersionUID = 4686591728074277206L;
    
    private final Set<Object> keys = new LinkedHashSet<Object>();
    
    @Override
    public Enumeration<Object> keys() {
        return Collections.<Object>enumeration(this.keys);
    }

    @Override
    public Object put(Object key, Object value) {
        this.keys.add(key);
        return super.put(key, value);
    }
}
