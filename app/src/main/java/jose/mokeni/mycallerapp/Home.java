package jose.mokeni.mycallerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    Button btn_add, btn_list, btn_logout;
    public static ArrayList<Profil> data = new ArrayList<Profil>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPref = getSharedPreferences(getPackageName()+".STAY_CONNECTED_PREFERENCE_FILE", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        btn_add = findViewById(R.id.btn_add_home);
        btn_list = findViewById(R.id.btn_list_home);
        btn_logout = findViewById(R.id.btn_logout_home);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Add.class);

                startActivity(i);

            }
        });

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, List.class);

                startActivity(i);

            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("varstayconnected", "notstayconnected");
                editor.apply();
                Intent i = new Intent(Home.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}