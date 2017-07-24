package de.slg.gianni.piapp;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

class SyncTask extends AsyncTask<NumberType, Void, Void> {

    private ProgressBar p;
    private Context c;

    SyncTask(ProgressBar p, Context c) {

        this.p = p;
        this.c = c;

    }

    @Override
    protected Void doInBackground(NumberType... params) {

        BufferedReader in = null;
        Queue<Integer> numbers = new Queue<>();

        try {
            URL interfaceDB = new URL("http://www.pibel.de/");

            in = null;
            in = new BufferedReader(new InputStreamReader(interfaceDB.openStream()));
            String inputLine;
            String result = "";
            while (!(inputLine = in.readLine()).contains("3. 1 4"));

            result+=inputLine;

            while ((inputLine = in.readLine()) != null) {
                if (inputLine.contains("</b>"))
                    break;
                result += inputLine;
            }

            result = result.replace(" ", "");
            result = result.replace(".", "");
            result = result.replaceFirst("3", "");
            for (int i = 0; i < result.length(); i += 2) {

                numbers.enqueue(Integer.parseInt(result.charAt(i) + "" + result.charAt(i + 1)));

            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        DisplayActivity.numbers = numbers;
        return null;
    }

    @Override
    protected void onPostExecute(Void v) {

        Toast.makeText(c, c.getString(R.string.sync_done), Toast.LENGTH_SHORT).show();
        p.setVisibility(View.GONE);

    }
}
