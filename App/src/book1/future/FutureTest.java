package src.book1.future;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class FutureTest {
    public static void main(String[] args) {
        System.out.println("start");
        String directory = System.getProperty("user.dir");
        String keyWord = "class";

        MatchCounter counter = new MatchCounter(new File(directory), keyWord);
        FutureTask<Integer> task = new FutureTask<>(counter);
        Thread t = new Thread(task);
        t.start();

        try {
            int count = task.get();
            System.out.println(count);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

/**
 * MatchCounter
 */
class MatchCounter implements Callable<Integer> {

    private File directory;
    private String keyword;

    public MatchCounter(File dic, String key) {
        this.directory = dic;
        this.keyword = key;
    }

    @Override
    public Integer call() throws Exception {
        int count = 0;
        try {
            File[] files = directory.listFiles();
            List<Future<Integer>> results = new ArrayList<>();
            for (File file : files) {
                if (file.isDirectory()) {
                    MatchCounter counter = new MatchCounter(file, this.keyword);
                    FutureTask<Integer> task = new FutureTask<>(counter);
                    results.add(task);
                    Thread t = new Thread(task);
                    t.start();
                } else {
                    if (this.search(file)) {
                        count += 1;
                    }
                }
            }

            for (Future<Integer> future : results) {
                try {
                    count += future.get();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return count;
    }

    public boolean search(File file) {
        try (Scanner in = new Scanner(file)) {
            boolean found = false;
            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (line.contains(this.keyword)) {
                    return true;
                }
            }
            return found;
        } catch (Exception e) {
            return false;
        }
    }
}