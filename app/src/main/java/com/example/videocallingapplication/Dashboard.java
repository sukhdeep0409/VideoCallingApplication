package com.example.videocallingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import org.jitsi.meet.sdk.log.JitsiMeetLogger;

import java.net.MalformedURLException;
import java.net.URL;

public class Dashboard extends AppCompatActivity {

    EditText meetingCode;
    Button join, share;

    URL serverURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        meetingCode = findViewById(R.id.codeBox);
        join = findViewById(R.id.join_button);
        share = findViewById(R.id.share_button);

        try {
            serverURL = new URL("https://meet.jit.si");
            meetingConfiguration();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }

        join.setOnClickListener(view -> {
            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                    .setRoom(meetingCode.getText().toString())
                    .setWelcomePageEnabled(false)
                    .build();

            JitsiMeetActivity.launch(Dashboard.this, options);
        });
    }

    private void meetingConfiguration() {

        JitsiMeetConferenceOptions options =
                new JitsiMeetConferenceOptions.Builder()
                .setServerURL(serverURL)
                .setWelcomePageEnabled(false)
                .build();

        JitsiMeet.setDefaultConferenceOptions(options);
    }
}