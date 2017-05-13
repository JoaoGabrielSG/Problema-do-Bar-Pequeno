package sample;

import java.util.concurrent.Semaphore;

/**
 * Created by João Gabriel on 13/05/2017.
 */
public class ClientThread extends Thread {

    int tempo_bar;
    int tempo_casa;

    Semaphore mutex1 = new Semaphore(1);



    public ClientThread(String name, int tempo_bar, int tempo_casa) {
        super(name);

        this.tempo_bar = tempo_bar;
        this.tempo_casa = tempo_casa;
    }
}
