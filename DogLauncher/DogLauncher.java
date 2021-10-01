import java.util.Comparator;

public class DogLauncher {
    public static void maxCp(Comparator cp,Dog o1,Dog o2){
        if(cp.compare(o1,o2) > 0){
            o1.makeNoise();
        } else {
            o2.makeNoise();
        }
    }
    public static void main(String[] args){
        Dog a = new Dog("alphadog",5);
        Dog b = new Dog("bravodog",25);
        Dog c = new Dog("charilydog",18);
        Dog[] dogs = {a,b,c};

        Dog maxDog = (Dog) Maximizer.max(dogs);
        maxDog.makeNoise();

        Comparator<Dog> cn = Dog.getNameComparator();
        Comparator<Dog> cw = Dog.getWeightComparator();
        maxCp(cn,a,c);
        maxCp(cw,a,b);

    }
}