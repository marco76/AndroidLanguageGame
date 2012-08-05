package ch.genidea;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marco
 * Date: 7/14/12
 * Time: 3:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class DrawingPanel extends SurfaceView implements SurfaceHolder.Callback{

    private Canvas canvas;
    private Bitmap bmp;
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private Sprite sprite;
    private Ball ball;
    private Bitmap grass;
    private List<TextSprite> textSpriteList;
    private List<TextSprite> textSpriteBottom;

    public DrawingPanel(Context context){
        super (context);
        ArrayList<TextPair> texts = new ArrayList<TextPair>();
        texts.add(new TextPair("gomb", "button"));
        texts.add(new TextPair("szem", "eye"));
        textSpriteList = new ArrayList<TextSprite>();

        textSpriteList.add(new TextSprite(this, texts.get(0), 100, 100));
        textSpriteList.add(new TextSprite(this, texts.get(1), 200, 100));

        textSpriteList.get(0).start();
        textSpriteList.get(1).start();


        getHolder().addCallback(this);
        gameLoopThread = new GameLoopThread(this);
        holder = getHolder();
        loadResources();

        setFocusable(true);
        grass = BitmapFactory.decodeResource(getResources(),R.drawable.green_grass);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.princess);

        sprite = new Sprite(this,bmp);
        sprite.setStringToShow("Detti");
        ball = new Ball(this.getContext());

        ball.setTextToShow("I love\nDetti");




    }

    private void loadResources() {
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        gameLoopThread.setRunning(true);
        gameLoopThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        gameLoopThread.setRunning(false);
        while (retry) {
            try {
                gameLoopThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event){
        //draw text at touch
        try {
            canvas = getHolder().lockCanvas();
            synchronized (getHolder()) {
                if (event.getAction() == MotionEvent.ACTION_DOWN
                        || event.getAction() == MotionEvent.ACTION_MOVE) {

                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                 sprite.setTargetDestination((int) event.getX(), (int) event.getY());



        }}} finally {
            if (canvas != null) {
                getHolder().unlockCanvasAndPost(canvas);
            }
        }
        // event was handled
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
      // this.setBackgroundResource(R.drawable.green_grass);

        canvas.drawColor(Color.BLACK);
        // if(ball.isToRedraw())
            ball.onDraw(canvas);
        sprite.onDraw(canvas);
        if (sprite.isCollision(ball.getBallBounds())){
            ball.setTextToShow("CATCHED");
        }
        for (TextSprite textSprite : textSpriteList){
            textSprite.onDraw(canvas);
        }


       // canvas.restore();


    }


}
