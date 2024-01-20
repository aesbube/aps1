package LabExercises.lab6.ex2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

interface Stack<E> {

    // Elementi na stekot se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty();
    // Vrakja true ako i samo ako stekot e prazen.

    public E peek();
    // Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:

    public void clear();
    // Go prazni stekot.

    public void push(E x);
    // Go dodava x na vrvot na stekot.

    public E pop();
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}

class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack(int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }


    public boolean isEmpty() {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }


    public E peek() {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth - 1];
    }


    public void clear() {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++) elems[i] = null;
        depth = 0;
    }


    public void push(E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }


    public E pop() {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}


public class CheckXML {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s);
        String[] redovi = new String[n];

        for (int i = 0; i < n; i++)
            redovi[i] = br.readLine();

        int valid = 1;

        boolean flag = false;
        ArrayStack<String> doc = new ArrayStack<String>(n);
        for (int i = 0; i < n; i++) {
            if ((redovi[i].charAt(0) == '[' && redovi[i].charAt(redovi[i].length() - 1) != ']') || (redovi[i].charAt(0) != '[' && redovi[i].charAt(redovi[i].length() - 1) == ']')) {
                valid = 0;
                break;
            }
            if (redovi[i].charAt(0) != '[') continue;
            if (redovi[i].charAt(1) == '/' && !doc.isEmpty()) {
                if (doc.peek().charAt(1) == '/') {
                    valid = 0;
                    break;
                }
                String name = doc.peek().substring(1, doc.peek().length() - 1);
                if (!redovi[i].substring(2, redovi[i].length() - 1).equals(name)) {
                    valid = 0;
                    break;
                }
                doc.pop();
            } else doc.push(redovi[i]);

        }


        System.out.println(valid);

        br.close();
    }
}