package com.example.gerardo.sqltest;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gerardo.sqltest.DataBase.SQLAdapter;

public class MainActivity extends AppCompatActivity {

    EditText userName, password, personName;

    SQLAdapter sqlAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.userText);
        password = (EditText) findViewById(R.id.passText);
        personName = (EditText) findViewById(R.id.editText);

        sqlAdapter =  new SQLAdapter(this);

    }

    //Metodo para agregar usuarios a la base de datos
    public void addUser(View view) {
        String user = userName.getText().toString();
        String pass = password.getText().toString();

        if(user.length()==0 || pass.length()==0)
        {
        Message.message(view, "el usuario o la contraseña no pueden estar vacios");
        }
        else
        {
        long id = sqlAdapter.insertData(user, pass);

        //si se agrego correctamente devolvera la id, sino sera un -1
        if (id < 0) {
            Message.message(view, "Algo ha fallado");
        } else {
            Message.message(view, "Usuario agregado correctamente");

        }
        }
    }


    //metodo para mostrar todos los usuarios
    public void viewDetails(View view){

        String data = sqlAdapter.getAllData();
        Message.message(view, data);
    }

    //metodo para eliminar un usuario
    public void deleteUser(View view){
        String dUser = personName.getText().toString();
        int count = sqlAdapter.deleteUser(dUser);
        if (count==0)
        {
            Message.message(view, "no hay usuarios con ese nombre");
        }
        else
        {
        Message.message(view, "se han eliminado "+count+" usuarios");
        }
    }
}
