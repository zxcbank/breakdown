package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main extends JPanel{
    static candle[] market = new candle[192];

    Main(){
        JFrame fr = new JFrame(" верхний пробой");
        fr.add(this);
        fr.setVisible(true);
        fr.setSize(1920,1080);
        fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fr.setFocusable(true);
    }

    public static void main(String[] args) {
        market[0] = new candle(790, 800);
        breakdownmaker();
        new Main();
    }

    public static void breakdownmaker(){
        int i = 0;
        while (market[i].close >= 200 && i < market.length){ //рост до сопротивления
            if (new Random().nextInt(100) >= 20)
                market[i + 1] = new candle(market[i].close, market[i].close - new Random().nextInt(50));
            else
                market[i + 1] = new candle(market[i].close, market[i].close + new Random().nextInt(50));
            i++;
        }
        while (market[i].close <= 800 && i < market.length){ //рост до сопротивления
            if (new Random().nextInt(100) >= 20)
                market[i + 1] = new candle(market[i].close, market[i].close + new Random().nextInt(50));
            else
                market[i + 1] = new candle(market[i].close, market[i].close - new Random().nextInt(50));
            i++;
        }
        while (i < market.length - 1){ //рост до сопротивления
            if (new Random().nextInt(100) >= 20)
                market[i + 1] = new candle(market[i].close, market[i].close - new Random().nextInt(50));
            else
                market[i + 1] = new candle(market[i].close, market[i].close + new Random().nextInt(50));
            i++;
        }
    }

    public void paintComponent( Graphics g ) {
        super.paintComponent(g);
        g.drawLine(0,800,1900,800);
        g.drawLine(0,200,1900,200);
        for (int i = 0; i < market.length; i++) {
            g.setColor(Color.BLACK);
            if (market[i].close < market[i].open) {
                g.drawRect(300 + 10 * i - 1, market[i].open - Math.abs(market[i].close - market[i].open) - 1, 11, Math.abs(market[i].close - market[i].open) + 1);
                g.setColor(Color.GREEN);
                g.fillRect(300 + 10 * i, market[i].open - Math.abs(market[i].close - market[i].open), 10, Math.abs(market[i].close - market[i].open));
            } else {
                g.drawRect(300 + 10 * i - 1, market[i].open - 1, 11, Math.abs(market[i].close - market[i].open) + 1);
                g.setColor(Color.RED);
                g.fillRect(300 + 10 * i, market[i].open, 10, Math.abs(market[i].close - market[i].open));
            }
        }
    }
}
