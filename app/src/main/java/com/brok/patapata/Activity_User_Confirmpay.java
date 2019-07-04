package com.brok.patapata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Activity_User_Confirmpay extends AppCompatActivity {

    private Button report, button;
    private TextView textView;
    //private Task<Void> mDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__user__confirmpay);

        Intent intent = getIntent();
        final String ident = intent.getStringExtra("ident");
        textView = (TextView)findViewById(R.id.tvMessage);
        textView.setText(ident);
        Bundle bundle = intent.getExtras();
        String status = bundle.getString("status");
        Toast toast = Toast.makeText(this, status, Toast.LENGTH_LONG);
        toast.show();

        button = (Button) findViewById(R.id.cancel_transaction);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("requests").child(ident).removeValue();
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