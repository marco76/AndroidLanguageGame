package ch.genidea;

import android.graphics.Canvas;

/**
 * Created with IntelliJ IDEA.
 * User: marco
 * Date: 7/14/12
 * Time: 6:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameLoopThread extends Thread{
    private DrawingPanel view;
    private boolean running = false;

    public GameLoopThread(DrawingPanel view) {
        this.view = view;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        while (running) {
            Canvas c = null;
            try {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {
                    view.onDraw(c);
                }
            } finally {
                if (c != null) {
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
        }
    }
}
