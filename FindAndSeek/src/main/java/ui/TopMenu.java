/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dev.jaakkovirtanen.findandseek.levels.Board;
import dev.jaakkovirtanen.findandseek.mapobjects.Answer;
import dev.jaakkovirtanen.findandseek.mapobjects.Location;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author User
 */
public class TopMenu extends JPanel {

    private GUI gui;
    private RectangleDrawing rectum;

    public TopMenu(GUI gui) {
        super(new GridLayout(1, 3));
        this.gui = gui;
        this.rectum = new RectangleDrawing(gui.getGame());
        createComponents();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        repaint();
    }

    private void createComponents() {
        InnerTarget targetti = new InnerTarget(gui.getGame().getGameBoard());
        targetti.setPreferredSize(targetti.getPrefSize());
        add(targetti);
    }

    class InnerTarget extends JPanel {

        private Dimension prefSize;

        /**
         * Constructor for DrawBoard.
         *
         * @param gameboard initialized with gameboard
         */
        public InnerTarget(Board gameboard) {
            this.prefSize = new Dimension(40, 40);
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            for (Answer a : gui.getGame().getGameBoard().getAnswers()) {
                if (isAnswerGoal(a.getRow(), a.getCol())) {
                    RectangleDrawing.drawRectangle(gui.getGame().getGameBoard().getWidth() * 10 - 10, 0, 35, 0, 0, 35, g2d, RectangleDrawing.charToColor(a.getValue()));
                }
            }
        }

        private boolean isAnswerGoal(int i, int j) {
            for (Answer a : gui.getGame().getGameBoard().getAnswers()) {
                if (a.getLocation().equals(new Location(i, j))) {
                    if (a.isTarget()) {
                        return true;
                    }
                }
            }
            return false;
        }

        public Dimension getPrefSize() {
            return prefSize;
        }
    }

}
