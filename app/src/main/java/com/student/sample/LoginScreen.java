package com.student.sample;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener {

    EditText username_txt;
    EditText password_txt;
    Button login;
    Button register;
    public static final String MyPref = "myPref";
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        username_txt = (EditText) findViewById(R.id.username_txt);
        password_txt = (EditText)findViewById(R.id.password_txt);
        login = (Button) findViewById(R.id.btn_login);
        register = (Button)findViewById(R.id.btn_register);

        login.setOnClickListener(this);
        register.setOnClickListener(this);


        String user = getIntent().getStringExtra("name");
        String pass = getIntent().getStringExtra("password");

        username_txt.setText(user);
        password_txt.setText(pass);


        sharedPreferences = getSharedPreferences(MyPref, Context.MODE_PRIVATE);
    }




    public String loginEmpty()
    {
        String _username = username_txt.getText().toString();
        String _password = password_txt.getText().toString();
        boolean username_empty = (_username == null)||(_username.equalsIgnoreCase(""));
        boolean password_empty = (_password == null)||(_password.equalsIgnoreCase(""));

        if(username_empty && password_empty)
        {
            return "Enter username and password or Register";
        }
        else if(username_empty)
        {
            return "Enter username";
        }
        else if(password_empty)
        {
            return "Enter password";
        }
        else
        {
            return "Login Successfull";
        }
    }

    public void showAlertDialog()
    {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(LoginScreen.this);

        alertBuilder.setTitle("Missing Fields");
        alertBuilder.setMessage(loginEmpty());
        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();


    }


    public void showCustomDialog()
    {

        final String _username = username_txt.getText().toString();
        final String _password = password_txt.getText().toString();

        final Dialog dialog = new Dialog(LoginScreen.this);
        dialog.setContentView(R.layout.activity_custom_dialog);



        //ImageView dialog_image = (ImageView)dialog.findViewById(R.id.dialog_logo);
        Button dialog_btn_yes = (Button)dialog.findViewById(R.id.dialog_yes);
        Button dialog_btn_no= (Button)dialog.findViewById(R.id.dialog_no);
        TextView msg = (TextView)dialog.findViewById(R.id.dialog_msg);
        TextView title = (TextView)dialog.findViewById(R.id.dialog_title);
        title.setText(loginEmpty());
        msg.setText("Welcome " + _username +"! \nClick Yes to login, No to cancel" );

        final SharedPreferences.Editor editor = sharedPreferences.edit();

        dialog_btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putString("username",_username);
                editor.putString("passkey",_password);
                editor.commit();

                Intent intent =  new Intent(LoginScreen.this, HomePage.class);
                intent.putExtra("name", _username);
                startActivity(intent);
                dialog.dismiss();

            }
        });

        dialog_btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        dialog.show();

    }

    @Override
    public void onClick(View view)
    {

        String _username = username_txt.getText().toString();
        String _password = password_txt.getText().toString();
        boolean username_empty = (_username == null)||(_username.equalsIgnoreCase(""));
        boolean password_empty = (_password == null)||(_password.equalsIgnoreCase(""));

        if(view.getId() == R.id.btn_login)
        {


            if(username_empty || password_empty)
            {
                showAlertDialog();

            }

            if(!username_empty && !password_empty)
            {
                showCustomDialog();

            }

        }


        if(view.getId() == R.id.btn_register)
        {
            Intent intent = new Intent(LoginScreen.this, RegistrationScreen.class);
            startActivity(intent);
        }

    }
}
