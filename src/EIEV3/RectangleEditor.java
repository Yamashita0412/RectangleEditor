/**
 * @author Yamashita Kyonosuke
 * @version 3.0
 */

package EIEV3;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Color;


public class RectangleEditor extends Applet implements Runnable {
  volatile Thread th = null;
  private DisplayCanvas display;
  private int APPLETWIDTH;
  private int APPLETHEIGHT;
  private int limitRectangleSize;
  private Input in;
  private Board board;
  private Command cm;
  private RectangleEditor re;

  public void init() {
    APPLETWIDTH = getSize().width;
    APPLETHEIGHT = getSize().height;
    limitRectangleSize = 10;
    board = new Board(limitRectangleSize, APPLETWIDTH, APPLETHEIGHT);
    display = new DisplayCanvas(this, board);
    cm = new Command(board);
    in = new Input();
    setBackground(Color.white);
    this.setVisible(true);
  }

  public void start() {
    if (th == null) {
      th = new Thread(this);
      th.start();
    }
  }

  public void run() {
    Thread thisThread = Thread.currentThread();
    while (th == thisThread) {
      operationListOutput();
      selectAction(in.inputInteger("操作名を数字で入力:"));
    }
    th = null;
  }

  public void stop() {
    th = null;
  }

  public void paint(Graphics g) {
    this.display.display(g);
  }


  void operationListOutput() {
    String text = "******************\n1:create\n2:move\n3:expand/shrink\n4:delete\n5:deleteAll\n6:intersect\n7:exit\n8:displayBoard\n******************";
    System.out.println(text);
  }

  void selectAction(int choiceCommand) {
    switch (choiceCommand) {
      case 1:
        cm.create(cm.selectColor());
        displayBoard();
        break;
      case 2:
        displayBoard();
        if (cm.inputIndex())
          cm.move();
        displayBoard();
        break;
      case 3:
        displayBoard();
        if (cm.inputIndex())
          cm.expandShrink();
        displayBoard();
        break;
      case 4:
        displayBoard();
        if (cm.inputIndex())
          cm.delete();
        displayBoard();
        break;
      case 5:
        cm.deleteAll();
        displayBoard();
        break;
      case 6:
        displayBoard();
        cm.intersect();
        displayBoard();
        break;
      case 7:
        System.out.println("終了します。");
        stop();
        break;
      case 8:
        displayBoard();
        break;
      default:
        System.out.println("範囲外の入力です。");
    }
  }

  void displayBoard() {
    repaint();
    System.out.println(board.toString());
  }
}