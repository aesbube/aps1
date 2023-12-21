package labs.aps.lab6.ex3;

import java.util.Scanner;
import java.util.NoSuchElementException;

interface Queue<E> {

    // Elementi na redicata se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty();
    // Vrakja true ako i samo ako redicata e prazena.

    public int size();
    // Ja vrakja dolzinata na redicata.

    public E peek();
    // Go vrakja elementot na vrvot t.e. pocetokot od redicata.

    // Metodi za transformacija:

    public void clear();
    // Ja prazni redicata.

    public void enqueue(E x);
    // Go dodava x na kraj od redicata.

    public E dequeue();
    // Go otstranuva i vrakja pochetniot element na redicata.
}

class ArrayQueue<E> implements Queue<E> {

    // Redicata e pretstavena na sledniot nacin:
    // length go sodrzi brojot na elementi.
    // Ako length > 0, togash elementite na redicata se zachuvani vo elems[front...rear-1]
    // Ako rear > front, togash vo  elems[front...maxlength-1] i elems[0...rear-1]
    E[] elems;
    int length, front, rear;

    // Konstruktor ...

    @SuppressWarnings("unchecked")
    public ArrayQueue(int maxlength) {
        elems = (E[]) new Object[maxlength];
        clear();
    }

    public boolean isEmpty() {
        // Vrakja true ako i samo ako redicata e prazena.
        return (length == 0);
    }

    public int size() {
        // Ja vrakja dolzinata na redicata.
        return length;
    }

    public E peek() {
        // Go vrakja elementot na vrvot t.e. pocetokot od redicata.
        if (length > 0)
            return elems[front];
        else
            throw new NoSuchElementException();
    }

    public void clear() {
        // Ja prazni redicata.
        length = 0;
        front = rear = 0;  // arbitrary
    }

    public void enqueue(E x) {
        // Go dodava x na kraj od redicata.
        elems[rear++] = x;
        if (rear == elems.length) rear = 0;
        length++;
    }

    public E dequeue() {
        // Go otstranuva i vrakja pochetniot element na redicata.
        if (length > 0) {
            E frontmost = elems[front];
            elems[front++] = null;
            if (front == elems.length) front = 0;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }
}

class Gragjanin {
    private String fullName;
    private Integer id;
    private Integer passport;
    private Integer drivingLicense;

    public Gragjanin(String fullName, Integer id, Integer passport, Integer drivingLicense) {
        this.fullName = fullName;
        this.id = id;
        this.passport = passport;
        this.drivingLicense = drivingLicense;
    }

    public String getFullName() {
        return fullName;
    }

    public Integer getPassport() {
        return passport;
    }

    public Integer getDrivingLicense() {
        return drivingLicense;
    }
}

public class MVR {

    public static void ministryQueue(Queue<Gragjanin> id, Queue<Gragjanin> passport, Queue<Gragjanin> drivingLicense) {
        while (!id.isEmpty()) {
            if (id.peek().getPassport() == 1) passport.enqueue(id.dequeue());
            else if (id.peek().getDrivingLicense() == 1) drivingLicense.enqueue(id.dequeue());
            else System.out.println(id.dequeue().getFullName());
        }
        while (!passport.isEmpty()) {
            if (passport.peek().getDrivingLicense() == 1) drivingLicense.enqueue(passport.dequeue());
            else System.out.println(passport.dequeue().getFullName());
        }
        while (!drivingLicense.isEmpty()) System.out.println(drivingLicense.dequeue().getFullName());
    }

    public static void main(String[] args) {

        Scanner br = new Scanner(System.in);

        int N = Integer.parseInt(br.nextLine());

        Queue<Gragjanin> id = new ArrayQueue<>(N);
        Queue<Gragjanin> passport = new ArrayQueue<>(N);
        Queue<Gragjanin> drivingLicense = new ArrayQueue<>(N);

        for (int i = 1; i <= N; i++) {
            String imePrezime = br.nextLine();
            int lKarta = Integer.parseInt(br.nextLine());
            int pasos = Integer.parseInt(br.nextLine());
            int vozacka = Integer.parseInt(br.nextLine());
            Gragjanin covek = new Gragjanin(imePrezime, lKarta, pasos, vozacka);

            if (lKarta == 1) id.enqueue(covek);
            else if (pasos == 1) passport.enqueue(covek);
            else if (vozacka == 1) drivingLicense.enqueue(covek);
        }

        ministryQueue(id, passport, drivingLicense);
    }
}
