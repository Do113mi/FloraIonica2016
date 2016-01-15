package at.bures.dominik.floraionica;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
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

import java.io.File;
import java.io.IOException;
import java.util.List;


public class NeuePflanzeFragment extends Fragment implements View.OnClickListener {

    //Button btnspeichern;
    Button btnfoto;
    EditText textFundpunktNr;
    String Fundpunkt;
    //Datum
    Spinner spinnerInsel;
    EditText textLokalitaet;
    EditText textKmFeld;
    EditText textHabitat;
    EditText textBeobachter;




    Button btnSpeichern2;

    EditText textFund;
    EditText textTaxon;
    EditText textBezirk;
    EditText textHerbar;
    EditText textKultur;
    Spinner spinnerstatus;
    EditText textHabitus;
    EditText textAnmerkungen;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.neuepflanze_layout, null);

        View view = inflater.inflate(R.layout.neuepflanze_layout, container, false);


        //btnspeichern.setOnClickListener(this);
        btnfoto = (Button) view.findViewById(R.id.btnfoto);
        btnfoto.setOnClickListener(this);


        btnSpeichern2 = (Button) view.findViewById(R.id.btnSpeichern2);
        btnSpeichern2.setOnClickListener(this);

        textTaxon = (EditText) view.findViewById(R.id.editTextTaxon);
        textBezirk = (EditText) view.findViewById(R.id.editTextBezirk);
        textHerbar = (EditText) view.findViewById(R.id.editTextHerbar);



        textFundpunktNr = (EditText) view.findViewById(R.id.editTextFundpunktNr);
        spinnerInsel = (Spinner) view.findViewById(R.id.spinnerInsel);
        textLokalitaet = (EditText) view.findViewById(R.id.editTextLokalitaet);
        textKmFeld = (EditText) view.findViewById(R.id.editTextKoordinaten);
        textHabitat = (EditText) view.findViewById(R.id.editTextHabitat);
        textBeobachter = (EditText) view.findViewById(R.id.editTextBeobachter);
        textKultur = (EditText) view.findViewById(R.id.editTextKulturNr);
        textHabitus = (EditText) view.findViewById(R.id.editTextHabitus);
        textAnmerkungen = (EditText) view.findViewById(R.id.editTextAnmerkungen);
        spinnerstatus = (Spinner) view.findViewById(R.id.spinnerStatus);


        return view;

        // Get a reference to the AutoCompleteTextView in the layout
        //AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_beobachter);
        // Get the string array
        //String[] beobachter = getResources().getStringArray(R.array.beobachter_array);
        // Create the adapter and set it to the AutoCompleteTextView
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, beobachter);
        //textView.setAdapter(adapter);


    }

    private void showToastMessage(String msg) {

        //Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        Toast toast1 = Toast.makeText(null, msg, Toast.LENGTH_SHORT);

        toast1.show();
    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btnSpeichern2: {

                //Eingabefelder speichern:
                Fundpunkt = textFundpunktNr.getText().toString();
                Toast.makeText(this.getActivity(), "oh baby", Toast.LENGTH_LONG).show();

                DatabaseHandler db = new DatabaseHandler(getContext(), "PfDB", null, 1);
                //DatabaseHandler db = new DatabaseHandler(this);

                /**
                 * CRUD Operations
                 * */
                // Inserting flowers

                Log.d("Insert: ", Fundpunkt.toString());
                //db.addFlower(new DatenPflanze("Ilyas", null, "Insel", null, null, null, null, null, null, null, null, null, null, null, null)); //15 Para

                //db.addFlower(new DatenPflanze(.getFundpunktNr(), R.id.textViewDate, "Wien", "blabla", "100", "trocken", "jemand", "taxon1wtf", "Bezirk", "herbar", "Paldat", "kultNr", "status", "habitus", "anmerk"));


                db.addFlower(new DatenPflanze(textFundpunktNr.getText().toString(),
                        null,
                        spinnerInsel.getSelectedItem().toString(),
                        textLokalitaet.getText().toString(),
                        textKmFeld.getText().toString(),
                        textHabitat.getText().toString(),
                        textBeobachter.getText().toString(),
                        textTaxon.getText().toString(),
                        textBezirk.getText().toString(),
                        textHerbar.getText().toString(),
                        null,
                        textKultur.getText().toString(),
                        spinnerInsel.getSelectedItem().toString(),
                        textHabitus.getText().toString(),
                        textAnmerkungen.getText().toString()));

                //Log.d("Fundpunkt: ", textFundpunktNr.getText().toString());

                // Reading all flowers
                Log.d("Reading: ", "Reading all flowers..");
                List<DatenPflanze> pflanzen = db.getAllFlowers();

                //GEHT auch oida
                //Log.d("Delete id 4: ", "bla");
                //db.deleteByID(4);


                for (DatenPflanze cn : pflanzen) {
                    String log = "FundNr: " + cn.getFundpunktNr() + ", Datum: " + cn.getDatum() + ", Insel: " + cn.getInsel() + ", Lokalität: " + cn.getLokalitaet() + ", Km: " + cn.getKmFeld() + ", Habitat: " + cn.getHabitat() + ", Beobachter: " + cn.getBeobachter() + ", Taxon: " + cn.getTaxon() + ", Bezirk: " + cn.getBezirk() + ", Herbar: " + cn.getHerbar() + ", Kultur: "+ cn.getKulturNr() + ", Status: " + cn.getStatus() + ", Habitus: " + cn.getHabitus() + ", Anmerkungen: " + cn.getAnmerkungen();
                    //+ ", Taxon: " + cn.getTaxon() + ", Bezirk: " + cn.getBezirk() + ", Herbar: " + cn.getHerbar();
                    // Writing flowers to log
                    Log.d("Name: ", log);
                }

                break;
            }

            case R.id.btnfoto: {

                Intent intent = new Intent(getActivity(), ImageActivity.class);
                startActivity(intent);


            }

            break;
        }


        //.... etc
    }


}
    

