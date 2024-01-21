package SecondMidtermExercises.bookHashMapExercises.BestOffer;

//page 176

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class MapEntry<K extends Comparable<K>, E> implements Comparable<K> {

    K key;
    E value;

    public MapEntry(K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo(K that) {
        @SuppressWarnings("unchecked")
        MapEntry<K, E> other = (MapEntry<K, E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString() {
        return "(" + key + "," + value + ")";
    }
}

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class CBHT<K extends Comparable<K>, E> {

    private SLLNode<MapEntry<K, E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        buckets = (SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K, E>> search(K targetKey) {
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {        // Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                curr.element = newEntry;
                return;
            }
        }
        buckets[b] = new SLLNode<MapEntry<K, E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        int b = hash(key);
        for (SLLNode<MapEntry<K, E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry<K, E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }

}

class Lecture implements Comparable<Lecture> {
    String date;
    String time;
    String place;
    Integer fee;

    public Lecture(String date, String time, String place, Integer fee) {
        this.date = date;
        this.time = time;
        this.place = place;
        this.fee = fee;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }

    public Integer getFee() {
        return fee;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return time + " " + place + " " + fee;
    }

    @Override
    public int compareTo(Lecture o) {
        return this.fee.compareTo(o.fee);
    }
}

public class BestOffer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        CBHT<String, List<Lecture>> dates = new CBHT<>(N);
        for (int i = 0; i < N; i++) {
            String[] line = sc.nextLine().split(" ");
            String date = line[0];
            Lecture lecture = new Lecture(date, line[1], line[2], Integer.parseInt(line[3]));
            SLLNode<MapEntry<String, List<Lecture>>> node = dates.search(date);
            if (node == null) {
                dates.insert(date, new ArrayList<>());
                dates.search(date).element.value.add(lecture);
                continue;
            }
            node.element.value.add(lecture);
            Collections.sort(node.element.value, Collections.reverseOrder());

        }
        String targetDate = sc.nextLine();
        SLLNode<MapEntry<String, List<Lecture>>> datesToSearch = dates.search(targetDate);
        if (datesToSearch == null) System.out.println("No Offers");
        else System.out.println(datesToSearch.element.value.get(0).toString());
    }
}
