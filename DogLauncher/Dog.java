import java.util.Comparator;

public class Dog implements Comparable<Dog> {
    private String name;
    private int weight;

    public Dog(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }


    public int compareTo(Dog d) {
        return this.weight- d.weight;
    }


    private static class WeightComparator implements Comparator<Dog>{
        @Override
        public int compare(Dog d1, Dog d2) {
            return d1.weight-d2.weight;
        }
    }
    private static class NameComparator implements Comparator<Dog>{
        @Override
        public int compare(Dog d1 ,Dog d2){
            return d1.name.compareTo(d2.name);
        }

    }
    public static Comparator<Dog> getNameComparator(){
        return new NameComparator();
    }
    public static Comparator<Dog> getWeightComparator(){
        return new WeightComparator();
    }
    public void makeNoise(){
        if(this.weight <= 5){
            System.out.println(name+":"+"Yeep");
        }
        else if(this.weight>5 && this.weight <25){
            System.out.println(name+":"+"Bark!");
        }
        else{
            System.out.println(name+":"+"Woof!");
        }
    }


}