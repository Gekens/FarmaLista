package com.example.giacomo.farmalista;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class UFC11 extends AppCompatActivity {

    JSONArray mArrJson;
    JSONObject mObjJson;

    Button accedi;
    EditText email, password;
    String prova;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ufc11);



        accedi = (Button) findViewById(R.id.buttonAccedi);
        email = (EditText) findViewById(R.id.emailLogin);
        password = (EditText) findViewById(R.id.passwordLogin);

        accedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                // se i campi email e password sono completi verifico l'identità utente
                // altrimenti avviso con un messaggio di toast
                if (email.getText().toString().length() != 0 && password.getText().toString().length() != 0) {

                    // cripto la password inserita dall'utente al momento del login, così posso confrontarla
                    // con quella inserita in fase di registrazione e criptata poi salvata dentro la variabile
                    // statica ApiCall.credenziali
                    String passwordLogin = UFC2.md5(password.getText().toString());
                    // salvo in una stringa la mail inserita dall'utente
                    String emailLogin = email.getText().toString();

                    // faccio una richiesta al database remoto che mi risponde con una stringa json che al momento
                    // è simulata dalla variabile globale stringa Apical.credenziali, da questa stringa ricavo un oggetto
                    // json dal quale prendo le proprietà che mi servono per verificare l'identità dell'utente
                    //Log.d("json",ApiCall.credenziali);


                    String mailDB = null;
                    String pswDB = null;

                    // creo un jsonobject con la mail e password inserite dall'utente
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("email", emailLogin);
                        obj.put("password", passwordLogin);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    // al momento salvo il json in una variabile globale di tipo string
                    //credenziali = obj.toString();
                    //ApiCall.credenziali = credenziali;

                    // istruzioni per scrivere nel db remoto
                    ApiCall api = new ApiCall(new ApiCall.AsyncResponse() {
                        @Override
                        public void processFinish(String output) {
                            Log.d("out", "#"+output+"#");
                            prova = output.trim();
                            if (prova.equals("test")) {
                                Log.d("out", "sono qui dentro!");
                                Intent intent = new Intent(UFC11.this, UFC8.class);
                                startActivity(intent);
                                finish();
                            } else {
                                //Boolean test = output.equals("test");
                                //Log.d("out", test.toString());
                                Toast.makeText(UFC11.this, "Non sei registrato", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    api.execute(obj.toString(), "http://10.0.2.2:3000/login", "POST");
                }
                //try {
                //mObjJson = new JSONObject(ApiCall.credenziali);

                // stampe di prova
                //Log.d("json",mObjJson.get("email").toString());
                //Log.d("json",mObjJson.get("password").toString());

                //mailDB = mObjJson.get("email").toString();
                //pswDB = mObjJson.get("password").toString();

                //} catch (JSONException e) {
                //  e.printStackTrace();
                //mObjJson = null;
                //}



                   /* // verifico che email e password siano quelle dell'utente
                    if (email.getText().toString().equals(mailDB) && passwordLogin.equals(pswDB)) {
                        Intent intent = new Intent(UFC11.this, UFC8.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(UFC11.this, "Username o password errati", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UFC11.this, "Username o password errati", Toast.LENGTH_SHORT).show();
                }

            }*/
            }
                    });
                }
}

