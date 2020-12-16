package com.example.android1lesson51;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private ImageButton imageBtnCalendar;

    private EditText editText;
    private Button button;
    public static String KEY2 = "key2";
    public  String date;
    private TextView textViewDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
        Intent intent = getIntent();
        if (intent!=null){
            Title title =(Title) intent.getSerializableExtra(MainActivity.KEY);
            editText.setText(title.name);
        }
    }

    private void init() {
        textViewDate=findViewById(R.id.textViewDate);
        editText=findViewById(R.id.etText);
        button=findViewById(R.id.buttonResult);
        imageBtnCalendar=findViewById(R.id.imageButtonPopup);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Title title = new Title(editText.getText().toString(), date);
                intent.putExtra(KEY2, title);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        imageBtnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        textViewDate.setText(date);
    }
}