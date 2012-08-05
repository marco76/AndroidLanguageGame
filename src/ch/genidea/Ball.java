package ch.genidea;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: marco
 * Date: 7/14/12
 * Time: 5:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Ball extends View {
    private int xMin = 0;
    private int xMax;
    private int yMin = 0;
    private int yMax;
    private float ballRadius = 20;
    private float ballX = ballRadius + 20;  // Ball's center (x,y)
    private float ballY = ballRadius + 40;
    private float ballSpeedX = 0;  // Ball's speed (x,y)
    private float ballSpeedY = 0;
    private RectF ballBounds;      // Needed for Canvas.drawOval
    private Paint paint;           // The paint (e.g. style, color) used for drawing
    private String textToShow = null;
    private boolean toRedraw = true;

    public Ball(Context context) {
        super(context);

        ballBounds = new RectF();
        ballX = new Random().nextInt(600);
        ballY = new Random().nextInt(600);
        paint = new Paint();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        toRedraw = false;
        // Draw the ball
        ballBounds.set(ballX-ballRadius, ballY-ballRadius, ballX+ballRadius, ballY+ballRadius);
          paint.setColor(Color.BLUE);
        canvas.drawOval(ballBounds, paint);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(12);
        int i= 0;
        for(String line: textToShow.split("\n")){
            canvas.drawText(line, ballX-ballRadius + 5, ballY +10*i++, paint);
            //y+=mTextPaint.ascent()+mTextPaint.decent();
        }
        //canvas.drawText(textToShow, ballX-ballRadius/2,ballY,paint);

        // Update the position of the ball, including collision detection and reaction.
        update();


       // invalidate();  // Force a re-draw
    }

    // Detect collision and update the position of the ball.
    private void update() { /*
        // Get new (x,y) position
        ballX += ballSpeedX;
        ballY += ballSpeedY;
        // Detect collision and react
        if (ballX + ballRadius > xMax) {
            ballSpeedX = -ballSpeedX;
            ballX = xMax-ballRadius;
        } else if (ballX - ballRadius < xMin) {
            ballSpeedX = -ballSpeedX;
            ballX = xMin+ballRadius;
        }
        if (ballY + ballRadius > yMax) {
            ballSpeedY = -ballSpeedY;
            ballY = yMax - ballRadius;
        } else if (ballY - ballRadius < yMin) {
            ballSpeedY = -ballSpeedY;
            ballY = yMin + ballRadius;
        }    */
    }

    // Called back when the view is first created or its size changes.
    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        // Set the movement bounds for the ball
        xMax = w-1;
        yMax = h-1;
    }

    public String getTextToShow() {
        return textToShow;
    }

    public void setTextToShow(String textToShow) {
        this.textToShow = textToShow;
    }

    public RectF getBallBounds(){
        return ballBounds;
    }


    public boolean isToRedraw() {
        return toRedraw;
    }

    public void setToRedraw(boolean toRedraw) {
        this.toRedraw = toRedraw;
    }
}

