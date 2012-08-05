package ch.genidea;

import android.graphics.*;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: marco
 * Date: 7/14/12
 * Time: 6:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class Sprite {
    // direction = 0 up, 1 left, 2 down, 3 right,
    // animation = 3 back, 1 left, 0 front, 2 right
    int[] DIRECTION_TO_ANIMATION_MAP = { 3, 1, 0, 2 };
    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 3;
    protected int x = 0;
    protected int y = 0;
    private int xDirectionalSpeed = 0;
    private int yDirectionalSpeed = 0;
    private int xSpeed = 5;
    private DrawingPanel gameView;
    private Bitmap bmp;
    private int currentFrame = 0;
    private int width;
    private int height;
    private int ySpeed;
    private int xTargetDestination = -1;
    private int yTargetDestination = -1;
    private String stringToShow;
    private Paint paintString;

    public Sprite(DrawingPanel gameView, Bitmap bmp) {
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
        this.gameView = gameView;
        this.bmp = bmp;

        Random rnd = new Random(System.currentTimeMillis());
        //xSpeed = rnd.nextInt(50) - 5;
        //ySpeed = rnd.nextInt(50) - 5;
        xSpeed = 2;
        ySpeed = 2;

         paintString = new Paint();
        paintString.setColor(Color.WHITE);
        paintString.setStyle(Paint.Style.FILL);
        paintString.setTextSize(12);
    }

    public Sprite (DrawingPanel gameView, String stringToShow){
        this.gameView = gameView;

        Random rnd = new Random(System.currentTimeMillis());
        //xSpeed = rnd.nextInt(50) - 5;
        //ySpeed = rnd.nextInt(50) - 5;
        xSpeed = 2;
        ySpeed = 2;

        paintString = new Paint();
        paintString.setColor(Color.WHITE);
        paintString.setStyle(Paint.Style.FILL);
        paintString.setTextSize(12);

    }

    public void setTargetDestination(int x, int y){
      xTargetDestination = x;
      yTargetDestination = y;
    }


    private void update() {
        if (xTargetDestination > -1 ){
            if (x >= xTargetDestination){
                xDirectionalSpeed = -xSpeed;
            }
            else xDirectionalSpeed = xSpeed;


            if(y >= yTargetDestination){
                yDirectionalSpeed = -ySpeed;
            }
            else yDirectionalSpeed = ySpeed;

        }

        if (x >= gameView.getWidth() - width - xSpeed || x + xSpeed <= 0) {
            xDirectionalSpeed = -xSpeed;
        }
        x = x + xDirectionalSpeed;
        if (y >= gameView.getHeight() - height - ySpeed || y + ySpeed <= 0) {
            yDirectionalSpeed = -ySpeed;
        }
        y = y + yDirectionalSpeed;
        currentFrame = ++currentFrame % BMP_COLUMNS;
    }

    public void onDraw(Canvas canvas) {
       if (!isTargetDestination()){
        update();
       }
        int srcX = currentFrame * width;
        int srcY = getAnimationRow() * height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + width, y + height);
        canvas.drawBitmap(bmp, src, dst, null);
        if (stringToShow != null) {


        canvas.drawText(stringToShow, x+10, y - 10, paintString);
        }
    }

    private boolean isTargetDestination(){
        if( xTargetDestination > 0){
            if (Math.abs(xTargetDestination - x) < 5 && Math.abs((yTargetDestination -y) )<5)
           return true;
        }
        return false;
    }
    // direction = 0 up, 1 left, 2 down, 3 right,
    // animation = 3 back, 1 left, 0 front, 2 right
    private int getAnimationRow() {
       if (isTargetDestination())
                return DIRECTION_TO_ANIMATION_MAP[2];


        double dirDouble = (Math.atan2(xDirectionalSpeed, yDirectionalSpeed) / (Math.PI / 2) + 2);
        //double dirDouble = (Math.atan2(xTargetDestination-x, yTargetDestination-y) / (Math.PI / 2) + 2);

        int direction = (int) Math.round(dirDouble) % BMP_ROWS;
        return DIRECTION_TO_ANIMATION_MAP[direction];
    }


    public String getStringToShow() {
        return stringToShow;
    }

    public void setStringToShow(String stringToShow) {
        this.stringToShow = stringToShow;
    }

    public boolean isCollision(float x2, float y2) {
        return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
    }
    public boolean isCollision(RectF bounds){
        if (bounds.contains(x,y)) return true;
        return false;

    }
}
