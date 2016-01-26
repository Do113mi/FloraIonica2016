package at.bures.dominik.floraionica;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class NeuePflanzeFragment extends Fragment implements View.OnClickListener {


    EditText textFundpunktNr;
    String Fundpunkt;
    //Datum
    Spinner spinnerInsel;
    EditText textKmFeld;
    EditText textLokalitaet;
    EditText textHabitat;
    EditText textBeobachter;

    Button btnFoto;
    Button btnSpeichern2;
    Button btnWeiter;


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

    /**
    private void showToastMessage(String msg) {

        //Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        Toast toast1 = Toast.makeText(null, msg, Toast.LENGTH_SHORT);

        toast1.show();
    }
     */


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
                        null,
                        null,
                        null,
                        null,
                        null,
                        spinnerInsel.getSelectedItem().toString(),
                        null,
                        null));

                //Log.d("Fundpunkt: ", textFundpunktNr.getText().toString());

                // Reading all flowers
                Log.d("Reading: ", "Reading all flowers..");
                List<DatenPflanze> pflanzen = db.getAllFlowers();

                //GEHT auch oida
                //Log.d("Delete id 4: ", "bla");
                //db.deleteByID(4);


                for (DatenPflanze cn : pflanzen) {
                    String log = "FundNr: " + cn.getFundpunktNr() + ", Datum: " + cn.getDatum() + ", Insel: " + cn.getInsel() +
                            ", Km: " + cn.getKmFeld() + ", Lokalität: " + cn.getLokalitaet() + ", Habitat: " + cn.getHabitat() +
                            ", Beobachter: " + cn.getBeobachter() + ", Taxon: " + cn.getTaxon() + ", Bezirk: " + cn.getBezirk() +
                            ", Herbar: " + cn.getHerbar() + ", Kultur: "+ cn.getKulturNr() + ", Status: " + cn.getStatus() +
                            ", Habitus: " + cn.getHabitus() + ", Anmerkungen: " + cn.getAnmerkungen();

                    // Writing flowers to log
                    Log.d("Name: ", log);
                }



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
                Intent intent = new Intent(getActivity(), ImageActivity.class);
                startActivity(intent);


        }



    }


}


