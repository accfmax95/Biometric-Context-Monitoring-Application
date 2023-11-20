package com.example.Maxwell;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

public class SymptomsScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinnerSymptom;
    RatingBar ratingBar;
    Button btnUpload, btnSubmit;
    String spinnerSelection = "";
    float selectedRating = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DataBaseModel DataBaseModelGlobal = (DataBaseModel) getIntent().getParcelableExtra("myDBObject");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_symptoms_screen);
        spinnerSymptom = findViewById(R.id.symptomsList);
        ratingBar = findViewById(R.id.ratingSymptom);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.symptoms_list));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinnerSymptom.setAdapter(spinnerAdapter);
        spinnerSymptom.setOnItemSelectedListener(this);
        submitSymptoms(DataBaseModelGlobal);
        uploadToDB(DataBaseModelGlobal);
    }

    public void submitSymptoms(DataBaseModel DataBaseModelGlobal) {

        btnSubmit = findViewById(R.id.submitButton);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedRating = ratingBar.getRating();

                if (spinnerSelection.equals("Nausea")) {

                    String s = Float.toString(selectedRating);
                    double d = Double.parseDouble(s);
                    DataBaseModelGlobal.setRATE_NAUSEA(d);
                }

                if (spinnerSelection.equals("Headache")) {

                    String s = Float.toString(selectedRating);
                    double d = Double.parseDouble(s);
                    DataBaseModelGlobal.setRATE_HEADACHE(d);
                }

                if (spinnerSelection.equals("Diarrhea")) {

                    String s = Float.toString(selectedRating);
                    double d = Double.parseDouble(s);
                    DataBaseModelGlobal.setRATE_DIARRHEA(d);
                }

                if (spinnerSelection.equals("Sore Throat")) {

                    String s = Float.toString(selectedRating);
                    double d = Double.parseDouble(s);
                    DataBaseModelGlobal.setRATE_SORE_THROAT(d);
                }

                if (spinnerSelection.equals("Fever")) {

                    String s = Float.toString(selectedRating);
                    double d = Double.parseDouble(s);
                    DataBaseModelGlobal.setRATE_FEVER(d);
                }

                if (spinnerSelection.equals("Muscle Pain")) {

                    String s = Float.toString(selectedRating);
                    double d = Double.parseDouble(s);
                    DataBaseModelGlobal.setRATE_MUSCLE_PAIN(d);
                }

                if (spinnerSelection.equals("Loss of Smell or Taste")) {

                    String s = Float.toString(selectedRating);
                    double d = Double.parseDouble(s);
                    DataBaseModelGlobal.setRATE_SMELL_TASTE(d);
                }

                if (spinnerSelection.equals("Cough")) {

                    String s = Float.toString(selectedRating);
                    double d = Double.parseDouble(s);
                    DataBaseModelGlobal.setRATE_COUGH(d);
                }

                if (spinnerSelection.equals("Shortness of Breath")) {

                    String s = Float.toString(selectedRating);
                    double d = Double.parseDouble(s);
                    DataBaseModelGlobal.setRATE_SHORTNESS_BREATH(d);
                }

                if (spinnerSelection.equals("Tiredness")) {

                    String s = Float.toString(selectedRating);
                    double d = Double.parseDouble(s);
                    DataBaseModelGlobal.setRATE_TIRED(d);
                }

                Toast.makeText(getApplicationContext(), spinnerSelection +": "+ selectedRating, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void uploadToDB(DataBaseModel DataBaseModelGlobal) {

        btnUpload = findViewById(R.id.uploadToDB);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Uploading to DB...", Toast.LENGTH_LONG).show();
                DataBaseFunc databaseAction = new DataBaseFunc(getApplicationContext());
                DataBaseModel reading = new DataBaseModel(DataBaseModelGlobal.getHEART_RATE(), DataBaseModelGlobal.getRESPIRATORY_RATE(), DataBaseModelGlobal.getRATE_NAUSEA(), DataBaseModelGlobal.getRATE_HEADACHE(), DataBaseModelGlobal.getRATE_DIARRHEA(), DataBaseModelGlobal.getRATE_SORE_THROAT(), DataBaseModelGlobal.getRATE_FEVER(), DataBaseModelGlobal.getRATE_MUSCLE_PAIN(), DataBaseModelGlobal.getRATE_SMELL_TASTE(), DataBaseModelGlobal.getRATE_COUGH(), DataBaseModelGlobal.getRATE_SHORTNESS_BREATH(), DataBaseModelGlobal.getRATE_TIRED());

                if (databaseAction.onInsert(reading) == false) {

                    Toast.makeText(getApplicationContext(), "Failed to Upload to DB. Please Try Again.", Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(getApplicationContext(), "Upload to DB Successful!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String symptom = parent.getItemAtPosition(position).toString();
        spinnerSelection = symptom;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }
}