package EIEV3;


import java.awt.Color;

public class Command {
  private int index;
  private Board board;
  private Input in;
  private Myrectangle rect;

  Command(Board board) {
    this.board = board;
    this.in = new Input();
  }

  Color selectColor() {
    while (true) {
      System.out.println("1:red\n2:blue\n3:yellow\n4:gray");
      switch (in.inputInteger("Rectangleの色を選択:")) {
        case 1:
          return Color.red;
        case 2:
          return Color.blue;
        case 3:
          return Color.yellow;
        case 4:
          return Color.gray;
        default:
          System.out.println("範囲外の入力です。");
      }
    }
  }

  void create(Color rectangleColor) {
    this.rect = new Myrectangle(in.inputInteger("width:"), in.inputInteger("height:"), in.inputInteger("x:"), in.inputInteger("y:"), rectangleColor, this.board.getBoardRectangle());
    addBoard();
  }

  void addBoard() {
    displayRectangle(rect);
    if (this.rect.checkBoundaryValue() && this.rect.checkRectangleAreas(this.board.getRectangles()) && this.board.checkLimitSize())
      board.add(this.rect);
  }

  boolean inputIndex() {
    this.index = in.inputInteger("Rectangleをindex番号で選択:");
    if (board.checkIndex(this.index)) {
      this.rect = board.getRectangle(this.index);
      return true;
    }
    return false;
  }

  void move() {
    Myrectangle rectDammy = moveDammy();
    changeRectangle(rectDammy);
  }

  private Myrectangle moveDammy() {
    int moveX = in.inputInteger("x方向:");
    int moveY = in.inputInteger("y方向:");
    Myrectangle rectDammy = new Myrectangle(this.rect.getRectangle(), this.rect.getColor(), this.board.getBoardRectangle());
    rectDammy.move(moveX, moveY);
    return rectDammy;
  }

  void expandShrink() {
    Myrectangle rectDammy = expandShrinkDammy();
    changeRectangle(rectDammy);
  }

  private Myrectangle expandShrinkDammy() {
    float mx = in.inputFloat("width倍率:");
    float my = in.inputFloat("height倍率:");
    Myrectangle rectDammy = new Myrectangle(this.rect.getRectangle(), this.rect.getColor(), this.board.getBoardRectangle());
    rectDammy.expandShrink(mx, my);
    return rectDammy;
  }

  void changeRectangle(Myrectangle rectDammy) {
    displayRectangle(rectDammy);
    if (rectDammy.checkRectangleAreas(this.board.getRectangles()) && rectDammy.checkBoundaryValue())
      this.rect.setRectangle(rectDammy.getRectangle());
  }

  void delete() {
    this.board.remove(this.index);
  }

  void deleteAll() {
    this.board.removeAll();
  }

  void intersect() {
    if (inputIndex()) {
      Myrectangle intersectRectangle = this.rect;
      if (inputIndex()) {
        if (this.rect.intersects(intersectRectangle)) {
          this.rect = new Myrectangle(this.rect.intersection(intersectRectangle),
              changeColor(intersectRectangle.getColor(), rect.getColor()),
              this.board.getBoardRectangle());
          addBoard();
        } else {
          System.out.println("交差していません。");
        }
      }
    }
  }

  Color changeColor(Color color1, Color color2) {
    if (color1.equals(color2))
      return Color.gray;
    else if ((color1.equals(Color.yellow) && color2.equals(Color.blue)) ||
        (color1.equals(Color.blue) && color2.equals(Color.yellow)))
      return Color.green;
    else if ((color1.equals(Color.red) && color2.equals(Color.yellow)) ||
        (color1.equals(Color.yellow) && color2.equals(Color.red)))
      return Color.orange;
    else if ((color1.equals(Color.red) && color2.equals(Color.blue)) ||
        (color1.equals(Color.blue) && color2.equals(Color.red)))
      return Color.magenta;
    else
      return Color.cyan;
  }

  void displayRectangle(Myrectangle rect) {
    System.out.println("成果物 ==>  " + rect.toString());
  }

}