import static org.junit.Assert.*;
import org.junit.Test;

import java.beans.Introspector;

public class TestArrayDequeGold {
    @Test
    public void testDeque() {
        StudentArrayDeque<Integer> testDeque = new StudentArrayDeque<Integer>();
        ArrayDequeSolution<Integer> stdDeque = new ArrayDequeSolution<Integer>();
        String log = "";
        for (int i = 0; i < 1000; ++i) {
            if (stdDeque.isEmpty()) {
                int op = StdRandom.uniform(2);
                if (op == 0) {
                    log = log + "addFirst(" + i + ")\n";
                    testDeque.addFirst(i);
                    stdDeque.addFirst(i);
                } else {
                    log = log + "addLast(" + i + ")\n";
                    testDeque.addLast(i);
                    stdDeque.addLast(i);
                }
            } else {
                int op = StdRandom.uniform(4);
                Integer testMovedNumber = null;
                Integer stdMovedNumber = null;
                switch (op) {
                    case 0:
                        log = log + "addFirst(" + i + ")\n";
                        testDeque.addFirst(i);
                        stdDeque.addFirst(i);
                        break;
                    case 1:
                        log = log + "addLast(" + i + ")\n";
                        testDeque.addLast(i);
                        stdDeque.addLast(i);
                        break;
                    case 2:
                        log = log + "removeFirst()\n";
                        testMovedNumber = testDeque.removeFirst();
                        stdMovedNumber = stdDeque.removeFirst();
                        break;
                    case 3:
                        log = log + "removeLast()\n";
                        testMovedNumber = testDeque.removeLast();
                        stdMovedNumber = stdDeque.removeLast();
                        break;
                    default:
                }
                assertEquals(log, stdMovedNumber, testMovedNumber);
            }
        }
    }
}
