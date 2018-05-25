/*=====================================================================
□ Infomation
  ○ Data : 25.05.2018
  ○ Mail : eun1310434@naver.com
  ○ Blog : https://blog.naver.com/eun1310434
  ○ Reference : Do it android app Programming

□ Function
  ○

□ Study
  ○ Intent
     - An Intent provides a facility for performing late runtime binding between the code in different applications.
       Its most significant use is in the launching of activities, where it can be thought of as the glue between activities.
       It is basically a passive data structure holding an abstract description of an action to be performed.s
=====================================================================*/

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
        editText.setText("PDF INTENT.pdf");
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
            Toast.makeText(this, "NO PDF File : "+fileDir, Toast.LENGTH_SHORT).show();
        }
    }

}
