package com.example.timedifference;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText startHourEditText;
    EditText startMinuteEditText;
    EditText endHourEditText;
    EditText endMinuteEditText;
    RadioButton startAMButton;
    RadioButton endAMButton;
    RadioButton startPMButton;
    RadioButton endPMButton;
    TextView displayTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startHourEditText = findViewById(R.id.start_hour);
        startMinuteEditText = findViewById(R.id.start_minutes);
        endHourEditText = findViewById(R.id.end_hour);
        endMinuteEditText = findViewById(R.id.end_minutes);
        startAMButton = findViewById(R.id.start_am);
        endAMButton = findViewById(R.id.end_am);
        startPMButton = findViewById(R.id.start_pm);
        endPMButton = findViewById(R.id.end_pm);
        displayTextView = findViewById(R.id.display);
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

        /* 11:00 PM - 1:00 AM -> 11:00 PM - 13:00 AM or 11:00 AM - 1:00 PM -> 11:00 AM - 13:00 PM*/
        if ((startPMButton.isChecked() && endAMButton.isChecked()) || (startAMButton.isChecked() && endPMButton.isChecked())) {
            endHour += 12;
        }

        /* 12:34 PM - 1:02 PM -> 12:34 PM - 12:62 PM (28 minutes) */
        if (endMinute < startMinute) {
            endMinute += 60;
            endHour -= 1;
        }

        int hourDifference = endHour - startHour;
        int minuteDifference = endMinute - startMinute;

    }

    public void clear(View view) {
        startHourEditText.setText("");
        startMinuteEditText.setText("");
        endHourEditText.setText("");
        endMinuteEditText.setText("");
    }
}
