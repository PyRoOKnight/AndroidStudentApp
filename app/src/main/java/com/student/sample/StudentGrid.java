package com.student.sample;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class StudentGrid extends AppCompatActivity implements AdapterView.OnItemClickListener{

    TextView welcome_txt;
    Button logout_btn;
    GridView studentgrid;
    int[] imagearray;
    String[] studentnamearray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_grid);


        welcome_txt = (TextView)findViewById(R.id.welcome_txt);
        String _stname = getIntent().getStringExtra("stuname");
        welcome_txt.setText(_stname + " Details");

        logout_btn = (Button)findViewById(R.id.logout_btn);

        studentgrid = (GridView)findViewById(R.id.studentgrid);

        imagearray = new int[]{R.drawable.call, R.drawable.messaging, R.drawable.e_mail,
                R.drawable.browser,R.drawable.camera, R.drawable.photos};

        studentnamearray = new String[]{"Call","Message","Email","Browser","camera","Gallery"};
        //studentclassarray = new String[]{"ECE","CSE","IT","ECE","CSE","IT","ECE","CSE","IT","Mech"};

        GridBinder gridBinder = new GridBinder();
        studentgrid.setAdapter(gridBinder);
        studentgrid.setOnItemClickListener(this);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(LoginScreen.MyPref, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent i = new Intent(getApplicationContext(),LoginScreen.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(i);
            }
        });


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Intent intent = null;

        if(position == 0)
        {
            intent = new Intent(Intent.ACTION_DIAL);
            startActivity(intent);
        }
        else if(position == 1)
        {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"));
            startActivity(intent);
        }
        else if(position == 2)
        {
            intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
            startActivity(intent);
        }
        else if(position==3)
        {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("about:blank"));

            startActivity(intent);
        }
        else if(position == 4)
        {
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(intent);
        }
        else if(position==5)
        {
            intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Not Assigned",Toast.LENGTH_SHORT).show();
        }

    }

    class GridBinder extends BaseAdapter
    {

        @Override
        public int getCount() {
            return studentnamearray.length;
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
            View view = LayoutInflater.from(StudentGrid.this).inflate(R.layout.gridview_child,null);

            ImageView studentimg = (ImageView)view.findViewById(R.id.stgrid_image);
            TextView studentname = (TextView)view.findViewById(R.id.stgrid_name);
            //TextView studentclass = (TextView)view.findViewById(R.id.stgrid_class);
            studentimg.setImageResource(imagearray[position]);
            studentname.setText(studentnamearray[position]);
            //studentclass.setText(studentclassarray[position]);

            return view;
        }
    }
}
