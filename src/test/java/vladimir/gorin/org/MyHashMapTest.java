package vladimir.gorin.org;

import org.junit.Test;
import vladimir.gorin.org.exceptions.NullKeyException;

import static org.junit.Assert.*;

public class MyHashMapTest<expected> {
    private MyHashMap myMap=new MyHashMap();


    @Test
    public  void testIncreaseTable(){
        for (int i = 0; i <66 ; i++) {
          try{
            myMap.add(i,1);}
          catch (NullKeyException e){
              //Do nothing
          }
        }
        assertEquals(myMap.amountBuckets,10);
    }
    @Test(expected =NullKeyException.class)
    public void testAdd() throws NullKeyException {

        myMap.add("1","2");

        assertTrue(myMap.isExistKey("1"));
            myMap.add(null,"2");
            myMap.add("1","3");


    }

    @Test
    public void testUpdate() {
        try {
            myMap.add("1","2");
        } catch (NullKeyException e) {
            e.printStackTrace();
        }
        myMap.update("1","3");
        assertTrue(myMap.isExistValue("3"));
    }

    @Test
    public void testDelete() {
        try {
            myMap.add("1","2");
            myMap.add("2","3");
        } catch (NullKeyException e) {
            e.printStackTrace();
        }
        myMap.delete("1");
        assertEquals(1,myMap.size());
    }

    @Test(expected = NullKeyException.class)
    public void testisExistKey() throws NullKeyException{
        assertFalse(myMap.isExistKey("1"));
        try {
            myMap.add("1","1");
        } catch (NullKeyException e) {
            e.printStackTrace();
        }
        assertTrue(myMap.isExistKey("1"));

            myMap.isExistKey(null);

    }

    @Test
    public void testIsExistValue() {
        assertFalse(myMap.isExistValue("1"));
        try {
            myMap.add("1","1");
        } catch (NullKeyException e) {
            e.printStackTrace();
        }
        assertTrue(myMap.isExistValue("1"));

    }

    @Test
    public void testSize() {
        try {
            myMap.add("1","2");
            myMap.add("2","2");
            myMap.add("3","2");
        } catch (NullKeyException e) {
            e.printStackTrace();
        }
        assertEquals(3,myMap.size());
    }
    @Test
    public void testGetMyObject() {
        try {
            myMap.add("3","2");
        } catch (NullKeyException e) {
            e.printStackTrace();
        }
        assertEquals("2",myMap.getMyObject("3"));
        assertNull(myMap.getMyObject("4"));
    }
}