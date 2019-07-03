package com.brok.patapata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Activity_User_Confirmpay extends AppCompatActivity {

    private Button report, button;
    //private Task<Void> mDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__user__confirmpay);

        button = (Button) findViewById(R.id.cancel_transaction);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("requeste").child(FirebaseAuth.
                        getInstance().getCurrentUser().getUid()).removeValue();
                Intent intent = new Intent(Activity_User_Confirmpay.this, activity_user.class);
                startActivity(intent);
            }
        });

        report = (Button) findViewById(R.id.report_transaction);
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_User_Confirmpay.this, Activity_Send_Report.class);
                startActivity(intent);
            }
        });
    }
}
