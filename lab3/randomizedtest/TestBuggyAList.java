package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  @Test
  public void testThreeAddThreeRemove() {
    AListNoResizing<Integer> cor = new AListNoResizing<Integer>();
    BuggyAList<Integer> bug = new BuggyAList<Integer>();
    cor.addLast(3);
    bug.addLast(3);
    cor.addLast(4);
    bug.addLast(4);
    cor.addLast(5);
    bug.addLast(5);
    assertEquals(cor.size(), bug.size());
    for(int i = 0; i < 3; i ++) {
      assertEquals(cor.removeLast(), bug.removeLast());
    }
  }

  @Test
  public void randomizedTest() {
    AListNoResizing<Integer> cor = new AListNoResizing<>();
    BuggyAList<Integer> bug = new BuggyAList<>();
    int N = 5000;
    for(int i = 0; i < N; i += 1) {
      int operationNumber = StdRandom.uniform(0, 2);
      if (operationNumber == 0) {
        if (cor.size() == 1000) continue;
        int randVal = StdRandom.uniform(0, 100);
        cor.addLast(randVal);
        bug.addLast(randVal);
      } else if (operationNumber == 1) {
        int size = cor.size();
        assertEquals(cor.size(), bug.size());
        if (size != 0) {
          assertEquals(cor.removeLast(), bug.removeLast());
        }
      }
    }
  }
}
