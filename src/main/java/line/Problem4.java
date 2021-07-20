package line;

import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;

/**
 * Name: Problem4
 * URL:
 * rating:
 * status: inprogress
 */
public class Problem4 {

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split("\\s");
        int numCommands = Integer.parseInt(firstLine[0]);
        int capacity = Integer.parseInt(firstLine[1]);


        FiFoQueue<String> queue = new FiFoQueue<String>(capacity);
        for (int i = 0; i < numCommands; i++) {
            String[] line = br.readLine().split("\\s+");
            switch (line[0].charAt(0)) {
                case 'O': // OFFER
                  System.out.println(queue.offer(line[1]));
                  break;
                case 'T': // TAKE
                    String element = queue.take();
                    if (element != null)
                        System.out.println(element);
                    break;
                case 'S': // SIZE
                    System.out.println(queue.size());
                    break;
            }
        }
    }

    public static class FiFoQueue<E> {
        private int capacity;
        private LinkedList<E> elements;

        public FiFoQueue(int capacity) {
            this.capacity = capacity;
            elements = new LinkedList<E>();
        }

        public boolean offer(E e) {
            if (size() >= capacity)
                return false;
            return elements.add(e);
        }

        public int size() {
            return elements.size();
        }

        public boolean isEmpty() {
            return size() == 0;
        }
        public E take() {
            if (isEmpty())
                return null;
            return elements.pollFirst();
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
