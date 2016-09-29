package com.student.sample;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationScreen extends AppCompatActivity implements View.OnClickListener {

    EditText fname_txt;

    EditText lname_txt;
    EditText uname_txt;
    EditText password_txt;
    EditText cpassword_txt;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);


        fname_txt = (EditText)findViewById(R.id.fname_txt);
        lname_txt = (EditText)findViewById(R.id.lname_txt);
        uname_txt = (EditText)findViewById(R.id.uname_txt);
        password_txt = (EditText)findViewById(R.id.password_txt);
        cpassword_txt = (EditText)findViewById(R.id.cpassword_txt);
        register = (Button)findViewById(R.id.registered);

        register.setOnClickListener(this);



    }

    public String RegisterEmpty()
    {
        String _username = uname_txt.getText().toString();
        String _password = password_txt.getText().toString();
        String _fname = fname_txt.getText().toString();
        String _lname = lname_txt.getText().toString();
        String _cpassword = cpassword_txt.getText().toString();
        boolean fname_empty = (_fname == null)||(_fname.equalsIgnoreCase(""));
        boolean lname_empty = (_lname == null)||(_lname.equalsIgnoreCase(""));
        boolean username_empty = (_username == null)||(_username.equalsIgnoreCase(""));
        boolean password_empty = (_password == null)||(_password.equalsIgnoreCase(""));
        boolean cpassword_empty = (_cpassword == null)||(_cpassword.equalsIgnoreCase(""));


        if(username_empty || password_empty || fname_empty || lname_empty || cpassword_empty)
        {
            return "Enter all the details";
        }
        else if(!(_cpassword.equals(_password)))
        {
            return "Passwords Missmatch";
        }
        else
        {
            return "Registration Successfull";
        }
    }


    @Override
    public void onClick(View view)
    {
        String _username = uname_txt.getText().toString();
        String _password = password_txt.getText().toString();
        String _fname = fname_txt.getText().toString();
        String _lname = lname_txt.getText().toString();
        String _cpassword = cpassword_txt.getText().toString();
        boolean fname_empty = (_fname == null)||(_fname.equalsIgnoreCase(""));
        boolean lname_empty = (_lname == null)||(_lname.equalsIgnoreCase(""));
        boolean username_empty = (_username == null)||(_username.equalsIgnoreCase(""));
        boolean password_empty = (_password == null)||(_password.equalsIgnoreCase(""));
        boolean cpassword_empty = (_cpassword == null)||(_cpassword.equalsIgnoreCase(""));
        boolean allentered = !(username_empty || password_empty || fname_empty || lname_empty || cpassword_empty);


        if(view.getId() == R.id.registered)
        {
            String alert_msg = RegisterEmpty();
            if(!allentered || !(_cpassword.equals(_password)))
            {

                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(RegistrationScreen.this);

                alertBuilder.setTitle("Missing Fields");
                alertBuilder.setMessage(alert_msg);
                alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();

                    }
                });

                AlertDialog alertDialog = alertBuilder.create();
                alertDialog.show();
            }


            if(allentered && (_cpassword.equals(_password)))
            {

                Intent intent = new Intent(RegistrationScreen.this, LoginScreen.class);
                intent.putExtra("name", _username);
                intent.putExtra("password",_password);
                startActivity(intent);
            }
        }

    }
}
