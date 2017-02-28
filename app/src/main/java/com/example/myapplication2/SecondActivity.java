package com.example.myapplication2;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("booleanExtra", true);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });


        findViewById(R.id.imageView).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder builder = new View.DragShadowBuilder(v);

                v.startDrag(data, builder, v, 0);
                v.setVisibility(View.INVISIBLE);

                return true;
            }
        });

        findViewById(R.id.left_ll).setOnDragListener(new MyDragListener());
        findViewById(R.id.right_ll).setOnDragListener(new MyDragListener());
    }

    class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();

            switch (action) {
                case DragEvent.ACTION_DROP:
                    View view = (View) event.getLocalState();
                    ViewGroup layout = (ViewGroup) view.getParent();
                    layout.removeView(view);

                    LinearLayout newLayout = (LinearLayout) v;
                    newLayout.addView(view);
                    view.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }

            return true;
        }
    }
}
