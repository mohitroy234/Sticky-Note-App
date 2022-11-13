package com.example.stickynote;

import androidx.appcompat.app.AppCompatActivity;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText noteEdit;
    private Button increaseSize,decreaseSize,boldText,underlText,italicText;
    private TextView ftext;
    float currentTextsize;
    StickNote note=new StickNote();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteEdit = findViewById(R.id.edittext);
        increaseSize = findViewById(R.id.increasesize);
        decreaseSize = findViewById(R.id.decreasesize);
        boldText = findViewById(R.id.bold);
        underlText = findViewById(R.id.underline);
        italicText = findViewById(R.id.italic);
        ftext = findViewById(R.id.fontsize);
        currentTextsize=noteEdit.getTextSize();
        ftext.setText(""+currentTextsize);
        increaseSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ftext.setText(""+currentTextsize);
            currentTextsize++;
            noteEdit.setTextSize(currentTextsize);
            }
        });
        decreaseSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ftext.setText(""+currentTextsize);
            currentTextsize--;
            noteEdit.setTextSize(currentTextsize);
            }
        });
        boldText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
            italicText.setTextColor(getResources().getColor(R.color.white));
            italicText.setBackgroundColor(getResources().getColor(R.color.purple_200));
            if(noteEdit.getTypeface().isBold()){
                noteEdit.setTypeface(Typeface.DEFAULT);
                noteEdit.setTextColor(getResources().getColor(R.color.white));
                boldText.setBackgroundColor(getResources().getColor(R.color.purple_200));
            }
            else{
                boldText.setTextColor(getResources().getColor(R.color.white));
                boldText.setBackgroundColor(getResources().getColor(R.color.purple_200));
                boldText.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            }
            }
        });
        underlText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
            if(noteEdit.getPaintFlags()==8){
                underlText.setTextColor(getResources().getColor(R.color.white));
                underlText.setBackgroundColor(getResources().getColor(R.color.purple_200));
                noteEdit.setPaintFlags(noteEdit.getPaintFlags() &(~Paint.UNDERLINE_TEXT_FLAG));
            }
            else{
                underlText.setTextColor(getResources().getColor(R.color.white));
                underlText.setBackgroundColor(getResources().getColor(R.color.purple_200));
                noteEdit.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
            }
            }
        });
        italicText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                boldText.setTextColor(getResources().getColor(R.color.white));
                boldText.setBackgroundColor(getResources().getColor(R.color.purple_200));
                if(noteEdit.getTypeface().isItalic()){
                    noteEdit.setTypeface(Typeface.DEFAULT);
                    italicText.setTextColor(getResources().getColor(R.color.white));
                    italicText.setBackgroundColor(getResources().getColor(R.color.purple_200));
                }
                else{
                    italicText.setTextColor(getResources().getColor(R.color.white));
                    italicText.setBackgroundColor(getResources().getColor(R.color.purple_200));
                    noteEdit.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                }
            }
        });



    }

    public void SaveButton(View view) {
        note.getStick(noteEdit.getText().toString(),this);
        updateWidget();
        Toast.makeText(this,"Note has been updated..",Toast.LENGTH_SHORT).show();
    }

    private void updateWidget() {
        AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(this);
        RemoteViews remoteViews=new RemoteViews(this.getPackageName(),R.layout.widget_layout);
        ComponentName thisWidget=new ComponentName(this,AppWidget.class);
        remoteViews.setTextViewText(R.id.idTwidget,noteEdit.getText().toString());
        appWidgetManager.updateAppWidget(thisWidget,remoteViews);
    }

}