package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {
    @Test
    public void randomTest () {
        StudentArrayDeque<Integer> stu = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> sol = new ArrayDequeSolution<>();
        Integer cnt = 0;
        String msg = "";
        while (true) {
            double r = StdRandom.uniform();
            cnt ++;
            if (r < 0.25) {
                stu.addFirst(cnt);
                sol.addFirst(cnt);
                msg += String.format("addFirst(%d)\n", cnt);
            } else if (r < 0.5) {
                stu.addLast(cnt);
                sol.addLast(cnt);
                msg += String.format("addLast(%d)\n", cnt);
            } else if (r < 0.75) {
                if(!sol.isEmpty()) {
                    Integer exp = sol.removeFirst();
                    Integer act = stu.removeFirst();
                    msg += String.format("removeFirst()\n");
                    assertEquals(msg, exp, act);
                }
            } else {
                if(!sol.isEmpty()) {
                    Integer exp = sol.removeLast();
                    Integer act = stu.removeLast();
                    msg += String.format("removeLast()\n");
                    assertEquals(msg, exp, act);
                }
            }
        }
    }
}
