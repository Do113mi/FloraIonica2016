package at.bures.dominik.floraionica;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;

import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.plus.model.people.Person;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.List;


public class NeuePflanzeFragment extends Fragment implements View.OnClickListener {


    static EditText textFundpunktNr, textKmFeld, textLokalitaet,textHabitat, textBeobachter;
    String Fundpunkt;
    //Datum
    static Spinner spinnerInsel;

    Button btnFoto;
    Button btnSpeichern2;
    Button btnWeiter;

    Context context;
    static String json;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.neuepflanze_layout, null);

        View view = inflater.inflate(R.layout.neuepflanze_layout, container, false);



        btnFoto = (Button) view.findViewById(R.id.btnFoto);
        btnFoto.setOnClickListener(this);

        btnSpeichern2 = (Button) view.findViewById(R.id.btnSpeichern2);
        btnSpeichern2.setOnClickListener(this);

        btnWeiter = (Button) view.findViewById(R.id.btnWeiter);
        btnWeiter.setOnClickListener(this);



        textFundpunktNr = (EditText) view.findViewById(R.id.editTextFundpunktNr);
        spinnerInsel = (Spinner) view.findViewById(R.id.spinnerInsel);
        textLokalitaet = (EditText) view.findViewById(R.id.editTextLokalitaet);
        textKmFeld = (EditText) view.findViewById(R.id.editTextKoordinaten);
        textHabitat = (EditText) view.findViewById(R.id.editTextHabitat);
        textBeobachter = (EditText) view.findViewById(R.id.editTextBeobachter);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, beobachter);
        //textView.setAdapter(adapter);
        return view;


    }
    public static String POST(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("fundpunktNr", textFundpunktNr.getText().toString());
            jsonObject.accumulate("insel", spinnerInsel.getSelectedItem().toString());
            jsonObject.accumulate("km", textKmFeld.getText().toString());
            jsonObject.accumulate("lokalität", textLokalitaet.getText().toString());
            jsonObject.accumulate("habitat", textHabitat.getText().toString());
            jsonObject.accumulate("beobachter", textBeobachter.getText().toString());

            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();


            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }












    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btnSpeichern2: {

                //Eingabefelder speichern:
                Fundpunkt = textFundpunktNr.getText().toString();
                //Toast.makeText(this.getActivity(), "Daten gesendet", Toast.LENGTH_LONG).show();
                new HttpAsyncTask().execute("http://192.168.216.1:8080/FloraIonicaWebService/rest/blume/blumenjson");

                break;
            }

            case R.id.btnWeiter: {


                Intent intent = new Intent(getActivity(), NeuePflanzeFragment2.class);
                intent.putExtra("fundNr", textFundpunktNr.getText().toString());
                intent.putExtra("Insel", spinnerInsel.getSelectedItem().toString());
                intent.putExtra("Km", textKmFeld.getText().toString());
                intent.putExtra("Lokalität", textLokalitaet.getText().toString());
                intent.putExtra("Habitat", textHabitat.getText().toString());
                intent.putExtra("Beobachter", textBeobachter.getText().toString());

                startActivity(intent);


                /*
                Fragment mFragment = new NeuePflanzeFragment2();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containerView, mFragment).commit();
                */



            }

            break;

            case R.id.btnFoto:
                //Intent intent = new Intent(getActivity(), ImageActivity.class);
                //startActivity(intent);


                Intent intent = new Intent(getActivity(), Sync.class);
                startActivity(intent);


        }




    }

private class HttpAsyncTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... urls) {



        String txtName;

        return POST(urls[0]);
    }
    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(getActivity(), "Data Sent!", Toast.LENGTH_LONG).show();

        Toast.makeText(getActivity(), json, Toast.LENGTH_LONG).show();
        System.out.println(json);
    }
}
    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }




}


