package com.example.android.explicitintent;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.annotation.Nullable;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.net.URL;

public class ImplicitIntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent);
    }

    // open a website
    public void openWebsite(View v) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        final EditText editText = new EditText(this);
        alert.setTitle("Open Website");
        alert.setView(editText);

        alert.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Uri uri = Uri.parse(editText.getText().toString());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getBaseContext(), "Error, Invalid Website: " + editText.getText().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
        alert.show();
    }

    public void openLocation(View v)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        final EditText editText = new EditText(this);
        alert.setTitle("Search Location or address");
        alert.setView(editText);

        alert.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Uri.Builder builder = new Uri.Builder();
                builder.scheme("geo");
                builder.path("0,0");
                String strQuery = editText.getText().toString();
                builder.query(strQuery);
                Uri uri = builder.build();

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getBaseContext(), "Error, Invalid Website: " + editText.getText().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
        alert.show();
    }

    public void shareText(final View v)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        final EditText editText = new EditText(this);
        alert.setTitle("Share a Text");
        alert.setView(editText);

        alert.setPositiveButton("share", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Intent shareIntent =   ShareCompat.IntentBuilder.from(ImplicitIntentActivity.this)
                        .setType("plain/text")
                        .setText(editText.getText())
                        .getIntent();
                if (shareIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(shareIntent);
                }
            }
        });
        alert.show();
    }

    public void createAlarm(View v) {
        String message = "create alarm";
        int hour = 6;
        int minutes = 30;

        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
