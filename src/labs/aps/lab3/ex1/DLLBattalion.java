package labs.aps.lab3.ex1;

import java.util.List;
import java.util.Scanner;

class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;

    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if (after == last) {
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if (before == first) {
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        } else
            return null;
    }

    public E delete(DLLNode<E> node) {
        if (node == first) {
            return deleteFirst();
        }
        if (node == last) {
            return deleteLast();
        }
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;
        return node.element;

    }

    public DLLNode<E> find(E o) {
        if (first != null) {
            DLLNode<E> tmp = first;
            while (!tmp.element.equals(o) && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element.equals(o)) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int getSize() {
        int listSize = 0;
        DLLNode<E> tmp = first;
        while (tmp != null) {
            listSize++;
            tmp = tmp.succ;
        }
        return listSize;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp.toString();
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += "<->" + tmp.toString();
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public String toStringR() {
        String ret = new String();
        if (last != null) {
            DLLNode<E> tmp = last;
            ret += tmp.toString();
            while (tmp.pred != null) {
                tmp = tmp.pred;
                ret += "<->" + tmp.toString();
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {

        return last;
    }

    public void setFirst(DLLNode<E> node) {
        this.first = node;
    }

    public void setLast(DLLNode<E> node) {
        this.last = node;
    }

    public void mirror() {

        DLLNode<E> tmp = null;
        DLLNode<E> current = first;
        last = first;
        while (current != null) {
            tmp = current.pred;
            current.pred = current.succ;
            current.succ = tmp;
            current = current.pred;
        }

        if (tmp != null && tmp.pred != null) {
            first = tmp.pred;
        }
    }
}

public class DLLBattalion {

    //TODO: implement function
    public static void battalion(DLL<Integer> list, int a, int b) {
//        DLLNode<Integer> first1 = list.find(a);
//        DLLNode<Integer> first = list.find(a);
//        DLLNode<Integer> last1 = list.find(b);
//        DLLNode<Integer> last = list.find(b);
//            DLL<Integer> pom = new DLL<>();
//            pom.setFirst(first);
//            pom.setLast(last);
//            pom.mirror();
//
//            if (first1.pred != null) {
//                first1.pred.succ = pom.getFirst();
//                pom.getFirst().pred = first1.pred;
//            } else {
//                list.setFirst(pom.getFirst());
//            }
//
//            if (last1.succ != null) {
//                last1.succ.pred = pom.getLast();
//                pom.getLast().succ = last1.succ;
//            } else {
//                list.setLast(pom.getLast());
//            }



        if (a != b) {
            DLLNode<Integer> temp1 = list.find(a);
            DLL<Integer> pom = new DLL<>();
            int size = list.getSize();
            DLLNode<Integer> beginning2 = list.find(a).pred;

            pom.insertLast(temp1.element);
            while (temp1 != null) {
                temp1 = temp1.succ;
                pom.insertLast(temp1.element);
                if (temp1.element == b) break;
            }

            temp1 = list.find(a);
            list.delete(temp1);
            while (temp1 != null) {
                temp1 = temp1.succ;
                list.delete(temp1);
                if (temp1.element == b) break;
            }

            pom.mirror();
            DLLNode<Integer> beg = pom.getFirst();
            if (beginning2 == null) {
                list.insertFirst(beg.element);
                DLLNode<Integer> beginning1 = list.getFirst();
                beg = beg.succ;
                for (int i = 0; i < pom.getSize() - 1; i++) {
                    list.insertAfter(beg.element, beginning1);
                    beginning1 = beginning1.succ;
                    beg = beg.succ;
                }
            } else {
                for (int i = 0; i < pom.getSize(); i++) {
                    list.insertAfter(beg.element, beginning2);
                    beg = beg.succ;
                    beginning2 = beginning2.succ;
                }
            }
        }

//        DLLNode<Integer> temp1 = list.find(a);
//        DLLNode<Integer> temp2 = list.find(b);
//
//        while (temp1 != temp2) {
//            int pom = temp1.element;
//            temp1.element = temp2.element;
//            temp2.element = pom;
//
//            temp1 = temp1.succ;
//            if (temp1 == temp2) break;
//            temp2 = temp2.pred;
//        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        DLL<Integer> list = new DLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(input.nextInt());
        }

        int a = input.nextInt();
        int b = input.nextInt();

        battalion(list, a, b);

        System.out.println(list);
        System.out.println(list.toStringR());


    }
}