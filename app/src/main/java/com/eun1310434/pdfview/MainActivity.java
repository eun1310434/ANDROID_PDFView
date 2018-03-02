/**
 * 02.03.2018
 * eun1310434@naver.com
 * https://blog.naver.com/eun1310434
 * 참고) Do it android programming
 */

package com.eun1310434.pdfview;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
    }

    public void onButton1Clicked(View v) {
        String filename = editText.getText().toString();
        if (filename.length() > 0) {
            openPDF(filename.trim());
        } else {
            Toast.makeText(getApplicationContext(), "Insert PDF file name!", Toast.LENGTH_LONG).show();
        }
    }

    public void openPDF(String filename) {
        String sdcardDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileDir = sdcardDir + File.separator + filename;
        File file = new File(fileDir);

        if (file.exists()) {
            Uri uri = Uri.fromFile(file);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, "application/pdf");

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException ex) {
                Toast.makeText(this, "There is no PDF File Viewer APP", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "NO PDF File", Toast.LENGTH_SHORT).show();
        }
    }

}
