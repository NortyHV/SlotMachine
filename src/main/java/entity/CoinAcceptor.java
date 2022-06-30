package entity;

import java.io.IOException;
import java.util.Random;

public class CoinAcceptor  {
    Services services;

    int coin;
    int attempts;
    int toys = 10;

    public CoinAcceptor() {
        services = new Services();
    }

    public void payment (int coin) {
        int plays = coin/50;
        if (plays%4 == 0 && plays>0) plays = plays + plays/4;
        attempts = plays;

    }

    private boolean isWin() {
        return (Math.random()<=0.2d);
    }

    public void winOrNotWin () {
        while (attempts-->0) {
            if (isWin()) {
                services.write("WIN");
                if (--toys<2) {
                    services.write("no toys");
                }
            } else {
                services.write("Lose");
            }

        }
    }

    private int readOperation() {
        String operation;
        try {
            if (!(operation = services.read()).equals("exit")) {
                Integer operationNumber = Integer.parseInt(operation);
                return operationNumber;
            }
        } catch (IOException e) {
            services.writeUnknownError();
            readOperation();
        }
        return 0;
    }

    public void run() {
        int a;
        while ((a = readOperation()) != 0) {
            payment(a);
            winOrNotWin();
        }
    }


}