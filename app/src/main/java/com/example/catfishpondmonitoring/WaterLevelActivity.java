// WaterLevelActivity.java
package com.example.catfishpondmonitoring;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WaterLevelActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WaterLevelAdapter waterLevelAdapter;
    private List<WaterLevelData> waterLevelList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_level); // Ensure this matches your layout file name

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recycler_waterlevel);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the list and adapter
        waterLevelList = new ArrayList<>();
        waterLevelAdapter = new WaterLevelAdapter(waterLevelList);
        recyclerView.setAdapter(waterLevelAdapter);

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("WaterLevels"); // Your Firebase path

        // Load data from Firebase
        loadWaterLevelData();

        // Set up back button
        Button backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the activity
            }
        });
    }

    private void loadWaterLevelData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                waterLevelList.clear(); // Clear the existing list
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    WaterLevelData waterLevel = snapshot.getValue(WaterLevelData.class);
                    waterLevelList.add(waterLevel); // Add to the list
                }
                waterLevelAdapter.notifyDataSetChanged(); // Notify the adapter of data changes
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors
            }
        });
    }
}
