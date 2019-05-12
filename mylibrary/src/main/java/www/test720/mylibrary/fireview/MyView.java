package www.test720.mylibrary.fireview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;
import java.util.Vector;

import www.test720.mylibrary.R;
import www.test720.mylibrary.dot.Dot;
import www.test720.mylibrary.dot.DotFactory;
import www.test720.mylibrary.dot.LittleDot;


public class MyView extends View {

    public static final int ID_SOUND_UP = 0;
    public static final int ID_SOUND_BLOW = 1;
    public static final int ID_SOUND_MULTIPLE = 2;
    final static int TIME = 8; // 圈数

    private Vector<Dot> lList = new Vector<Dot>();

    LittleDot[] ld = new LittleDot[200];
    private DotFactory df = null;

    boolean running = false;


    Context mContext;

    public static SoundPlay soundPlay;

    public MyView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        df = new DotFactory();
        new MyThread().start();
        mContext = context;
        initSound(mContext);

    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        df = new DotFactory();
        new MyThread().start();
        mContext = context;
        initSound(mContext);
    }

    public static void initSound(Context context) {
        soundPlay = new SoundPlay();
        soundPlay.initSounds(context);
        soundPlay.loadSfx(context, R.raw.up, ID_SOUND_UP);
        soundPlay.loadSfx(context, R.raw.blow, ID_SOUND_BLOW);
        soundPlay.loadSfx(context, R.raw.multiple, ID_SOUND_MULTIPLE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        synchronized (lList) {
            for (int i = 0; i < lList.size(); i++) {
                lList.get(i).myPaint(canvas, lList);
            }
        }
        invalidate();
    }

  /*  @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (event.getY() < 400) {
                Dot dot = null;
                int rand = (int) (Math.random() * 99);

                dot = df.makeDot(mContext, rand, (int) event.getX(),
                        (int) event.getY());
                synchronized (lList) {
                    lList.add(dot);
                    soundPlay.play(ID_SOUND_UP, 0);
                }
            }
        }
        return true;
    }*/

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }


    class MyThread extends Thread {
        // 新建一个进程类来处理重画
        // 用于控制烟火在空中滞留的时间
        int times = 0;

        public void run() {
            Dot dot = null;
            while (running) {

                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    System.out.println(e);
                }

                synchronized (lList) {
                    // 防止画面的烟花个数多于50个
                    while (lList.size() > 50) {
                        System.out.println("当前数目超过50");
                        for (int i = 0; i < 10; i++) {
                            lList.remove(i);
                        }
                    }

                    // 自动添加烟火
                    if (lList.size() <= 2) {
                        Dot tmp = null;
                        int rand = (int) (Math.random() * 99);
                        Random random = new Random();
                        tmp = df.makeDot(mContext, rand, random.nextInt(480),
                                50 + random.nextInt(300));
                        lList.add(tmp);
                    }

                }

                for (int i = 0; i < lList.size(); i++) {
                    dot = (Dot) lList.get(i);
                    if (dot.state == 1 && !dot.whetherBlast()) {
                        dot.rise();
                    }
                    // 如果是whetherBlast()返回的是true，那么就把该dot的state设置为2
                    else if (dot.state == 1 && dot.state != 2) {
                        dot.state = 2;
                        soundPlay.play(ID_SOUND_BLOW, 0);
                    } else if (dot.state == 3) {

                    }
                    // 规定，每个爆炸点最多是TIME圈，超过就会消失
                    if (dot.circle >= TIME) {
                        // 在空中滞留一秒才消失
                        if (times >= 10) {
                            dot.state = 4;
                            times = 0;
                        } else {
                            times++;
                        }
                        // dot.state = 4;
                    }
                }
            }
        }
    }
}
