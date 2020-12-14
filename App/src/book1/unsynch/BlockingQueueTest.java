package src.book1.unsynch;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;

public class BlockingQueueTest {
    private static final int FILE_QUEUE_SIZE = 10;
    private static final int SEARCH_THREADS = 100;
    private static final File DUMMY = new File("");
    private static BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    public static void main(String[] args) {
        System.out.println("start");
        String directory = System.getProperty("user.dir");
        ;
        String keyWord = "class";

        Runnable enumerator = () -> {
            try {
                BlockingQueueTest.enumerate(new File(directory));
                BlockingQueueTest.queue.put(DUMMY);
            } catch (Exception e) {
                // TODO: handle exception
            }
        };
        Thread t = new Thread(enumerator);
        t.start();

        for (int i = 0; i < SEARCH_THREADS; i++) {
            Runnable r = () -> {
                try {
                    boolean done = false;
                    while (!done) {
                        File file = BlockingQueueTest.queue.take();
                        if (file == DUMMY) {
                            BlockingQueueTest.queue.put(file);
                            done = true;
                        } else {
                            BlockingQueueTest.search(file, keyWord);
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            };
            Thread kt = new Thread(r);
            kt.start();
        }
    }

    private static void enumerate(File directory) throws InterruptedException {
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                BlockingQueueTest.enumerate(file);
            } else {
                BlockingQueueTest.queue.put(file);
            }
        }
    }

    private static void search(File file, String key) throws IOException {
        try (Scanner in = new Scanner(file, StandardCharsets.UTF_8)) {
            int lineNumber = 0;
            while (in.hasNextLine()) {
                lineNumber++;
                String line = in.nextLine();
                if (line.contains(key)) {
                    System.out.printf("%s : %d : %s %n", file.getPath(), lineNumber, line);
                }
            }
        }
    }

}
