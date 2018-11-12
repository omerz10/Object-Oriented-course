package sprites;

import game.GameLevelAlien;
import geometrics.Point;
import geometrics.Rectangle;
import listeners.HitListener;
import listeners.ScoreTrackingListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * AliensArmy.
 */
public class AliensArmy {
    private List<List<Alien>> army;
    private Point upperleftCorner;
    private Point upperRigthCorner;
    private int leftLimit;
    private int rightLimit;
    private double speed;
    private double changingSpeed;
    private static final int COLUMNS = 10;
    private static final int ROWS = 5;
    public static final int ALIEN_WIDTH = 40;
    public static final int ALIEN_HEIGHT = 30;
    public static final int SPACE = 10;

    /**
     * Contruct AliensArmy with boarders and speed.
     *
     * @param leftL  left layout of the gui
     * @param rightL right layout of the gui
     * @param speed  speed of army
     */
    public AliensArmy(int leftL, int rightL, double speed) {
        this.army = new ArrayList<List<Alien>>(50);
        this.leftLimit = leftL;
        this.rightLimit = rightL;
        this.speed = speed;
        this.changingSpeed = speed;
    }

    /**
     * Creates all Aliens in the army.
     */
    public void createArmy() {
        for (int i = 0; i < COLUMNS; i++) {
            List<Alien> column = new ArrayList<Alien>();
            for (int j = 0; j < ROWS; j++) {
                Alien alien = new Alien(160 + i * (ALIEN_WIDTH + SPACE), 70 + j * (ALIEN_HEIGHT + SPACE));
                column.add(alien);
            }
            this.army.add(column);
        }
    }

    /**
     * Moves the army to right side according to given speed.
     *
     * @param s speed
     */
    public void moveRight(double s) {
        double dx = s;
        double dy = 0;
        for (int i = 0; i < this.army.size(); i++) {
            for (int j = 0; j < this.army.get(i).size(); j++) {
                this.army.get(i).get(j).moveAlien(dx, dy);
            }
        }
    }

    /**
     * Moves the army to left side according to given speed.
     *
     * @param s speed
     */
    public void moveLeft(double s) {
        double dx = s;
        double dy = 0;
        for (int i = 0; i < this.army.size(); i++) {
            for (int j = 0; j < this.army.get(i).size(); j++) {
                this.army.get(i).get(j).moveAlien(dx, dy);
            }
        }
    }

    /**
     * If the army collides with the right layout of the gui return true.
     * Otherwise return false.
     *
     * @param s speed
     * @return boolean
     */
    public boolean collisionRightLimit(double s) {
        this.upperRigthCorner = this.army.get(this.army.size() - 1).get(0).getCollisionRectangle().getUpperLeft();
        if (upperRigthCorner.getX() + s > rightLimit - ALIEN_WIDTH) {
            return true;
        }
        return false;
    }

    /**
     * Moves army down- just in Y axe.
     */
    public void moveDownRightSide() {
        for (List<Alien> col : this.army) {
            for (Alien tAlien : col) {
                double yVal = tAlien.getCollisionRectangle().getUpperLeft().getY();
                tAlien.getCollisionRectangle().getUpperLeft().setY(yVal + SPACE + ALIEN_HEIGHT);
            }
        }
        changingSpeed *= -1.1;
    }

    /**
     * If the army collides with the left layout of the gui return true.
     * Otherwise return false.
     *
     * @param s speed
     * @return boolean
     */
    public boolean collisionLeftLimit(double s) {
        this.upperleftCorner = this.army.get(0).get(0).getCollisionRectangle().getUpperLeft();
        if (upperleftCorner.getX() + s < leftLimit) {
            return true;
        }
        return false;
    }

    /**
     * Moves army down- just in Y axe.
     */
    public void moveDownLeftSide() {
        for (List<Alien> col : this.army) {
            for (Alien tAlien : col) {
                double yVal = tAlien.getCollisionRectangle().getUpperLeft().getY();
                tAlien.getCollisionRectangle().getUpperLeft().setY(yVal + ALIEN_HEIGHT);
            }
        }
        changingSpeed *= -1.1;
    }

