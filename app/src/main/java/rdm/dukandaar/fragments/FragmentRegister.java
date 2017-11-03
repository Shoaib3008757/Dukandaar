package rdm.dukandaar.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rdm.dukandaar.LoginUserClass;
import rdm.dukandaar.MainActivity;
import rdm.dukandaar.Members;
import rdm.dukandaar.R;

/**
 * Created by User-10 on 01-Nov-17.
 */

public class FragmentRegister extends Fragment {

    ProgressBar bar;
    String name = null;
    EditText etName, etEmail, etPhone, etPassword;
    Button bt_register;
    final String UserRegistrationUrl = "http://donate-life.ranglerz.be/dukandaar/dukandaar_index.php";

    String userName;
    String user_type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.frg_register, container, false);

        Intent i = getActivity().getIntent();

            int usertype = i.getExtras().getInt("user_type", -1);
            user_type = Integer.toString(usertype);
            Log.e("TAG", "from actvity: " + user_type);

        bar = (ProgressBar) view.findViewById(R.id.progressBar);

        etName = (EditText) view.findViewById(R.id.et_register_name);
        etEmail = (EditText) view.findViewById(R.id.et_register_email);
        etPhone = (EditText) view.findViewById(R.id.et_register_phone);
        etPassword = (EditText) view.findViewById(R.id.et_register_passwrod);
        bt_register = (Button) view.findViewById(R.id.bt_register);

        //callig regisratin funciton
        registerButtonClick();

        return view;
    }

    public void registerButtonClick(){

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mName = etName.getText().toString();
                String mEmail = etEmail.getText().toString();
                String mPhone = etPhone.getText().toString();
                String mPassword = etPassword.getText().toString();

                if (mName.length()==0){
                    Toast.makeText(getActivity(), "Please Enter Name", Toast.LENGTH_SHORT).show();
                }
                else if (mEmail.length()==0){
                    Toast.makeText(getActivity(), "Please Enter Email", Toast.LENGTH_SHORT).show();
                }
                else if (mPhone.length()==0){
                    Toast.makeText(getActivity(), "Please Enter Phone No.", Toast.LENGTH_SHORT).show();
                }
                else if (mPassword.length()==0){
                    Toast.makeText(getActivity(), "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
                else if (!mEmail.contains("@")){
                    Toast.makeText(getActivity(), "Please Enter Valid Email", Toast.LENGTH_SHORT).show();
                }
                else if (mPassword.length()<4){
                    Toast.makeText(getActivity(), "Password should not be less than 4 charecters", Toast.LENGTH_SHORT).show();
                }
                else {

                    String refreshedToken = FirebaseInstanceId.getInstance().getToken();

                    //registering new user
                   // loginUser(mName, mEmail, mPhone, mPassword, refreshedToken);


                    Log.e("TAG", "from actvity 1: " + user_type);
                    /*AsyncDataClass asyncRequestObject = new AsyncDataClass();
                    asyncRequestObject.execute(UserRegistrationUrl, mName, mEmail, mPhone, mPassword, user_type, refreshedToken);
*/
                    registringUser(mName, mEmail, mPassword, mPhone, user_type, refreshedToken);
                    userName = mName;


                }
            }
        });
    }

    private class AsyncDataClass extends AsyncTask<String, Void, String> {

        @Override

        protected String doInBackground(String... params) {

            HttpParams httpParameters = new BasicHttpParams();

            HttpConnectionParams.setConnectionTimeout(httpParameters, 8000);

            HttpConnectionParams.setSoTimeout(httpParameters, 8000);

            HttpClient httpClient = new DefaultHttpClient(httpParameters);

            HttpPost httpPost = new HttpPost(params[0]);

            String jsonResult = "";

            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

                nameValuePairs.add(new BasicNameValuePair("name", params[1]));

                nameValuePairs.add(new BasicNameValuePair("email", params[2]));

                nameValuePairs.add(new BasicNameValuePair("phone", params[3]));

                nameValuePairs.add(new BasicNameValuePair("password", params[4]));

                nameValuePairs.add(new BasicNameValuePair("user_type", params[5]));

                nameValuePairs.add(new BasicNameValuePair("reg_id", params[6]));





                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = httpClient.execute(httpPost);

                jsonResult = inputStreamToString(response.getEntity().getContent()).toString();

                Log.e("TAG", "Resulted Returned Json object " + jsonResult.toString());

            } catch (ClientProtocolException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }

            return jsonResult;

        }

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

            bar.setVisibility(View.VISIBLE);


        }

        @Override

        protected void onPostExecute(String result) {

            super.onPostExecute(result);




            Log.e("TAG", "Resulted Value: " + result);

            if(result.equals("") || result == null){

                Toast.makeText(getActivity(), "Server connection failed", Toast.LENGTH_LONG).show();

                bar.setVisibility(View.GONE);

                return;

            }

            int jsonResult = returnParsedJsonObject(result);

            if(jsonResult == 0){

                Toast.makeText(getActivity(), "Email Address Already Registered", Toast.LENGTH_LONG).show();
                bar.setVisibility(View.GONE);


                return;

            }

            if(jsonResult == 1) {

                Toast.makeText(getActivity(), "Register Successfully", Toast.LENGTH_LONG).show();

                /*Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.putExtra("USERNAME", enteredUsername);
                intent.putExtra("MESSAGE", "You have been successfully Registered");
                startActivity(intent);*/
                bar.setVisibility(View.GONE);



            }

        }

        private StringBuilder inputStreamToString(InputStream is) {

            String rLine = "";

            StringBuilder answer = new StringBuilder();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            try {

                while ((rLine = br.readLine()) != null) {

                    answer.append(rLine);

                }

            } catch (IOException e) {

// TODO Auto-generated catch block

                e.printStackTrace();

            }

            return answer;

        }

    }

    private int returnParsedJsonObject(String result){

        JSONObject resultObject = null;

        int returnedResult = 0;

        try {

            resultObject = new JSONObject(result);

            returnedResult = resultObject.getInt("success");

        } catch (JSONException e) {

            e.printStackTrace();

        }

        return returnedResult;

    }
    //login User
    private void registringUser(String name, String email, final String password, String phone, String user_type, final String reg_id) {
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


                data.put("name",params[0]);
                data.put("email",params[1]);
                data.put("password", params[2]);
                data.put("phone", params[3]);
                data.put("user_type", params[4]);
                data.put("reg_id", params[5]);



                String result = ruc.sendPostRequest(UserRegistrationUrl,data);

                //  Toast.makeText(LoginOrRegister.this, "rss : " + result, Toast.LENGTH_SHORT).show();
                Log.e("TAG", "RESULT RESULT : " + result);

                return  result;


            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                bar.setVisibility(View.GONE);
                Log.e("TAG", "result status: " + result);

                Log.e("TAG", "Resulted Value: " + result);

                if(result.equals("") || result == null){

                    Toast.makeText(getActivity(), "Server connection failed", Toast.LENGTH_LONG).show();

                    bar.setVisibility(View.GONE);

                    return;

                }

                int jsonResult = returnParsedJsonObject(result);

                if(jsonResult == 0){

                    Toast.makeText(getActivity(), "Email Address Already Registered", Toast.LENGTH_LONG).show();
                    bar.setVisibility(View.GONE);


                    return;

                }

                if(jsonResult == 1) {

                    //Toast.makeText(getActivity(), "Register Successfully", Toast.LENGTH_LONG).show();

                    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                    alert.setTitle("Register Successfully");
                    alert.setMessage(userName + " Has Been Register Successfully, Press Ok To Login");
                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            getActivity().finish();

                            Intent currentActivity = new Intent(getActivity(), MainActivity.class);
                            startActivity(currentActivity);

                        }
                    });
                    alert.setIcon(R.drawable.item_arrow);
                    alert.setCancelable(false);
                    alert.show();
                    bar.setVisibility(View.GONE);

                }


            }
        }

        LoginUser ru = new LoginUser();
        ru.execute(name,email, password, phone, user_type, reg_id);
    }

}

