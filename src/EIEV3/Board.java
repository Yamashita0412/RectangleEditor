package EIEV3;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Rectangle;

public class Board {
  private ArrayList<Myrectangle> rectangles;
  private Rectangle boardRectangle;
  private int limitRectangleSize;
  private Color boardColor;

  Board(int limitRectangleSize, int widthCUI, int heightCUI) {
    this.rectangles = new ArrayList<Myrectangle>();
    this.limitRectangleSize = limitRectangleSize;
    this.boardRectangle = new Rectangle(widthCUI, heightCUI);
    this.boardColor = Color.white;
  }

  ArrayList<Myrectangle> getRectangles() {
    return this.rectangles;
  }

  void add(Myrectangle rect) {
    rectangles.add(rect);
  }

  Myrectangle getRectangle(int index) {
    return this.rectangles.get(index);
  }

  Rectangle getBoardRectangle() {
    return this.boardRectangle;
  }

  boolean checkIndex(int index) {
    if (0 <= index && index < rectangles.size())
      return true;
    System.out.println("範囲外の数値です。");
    return false;
  }

  boolean checkLimitSize() {
    if (rectangles.size() < this.limitRectangleSize)
      return true;
    System.out.println("Rectangle最大数を超えるため追加できません！！");
    return false;
  }

  void remove(int index) {
    rectangles.remove(index);
    System.out.println("delete完了");
  }

  void removeAll() {
    rectangles.removeAll(rectangles);
    System.out.println("deleteAll完了");
  }


  public String toString() {
    ArrayList<Myrectangle> rectangles = getRectangles();
    List<Myrectangle> list = rectangles;
    String currentBoardStatus = "=============================現在のボード状態=============================\n";
    int i = 0;
    for (Myrectangle rect : list ){
      currentBoardStatus += i++ + ":" + rect.toString() + "\n";
    }
    currentBoardStatus += "==========================================================================\n";
    return currentBoardStatus;
  }
}