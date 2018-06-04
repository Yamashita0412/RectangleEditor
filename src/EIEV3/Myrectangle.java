package EIEV3;

import java.lang.Math;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.applet.Applet;

public class Myrectangle {
  private Rectangle rectangle;
  private Rectangle boardRectangle;
  private Color rectangleColor;

  Myrectangle(int width, int height, int x, int y, Color rectangleColor, Rectangle boardRectangle) {
    this.rectangle = new Rectangle(x, y, width, height);
    this.rectangleColor = rectangleColor;
    this.boardRectangle = boardRectangle;
  }

  Myrectangle(Rectangle rect, Color rectangleColor, Rectangle boardRectangle) {
    this.rectangle = rect;
    this.rectangleColor = rectangleColor;
    this.boardRectangle = boardRectangle;
  }

  void setRectangle(Rectangle rectangle) {
    this.rectangle = rectangle;
  }

  Color getColor() {
    return this.rectangleColor;
  }

  Rectangle getRectangle() {
    return this.rectangle.getBounds();
  }

  int getX() {
    return (int) this.rectangle.getX();
  }

  int getY() {
    return (int) this.rectangle.getY();
  }

  int getWidth() {
    return (int) this.rectangle.getWidth();
  }

  int getHeight() {
    return (int) this.rectangle.getHeight();
  }

  boolean checkRectangleAreas(ArrayList<Myrectangle> rectangles) {
    List<Myrectangle> list = rectangles;
    for (Myrectangle rect : list) {
      if (this.rectangle.equals(rect.getRectangle())) {
        System.out.println("重複するRectangleがあります。");
        return false;
      }
    }
    return true;
  }

  boolean checkBoundaryValue() {
    if (this.boardRectangle.contains(this.rectangle))
      return true;
    System.out.println("Rectangleがボードに乗れない数値です!");
    return false;
  }

  boolean intersects(Myrectangle rect) {
    return this.rectangle.intersects(rect.getRectangle());
  }

  Rectangle intersection(Myrectangle rect) {
    return this.rectangle.intersection(rect.getRectangle());
  }


  void move(int moveX, int moveY) {
    this.rectangle.translate(moveX, moveY);
  }

  void expandShrink(float mx, float my) {
    this.rectangle.setSize(Math.round((float) this.rectangle.getWidth() * mx), Math.round((float) this.rectangle.getHeight() * my));
  }

  public String toString() {
    return ("width:" + (int) this.rectangle.getWidth() + " ,height:" + (int) this.rectangle.getHeight() + " ,x座標:" + (int) this.rectangle.getX() + " ,y座標:" + (int) this.rectangle.getY() + " ,Color:" + this.rectangleColor);
  }
}