package com.example.timedifference;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

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
            return;
        }
        else if (startMinute > 59 || endMinute > 59) {
            Toast.makeText(this, "Enter minutes between 0 and 59", Toast.LENGTH_SHORT).show();
            return;
        }

        /* Deal with cases like 11:00 PM - 1:00 AM */
        if ((startPMButton.isChecked() && endAMButton.isChecked()) || (startAMButton.isChecked() && endPMButton.isChecked())) {
            /* Deal with cases like 11:58 AM - 12:00 PM */
            if (endHour != 12) {
                endHour += 12;
            }
        }

        /* Deal with cases like 11:58 AM - 12:00 AM */
        /* Deal with cases like 12:46 PM - 2:14 PM */
        if (startHour == 12) {
            if (startAMButton.isChecked()) {
                startHour = 0;
            }
            else if (startPMButton.isChecked() && endPMButton.isChecked()) {
                startHour = 0;
            }
        }
        else if (endHour == 12) {
            if (endAMButton.isChecked()) {
                endHour = 0;
            }
            else if (startPMButton.isChecked() && endPMButton.isChecked()) {
                endHour = 0;
            }
        }

        /* Convert to minutes */
        int startTime = startHour*60 + startMinute;
        int endTime = endHour*60 + endMinute;

        int difference = endTime - startTime;

        if (difference < 0) {
            difference += 1440;
        }

        int displayHour = difference/60;
        int displayMinute = difference % 60;

        displayTextView.setText(displayHour + " hours, " + displayMinute + " minutes");
    }

    public void clear(View view) {
        startHourEditText.setText("");
        startMinuteEditText.setText("");
        endHourEditText.setText("");
        endMinuteEditText.setText("");
        displayTextView.setText("");
        startHourEditText.requestFocus();
    }
}
