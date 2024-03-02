package jose.mokeni.mycallerapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyProfileAdapter extends BaseAdapter {
    //role create views

    ArrayList<Profil> data;
    Context context;
    public MyProfileAdapter(Context context, ArrayList<Profil> data){
        this.data = data;
        this.context = context;
    }


    @Override
    public int getCount() {
        //return number of views to create
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        //generate prototype
        //convert xml
        LayoutInflater inf = LayoutInflater.from(context);
        View v = inf.inflate(R.layout.view_profil, null);

        //recuperate holders /sub views
        TextView tvfirstname = v.findViewById(R.id.tvfirstname_profil);
        TextView tvlastname = v.findViewById(R.id.tvlastname_profil);
        TextView tvphone = v.findViewById(R.id.tvphone_profil);

        ImageView imgDelete = v.findViewById(R.id.ivdelete_profil);
        ImageView imgEdit = v.findViewById(R.id.ivedit_profil);
        ImageView imgCall = v.findViewById(R.id.ivcall_profil);

        //te5ou profil mil data
        Profil p = data.get(position);
        //t7ot data fil button text
        tvfirstname.setText(p.firstname);
        tvlastname.setText(p.lastname);
        tvphone.setText(p.number);

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
                Intent i = new Intent();
                i.setAction(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+p.number));
                context.startActivity(i);
            }
        });



        return v;
    }
}
