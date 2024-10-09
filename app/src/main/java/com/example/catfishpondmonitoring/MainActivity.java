package com.example.catfishpondmonitoring;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private CardView turbidityCard, acidCard, waterLevelCard;
    private ImageView turbidityImage, acidImage, waterImage;
    private TextView catfishName, catfishLet;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private static final String CHANNEL_ID = "PondMonitorChannel"; // Notification Channel ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ensure this matches your layout file name

        // Initialize Firebase Database
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("pondData"); // Replace with your node name

        // Initialize CardViews by their IDs
        turbidityCard = findViewById(R.id.turbidityCard);
        acidCard = findViewById(R.id.acidCard);
        waterLevelCard = findViewById(R.id.waterLevelCard);

        // Initialize ImageViews by their IDs (if needed)
        turbidityImage = findViewById(R.id.turbidityImage);
        acidImage = findViewById(R.id.acidImage);
        waterImage = findViewById(R.id.waterImage);

        // Initialize TextViews by their IDs
        catfishName = findViewById(R.id.catfishName);
        catfishLet = findViewById(R.id.catfishLet);

        // Set up click listeners for each CardView
        turbidityCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTurbidityActivity();
            }
        });

        acidCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAcidActivity();
            }
        });

        waterLevelCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWaterLevelActivity();
            }
        });

        // Optional: Set dynamic content for the TextViews if needed
        catfishName.setText("CATFISH");
        catfishLet.setText("Pond Monitoring");

        // Setup Firebase listeners to read data and send notifications
        createNotificationChannel(); // Create the notification channel
        setupFirebaseListeners();
    }

    private void setupFirebaseListeners() {
        databaseReference.child("turbidity").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Double turbidityValue = dataSnapshot.getValue(Double.class);
                Log.d("FirebaseData", "Turbidity: " + turbidityValue);
                Toast.makeText(MainActivity.this, "Turbidity: " + turbidityValue, Toast.LENGTH_SHORT).show();
                showNotification("Turbidity Alert", "New turbidity data received: " + turbidityValue);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("FirebaseData", "Failed to read turbidity value.", databaseError.toException());
            }
        });

        databaseReference.child("acid").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Double acidValue = dataSnapshot.getValue(Double.class);
                Log.d("FirebaseData", "Acid: " + acidValue);
                Toast.makeText(MainActivity.this, "Acid: " + acidValue, Toast.LENGTH_SHORT).show();
                showNotification("Acid Alert", "New acid data received: " + acidValue);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("FirebaseData", "Failed to read acid value.", databaseError.toException());
            }
        });

        databaseReference.child("waterLevel").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer waterLevelValue = dataSnapshot.getValue(Integer.class);
                Log.d("FirebaseData", "Water Level: " + waterLevelValue);
                Toast.makeText(MainActivity.this, "Water Level: " + waterLevelValue, Toast.LENGTH_SHORT).show();
                showNotification("Water Level Alert", "New water level data received: " + waterLevelValue);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("FirebaseData", "Failed to read water level.", databaseError.toException());
            }
        });
    }

    // Method to show notification
    private void showNotification(String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification) // Make sure to add a notification icon in your drawable folder
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify((int) System.currentTimeMillis(), builder.build()); // Unique notification ID
    }

    // Create the NotificationChannel, required for Android 8.0 and above
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "PondMonitorChannel";
            String description = "Channel for pond monitoring notifications";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Register the channel with the system
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void openTurbidityActivity() {
        // Intent to open a new activity for Turbidity
        Intent intent = new Intent(MainActivity.this, TurbidityActivity.class);
        startActivity(intent);
    }

    private void openAcidActivity() {
        // Intent to open a new activity for Acid
        Intent intent = new Intent(MainActivity.this, AcidActivity.class);
        startActivity(intent);
    }

    private void openWaterLevelActivity() {
        // Intent to open a new activity for Water Level
        Intent intent = new Intent(MainActivity.this, WaterLevelActivity.class);
        startActivity(intent);
    }
}
