package rdm.dukandaar.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import rdm.dukandaar.LoginUserClass;
import rdm.dukandaar.MainActivity;
import rdm.dukandaar.R;

/**
 * Created by User-10 on 01-Nov-17.
 */

public class FragmentLogin extends Fragment {

    ProgressBar bar;
    String name = null;
    final String UserLoginUrl = "http://donate-life.ranglerz.be/dukandaar/dukandaar_index.php";
    EditText et_login_email;
    EditText et_login_password;
    Button bt_login;

    String notLogin = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_login, container, false);

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("TAG", "device token: " + refreshedToken);

        bar = (ProgressBar) view.findViewById(R.id.progressBar);
        et_login_email = (EditText) view.findViewById(R.id.et_login_email);
        et_login_password = (EditText) view.findViewById(R.id.et_login_password);
        bt_login = (Button) view.findViewById(R.id.bt_login);

        loginBtClick();

        return view;
    }

    public void loginBtClick(){

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mEmail = et_login_email.getText().toString();
                String mPassword = et_login_password.getText().toString();

                if (mEmail.length()==0){
                    Toast.makeText(getActivity(), "Please Enter Email", Toast.LENGTH_SHORT).show();
                }
                else if (mPassword.length()==0){
                    Toast.makeText(getActivity(), "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
                else if (!mEmail.contains("@")){
                    Toast.makeText(getActivity(), "Please Enter Valid Email", Toast.LENGTH_SHORT).show();
                }
                else if (mPassword.length()<4){
                    Toast.makeText(getActivity(), "Password shoulb be more then 4 charecters", Toast.LENGTH_SHORT).show();
                }
                else {


                    //calling user login task
                    loginUser(mEmail, mPassword);
                }
            }
        });
    }

    //login User
    private void loginUser(String email, final String password) {
        class LoginUser extends AsyncTask<String, Void, String>{


            LoginUserClass ruc = new LoginUserClass();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                bar.setVisibility(View.VISIBLE);


            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String,String>();

                //for login user


                data.put("email",params[0]);
                data.put("password",params[1]);



                String result = ruc.sendPostRequest(UserLoginUrl,data);

                //  Toast.makeText(LoginOrRegister.this, "rss : " + result, Toast.LENGTH_SHORT).show();
                Log.e("TAG", "RESULT RESULT : " + result);

                return  result;


            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                bar.setVisibility(View.GONE);

                Log.e("TAG", "result status: " + s);

                if (s != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(s);

                        JSONObject resul = jsonObj.optJSONObject("result"); //for checking reponse is object or array
                        Log.e("TAG", "resso 1243 " + resul);

                        if (resul!=null) { //not null mean response is objectm, null mean respose is array
                            Toast.makeText(getActivity(), "Email or Password is Invalid", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            // Getting JSON Array node
                            JSONArray contacts = jsonObj.getJSONArray("result");

                            Log.e("TAG", "RESULT 2" + contacts);

                            JSONObject c = contacts.getJSONObject(0);
                            String name = c.getString("name");
                            String email = c.getString("email");
                            String phone = c.getString("phone");
                            String reg_id = c.getString("reg_id");


                            Log.e("TAG", "Loged Name " + name);
                            Log.e("TAG", "Loged Email " + email);
                            Log.e("TAG", "Loged Phone " + phone);
                            Log.e("TAG", "Loged Reg_ID " + reg_id);

                            Toast.makeText(getActivity(), "Login Successfully", Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                            Intent mainActivity = new Intent(getActivity(), MainActivity.class);
                            startActivity(mainActivity);

                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", 0);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("name", name);
                            editor.putString("email", email);
                            editor.putString("phone",phone);
                            editor.clear();
                            editor.commit();

                        }

                        }catch( final JSONException e){
                            Log.e("TAG", "Json parsing error: " + e.getMessage());
                            Toast.makeText(getActivity(), "Server Connection Error", Toast.LENGTH_SHORT).show();


                        }



                    }
                    else{
                        Log.e("TAG", "Couldn't get json from server.");
                        Toast.makeText(getActivity(), "Server Connection Error", Toast.LENGTH_SHORT).show();

                    }
            }


        }

        LoginUser ru = new LoginUser();
        ru.execute(email,password);
    }


}