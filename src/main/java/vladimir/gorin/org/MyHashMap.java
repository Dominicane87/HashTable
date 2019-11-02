package vladimir.gorin.org;

import vladimir.gorin.org.exceptions.NullKeyException;
import vladimir.gorin.org.exceptions.NullObjectException;

import java.util.HashSet;

class MyHashMap {
    private MyLinkedList<MyObjectEntry>[] hashMap;
    private static final double CAPACITY_LIMIT = 0.75;
    private static final int DEEPNESS_LIST = 16;
    int amountBuckets = 5;

    private int count = 0;

    MyHashMap() {
        hashMap = new MyLinkedList[amountBuckets];
        for (int i = 0; i < hashMap.length; i++) {
            hashMap[i] = new MyLinkedList<>();
        }
    }

    private MyLinkedList<MyObjectEntry> getList(Object key) {
        int hashPos = findHashTable(key);
        return hashMap[hashPos];
    }


    private void increaseMyHashMap(MyLinkedList<MyObjectEntry>[] hashMap) {
        amountBuckets = amountBuckets * 2;
        MyLinkedList<MyObjectEntry>[] tmpHashMap = new MyLinkedList[amountBuckets];
        for (int i = 0; i < tmpHashMap.length; i++) {
            tmpHashMap[i] = new MyLinkedList<>();
        }
        System.arraycopy(hashMap, 0, tmpHashMap, 0, hashMap.length);
    }

    void add(Object key, Object value) throws NullKeyException {
        //Think about increasing massive and evolving
        if (size() > CAPACITY_LIMIT * amountBuckets * DEEPNESS_LIST) increaseMyHashMap(hashMap);

        if (key == null) {
            throw new NullKeyException("The key for addLast() is null.");
        }
        MyObjectEntry myObjectEntry = new MyObjectEntry(key, value);

        if (!isExistKey(key)) {
            try {
                getList(key).add(myObjectEntry);
            } catch (NullObjectException e) {
                //do nothing
            }
            count++;
        } else {
            throw new NullKeyException("This key is already exist");
        }

    }

    void update(Object key, Object value) {
        try {
            getList(key).findByKey(new MyObjectEntry(key, null)).setValue(value);
        } catch (NullObjectException|NullPointerException e) {
            e.printStackTrace();
        }
    }

    Object getMyObject(Object key) {
        MyObjectEntry objectEntry = null;
        try {
            objectEntry = getList(key).findByKey(new MyObjectEntry(key, null));
        } catch (NullPointerException|NullObjectException e1) {
            return null;
        }
        return objectEntry.getValue();

    }

    void delete(Object key) {
        MyObjectEntry isDeleting = new MyObjectEntry(key, null);
        boolean result = getList(key).remove(isDeleting);
        if (result) count--;
    }


    boolean isExistKey(Object key) throws NullKeyException {
        if (key == null) throw new NullKeyException("Input key is null");
        HashSet<MyObjectEntry> set = entrySet();
        for (MyObjectEntry myObjectEntry : set) {
            if (myObjectEntry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    boolean isExistValue(Object value) {
        HashSet<MyObjectEntry> set = entrySet();
        for (MyObjectEntry myObjectEntry : set) {
            if (myObjectEntry.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    int size() {
//        int count = 0;
//        for (MyLinkedList<MyObjectEntry> myObjectEntries : hashMap) {
//            count += myObjectEntries.size();
//        }
        return count;
    }

    private HashSet<MyObjectEntry> entrySet() {
        HashSet<MyObjectEntry> set = new HashSet<>();
        for (MyLinkedList<MyObjectEntry> myObjectEntries : hashMap) {

            for (MyObjectEntry myObjectEntry : myObjectEntries) {
                set.add(myObjectEntry);
            }
        }
        return set;
    }

    private int findHashTable(Object key) {
        return key.hashCode() % hashMap.length;
    }

}
