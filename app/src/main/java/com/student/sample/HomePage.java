package com.student.sample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity implements OnItemClickListener {

    TextView welcome_txt;
    Button logout_btn;
    ListView studentlist;
    int[] imagearray;
    String[] studentnamearray, studentclassarray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        welcome_txt = (TextView)findViewById(R.id.welcome_txt);
        String user = getIntent().getStringExtra("name");
        welcome_txt.append(" " + user);

        logout_btn = (Button)findViewById(R.id.logout_btn);
        studentlist = (ListView)findViewById(R.id.studentlist);



        imagearray = new int[]{R.drawable.campamb, R.drawable.studentboyimg, R.drawable.studentgirlimg,
                R.drawable.campamb,R.drawable.studentboyimg, R.drawable.studentgirlimg,
                R.drawable.campamb,R.drawable.studentboyimg,R.drawable.studentgirlimg,R.drawable.studentboyimg};

        studentnamearray = new String[]{"AAAA","SSSS","DDDDDD","FFFFF","GGGG","QQQQQ","WWWW","EEEE","RRRR","TTTTT"};
        studentclassarray = new String[]{"ECE","CSE","IT","ECE","CSE","IT","ECE","CSE","IT","Mech"};

        ListBinder listBinder = new ListBinder();
        studentlist.setAdapter(listBinder);
        studentlist.setOnItemClickListener(this);
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
        TextView stname_txt = (TextView)view.findViewById(R.id.student_name);
        String stname = stname_txt.getText().toString();
        Intent intent = new Intent(HomePage.this,StudentGrid.class);
        intent.putExtra("stuname",stname);
        startActivity(intent);

    }

    class ListBinder extends BaseAdapter
    {

        @Override
        public int getCount() {
            return imagearray.length;
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
            View view = LayoutInflater.from(HomePage.this).inflate(R.layout.listview_child,null);
            ImageView studentimg = (ImageView)view.findViewById(R.id.student_image);
            TextView studentname = (TextView)view.findViewById(R.id.student_name);
            TextView studentclass = (TextView)view.findViewById(R.id.student_class);
            studentimg.setImageResource(imagearray[position]);
            studentname.setText(studentnamearray[position]);
            studentclass.setText(studentclassarray[position]);
            return view;
        }




    }
}
