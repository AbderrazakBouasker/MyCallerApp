package jose.mokeni.mycallerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//    SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
    Context context = this;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    boolean varischecked;
    Button btnlogin;
    CheckBox cbconnected;

    EditText edemail, edpwd;
    String varstconnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = getSharedPreferences(getPackageName()+".STAY_CONNECTED_PREFERENCE_FILE", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        // Recuperation
        btnlogin = findViewById(R.id.btn_login_auth);
        edemail = findViewById(R.id.edemail_auth);
        edpwd = findViewById(R.id.edpwd_auth);
        cbconnected = findViewById(R.id.cb_stconnected_main);
        varstconnected = sharedPref.getString("varstayconnected", "notstayconnected");
        if(varstconnected.equals("stayconnected")){
            Intent intent = new Intent(MainActivity.this,Home.class);
            startActivity(intent);
        }



        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get strings
                    String email = edemail.getText().toString();
                    String pwd = edpwd.getText().toString();


                        if (email.equals("azer") && pwd.equals("111")){
                            // Passage vers une autre activite
                            // on doit créer une intent: intention de lancer quelque chose
                            // param: activite courante, activite cible
                            if(varischecked){
                              editor.putString("varstayconnected", "stayconnected");
                              editor.apply();
                            }else {
                              editor.putString("varstayconnected", "notstayconnected");
                              editor.apply();
                            }
                            Intent i = new Intent(MainActivity.this, Home.class);

                            startActivity(i);
                        }
                    else {
                        // Message d'erreur
                        Toast.makeText(MainActivity.this, "Email or password not valid", Toast.LENGTH_SHORT).show();
                    }


            }
        });
        cbconnected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
//                    editor.putString("varstayconnected", "stayconnected");
//                    editor.apply();
                    varischecked = true;
                }else {
//                    editor.putString("varstayconnected", "notstayconnected");
//                    editor.apply();
                    varischecked = false;
                }
            }
        });

    }
}