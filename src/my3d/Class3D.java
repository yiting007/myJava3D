/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my3d;

import java.util.ArrayList;

/**
 *
 * @author LYT
 */
public class Class3D {

    private ArrayList<Point> Initpoints = new ArrayList<Point>();
    private ArrayList<Point> tempPoints = new ArrayList<Point>();
    private ArrayList<Point> points = new ArrayList<Point>();
    private Point cameraPoint, P1, P2, RP;

    public Class3D() {
    }

    public Matrix Mwc_vrc(Matrix camera, Point VRP) {
        Matrix Mvrp = new Matrix(VRP);
        Matrix Mnew = new Matrix();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Mnew.matrix[i][j] = camera.matrix[i][0] * Mvrp.matrix[0][j]
                        + camera.matrix[i][1] * Mvrp.matrix[1][j]
                        + camera.matrix[i][2] * Mvrp.matrix[2][j]
                        + camera.matrix[i][3] * Mvrp.matrix[3][j];
            }
        }
        return Mnew;
    }

    public Point New_Point(Matrix Tmatrix, Point oldPoint) {
        HoPoint oldP = new HoPoint(oldPoint);
        HoPoint newP = new HoPoint();
        for (int i = 0; i < 4; i++) {
            newP.point[i] =
                    oldP.point[0] * Tmatrix.matrix[i][0]
                    + oldP.point[1] * Tmatrix.matrix[i][1]
                    + oldP.point[2] * Tmatrix.matrix[i][2]
                    + oldP.point[3] * Tmatrix.matrix[i][3];
        }
        if (newP.point[3] != 0) {
            for (int i = 0; i < 4; i++) {
                newP.point[i] = newP.point[i] / newP.point[3];
            }
        }
        Point newPoint = new Point(newP.point[0], newP.point[1], newP.point[2], oldPoint.getCode(), oldPoint.getPosition());
        return newPoint;
    }

    public Point Mper(Point ReferencePoint, Point P) {
        Matrix Mper = new Matrix();
        Mper.matrix = new float[][]{{1, 0, 0, 0}, {0, 1, 0, 0},
            {0, 0, 0, 0}, {0, 0, -1 / ReferencePoint.z, 1}};
        HoPoint pointP = new HoPoint(P);
        HoPoint newP = new HoPoint();
        for (int i = 0; i < 4; i++) {
            newP.point[i] = Mper.matrix[i][0] * pointP.point[0]
                    + Mper.matrix[i][1] * pointP.point[1]
                    + Mper.matrix[i][2] * pointP.point[2]
                    + Mper.matrix[i][3] * pointP.point[3];
        }
        if (newP.point[3] != 0) {
            for (int i = 0; i < 4; i++) {
                newP.point[i] = newP.point[i] / newP.point[3];
            }
        }
        Point newPoint = new Point(newP.point[0], newP.point[1], newP.point[2],P.getCode(),P.getPosition());
        return newPoint;
    }

    public ArrayList<Point> Rotate(ArrayList<Point> points, Point p, double angel, int direction) {
        //平移变换
        Translation(points, -1, p);
        //旋转变换
        if (direction == 1) {
            if (angel != 0) {//Rz
                float cos = (float) Math.cos(Math.toRadians(angel));
                float sin = (float) Math.sin(Math.toRadians(angel));
                Matrix mZ = new Matrix();
                mZ.matrix = new float[][]{{cos, sin, 0, 0}, {-sin, cos, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
                for (int i = 0; i < points.size(); i++) {
                    points.set(i, New_Point(mZ, points.get(i)));
                }
            }
        }
        if (direction == 2) {
            if (angel != 0) {//Rx
                float cos = (float) Math.cos(Math.toRadians(angel));
                float sin = (float) Math.sin(Math.toRadians(angel));
                Matrix mZ = new Matrix();
                mZ.matrix = new float[][]{{1, 0, 0, 0}, {0, cos, sin, 0}, {0, -sin, cos, 0}, {0, 0, 0, 1}};
                for (int i = 0; i < points.size(); i++) {
                    points.set(i, New_Point(mZ, points.get(i)));
                }
            }
        }
        if (direction == 3) {
            if (angel != 0) {//Ry
                float cos = (float) Math.cos(Math.toRadians(angel));
                float sin = (float) Math.sin(Math.toRadians(angel));
                Matrix mZ = new Matrix();
                mZ.matrix = new float[][]{{cos, 0, -sin, 0}, {0, 1, 0, 0}, {sin, 0, cos, 0}, {0, 0, 0, 1}};
                for (int i = 0; i < points.size(); i++) {
                    points.set(i, New_Point(mZ, points.get(i)));
                }
            }
        }
        //平移变换
        Translation(points, 1, p);
        return points;
    }

    public ArrayList<Point> Translation(ArrayList<Point> points, int direction, Point p) {
        Matrix tempM = new Matrix();
        tempM.matrix = new float[][]{{1, 0, 0, direction * p.x}, {0, 1, 0, direction * p.y},
            {0, 0, 1, direction * p.z}, {0, 0, 0, 1}};

        for (int i = 0; i < points.size(); i++) {
            points.set(i, New_Point(tempM, points.get(i)));
        }
        return points;
    }

    //<editor-fold defaultstate="collapsed" desc="Sets and Gets">
    public void setInitpoints(ArrayList<Point> Initpoints) {
        this.Initpoints = (ArrayList<Point>) Initpoints.clone();
    }

    public ArrayList<Point> getInitpoints() {
        return Initpoints;
    }

    public void setTempPoints(ArrayList<Point> tempPoints) {
        this.tempPoints = (ArrayList<Point>) tempPoints.clone();
    }

    public ArrayList<Point> getTempPoints() {
        //return this.tempPoints;
        ArrayList<Point> result = new ArrayList<Point>();
        for (int i = 0; i < tempPoints.size(); i++) {
            result.add(new Point(tempPoints.get(i).x, tempPoints.get(i).y, tempPoints.get(i).z, tempPoints.get(i).getCode(), tempPoints.get(i).getPosition()));
        }
        return result;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points=(ArrayList<Point>) points.clone();
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setCameraPoint(Point cameraPoint) {
        this.cameraPoint = cameraPoint;
    }

    public Point getCameraPoint() {
        return cameraPoint;
    }

    public void setP1(Point P1) {
        this.P1 = P1;
    }

    public Point getP1() {
        return P1;
    }

    public void setP2(Point P2) {
        this.P2 = P2;
    }

    public Point getP2() {
        return P2;
    }

    public void setRP(Point RP) {
        this.RP = RP;
    }

    public Point getRP() {
        return RP;
    }

    public void setCameraX(int add) {
        this.cameraPoint.x += add;
    }

    public void setCameraY(int add) {
        this.cameraPoint.y += add;
    }

    public void setCameraZ(int add) {
        this.cameraPoint.z += add;
    }
    //</editor-fold>
}
