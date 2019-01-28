package com.example.timedifference;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText startHourEditText;
    EditText startMinuteEditText;
    EditText endHourEditText;
    EditText endMinuteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startHourEditText = findViewById(R.id.start_hour);
        startMinuteEditText = findViewById(R.id.start_minutes);
        endHourEditText = findViewById(R.id.end_hour);
        endMinuteEditText = findViewById(R.id.end_minutes);
    }

    public void calculate(View view) {
        String startHourString = startHourEditText.getText().toString();
        String startMinuteString = startMinuteEditText.getText().toString();
        String endHourString = endHourEditText.getText().toString();
        String endMinuteString = endMinuteEditText.getText().toString();

        if (startHourString.matches("") || startMinuteString.matches("") || endHourString.matches("") || endMinuteString.matches("")) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int startHour = Integer.parseInt(startHourString);
        int startMinute = Integer.parseInt(startMinuteString);
        int endHour = Integer.parseInt(endHourString);
        int endMinute = Integer.parseInt(endMinuteString);

        if (startHour > 12 || startHour < 1 || endHour < 1 || endHour > 12) {
            Toast.makeText(this, "Enter hour between 1 and 12", Toast.LENGTH_SHORT).show();
        }
        else if (startMinute > 59 || endMinute > 59) {
            Toast.makeText(this, "Enter minutes between 0 and 59", Toast.LENGTH_SHORT).show();
        }
    }

    public void clear(View view) {
        startHourEditText.setText("");
        startMinuteEditText.setText("");
        endHourEditText.setText("");
        endMinuteEditText.setText("");
    }
}
