package org.demchuko.y2025.day9;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.awt.geom.Line2D;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

@Slf4j
public class Day9 {

    public static char RED_TILE = '#';
    public static char BLUE_TILE = '.';

    double solveTask1(List<String> inputData) {
        List<Point> collect = inputData
                .stream()
                .map(Point::new)
                .collect(Collectors.toList());
        Collections.sort(collect);

        double maxArea = -1.0;

        for (int i = 0; i < collect.size(); i++) {
            for (int j = collect.size() - 1; j > i; j--) {
                maxArea = collect.get(i).getAreaIfBigger(maxArea, collect.get(j));
            }
        }

        return maxArea;
    }

    double solveTask2(List<String> inputData) {
        List<Point> points = inputData
                .stream()
                .map(Point::new)
                .collect(Collectors.toList());

        double maxArea = -1.0;

        for (int i = 0; i < points.size(); i++) {
            for (int j = points.size() - 1; j > i; j--) {
                Point point1 = points.get(i);
                Point point2 = points.get(j);

                if (isSquareValid(Square.builder()
                        .corner1(point1)
                        .corner2(point2)
                        .build(), points)) {
                    maxArea = point1.getAreaIfBigger(maxArea, point2);
                }
            }
        }

        return maxArea;
    }

    public boolean isSquareValid(Square square, List<Point> points) {
        Square shrinked = shrinkSquare(square);
        
        Point corner1 = shrinked.corner1;
        Point corner2 = shrinked.corner2;
        Point corner3 = new Point(corner1.x, corner2.y);
        Point corner4 = new Point(corner2.x, corner1.y);

        if (!pointInPolygon(corner1, points) ||
                !pointInPolygon(corner2, points) ||
                !pointInPolygon(corner3, points) ||
                !pointInPolygon(corner4, points)) {
            return false;
        }

        for (int i = 0; i < points.size() - 1; i++) {
            Point start = points.get(i);
            Point end = points.get((i + 1) % points.size());

            if (linesIntersect(corner1, corner3, start, end) ||
                    linesIntersect(corner1, corner4, start, end) ||
                    linesIntersect(corner2, corner3, start, end) ||
                    linesIntersect(corner2, corner4, start, end)) {
                return false;
            }
        }
        return true;

    }
    
    public Square shrinkSquare(final Square square) {
        double shrink_size = 0.001;
        Point corner1 = new Point(square.corner1.x, square.corner1.y);
        Point corner2 = new Point(square.corner2.x, square.corner2.y);

        corner1.x += corner1.x == Math.min(corner1.x, corner2.x) ? shrink_size : -1 * shrink_size;
        corner2.x += corner2.x == Math.min(corner1.x, corner2.x) ? shrink_size : -1 * shrink_size;
        corner1.y += corner1.y == Math.min(corner1.y, corner2.y) ? shrink_size : -1 * shrink_size;
        corner2.y += corner2.y == Math.min(corner1.y, corner2.y) ? shrink_size : -1 * shrink_size;
        
        return new Square(corner1, corner2);
    }

    public boolean pointInPolygon(Point point, List<Point> points) {
        return pointInPolygon(point.x, point.y, points);
    }

    public boolean pointInPolygon(double x, double y, List<Point> poly) {
        boolean inside = false;
        for (int i = 0; i < poly.size(); i++) {
            Point pi = poly.get(i);
            Point pj = poly.get((i + 1) % poly.size());

            boolean intersect =
                    ((pi.y > y) != (pj.y > y)) &&
                            (x < (pj.x - pi.x) * (y - pi.y) / (pj.y - pi.y) + pi.x);

            if (intersect) {
                inside = !inside;
            }
        }
        return inside;
    }

    public boolean linesIntersect(Point a, Point b, Point c, Point d) {


        if (b.x == a.x && c.x == a.x && d.x == a.x ||
                b.y == a.y && c.y == a.y && d.y == a.y) {
            return false;
        }

        return Line2D.linesIntersect(
                a.x, a.y,
                b.x, b.y,
                c.x, c.y,
                d.x, d.y
        );
    }

    @AllArgsConstructor
    @Builder
    static class Square {
        Point corner1;
        Point corner2;
    }

    @AllArgsConstructor
    @Builder
    static class Line {
        Point p1;
        Point p2;
    }

    static class Point implements Comparable<Point> {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public Point(String s) {
            String[] split = s.split(",");
            x = Integer.parseInt(split[0].trim());
            y = Integer.parseInt(split[1].trim());
        }

        double getAreaIfBigger(double maxArea, Point p) {
            double newArea = calcArea(p);
            if (newArea > maxArea) {
                log.info("New max area is: {} for points {} & {}", newArea, this, p);

                return newArea;
            }
            return maxArea;
        }

        double calcArea(Point p) {
            return (abs((p.x - this.x)) + 1.0) * (abs(p.y - this.y) + 1.0) * 1.0;
        }

        @Override
        public int compareTo(Point o) {
            return Double.compare(this.x, o.x);
        }

        @Override
        public String toString() {
            return "[x=" + x + ",y=" + y + "]";
        }
    }

}
