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

    Semaphore mutex1;
    Semaphore mutex2;

    public ClientThread(String name, int tempo_bar, int tempo_casa) {
        super(name);

        this.tempo_bar = tempo_bar;
        this.tempo_casa = tempo_casa;
        this.eating = 0;
        this.waiting = 0;
        this.must_wait = false;

        this.mutex1 = new Semaphore(1);
        this.mutex2 = new Semaphore(0);

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

            this.drinking();
            System.out.print("Esta voltando para o bar:" + this.getName() + "\n");

            try {
                mutex1.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            eating -= 1;
            if (eating == 0){
                must_wait = (eating == 5);
                mutex2.release();
            }

            this.goHome();
            System.out.print("Acabou de beber:" + this.getName() + "\n");

            mutex1.release();
            this.goHome();
        }
    }

    private void goHome(){
        try {
            wait(20000);
        } catch(Exception e) { }
    }

    private void drinking() {
        try {
            wait(20000);
        } catch(Exception e) { }
    }
}
