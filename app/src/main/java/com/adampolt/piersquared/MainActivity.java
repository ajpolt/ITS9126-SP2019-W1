package com.adampolt.piersquared;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the xml layout file of this Activity to the one we edited
        setContentView(R.layout.activity_main);

        // Get references to the views we created in activity_main.xml
        // We have to set these as Final if they're used inside the on-click
        // listener below
        Button button = findViewById(R.id.button);
        final EditText pizza1diameter = findViewById(R.id.pizza1);
        final EditText pizza2diameter = findViewById(R.id.pizza2);
        final TextView result = findViewById(R.id.result);

        // Add an on-click action to the Button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the EditTexts in the XML file
                String p1String = pizza1diameter.getText().toString();
                String p2String = pizza2diameter.getText().toString();

                // Stop from crashing if the text fields are empty
                if(TextUtils.isEmpty(p1String) || TextUtils.isEmpty(p2String)) {
                    Toast.makeText(MainActivity.this, "Please enter values for both pizzas", Toast.LENGTH_LONG).show();
                    return;
                }

                // Stop from crashing if the text isn't numeric
                if(!TextUtils.isDigitsOnly(p1String) || !TextUtils.isDigitsOnly(p2String)) {
                    Toast.makeText(MainActivity.this, "Please enter numeric values", Toast.LENGTH_LONG).show();
                    return;
                }

                // Convert the Strings into numeric variables
                double p1double = Double.parseDouble(p1String);
                double p2double = Double.parseDouble(p2String);

                // Do the math -- Area of a circle is Pi * radius squared
                double p1Area = Math.PI * (p1double / 2) * (p1double / 2);
                double p2Area = Math.PI * (p2double / 2) * (p2double / 2);

                // Make the results pretty using Java's NumberFormat class
                NumberFormat decimalFormat = NumberFormat.getInstance(); // Get a NumberFormat object
                decimalFormat.setMaximumFractionDigits(2); // Set the maximum fraction digits to 2
                String result1 = decimalFormat.format(p1Area); // Apply it to our area variables and save as Strings
                String result2 = decimalFormat.format(p2Area);

                // Set the results in the text view
                result.setText("Pizza 1 is " + result1 + " square inches, "
                        + "and Pizza 2 is " + result2 + " square inches");
            }
        });
    }
}