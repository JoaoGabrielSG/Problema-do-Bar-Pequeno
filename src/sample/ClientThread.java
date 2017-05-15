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

            try {
                mutex1.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            eating -= 1;
            if (eating == 0){
                waiting -= 1;
                eating += 1;
                must_wait = (eating == 5);
                mutex2.release(5);
            }

            this.goHome();

            mutex1.release();


        }
    }

    private void goHome(){
        for(int i = 0; i < 5000000; i++){
            for(int j = 0; j < 10000000; j++){
                System.out.print("Esta voltando para o bar:" + this.getName() + "\n");
            }
        }
    }

    private void drinking() {
        for(int i = 0; i < 500000000; i++){
            for(int j = 0; j < 10000000; j++){
                System.out.print("Acabou de beber:" + this.getName() + "\n");
            }
        }
    }
}
