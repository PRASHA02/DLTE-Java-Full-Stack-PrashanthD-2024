package generics.credit;

import java.util.Arrays;

public  abstract  class MyBankDatabaseActivity <T>{
    public T[] myObjects;
    public abstract String insertNewRecord(T objects);
    public void viewAll(){
        System.out.println(Arrays.toString(myObjects));
    }
    public abstract T read(int index);
    public abstract String delete(int index);
    public abstract void update(int index,T object);
}
