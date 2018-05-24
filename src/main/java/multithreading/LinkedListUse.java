package multithreading;

import java.util.LinkedList;

/**
 * Description:
 * LinkedList在多线程下的使用
 *
 * @author heyefu
 * Create in: 2018-05-24
 * Time: 10:31
 **/
public class LinkedListUse {

    private static LinkedList<String> infos = new LinkedList<>();


    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                String s;

                @Override
                public void run() {

                    while (true) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(s + " " + infos.size());
                        LinkedListUse.add(this.toString());
                    }
                }

                public Runnable setS(String s) {
                    this.s = s;
                    return this;
                }
            }.setS("Thread-" + i)).start();
        }
    }

    public static void add(String info) {
        if (infos.size() > 3) {
            infos.removeFirst();
        }
        infos.add(info);
    }

}
