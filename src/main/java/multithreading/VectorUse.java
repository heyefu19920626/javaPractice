package multithreading;

import java.util.Vector;

/**
 * Description:
 * Vector在多线程下的使用
 *
 * @author heyefu
 * Create in: 2018-05-24
 * Time: 10:43
 **/
public class VectorUse {

    private static VectorUse vectorUse = new VectorUse();

    private Vector<String> infos = new Vector<>();

    public synchronized void add(String s) {
        while (infos.size() > 3) {
            infos.remove(0);
        }
        infos.add(s);
    }


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
//                        System.out.println(s + " " + infos.size());
                        vectorUse.add(s);
                    }
                }

                public Runnable setS(String s) {
                    this.s = s;
                    return this;
                }
            }.setS("Vector Thread-" + i)).start();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(vectorUse.infos.size());
                    for (int i = 0; i < vectorUse.infos.size(); i++) {
                        System.out.print(vectorUse.infos.get(i) + "\t");
                    }
                    System.out.println();
                }
            }
        }).start();


    }

}
