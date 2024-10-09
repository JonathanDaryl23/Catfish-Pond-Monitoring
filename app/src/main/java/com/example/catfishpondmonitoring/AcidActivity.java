package com.example.catfishpondmonitoring;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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

public class AcidActivity extends AppCompatActivity {

    private TextView acidTitle;
    private ImageView actAcid;
    private Button buttonBack;
    private RecyclerView recyclerAcid;

    private AcidAdapter acidAdapter;
    private List<AcidData> acidDataList;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acid);

        // Initialize views
        acidTitle = findViewById(R.id.acidTitle);
        actAcid = findViewById(R.id.actAcid);
        buttonBack = findViewById(R.id.button_back);
        recyclerAcid = findViewById(R.id.recycler_acid);

        // Set up RecyclerView
        recyclerAcid.setLayoutManager(new LinearLayoutManager(this));
        acidDataList = new ArrayList<>();
        acidAdapter = new AcidAdapter(acidDataList);
        recyclerAcid.setAdapter(acidAdapter);

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("acidLevels");

        // Set up Firebase listener
        setupFirebaseListener();

        // Back button functionality
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AcidActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setupFirebaseListener() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                acidDataList.clear(); // Clear the list before adding new data
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    AcidData acidData = snapshot.getValue(AcidData.class);
                    acidDataList.add(acidData);
                }
                acidAdapter.notifyDataSetChanged(); // Notify the adapter of data changes
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle possible errors
            }
        });
    }
}
