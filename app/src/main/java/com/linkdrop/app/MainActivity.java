package com.linkdrop.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;

public class MainActivity extends Activity {
    private EditText urlInput;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(28, 36, 28, 24);
        layout.setBackgroundColor(Color.rgb(246, 248, 252));

        TextView title = new TextView(this);
        title.setText("LinkDrop");
        title.setTextSize(30);
        title.setTextColor(Color.rgb(20, 45, 90));
        title.setGravity(Gravity.CENTER);
        layout.addView(title, new LinearLayout.LayoutParams(-1, -2));

        TextView subtitle = new TextView(this);
        subtitle.setText("Paste a direct file URL to open or download it");
        subtitle.setTextSize(15);
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setPadding(0, 12, 0, 24);
        layout.addView(subtitle);

        urlInput = new EditText(this);
        urlInput.setHint("https://example.com/file.mp4");
        urlInput.setSingleLine(true);
        urlInput.setInputType( android.text.InputType.TYPE_CLASS_TEXT
            | android.text.InputType.TYPE_TEXT_VARIATION_URI);
        layout.addView(urlInput, new LinearLayout.LayoutParams(-1, -2));

        Button open = new Button(this);
        open.setText("Open link");
        layout.addView(open, new LinearLayout.LayoutParams(-1, -2));
        open.setOnClickListener(v -> openLink());

        TextView note = new TextView(this);
        note.setText("This starter build handles links. MP3 conversion and video-site downloading are not implemented yet.");
        note.setTextSize(13);
        note.setPadding(0, 20, 0, 0);
        layout.addView(note);

        setContentView(layout);
    }

    private void openLink() {
        String text = urlInput.getText().toString().trim();
        Uri uri = Uri.parse(text);
        if (!(text.startsWith("https://") || text.startsWith("http://"))
            || uri.getHost() == null) {
            Toast.makeText(this, "Enter a valid http/https URL", Toast.LENGTH_LONG).show();
            return;
        }
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        } catch (Exception e) {
            Toast.makeText(this, "No app found to open this link", Toast.LENGTH_LONG).show();
        }
    }
}
