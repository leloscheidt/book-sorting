package br.com.scheidt.booksorting.service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MultiComparator<T> implements Comparator<T> {
    private final Map<Comparator<? super T>, Boolean> comparators;
    
    public MultiComparator() {
        this.comparators = new LinkedHashMap<Comparator<? super T>, Boolean>();
    }

    @Override
    public int compare(T o1, T o2) {
        for (Entry<Comparator<? super T>,Boolean> entry : this.comparators.entrySet()) {
            int result = entry.getKey().compare(o1, o2);
            
            if(entry.getValue()) {
                result *= -1;
            }
            
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }
    
    public void addComparator(Comparator<T> comparator, boolean direction) {
        this.comparators.put(comparator, direction);
        
    }

    public int size() {
        return this.comparators.size();
    }
}
