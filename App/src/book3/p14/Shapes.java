package src.book3.p14;

import java.util.ArrayList;
import java.util.List;

import src.book3.Print;

public class Shapes {
    public static void main(String[] args) {
        Print.println("shapes");
        List<Shape> shapeList = new ArrayList<>();
        shapeList.add(new Circle(1));
        shapeList.add(new Triangle(1));
        shapeList.add(new Circle(2));
        shapeList.add(new Triangle(2));
        for (Shape shape : shapeList) {
            shape.draw();
        }
    }
}

abstract class Shape {
    public void draw() {
        Print.println(this + ".draw");
    }

    abstract public String toString();
}

class Circle extends Shape {
    private int id;

    public Circle(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Circle" + id;
    }
}

class Triangle extends Shape {
    private int id;

    public Triangle(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Triangle" + id;
    }
}