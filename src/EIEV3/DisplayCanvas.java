package EIEV3;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//class DisplayCanvas extends Canvas{
class DisplayCanvas extends Component {
  //public class DisplayCanvas extends Container {
  RectangleEditor parent;
  Board board;

  DisplayCanvas(RectangleEditor app, Board board) {
    this.parent = app;
    this.board = board;
  }

  public void display(Graphics g) {
    List<Myrectangle> list = getList();
    for (Myrectangle rect : list) {
      g.setColor(rect.getColor());
      g.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }
  }

  private List<Myrectangle> getList() {
    ArrayList<Myrectangle> rectangles = this.board.getRectangles();
    return (List<Myrectangle>) rectangles;
  }
}