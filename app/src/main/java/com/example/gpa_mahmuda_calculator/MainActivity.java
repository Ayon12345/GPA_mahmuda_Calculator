package com.example.gpa_mahmuda_calculator;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextCourse1, editTextCourse2, editTextCourse3, editTextCourse4, editTextCourse5;
    private EditText editTextGrade1, editTextGrade2, editTextGrade3, editTextGrade4, editTextGrade5;
    private Button buttonComputeGPA;
    private TextView textViewGPA;

    private boolean isFormCleared = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        editTextCourse1 = findViewById(R.id.editTextCourse1);
        editTextCourse2 = findViewById(R.id.editTextCourse2);
        editTextCourse3 = findViewById(R.id.editTextCourse3);
        editTextCourse4 = findViewById(R.id.editTextCourse4);
        editTextCourse5 = findViewById(R.id.editTextCourse5);

        editTextGrade1 = findViewById(R.id.editTextGrade1);
        editTextGrade2 = findViewById(R.id.editTextGrade2);
        editTextGrade3 = findViewById(R.id.editTextGrade3);
        editTextGrade4 = findViewById(R.id.editTextGrade4);
        editTextGrade5 = findViewById(R.id.editTextGrade5);

        buttonComputeGPA = findViewById(R.id.buttonComputeGPA);
        textViewGPA = findViewById(R.id.textViewGPA);

        // Set onClickListener for the compute button
        buttonComputeGPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFormCleared) {
                    // Check if any field is empty
                    if (isEmpty(editTextCourse1) || isEmpty(editTextCourse2) || isEmpty(editTextCourse3) ||
                            isEmpty(editTextCourse4) || isEmpty(editTextCourse5) || isEmpty(editTextGrade1) ||
                            isEmpty(editTextGrade2) || isEmpty(editTextGrade3) || isEmpty(editTextGrade4) ||
                            isEmpty(editTextGrade5)) {
                        Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Calculate GPA
                    double totalGradePoints = 0;
                    int totalCredits = 0;
                    totalGradePoints += Double.parseDouble(editTextGrade1.getText().toString()) * 3;
                    totalGradePoints += Double.parseDouble(editTextGrade2.getText().toString()) * 3;
                    totalGradePoints += Double.parseDouble(editTextGrade3.getText().toString()) * 3;
                    totalGradePoints += Double.parseDouble(editTextGrade4.getText().toString()) * 3;
                    totalGradePoints += Double.parseDouble(editTextGrade5.getText().toString()) * 3;
                    totalCredits += 3 * 5; // Assuming 3 credits for each course

                    // Calculate GPA
                    double gpa = totalGradePoints / totalCredits;

                    // Set background color based on GPA
                    int backgroundColor;
                    if (gpa < 60) {
                        backgroundColor = Color.RED;
                    } else if (gpa >= 61 && gpa <= 79) {
                        backgroundColor = Color.YELLOW;
                    } else {
                        backgroundColor = Color.GREEN;
                    }
                    textViewGPA.setBackgroundColor(backgroundColor);

                    // Display GPA
                    textViewGPA.setText("GPA: " + String.format("%.2f", gpa));

                    // Change button text to "Clear Form" and enable it
                    buttonComputeGPA.setText("Clear Form");
                    isFormCleared = true;
                } else {
                    // Clear the form
                    clearForm();
                }
            }
        });
    }

    // Method to check if EditText is empty
    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }

    // Method to clear the form
    private void clearForm() {
        editTextCourse1.getText().clear();
        editTextCourse2.getText().clear();
        editTextCourse3.getText().clear();
        editTextCourse4.getText().clear();
        editTextCourse5.getText().clear();

        editTextGrade1.getText().clear();
        editTextGrade2.getText().clear();
        editTextGrade3.getText().clear();
        editTextGrade4.getText().clear();
        editTextGrade5.getText().clear();

        textViewGPA.setText("GPA: ");
        textViewGPA.setBackgroundColor(Color.TRANSPARENT);

        buttonComputeGPA.setText("Compute GPA");
        isFormCleared = false;
    }
}
