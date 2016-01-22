package at.bures.dominik.floraionica;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;


public class NeuePflanzeFragment2 extends Activity {







    Button btnSpeichern3;
    EditText textBezirk;
    EditText textHerbar;
    EditText textKultur;
    Spinner spinnerstatus;
    EditText textHabitus;
    EditText textAnmerkungen;
    static String fundNr;


    @Nullable

    protected void onCreate(Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.neuepflanze_layout, null);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.neuepflanze2_layout);
        String insel, km, lokalität, habitat, beobachter;
        if(savedInstanceState==null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                fundNr = null;
                insel = null;
                km = null;
                lokalität = null;
                habitat = null;
                beobachter = null;
            } else {
                fundNr = extras.getString("fundNr");
            }
        }
            else
            {
                fundNr=(String) savedInstanceState.getSerializable("fundNr");
            }

                /*
        intent.putExtra("fundNr", textFundpunktNr.getText().toString());
        intent.putExtra("Insel", spinnerInsel.getSelectedItem().toString());
        intent.putExtra("Km", textKmFeld.getText().toString());
        intent.putExtra("Lokalität", textLokalitaet.getText().toString());
        intent.putExtra("Habitat", textHabitat.getText().toString());
        intent.putExtra("Beobachter", textBeobachter.getText().toString());
        */



        btnSpeichern3 = (Button) findViewById(R.id.btnSpeichern3);
        btnSpeichern3.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 Toast.makeText(getApplicationContext(), "lol", Toast.LENGTH_LONG).show();
                                                 datenSeichern();
                                                 finish();
                                             }
                                         });

                textHerbar = (EditText) findViewById(R.id.editTextHerbar);

        textBezirk = (EditText) findViewById(R.id.editTextBez);
        textKultur = (EditText) findViewById(R.id.editTextKulturNr);
        textHabitus = (EditText) findViewById(R.id.editTextHabitus);
        textAnmerkungen = (EditText) findViewById(R.id.editTextAnmerkungen);
        spinnerstatus = (Spinner) findViewById(R.id.spinnerStatus);

    }










    public void datenSeichern() {
        DatabaseHandler db = new DatabaseHandler(getApplicationContext(), "PfDB", null, 1);

        db.addFlower(new DatenPflanze(fundNr,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                textBezirk.getText().toString(),
                textHerbar.getText().toString(),
                null,
                textKultur.getText().toString(),
                null,
                textHabitus.getText().toString(),
                textAnmerkungen.getText().toString()));

        Log.d("Reading: ", "Reading all flowers..");
        List<DatenPflanze> pflanzen = db.getAllFlowers();

        for (DatenPflanze cn : pflanzen) {
            String log = "FundNr: " + cn.getFundpunktNr() + ", Datum: " + cn.getDatum() + ", Insel: " + cn.getInsel() +
                    ", Lokalität: " + cn.getLokalitaet() + ", Km: " + cn.getKmFeld() + ", Habitat: " + cn.getHabitat() +
                    ", Beobachter: " + cn.getBeobachter() + ", Taxon: " + cn.getTaxon() + ", Bezirk: " + cn.getBezirk() +
                    ", Herbar: " + cn.getHerbar() + ", Kultur: " + cn.getKulturNr() + ", Status: " + cn.getStatus() +
                    ", Habitus: " + cn.getHabitus() + ", Anmerkungen: " + cn.getAnmerkungen();

            // Writing flowers to log
            Log.d("Name: ", log);

        }
    }
        //.... etc




}
    

