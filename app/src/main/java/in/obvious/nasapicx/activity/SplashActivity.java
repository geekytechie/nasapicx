package in.obvious.nasapicx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import in.obvious.nasapicx.R;

public class SplashActivity extends AppCompatActivity implements MotionLayout.TransitionListener {

    private MotionLayout motionSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        init();
    }

    private void init(){
        motionSplash = findViewById(R.id.motionSplash);
        motionSplash.addTransitionListener(this);
    }


    @Override
    public void onTransitionStarted(MotionLayout motionLayout, int i, int i1) {

    }

    @Override
    public void onTransitionChange(MotionLayout motionLayout, int i, int i1, float v) {

    }

    @Override
    public void onTransitionCompleted(MotionLayout motionLayout, int i) {
        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        finish();
    }

    @Override
    public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {

    }
}