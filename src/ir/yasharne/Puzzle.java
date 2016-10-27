/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.yasharne;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JApplet;

/**
 *
 * @author yashar
 */
public class Puzzle extends JApplet implements KeyListener {

    int dh = 200;
    int dw = 200;
    int board[][];
    int x = 3, y = 2;

    public void init() {
        setSize(800, 600);
        initBoard();
        this.addKeyListener(this);
        setFocusable(true);
        requestFocus();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.red);
        for (int i = 0; i <= getHeight() / dh; i++) {
            g.drawLine(0, dh * i, getWidth(), dh * i);
        }
        for (int j = 0; j <= getWidth() / dw; j++) {
            g.drawLine(dh * j, 0, dh * j, getHeight());
        }
        g.setFont(new Font("Arial", Font.BOLD, 50));
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (i == y && j == x) {
                    continue;
                }
                g.drawString(board[i][j] + "", (dw * j) + (dw / 2), (dh * i) + (dh / 2));
            }
        }
        if (checkBoard()) {
            removeKeyListener(this);
            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.BOLD, 100));
            g.drawString("You Won!", dw, getHeight() / 2);
        }
    }

    private boolean checkBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 4 * i + j) {
                    if (i == y && j == x) {
                        continue;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private void initBoard() {
        board = new int[3][4];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 4 * i + j;
            }
        }
        shuffleBoard();
    }

    private void shuffleBoard() {
        for (int i = 0; i < 20; i++) {
            int rand = (int) (4 * Math.random());
            switch (rand) {
                case 0:
                    up();
                    break;
                case 1:
                    down();
                    break;
                case 2:
                    left();
                    break;
                case 3:
                    right();
                    break;
            }
        }
    }

    private void up() {
        if (y + 1 <= 2) {
            board[y][x] = board[y + 1][x];
            y += 1;
        }
    }

    private void down() {
        if (y - 1 >= 0) {
            board[y][x] = board[y - 1][x];
            y -= 1;
        }
    }

    private void left() {
        if (x + 1 <= 3) {
            board[y][x] = board[y][x + 1];
            x += 1;
        }
    }

    private void right() {
        if (x - 1 >= 0) {
            board[y][x] = board[y][x - 1];
            x -= 1;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                up();
                break;
            case KeyEvent.VK_DOWN:
                down();
                break;
            case KeyEvent.VK_LEFT:
                left();
                break;
            case KeyEvent.VK_RIGHT:
                right();
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
