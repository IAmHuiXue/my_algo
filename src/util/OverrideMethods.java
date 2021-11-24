package util;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class OverrideMethods {
    public static void main(String[] args) {
        Pair a = new Pair("Mike", "Ruby");
        Pair b = new Pair("Ruby", "Mike");
        Pair c = new Pair("Ruby", "Luke");

        System.out.println(a == b);

        System.out.println(a.equals(b));

        Set<Pair> set = new HashSet<Pair>() {
            {
                add(a);
                add(c);
            }
        };

        System.out.println(set.contains(b));
    }
}

class Pair {
    String name1;
    String name2;

    public Pair(String name1, String name2) {
        this.name1 = name1;
        this.name2 = name2;
    }

    @Override
    // to pass in an instance of class Object
    public boolean equals(Object o) {
        // check if the reference points to the same object
        if (this == o) return true;
        // check if it is null, or not the same class
        if (o == null || getClass() != o.getClass()) return false;
        // cast to the same class from Object to class Pair
        Pair pair = (Pair) o;
        // apply your own 'equal' logic in here
        // in our case, [a, b] should equal [b, a]
        return Objects.equals(name1, pair.name1) && Objects.equals(name2, pair.name2)
                || Objects.equals(name1, pair.name2) && Objects.equals(name2, pair.name1);
    }

    @Override
    public int hashCode() {
        // Pay attention: hashCode needs to be applied with the same logic as well!

        // normally each part to time with primes like 7, 31, 101, etc
        return (name1.hashCode() + name2.hashCode()) * 31;
//        return Objects.hash(name1, name2) + Objects.hash(name2, name1);
    }

    @Override
    // sometimes you want to override this method, so it suits the need when testing the codes
    public String toString() {
        return "[" + name1 + ", " + name2 + "]";
    }
}