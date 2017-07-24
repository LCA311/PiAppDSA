package de.slg.gianni.piapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    public static Queue<Integer> numbers;
    private Vector current;

    public void onCreate(Bundle b) {

        super.onCreate(b);
        setContentView(R.layout.display_activity);

        Toolbar t = (Toolbar) findViewById(R.id.toolbar);
        t.setTitle("Nachtwanderung Extended");
        t.setTitleTextColor(Color.WHITE);

        setSupportActionBar(t);
        t.setKeepScreenOn(true);

        ImageButton b1 = (ImageButton) findViewById(R.id.button_next);
        ImageButton b2 = (ImageButton) findViewById(R.id.button_skip);
        ImageButton b3 = (ImageButton) findViewById(R.id.button_reset);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                next();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

    }

    private void reset() {
        /*
        ((TextView) findViewById(R.id.text_view_direction)).setText(String.valueOf(before.direction.toString().charAt(0)).toUpperCase());
        ((TextView) findViewById(R.id.text_view_number)).setText(String.valueOf(before.number));
        */
    }

    private void next() {

        if (numbers == null || numbers.isEmpty())
            return;

        int number;
        Direction d;

        number = numbers.front();
        numbers.dequeue();
        d = Direction.values()[number % 4];

        current = new Vector(d, number);

        ((TextView) findViewById(R.id.text_view_direction)).setText(String.valueOf(d.toString().charAt(0)).toUpperCase());
        ((TextView) findViewById(R.id.text_view_number)).setText(String.valueOf(number));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem i) {

        if (numbers != null)
            return false;

        ProgressBar p = (ProgressBar) findViewById(R.id.progress);

        p.setVisibility(View.VISIBLE);
        new SyncTask(p, getApplicationContext()).execute();

        return true;
    }

}
