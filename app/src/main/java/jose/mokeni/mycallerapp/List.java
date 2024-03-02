package jose.mokeni.mycallerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class List extends AppCompatActivity {
    ImageButton btn_return;
    RecyclerView recyclerview_profiles;
    EditText ed_search;
    ArrayList<Profil> profile_list;
    ArrayList<Profil> search_profile_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerview_profiles = findViewById(R.id.recyclerview_profiles_list);
        ed_search = findViewById(R.id.ed_search_list);
        btn_return = findViewById(R.id.btn_return_list);

        ProfileDAO profileDAO = new ProfileDAO(List.this);
        profileDAO.open();
        profile_list = profileDAO.select_all();
        profileDAO.close();

        Recycleradapter(profile_list);

        ed_search.addTextChangedListener(textWatcher);

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(List.this, Home.class);
                startActivity(intent);
            }
        });

    }

    void Recycleradapter(ArrayList<Profil> profile_list){
        MyRecyclerProfilAdapter ad = new MyRecyclerProfilAdapter(List.this, profile_list);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(List.this,LinearLayoutManager.HORIZONTAL,true);
//        recyclerview_profiles.setLayoutManager(linearLayoutManager);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(List.this,2,GridLayoutManager.VERTICAL,false);
        recyclerview_profiles.setLayoutManager(gridLayoutManager);
        recyclerview_profiles.setAdapter(ad);
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String edsearch=ed_search.getText().toString();
            search_profile_list = new ArrayList<Profil>();
            int i=0;
            for (Profil p:profile_list) {
                Pattern pattern = Pattern.compile(edsearch, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(p.firstname+p.lastname+p.number);
                boolean matchFound = matcher.find();
                if (matchFound){
                    i++;
                    System.out.println(p.firstname+i);
                    search_profile_list.add(p);
//                    MyRecyclerProfilAdapter ad = new MyRecyclerProfilAdapter(List.this, profile_list);
                }
            }
//            for (Profil p:
//                    search_profile_list) {
//                System.out.println(p.firstname+" "+p.lastname+" "+p.number);
//            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            Recycleradapter(search_profile_list);

        }
    };


}