package vladimir.gorin.org;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyHashMapTest {
    private MyHashMap myMap=new MyHashMap();


    @Test
    public  void increaseTable(){
        for (int i = 0; i <66 ; i++) {
          myMap.add(i,1);
        }
        assertEquals(myMap.AMOUNT_BUCKETS,10);
    }
    @Test
    public void add() {
        myMap.add("1","2");
        assertTrue(myMap.isExistKey("1"));
        try {
            myMap.add(null,"2");
            myMap.add("1","3");
        } catch (NullPointerException e){
            assertTrue(true);
        }

    }

    @Test
    public void update() {
        myMap.add("1","2");
        myMap.update("1","3");
        assertTrue(myMap.isExistValue("3"));
    }

    @Test
    public void delete() {
        myMap.add("1","2");
        myMap.add("2","3");
        myMap.delete("1");
        assertEquals(1,myMap.size());
    }

    @Test
    public void isExistKey() {
        assertFalse(myMap.isExistKey("1"));
        myMap.add("1","1");
        assertTrue(myMap.isExistKey("1"));
        try {
            myMap.isExistKey(null);
        }catch (IllegalMonitorStateException e){
            assertTrue(true);
        }
    }

    @Test
    public void isExistValue() {
        assertFalse(myMap.isExistValue("1"));
        myMap.add("1","1");
        assertTrue(myMap.isExistValue("1"));

    }

    @Test
    public void size() {
        myMap.add("1","2");
        myMap.add("2","2");
        myMap.add("3","2");
        assertEquals(3,myMap.size());
    }
    @Test
    public void getMyObject() {
        myMap.add("3","2");
        assertEquals("2",myMap.getMyObject("3"));
        assertFalse(myMap.getMyObject("4")!=null);
    }
}