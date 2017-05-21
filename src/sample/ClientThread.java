package sample;

import java.util.concurrent.Semaphore;

/**
 * Created by João Gabriel on 13/05/2017.
 */
public class ClientThread extends Thread {

    int eating;
    int waiting;
    int tempo_bar;
    int tempo_casa;
    boolean must_wait;

    public static Semaphore mutex1;
    public static Semaphore mutex2;

    Animations animation;

    public ClientThread(String name, int tempo_bar, int tempo_casa, Animations animation) {
        super(name);

        this.tempo_bar = tempo_bar;
        this.tempo_casa = tempo_casa;
        this.eating = 0;
        this.waiting = 0;
        this.must_wait = false;

        this.mutex1 = new Semaphore(1);
        this.mutex2 = new Semaphore(0);

        this.animation = animation;
    }

    public void run(){

        while(true){
            try {
                mutex1.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (must_wait){
                waiting += 1;
                mutex1.release();
                try {
                    mutex2.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                eating += 1;
                must_wait = (eating == 5);
                mutex1.release();
            }

            try {
                this.drinking(animation);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            try {
                mutex1.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            eating -= 1;
            if (eating == 0){
                int n = (5 < waiting) ? 5 : waiting;
                waiting -= n;
                eating += n;

                must_wait = (eating == 5);
                mutex2.release();
            }
            mutex1.release();

            try {
                this.goHome(animation);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void goHome(Animations animation) throws InterruptedException {
//        System.out.print("Esta indo para casa:" + this.getName() + "\n");
        animation.goHome(this.getName());
        sleep(tempo_casa);
//        System.out.print("Esta voltando para o bar:" + this.getName() + "\n");
    }

    private void drinking(Animations animation) throws InterruptedException {
//        System.out.print("Comecando a beber:" + this.getName() + "\n");
        animation.goBar(this.getName());
        sleep(tempo_bar);
//        System.out.print("Terminou de beber:" + this.getName() + "\n");
    }

}
