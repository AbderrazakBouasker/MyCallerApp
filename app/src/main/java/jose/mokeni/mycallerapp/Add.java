package jose.mokeni.mycallerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add extends AppCompatActivity {
    Button btn_save, btn_cancel, btn_back;

    EditText edfirstname, edlastname, edphone;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btn_save = findViewById(R.id.btn_save_add);
        btn_cancel = findViewById(R.id.btn_cancel_add);
        btn_back = findViewById(R.id.btn_back_add);

        edfirstname = findViewById(R.id.edfirstname_add);
        edlastname = findViewById(R.id.ed_lastname_add);
        edphone = findViewById(R.id.ed_phone_add);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edfirstname.getText().toString();
                String lastname = edlastname.getText().toString();
                String phone = edphone.getText().toString();
                if(name.equals("") | lastname.equals("") | phone.equals("")){
                    Toast.makeText(Add.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }else{
                Profil p = new Profil(name,lastname,phone);
                //Home.data.add(p);
                ProfileDAO profileDAO = new ProfileDAO(Add.this);
                profileDAO.open();
                profileDAO.insert(p.firstname,p.lastname,p.number);
                profileDAO.close();
                Toast.makeText(Add.this, "Saved New Contact", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Add.this, "Cancel button pressed", Toast.LENGTH_SHORT).show();
                edfirstname.setText("");
                edlastname.setText("");
                edphone.setText("");
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Add.this, Home.class);
                startActivity(i);
            }
        });

    }
}