    /**
     * @param dt dt
     */
    public void timePassed(double dt) {
        double newSpeed = dt * changingSpeed;
        if (newSpeed > 0) {
            // army touches right limit
            if (collisionRightLimit(newSpeed)) {
                moveDownRightSide();
            } else {
                this.moveRight(newSpeed);
            }
        } else {
            // army touches left limit
            if (collisionLeftLimit(newSpeed)) {
                moveDownLeftSide();
            } else {
                this.moveLeft(newSpeed);
            }
        }
    }

    /**
     * Add all Aloens in the army to the game as listeners and to game's environment.
     *
     * @param g          GameLevelAlien
     * @param ar         AnimationRunner
     * @param stListener ScoreTrackingListener
     */
    public void addToGame(GameLevelAlien g, HitListener ar, ScoreTrackingListener stListener) {
        for (int i = 0; i < COLUMNS; i++) {
            for (int j = 0; j < ROWS; j++) {
                this.army.get(i).get(j).addToGame(g);
                this.army.get(i).get(j).addHitListener(ar);
                this.army.get(i).get(j).addHitListener(stListener);
            }
        }
    }

    /**
     * Return lowest y value of the lowest Alien.
     *
     * @return lowest y value
     */
    public double getLowestValueY() {
        double lowestY = 0;
        for (int i = 0; i < this.army.size(); i++) {
            List<Alien> column = this.army.get(i);
            Rectangle rect = column.get(column.size() - 1).getCollisionRectangle();
            double lowerLine = rect.getUpperLeft().getY() + rect.getHeight();
            if (lowerLine > lowestY) {
                lowestY = lowerLine;
            }
        }
        return lowestY;
    }

    /**
     * Reposition army to the initial location.
     */
    public void resetPosition() {
        this.changingSpeed = this.speed;
        Point rightestPoint = null;
        for (int i = 0; i < this.army.size(); i++) {
            for (int j = 0; j < this.army.get(i).size(); j++) {
                this.army.get(i).get(j).goStartPosition();
                rightestPoint = new Point(this.army.get(i).get(j).getCollisionRectangle().getUpperLeft().getX(),
                        this.army.get(i).get(j).getCollisionRectangle().getUpperLeft().getY());
            }
        }
        for (List<Alien> column : this.army) {
            if (column.isEmpty()) {
                this.army.remove(column);
            }
        }
        this.upperRigthCorner = rightestPoint;
        this.upperleftCorner = this.army.get(0).get(0).getCollisionRectangle().getUpperLeft();
        double deltaFromLeftX = this.upperleftCorner.getX() - 170;
        if (deltaFromLeftX > 0) {

            for (int i = 0; i < this.army.size(); i++) {
                for (int j = 0; j < this.army.get(i).size(); j++) {
                    this.army.get(i).get(j).moveAlien(-deltaFromLeftX, 0);
                    rightestPoint = new Point(this.army.get(i).get(j).getCollisionRectangle().getUpperLeft().getX(),
                            this.army.get(i).get(j).getCollisionRectangle().getUpperLeft().getY());
                }
            }
            this.upperRigthCorner = rightestPoint;
            this.upperleftCorner = this.army.get(0).get(0).getCollisionRectangle().getUpperLeft();
        }
    }

    /**
     * While hitting the Aliens with player's ball- clear Alien from army.
     *
     * @param alien an Alien
     */
    public void clearColumns(Alien alien) {
        for (List<Alien> column : this.army) {
            if (column.contains(alien)) {
                column.remove(alien);
                if (column.size() == 0) {
                    this.army.remove(column);
                }
                break;
            }
        }
    }

    /**
     * Return a point of a random Alien.
     *
     * @return a Point
     */
    public Point chooseRandomAlien() {
        Random rand = new Random();
        int columns = this.army.size();
        int randCol = rand.nextInt(columns);
        List<Alien> aliens = this.army.get(randCol);
        Alien alienS = aliens.get(aliens.size() - 1);
        Rectangle rectangle = alienS.getCollisionRectangle();
        Point point = new Point(rectangle.getUpperLeft().getX() + (rectangle.getWidth() / 2),
                rectangle.getUpperLeft().getY() + (rectangle.getHeight()) + 10);
        return point;
    }
}
