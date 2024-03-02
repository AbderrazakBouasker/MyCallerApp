package jose.mokeni.mycallerapp;

import static android.Manifest.permission.CALL_PHONE;
import static androidx.core.app.ActivityCompat.requestPermissions;
import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerProfilAdapter extends RecyclerView.Adapter<MyRecyclerProfilAdapter.MyViewHolder> {
    Context context;
    ArrayList<Profil> data;

    public MyRecyclerProfilAdapter(Context context, ArrayList<Profil> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyRecyclerProfilAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //creation de la view
        //generate prototype
        //convert xml
        LayoutInflater inf = LayoutInflater.from(context);
        View v = inf.inflate(R.layout.view_profil, null);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerProfilAdapter.MyViewHolder holder, int position) {
        //affectation des holders
        //te5ou profil mil data
        Profil p = data.get(position);
        //t7ot data fil button text
        holder.tvfirstname.setText(p.firstname);
        holder.tvlastname.setText(p.lastname);
        holder.tvphone.setText(p.number);
    }

    @Override
    public int getItemCount() {
        //nomber total des views
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvfirstname;
        TextView tvlastname;
        TextView tvphone;
        ImageView imgDelete;
        ImageView imgEdit;
        ImageView imgCall;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //recuperate holders /sub views
            tvfirstname = itemView.findViewById(R.id.tvfirstname_profil);
            tvlastname = itemView.findViewById(R.id.tvlastname_profil);
            tvphone = itemView.findViewById(R.id.tvphone_profil);

            imgDelete = itemView.findViewById(R.id.ivdelete_profil);
            imgEdit = itemView.findViewById(R.id.ivedit_profil);
            imgCall = itemView.findViewById(R.id.ivcall_profil);

            //events
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setTitle("Deleting");
                    alert.setMessage("Confirm deletion ?");

                    alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //getselected element position
                            int position = getAdapterPosition();
                            ProfileDAO profileDAO = new ProfileDAO(context);
                            profileDAO.open();
                            profileDAO.delete(data.get(position).id);
                            profileDAO.close();
                            data.remove(position);
                            notifyDataSetChanged();
                        }
                    });
                    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alert.setNeutralButton("Exit",null);
                    alert.show();


                }
            });

            imgCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent i = new Intent();
                    i.setAction(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:"+data.get(position).number));


                    if (ContextCompat.checkSelfPermission(context.getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        context.startActivity(i);
                    } else {
                        requestPermissions((Activity) context,new String[]{CALL_PHONE}, 1);
                    }


//                    context.startActivity(i);
                }
            });

            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder editalert = new AlertDialog.Builder(context);
                    editalert.setTitle("Edit Dialog");
                    editalert.setMessage("Edit the data");

                    //view edit
                    LayoutInflater inflater = LayoutInflater.from(context);
                    View view = inflater.inflate(R.layout.view_edit_profil,null);

                    TextView firstname = view.findViewById(R.id.edit_firstname_input);
                    TextView lastname = view.findViewById(R.id.edit_lastname_input);
                    TextView number = view.findViewById(R.id.edit_number_input);

                    int position = getAdapterPosition();


                    firstname.setText(data.get(position).firstname);
                    lastname.setText(data.get(position).lastname);
                    number.setText(data.get(position).number);


                    editalert.setView(view);

                    editalert.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Profil profil = new Profil(data.get(position).id,firstname.getText().toString(),lastname.getText().toString(),number.getText().toString());
                            ProfileDAO profileDAO = new ProfileDAO(context);
                            profileDAO.open();
                            profileDAO.modify(data.get(position).id,firstname.getText().toString(),lastname.getText().toString(),number.getText().toString());
                            data.set(position,profil);
                            notifyDataSetChanged();
                        }
                    });
                    editalert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    editalert.show();

                }
            });


        }
    }
}
