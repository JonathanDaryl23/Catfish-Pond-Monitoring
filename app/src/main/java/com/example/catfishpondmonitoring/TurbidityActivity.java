package com.example.catfishpondmonitoring;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class TurbidityActivity extends AppCompatActivity {

    private TextView turbidityTitle;
    private ImageView actTurbidity;
    private Button buttonBack;
    private RecyclerView recyclerTurbidity;
    private TurbidityAdapter turbidityAdapter;
    private List<TurbidityData> turbidityDataList;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turbidity); // Ensure this matches your layout file name

        // Initialize Firebase Database
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("pondData/turbidity"); // Adjust according to your database structure

        // Initialize UI components
        turbidityTitle = findViewById(R.id.turbidityTitle);
        actTurbidity = findViewById(R.id.actTurbidity);
        buttonBack = findViewById(R.id.button_back);
        recyclerTurbidity = findViewById(R.id.recycler_turbidity);

        // Initialize list and adapter
        turbidityDataList = new ArrayList<>();
        turbidityAdapter = new TurbidityAdapter(turbidityDataList);
        recyclerTurbidity.setLayoutManager(new LinearLayoutManager(this));
        recyclerTurbidity.setAdapter(turbidityAdapter);

        // Load data from Firebase
        loadDataFromFirebase();

        // Set up the back button
        buttonBack.setOnClickListener(v -> finish());
    }

    private void loadDataFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                turbidityDataList.clear(); // Clear previous data
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    TurbidityData data = snapshot.getValue(TurbidityData.class);
                    if (data != null) {
                        turbidityDataList.add(data);
                    }
                }
                turbidityAdapter.notifyDataSetChanged(); // Notify adapter to update the UI
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(TurbidityActivity.this, "Failed to load data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
