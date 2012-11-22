package com.cybersoft;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cybersoft.entities.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Date;

public class InitActivity extends Activity {

    private final static String virtualsoccerSiteUrl = "http://www.virtualsoccer.ru/managerzone.php?";

    private VSDataModel dataModel;
    private Config config;

    DialogInterface.OnClickListener finishOnClick = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            finish();
        }
    };

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        config = new Config("test", "test");
        loadVCData(config);
        fillView();
    }

    private void fillView() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.played_matches);
        for (PlayedMatch playedMatch : dataModel.getPlayedMatchList()) {
            TextView playedMatches = new TextView(this);
            playedMatches.setText(playedMatch.toString());
            playedMatches.setId(playedMatch.hashCode());
            playedMatches.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(playedMatches);
        }
    }

    private void fireNotification(String title, String message, DialogInterface.OnClickListener listener) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton("OK", listener);
        //alertDialog.setIcon(R.drawable.icon);
        alertDialog.show();
    }

    private boolean loadVCData(Config config) {
        try {
            Connection connection = Jsoup.connect(virtualsoccerSiteUrl);
            connection.data("login_n", config.getLogin());
            connection.data("login_p", config.getPassword());
            Document doc = connection.post();

            Elements playedMatcheHRefs = doc.select("a.mnuh[href~=viewmatch]");
            if (playedMatcheHRefs.size() == 0) {
                fireNotification("Error", "Incorrect login or password. Click to Exit.", finishOnClick);
                return false;
            }

            dataModel = new VSDataModel();
            dataModel.setServerDateTime(new DateTime(new Date()));

            for (Element href : playedMatcheHRefs) {
                Element playedMatch = href.parent();
                dataModel.addFinishedMatch(new PlayedMatch(playedMatch.text()));
            }

            return true;
        } catch (IOException exc) {
            fireNotification("Error", "Can't access virtualsoccer.ru, check your Internet Connection. Click to Exit.", finishOnClick);
            return false;
        }
    }
}
