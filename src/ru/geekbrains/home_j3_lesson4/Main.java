package ru.geekbrains.home_j3_lesson4;

public class Main {
    static String str = "A";

    public static void main(String[] args) {
        Object lock = new Object();

        class SThred implements Runnable {
            private String letter;
            private String nextLetter;

            public SThred(String letter, String nextLetter) {
                this.letter = letter;
                this.nextLetter = nextLetter;
            }

            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    synchronized (lock) {
                        try {
                            while (!str.equals(letter))
                                lock.wait();
                            System.out.print(letter);
                            str = nextLetter;
                            lock.notifyAll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        new Thread(new SThred("A", "B")).start();
        new Thread(new SThred("B", "C")).start();
        new Thread(new SThred("C", "A")).start();


    }
}
