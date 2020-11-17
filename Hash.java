import java.util.LinkedList;

class Hash<K, V> implements HashI<K, V> {
    class HashElement<K, V> implements Comparable<HashElement<K, V>> {
        K key;
        V value;

        public HashElement(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public int compareTo(HashElement<K, V> h) {
            return ((Comparable<K>) h.key).compareTo(this.key);
        }
    }
    // I came here only for test git
    LinkedList<HashElement<K, V>>[] harray;
    int tableSize;
    double maxLoadFactor;
    int numElements;
    public Hash(int tableSize) {
        this.tableSize = tableSize;
        // Create array then cast
        harray = (LinkedList<HashElement<K, V>>[]) new LinkedList[tableSize];
        for (int i = 0; i < tableSize; ++i) {
            harray[i] = new LinkedList<HashElement<K, V>>();
        }
        maxLoadFactor = 0.75;
        numElements = 0;
    }

    public boolean add(K key, V value) {
        // Resizing
        // No need for remove method
        if (loadFactor() > maxLoadFactor)
            resize(tableSize * 2);
        // Create new obj
        // No need for remove method
        HashElement<K, V> ne = new HashElement(key, value);
        // Index
        // Need for remove method
        int hashVal = key.hashCode();
        hashVal = hashVal & 0x7FFFFFF;
        hashVal = hashVal % tableSize;
        // Need for remove method
        harray[hashVal].add(ne);
        numElements++;
        return true;
    }
    
    public V getValue(K key) {
        int hashVal = key.hashCode();
        hashVal = hashVal & 0x7FFFFFF;
        hashVal = hashVal % tableSize;

        for (HashElement<K, V> he : harray[hashVal]) {
            if (((Comparable<K>) key).compareTo(he.key) == 0)
                return he.value;
            return null;
        }
    }
    
    public void resize(int newSize) {
        LinkedList<HashElement<K, V>>[] new_array = new (LinkedList<HashElement<K, V>>[]) LinkedList[newSize];
        for (int i = 0; i < newSize; ++i) {
            new_array[i] = new LinkedList<>();
        } 
        for (K key : keys) {
            V val = getValue(key);

            HashElement<K, V> ne = new HashElement<K, V>(key, val);

            int hashVal = key.hashCode() & 0x7FFFFFF % newSize;
            new_array[hashVal].add(ne);
        }
        harray = new_array;
        tableSize = newSize;
    }

    class IteratorHelper<T> implements Iterator<T> {
        T[] keys;
        int position;

        public IteratorHelper() {
            keys = (T[]) Object[numElements];
            int p = 0;
            for (int i = 0; i < tableSize; ++i) {
                LinkedList<HashElement<K, V>> list = harray[i];
                for (HashElement<K, V> h : list) {
                    keys[p++] = (T) h.key;
                }
            }
            position = 0;
        }

        public boolean hasNext() {
            return position < keys.length;
        }

        public T next() {
            if (!hasNext())
                return null;
            return keys[position++];
        }
    }
}
