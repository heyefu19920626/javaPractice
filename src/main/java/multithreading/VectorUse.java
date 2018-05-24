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

    private static Vector<String> infos = new Vector<>();

    public static void add(String s) {
        if (infos.size() > 3) {
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
                        VectorUse.add(this.toString());
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
                while (true){
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(infos.size());
                }
            }
        }).start();


    }

}
