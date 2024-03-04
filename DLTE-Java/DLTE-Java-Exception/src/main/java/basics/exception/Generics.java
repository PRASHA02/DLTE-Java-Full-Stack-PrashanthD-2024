package basics.exception;

import java.util.*;

public abstract  class Generics {
    public static  void main(String[] args) {
          Test test = new Test();
//        ArrayList<Test> arrayList = new ArrayList<>();
//        arrayList.add(new Test(22));
//        for(Test each:arrayList){
//            System.out.println("My Age is "+each.getAge());
//        }

//        arrayList.add("name");
//        System.out.println(arrayList.get(0));
//        arrayList.add("empID");
//        arrayList.add("address");
//        arrayList.forEach(data -> System.out.println(data));
//        System.out.println(arrayList.contains("name"));
//
//        Vector <Double> vectorList = new Vector<>();
//        vectorList.add(12.0);
//        vectorList.add(22.0);
//        vectorList.forEach(System.out::println);
//        System.out.println(vectorList.remove(12.0));
//        vectorList.forEach(System.out::println);

//           List<String> linkedList = new LinkedList<>();
//           linkedList.add("name");
//           linkedList.add("age");
//           linkedList.add(2,"gender");
//           linkedList.forEach(System.out::println);
//

           Sample<Integer> sampleInteger = new Sample<>(10);
           Sample<String> sampleString = new Sample<>("Name: Prashanth");
           System.out.println(sampleInteger.getTask());
           System.out.println(sampleString.getTask());
//
//           HashSet<Sample> hashSet = new HashSet();
//           hashSet.add(new Sample(10));
//           hashSet.add(new Sample("string"));
//           for(Sample each:hashSet){
//            System.out.println(each.getTask());

//           }




    }
}
class Test{
    int age;

    public Test() {
    }

    public Test(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}


class Sample<T>{
    private  T task;

    public Sample(T task) {
        this.task = task;
    }

    public T getTask() {
        return task;
    }
}

class BoxType<T>{
    private  T type;
    public  BoxType(T type){
        this.type = type;
    }
}