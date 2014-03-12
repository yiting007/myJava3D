/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * platform.java
 *
 * Created on Apr 25, 2012, 2:41:48 PM
 */
package my3d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author LYT
 */
public class platform extends javax.swing.JFrame {

    static float width = 960 / 2 + 200, height = 480 / 2 - 100;
    static Class3D my3d;
    Point tempCenter = new Point();
    boolean change = false;
    int countUp = 0;
    int countRight = 0;
    int countFront = 0;
    int angel = 10;
    int move = 20;

    public platform() {
        initComponents();
        //width = DrawPanel.getWidth();
        //height = DrawPanel.getHeight();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DrawPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        DrawPanel.setBackground(new java.awt.Color(255, 255, 255));
        DrawPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 2));
        DrawPanel.setForeground(new java.awt.Color(255, 255, 255));
        DrawPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DrawPanel.setPreferredSize(new java.awt.Dimension(650, 350));
        DrawPanel.setRequestFocusEnabled(false);
        DrawPanel.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout DrawPanelLayout = new javax.swing.GroupLayout(DrawPanel);
        DrawPanel.setLayout(DrawPanelLayout);
        DrawPanelLayout.setHorizontalGroup(
            DrawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 956, Short.MAX_VALUE)
        );
        DrawPanelLayout.setVerticalGroup(
            DrawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 476, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DrawPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DrawPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void clearArrow() {
        countFront = 0;
        countRight = 0;
        countUp = 0;
    }

    public void setArrow() {
        countFront = -1;
        countRight = -1;
        countUp = -1;
    }

    public int getPoint(int code, ArrayList<Point> rotateList) {
        for (int i = 0; i < rotateList.size(); i++) {
            if (rotateList.get(i).getCode() == code) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList setNewPoint(ArrayList<Point> rotateList, ArrayList<Point> temp, int from, int to, int newcode) {
        int tempcode = getPoint(newcode, rotateList);
        String tempPosition = temp.get(tempcode).getPosition();
        if (tempcode == -1) {
            //error!
        }
        for (int i = from * 8; i < to * 8; i++) {
            temp.get(i).setPosition(tempPosition);
            temp.get(i).setPoint(rotateList.get(tempcode).x, rotateList.get(tempcode).y, rotateList.get(tempcode).z);
            tempcode++;
        }
        return temp;
    }

    public boolean checkCube(ArrayList<Point> rotateList) {
        ArrayList<Point> temp = my3d.getTempPoints();
        //<editor-fold defaultstate="collapsed" desc="count up">
        if (countUp == 90 / Math.abs(angel)) {
            if (angel < 0) {//U
                temp = setNewPoint(rotateList, temp, 6, 7, 19);
                temp = setNewPoint(rotateList, temp, 7, 8, 29);
                temp = setNewPoint(rotateList, temp, 8, 9, 39);
                temp = setNewPoint(rotateList, temp, 15, 16, 18);
                temp = setNewPoint(rotateList, temp, 16, 17, 28);
                temp = setNewPoint(rotateList, temp, 17, 18, 38);
                temp = setNewPoint(rotateList, temp, 24, 25, 17);
                temp = setNewPoint(rotateList, temp, 25, 26, 27);
                temp = setNewPoint(rotateList, temp, 26, 27, 37);
            } else {//U'
                temp = setNewPoint(rotateList, temp, 6, 7, 37);
                temp = setNewPoint(rotateList, temp, 7, 8, 27);
                temp = setNewPoint(rotateList, temp, 8, 9, 17);
                temp = setNewPoint(rotateList, temp, 15, 16, 38);
                temp = setNewPoint(rotateList, temp, 16, 17, 28);
                temp = setNewPoint(rotateList, temp, 17, 18, 18);
                temp = setNewPoint(rotateList, temp, 24, 25, 39);
                temp = setNewPoint(rotateList, temp, 25, 26, 29);
                temp = setNewPoint(rotateList, temp, 26, 27, 19);
            }
            temp = startCamera(my3d.getCameraPoint(), my3d.getP1(), my3d.getP2(), temp);
            temp = Projection(temp, my3d.getRP());
            temp = Coordinate_System(temp);
            my3d.setPoints(temp);
            countUp = 0;
            return true;
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="count right">
        if (countRight == 90 / Math.abs(angel)) {
            if (angel < 0) {//R
                temp = setNewPoint(rotateList, temp, 0, 1, 17);
                temp = setNewPoint(rotateList, temp, 3, 4, 27);
                temp = setNewPoint(rotateList, temp, 6, 7, 37);
                temp = setNewPoint(rotateList, temp, 9, 10, 14);
                temp = setNewPoint(rotateList, temp, 12, 13, 24);
                temp = setNewPoint(rotateList, temp, 15, 16, 34);
                temp = setNewPoint(rotateList, temp, 18, 19, 11);
                temp = setNewPoint(rotateList, temp, 21, 22, 21);
                temp = setNewPoint(rotateList, temp, 24, 25, 31);
            } else {//R'
                temp = setNewPoint(rotateList, temp, 0, 1, 31);
                temp = setNewPoint(rotateList, temp, 3, 4, 21);
                temp = setNewPoint(rotateList, temp, 6, 7, 11);
                temp = setNewPoint(rotateList, temp, 9, 10, 34);
                temp = setNewPoint(rotateList, temp, 12, 13, 24);
                temp = setNewPoint(rotateList, temp, 15, 16, 14);
                temp = setNewPoint(rotateList, temp, 18, 19, 37);
                temp = setNewPoint(rotateList, temp, 21, 22, 27);
                temp = setNewPoint(rotateList, temp, 24, 25, 17);
            }

            temp = startCamera(my3d.getCameraPoint(), my3d.getP1(), my3d.getP2(), temp);
            temp = Projection(temp, my3d.getRP());
            temp = Coordinate_System(temp);
            my3d.setPoints(temp);
            countRight = 0;
            return true;
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="count front">
        if (countFront == 90 / Math.abs(angel)) {
            if (angel > 0) {//F
                temp = setNewPoint(rotateList, temp, 0, 1, 17);
                temp = setNewPoint(rotateList, temp, 1, 2, 14);
                temp = setNewPoint(rotateList, temp, 2, 3, 11);
                temp = setNewPoint(rotateList, temp, 3, 4, 18);
                temp = setNewPoint(rotateList, temp, 4, 5, 15);
                temp = setNewPoint(rotateList, temp, 5, 6, 12);
                temp = setNewPoint(rotateList, temp, 6, 7, 19);
                temp = setNewPoint(rotateList, temp, 7, 8, 16);
                temp = setNewPoint(rotateList, temp, 8, 9, 13);
            } else {//F'
                temp = setNewPoint(rotateList, temp, 0, 1, 13);
                temp = setNewPoint(rotateList, temp, 1, 2, 16);
                temp = setNewPoint(rotateList, temp, 2, 3, 19);
                temp = setNewPoint(rotateList, temp, 3, 4, 12);
                temp = setNewPoint(rotateList, temp, 4, 5, 15);
                temp = setNewPoint(rotateList, temp, 5, 6, 18);
                temp = setNewPoint(rotateList, temp, 6, 7, 11);
                temp = setNewPoint(rotateList, temp, 7, 8, 14);
                temp = setNewPoint(rotateList, temp, 8, 9, 17);
            }

            temp = startCamera(my3d.getCameraPoint(), my3d.getP1(), my3d.getP2(), temp);
            temp = Projection(temp, my3d.getRP());
            temp = Coordinate_System(temp);
            my3d.setPoints(temp);
            countFront = 0;
            return true;
        }
        //</editor-fold>
        return false;
    }
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        //<editor-fold defaultstate="collapsed" desc="Arrow Keys">
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            clearArrow();
            my3d.setCameraX(-move);
            ArrayList<Point> temp = startCamera(my3d.getCameraPoint(), my3d.getP1(), my3d.getP2(), my3d.getInitpoints());
            temp = Projection(temp, my3d.getRP());
            temp = Coordinate_System(temp);
            my3d.setPoints(temp);
            repaint();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            clearArrow();
            my3d.setCameraX(move);
            ArrayList<Point> temp = startCamera(my3d.getCameraPoint(), my3d.getP1(), my3d.getP2(), my3d.getInitpoints());
            temp = Projection(temp, my3d.getRP());
            temp = Coordinate_System(temp);
            my3d.setPoints(temp);
            repaint();
        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            clearArrow();
            my3d.setCameraY(move);
            ArrayList<Point> temp = startCamera(my3d.getCameraPoint(), my3d.getP1(), my3d.getP2(), my3d.getInitpoints());
            temp = Projection(temp, my3d.getRP());
            temp = Coordinate_System(temp);
            my3d.setPoints(temp);
            repaint();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            clearArrow();
            my3d.setCameraY(-move);
            ArrayList<Point> temp = startCamera(my3d.getCameraPoint(), my3d.getP1(), my3d.getP2(), my3d.getInitpoints());
            temp = Projection(temp, my3d.getRP());
            temp = Coordinate_System(temp);
            my3d.setPoints(temp);
            repaint();
        }
        if (evt.getKeyCode() == KeyEvent.VK_Z) {
            clearArrow();
            my3d.setCameraZ(-move);
            ArrayList<Point> temp = startCamera(my3d.getCameraPoint(), my3d.getP1(), my3d.getP2(), my3d.getInitpoints());
            temp = Projection(temp, my3d.getRP());
            temp = Coordinate_System(temp);
            my3d.setPoints(temp);
            repaint();
        }
        if (evt.getKeyCode() == KeyEvent.VK_X) {
            clearArrow();
            my3d.setCameraZ(move);
            ArrayList<Point> temp = startCamera(my3d.getCameraPoint(), my3d.getP1(), my3d.getP2(), my3d.getInitpoints());
            temp = Projection(temp, my3d.getRP());
            temp = Coordinate_System(temp);
            my3d.setPoints(temp);
            repaint();
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Rotate Front">
        if (evt.getKeyCode() == KeyEvent.VK_F) {//Front rotate
            if (countRight != 0 || countUp != 0) {
            } else {
                countUp = 0;
                countFront++;
                countRight = 0;
                ArrayList<Point> temp = my3d.getTempPoints();
                ArrayList<Point> rotateList = new ArrayList<Point>();
                for (int i = 0; i < 8 * 9; i++) {
                    rotateList.add(temp.get(i));
                }
                tempCenter = new Point(75, 75, 0);
                rotateList = my3d.Rotate(rotateList, tempCenter, angel, 1);
                for (int i = 0; i < 8 * 9; i++) {
                    temp.set(i, rotateList.get(i));
                }
                temp = startCamera(my3d.getCameraPoint(), my3d.getP1(), my3d.getP2(), temp);
                temp = Projection(temp, my3d.getRP());
                temp = Coordinate_System(temp);
                if (!checkCube(rotateList)) {
                    my3d.setPoints(temp);
                }
                repaint();
            }
        }
        if ((evt.getModifiers() == InputEvent.CTRL_MASK) && (evt.getKeyCode() == KeyEvent.VK_1)) {//Front rotate
            setArrow();
            ArrayList<Point> temp = my3d.getTempPoints();
            tempCenter = new Point(75, 75, 0);
            temp = my3d.Rotate(temp, tempCenter, 10, 1);
            temp = startCamera(my3d.getCameraPoint(), my3d.getP1(), my3d.getP2(), temp);
            temp = Projection(temp, my3d.getRP());
            temp = Coordinate_System(temp);
            my3d.setPoints(temp);
            repaint();
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Rotate Right">
        if (evt.getKeyCode() == KeyEvent.VK_R) {
            if (countFront != 0 || countUp != 0) {
            } else {
                countUp = 0;
                countRight++;
                countFront = 0;
                ArrayList<Point> temp = my3d.getTempPoints();
                ArrayList<Point> rotateList = new ArrayList<Point>();
                for (int i = 0; i < 8 * 25; i++) {
                    if (i > 0 && i % 8 == 0) {
                        i = i + 2 * 8;
                    }
                    rotateList.add(temp.get(i));
                }
                Point p = new Point(0, 75, -75);
                rotateList = my3d.Rotate(rotateList, p, -angel, 2);
                int j = 0;
                for (int i = 0; i < 8 * 25; i++) {
                    if (i > 0 && i % 8 == 0) {
                        i = i + 2 * 8;
                    }
                    rotateList.get(j);
                    temp.set(i, rotateList.get(j));
                    j++;
                }
                temp = startCamera(my3d.getCameraPoint(), my3d.getP1(), my3d.getP2(), temp);
                temp = Projection(temp, my3d.getRP());
                temp = Coordinate_System(temp);
                if (!checkCube(rotateList)) {
                    my3d.setPoints(temp);
                }
                repaint();
            }
        }
        if ((evt.getModifiers() == InputEvent.CTRL_MASK) && (evt.getKeyCode() == KeyEvent.VK_2)) {//Front rotate
            setArrow();
            ArrayList<Point> temp = my3d.getTempPoints();
            tempCenter = new Point(0, 75, -75);
            temp = my3d.Rotate(temp, tempCenter, 10, 2);
            temp = startCamera(my3d.getCameraPoint(), my3d.getP1(), my3d.getP2(), temp);
            temp = Projection(temp, my3d.getRP());
            temp = Coordinate_System(temp);
            my3d.setPoints(temp);
            repaint();
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Rotate Up">
        if (evt.getKeyCode() == KeyEvent.VK_U) {
            if (countFront != 0 || countRight != 0) {
            } else {
                countUp++;
                countFront = 0;
                countRight = 0;
                ArrayList<Point> temp = my3d.getTempPoints();
                ArrayList<Point> rotateList = new ArrayList<Point>();
                for (int i = 6 * 8; i < 8 * 27; i++) {
                    if (i == 72 || i == 144) {
                        i = i + 6 * 8;
                    }
                    rotateList.add(temp.get(i));
                }
                Point p = new Point(75, 0, -75);
                rotateList = my3d.Rotate(rotateList, p, -angel, 3);
                int j = 0;
                for (int i = 6 * 8; i < 8 * 27; i++) {
                    if (i == 72 || i == 144) {
                        i = i + 6 * 8;
                    }
                    rotateList.get(j);
                    temp.set(i, rotateList.get(j));
                    j++;
                }
                temp = startCamera(my3d.getCameraPoint(), my3d.getP1(), my3d.getP2(), temp);
                temp = Projection(temp, my3d.getRP());
                temp = Coordinate_System(temp);

                if (!checkCube(rotateList)) {
                    my3d.setPoints(temp);
                }
                repaint();
            }
        }
        if ((evt.getModifiers() == InputEvent.CTRL_MASK) && (evt.getKeyCode() == KeyEvent.VK_3)) {//Front rotate
            setArrow();
            ArrayList<Point> temp = my3d.getTempPoints();
            tempCenter = new Point(75, 0, -75);
            temp = my3d.Rotate(temp, tempCenter, 10, 3);
            temp = startCamera(my3d.getCameraPoint(), my3d.getP1(), my3d.getP2(), temp);
            temp = Projection(temp, my3d.getRP());
            temp = Coordinate_System(temp);
            my3d.setPoints(temp);
            repaint();
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Change Rotate Direction">
        if (evt.getKeyCode() == KeyEvent.VK_C) {
            if (countFront == 0 && countRight == 0 && countUp == 0) {
                angel = angel * -1;
            }
        }
        //</editor-fold>
    }//GEN-LAST:event_formKeyPressed

    public static ArrayList<Point> init(ArrayList<Point> pointList, int length, int x, int y, int z, int code, String position) {
        pointList.add(new Point(length + x, 0 + y, 0 + z, code, position));
        pointList.add(new Point(length + x, length + y, 0 + z, code, position));
        pointList.add(new Point(0 + x, length + y, 0 + z, code, position));
        pointList.add(new Point(0 + x, 0 + y, 0 + z, code, position));
        pointList.add(new Point(length + x, 0 + y, -length + z, code, position));
        pointList.add(new Point(length + x, length + y, -length + z, code, position));
        pointList.add(new Point(0 + x, length + y, -length + z, code, position));
        pointList.add(new Point(0 + x, 0 + y, -length + z, code, position));
        return pointList;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new platform().setVisible(true);
            }
        });
        my3d = new Class3D();
        int length = 150;
        ArrayList<Point> pointList = new ArrayList<Point>();
        //<editor-fold defaultstate="collapsed" desc="Build up the magic cube, 26 small cubes">
        pointList = init(pointList, length, -length, length, length, 11, "DRF");//11
        pointList = init(pointList, length, 0, length, length, 12, "DF");//12
        pointList = init(pointList, length, length, length, length, 13, "DFL");//13
        pointList = init(pointList, length, -length, 0, length, 14, "FR");//14
        pointList = init(pointList, length, 0, 0, length, 15, "M");//15
        pointList = init(pointList, length, length, 0, length, 16, "FL");//16
        pointList = init(pointList, length, -length, -length, length, 17, "UFR");//17
        pointList = init(pointList, length, 0, -length, length, 18, "UF");//18
        pointList = init(pointList, length, length, -length, length, 19, "ULF");//19

        pointList = init(pointList, length, -length, length, 0, 21, "DR");//21
        pointList = init(pointList, length, 0, length, 0, 22, "M");//22
        pointList = init(pointList, length, length, length, 0, 23, "DL");//23
        pointList = init(pointList, length, -length, 0, 0, 24, "M");//24
        pointList = init(pointList, length, 0, 0, 0, 25, "M");//25 not show actually
        pointList = init(pointList, length, length, 0, 0, 26, "M");//26
        pointList = init(pointList, length, -length, -length, 0, 27, "UR");//27
        pointList = init(pointList, length, length, -length, 0, 28, "M");//28
        pointList = init(pointList, length, length, -length, 0, 29, "UL");//29

        pointList = init(pointList, length, -length, length, -length, 31, "DBR");//31
        pointList = init(pointList, length, 0, length, -length, 32, "DB");//32
        pointList = init(pointList, length, length, length, -length, 33, "DLB");//33
        pointList = init(pointList, length, -length, 0, -length, 34, "BR");//34
        pointList = init(pointList, length, 0, 0, -length, 35, "M");//35
        pointList = init(pointList, length, length, 0, -length, 36, "BR");//36
        pointList = init(pointList, length, -length, -length, -length, 37, "URB");//37
        pointList = init(pointList, length, 0, -length, -length, 38, "UB");//38
        pointList = init(pointList, length, length, -length, -length, 39, "UBL");//39
        //</editor-fold>

        my3d.setInitpoints(pointList);
        Point cameraPointCenter = new Point(-400, -300, 500);
        Point ReferencePoint = new Point(0, 0, -300);
        Point cameraPoint1 = new Point(-1, 0, 0);
        Point cameraPoint2 = new Point(0, 1, 0);
        my3d.setCameraPoint(cameraPointCenter);
        my3d.setP1(cameraPoint1);
        my3d.setP2(cameraPoint2);
        my3d.setRP(ReferencePoint);
        pointList = startCamera(cameraPointCenter, cameraPoint1, cameraPoint2, pointList);
        pointList = Projection(pointList, ReferencePoint);
        pointList = Coordinate_System(pointList);
        my3d.setPoints(pointList);
    }

    public static ArrayList startCamera(Point cameraPointCenter, Point P1, Point P2, ArrayList<Point> pointList) {
        ArrayList<Point> temp = new ArrayList<Point>();
        Camera camera = new Camera(cameraPointCenter, P1, P2);
        Matrix Tmatrix = my3d.Mwc_vrc(camera.getCameraMatrix(), cameraPointCenter);
        for (int i = 0; i < pointList.size(); i++) {
            temp.add(my3d.New_Point(Tmatrix, pointList.get(i)));
        }
        my3d.setTempPoints(pointList);
        return temp;
    }

    public static ArrayList Projection(ArrayList<Point> pointList, Point RP) {
        for (int i = 0; i < pointList.size(); i++) {
            if (pointList.get(i).z != 0) {
                pointList.set(i, my3d.Mper(RP, pointList.get(i)));
            }
        }
        return pointList;
    }

    public static ArrayList Coordinate_System(ArrayList<Point> pointList) {
        for (int i = 0; i < pointList.size(); i++) {
            pointList.get(i).x += width;
            pointList.get(i).y += height;
        }
        return pointList;
    }

    //<editor-fold defaultstate="collapsed" desc="Paint">
    @Override
    public void paint(Graphics g) {
        DrawPanel.getGraphics().clearRect(0, 0, DrawPanel.getWidth(), DrawPanel.getHeight());
        ArrayList<Point> points = my3d.getPoints();
        ArrayList<Point> temp = new ArrayList<Point>();
        Color c = null;
        for (int i = 0; i < 8 * 27; i = i + 8) {
            for (int j = 0; j < 8; j++) {
                if (temp.size() < 8) {
                    temp.add(points.get(i + j));
                } else {
                    temp.set(j, points.get(i + j));
                }
            }
            c = Color.BLACK;
            Draw(g, temp, c);
        }
    }

    public void Draw(Graphics g, ArrayList<Point> points, Color color) {
        int i = 0, j = 1;
        g.setColor(color);
        while (true) {
            g.drawLine((int) points.get(i).x, (int) points.get(i).y, (int) points.get(j).x, (int) points.get(j).y);
            g.drawLine((int) points.get(i + 4).x, (int) points.get(i + 4).y, (int) points.get(j + 4).x, (int) points.get(j + 4).y);
            g.drawLine((int) points.get(i).x, (int) points.get(i).y, (int) points.get(i + 4).x, (int) points.get(i + 4).y);
            i++;
            j++;
            if (j == 4) {
                j = 0;
            }
            if (i == 4) {
                break;
            }
        }
    }

    public void Color(Graphics g, ArrayList<Point> points) {
        //input array:9 * 8 pixel
    }
    //</editor-fold>
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DrawPanel;
    // End of variables declaration//GEN-END:variables
}

//<editor-fold defaultstate="collapsed" desc="Class Point">
class Point {

    float x, y, z;
    private int code;
    private String position;
    char color;

    public Point() {
    }

    public Point(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        color = 'x';
    }

    public Point(float x, float y, float z, int code, String position) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.code = code;
        this.position = position;
    }

    public Point(float x, float y, float z, int code, String position, char color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.code = code;
        this.position = position;
        this.color = color;
    }

    public void setPoint(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public char getColor() {
        return color;
    }

    public String PointtoString() {
        return "(" + x + "," + y + "," + z + "," + "):" + code;
    }
}
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Class Homogeneous Point">
class HoPoint {

    float[] point = new float[4];

    public HoPoint() {
    }

    public HoPoint(Point p) {
        point[0] = p.x;
        point[1] = p.y;
        point[2] = p.z;
        point[3] = 1;
    }
}
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Class Vector">
class Vector {

    float x, y, z;
}
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Class Matrix">
class Matrix {

    Point point;
    Vector v1, v2, v3;
    float[][] matrix;

    public Matrix() {
        matrix = new float[4][4];
    }

    public Matrix(Vector v1, Vector v2, Vector v3) {//init by vector
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        matrix = new float[][]{{v1.x, v1.y, v1.z, 0}, {v2.x, v2.y, v2.z, 0}, {v3.x, v3.y, v3.z, 0}, {0, 0, 0, 1}};
    }

    public Matrix(Point point) {
        this.point = point;
        matrix = new float[][]{{1, 0, 0, -point.x}, {0, 1, 0, -point.y}, {0, 0, 1, -point.z}, {0, 0, 0, 1}};
    }

    public String MatrixToString() {
        String m = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                m = m + matrix[i][j] + "  ";
            }
            m = m + "\n";
        }
        return m;
    }
}
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Class Camera">
class Camera {

    Point center, LR, UD;
    Vector v1, v2, v3;
    Matrix camera;

    public Camera(Point center, Point LR, Point UD) {
        this.center = center;
        this.LR = LR;
        this.UD = UD;
        v1 = new Vector();
        v2 = new Vector();
        v3 = new Vector();
        SetVector();
    }

    private void SetVector() {
        v1.x = LR.x;// - center.x;//set v1
        v1.y = LR.y;// - center.y;
        v1.z = LR.z;// - center.z;
        v2.x = UD.x;// - center.x;//set v2
        v2.y = UD.y;// - center.y;
        v2.z = UD.z;// - center.z;
        //(a2b3-b2a3,a3b1-a1b3,a1b2-b1a2)
        v3.x = v1.y * v2.z - v2.y * v1.z;//set v3
        v3.y = v1.z * v2.x - v1.x * v2.z;
        v3.z = v1.x * v2.y - v2.x * v1.y;
        v1.x = v2.y * v3.z - v3.y * v2.z;//set new v2
        v1.y = v2.z * v3.x - v2.x * v3.z;
        v1.z = v2.x * v3.y - v3.x * v2.y;
        v1 = modifyVector(v1);
        v2 = modifyVector(v2);
        v3 = modifyVector(v3);
        camera = new Matrix(v1, v2, v3);
    }

    private Vector modifyVector(Vector v) {
        float sum = Math.abs((float) Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z));
        v.x = v.x / sum;
        v.y = v.y / sum;
        v.z = v.z / sum;
        return v;
    }

    public Matrix getCameraMatrix() {
        return camera;
    }

    public String cameraToString() {
        return "v1=(" + v1.x + "," + v1.y + "," + v1.z + ")" + "\n"
                + "v2=(" + v2.x + "," + v2.y + "," + v2.z + ")" + "\n"
                + "v3=(" + v3.x + "," + v3.y + "," + v3.z + ")" + "\n";
    }
}
//</editor-fold>

