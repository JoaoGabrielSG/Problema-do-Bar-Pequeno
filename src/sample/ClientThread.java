package sample;

import java.text.SimpleDateFormat;
import java.util.concurrent.Semaphore;
import javafx.scene.shape.*;

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

    public Circle clienteSprite;

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

        this.clienteSprite = new Circle(100.0, 100.0, 5.0);

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
                this.drinking(this);
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
                this.goHome(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void goHome(ClientThread client) throws InterruptedException {
        SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        long start = time.getCalendar().getTimeInMillis();

        while ((time.getCalendar().getTimeInMillis() - start) < (long) tempo_casa && eating < 5) {

            time = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        }
        animation.goHome(this);
    }


    private void drinking(ClientThread client) throws InterruptedException {
//        System.out.print("Comecando a beber:" + this.getName() + "\n");
        animation.goBar(this);
        sleep(tempo_bar);
//        System.out.print("Terminou de beber:" + this.getName() + "\n");
    }

}
