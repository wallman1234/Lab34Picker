package my.edu.tarc.lab34picker;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private int age = 0;
    private EditText editTextAccountBalance;
    private TextView textViewEligibleAmount;
    private TextView textViewAge;
    private Button DOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextAccountBalance = findViewById(R.id.editTextAccountBalance);
        textViewEligibleAmount = findViewById(R.id.textViewEligibleAmount);
        textViewAge = findViewById(R.id.textViewAge);
        DOB = findViewById(R.id.buttonDOB);
    }

    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void processDatePickerResult(int year, int month, int day) {

        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (day_string +
                "/" + month_string + "/" + year_string);

        age = 0;

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int currentDate = Calendar.getInstance().get(Calendar.DATE);

        if (currentYear > year) {
            age = currentYear - year;
            if (currentMonth < month) {
                age--;
            } else if (currentMonth == month) {
                if (currentDate < day) {
                    age--;
                }
            }
        }

        String date = "Date";
        Toast.makeText(this, date + ": " + dateMessage,
                Toast.LENGTH_SHORT).show();

        textViewAge.setText("Age: " + age);
        DOB.setText(dateMessage);

    }

    public void calculateAllowedAmount(View view) {

        double currentAmount = Double.parseDouble(editTextAccountBalance.getText().toString());
        double allowAmount = 0.0;

        if (age >= 16 && age <= 20) {
            allowAmount = currentAmount - 5000;
        } else if (age <= 25) {
            allowAmount = currentAmount - 14000;
        } else if (age <= 30) {
            allowAmount = currentAmount - 29000;
        } else if (age <= 35) {
            allowAmount = currentAmount - 50000;
        } else if (age <= 40) {
            allowAmount = currentAmount - 78000;
        } else if (age <= 45) {
            allowAmount = currentAmount - 116000;
        } else if (age <= 50) {
            allowAmount = currentAmount - 165000;
        } else if (age <= 55) {
            allowAmount = currentAmount - 228000;
        }
        if (allowAmount > 0) {
            allowAmount = allowAmount * 0.3;
        } else {
            allowAmount = 0.00;
        }

        if (age < 16) {
            textViewEligibleAmount.setText("Not eligible!!!");
        } else {
            textViewEligibleAmount.setText("Eligible Amount : RM " + allowAmount);
        }
    }

    public void resetView(View view) {

        editTextAccountBalance.setText("");
        DOB.setText("Select Date Of Birth");
        textViewAge.setText("Age");
        textViewEligibleAmount.setText("Eligible Amount");

    }

}

