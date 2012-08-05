package ch.genidea;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created with IntelliJ IDEA.
 * User: marco
 * Date: 7/16/12
 * Time: 9:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class TextSprite{
    TextPair textPair;
    protected int x = 0;
    protected int y = 0;
    protected int xSpeed = 0;
    protected int ySpeed = 0;

    private Paint paintString;
    protected String stringToShow;

    public TextSprite(DrawingPanel gameView, Bitmap bmp, TextPair textPair) {
        this.textPair = textPair;
        this.stringToShow = textPair.getLanguage1();
        this.x = 100;
        this.y = 100;
        paintString = new Paint();
        paintString.setColor(Color.WHITE);
        paintString.setStyle(Paint.Style.FILL);
        paintString.setTextSize(12);

    }

    public void start(){
        ySpeed = 1;
    }

    public TextSprite(DrawingPanel gameView, TextPair textPair, int startX, int startY){
       this(gameView, null, textPair);
        x=startX;
        y=startY;
    }

    public void onDraw(Canvas canvas) {
        update();
        canvas.drawText(stringToShow, x, y, paintString);
        canvas.drawText(textPair.getLanguage2(), x, 700, paintString);
    }

    private void update() {
      x += xSpeed;
      y += ySpeed;
    }
}

