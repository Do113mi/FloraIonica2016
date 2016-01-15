package at.bures.dominik.floraionica;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;


public class BeobachtungFragment extends Fragment implements View.OnClickListener {

    Button btnSpeichern2;

    EditText textFund;
    EditText textTaxon;
    EditText textBezirk;
    EditText textHerbar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.beobachtung_layout, container, false);

        btnSpeichern2 = (Button) view.findViewById(R.id.btnSpeichern2);
        btnSpeichern2.setOnClickListener(this);

        textTaxon = (EditText) view.findViewById(R.id.editTextTaxon);
        textBezirk = (EditText) view.findViewById(R.id.editTextBezirk);
        textHerbar = (EditText) view.findViewById(R.id.editTextHerbar);

        //return inflater.inflate(R.layout.beobachtung_layout,null);
        return view;

    }


    @Override
    public void onClick(View v) {

        Toast.makeText(this.getActivity(), "DANK 420", Toast.LENGTH_LONG).show();
        //Eingabefelder speichern:


        DatabaseHandler db = new DatabaseHandler(getContext(), "PfDB", null, 1);
        //DatabaseHandler db = new DatabaseHandler(this);

        Log.d("Insert2: ", "Inserting2 ..");


        db.updateFlower(new DatenPflanze(null, null, null, null, null, null, null, textTaxon.getText().toString(), textBezirk.getText().toString(), textHerbar.getText().toString(), null, null, null, null, null));

        //db.addFlower(new DatenPflanze(null, null, null, null, null, null, null, textTaxon.getText().toString(), textBezirk.getText().toString(), textHerbar.getText().toString(), null, null, null, null, null));

        //Log.d("Fundpunkt: ", textFundpunktNr.getText().toString());

        // Reading all flowers
        Log.d("Reading2: ", "Reading all flowers2..");
        List<DatenPflanze> pflanzen = db.getAllFlowers();

        //GEHT auch oida
        //Log.d("Delete id 4: ", "bla");
        //db.deleteByID(4);


        for (DatenPflanze cn : pflanzen) {
            String log =  "FundNr: " + cn.getFundpunktNr() + ", Datum: " + cn.getDatum() + ", Insel: " + cn.getInsel() + ", Lokalität: " + cn.getLokalitaet() + ", Km: " + cn.getKmFeld() + ", Habitat: " + cn.getHabitat() + ", Beobachter: " + cn.getBeobachter() + ", +++ Taxon: " + cn.getTaxon() + ", Bezirk: " + cn.getBezirk() + ", Herbar: " + cn.getHerbar();

            // Writing flowers to log
            Log.d("Name: ", log);
        }
    }

    private void showToastMessage(String msg) {

        //Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        Toast toast1 = Toast.makeText(null, msg, Toast.LENGTH_SHORT);

        toast1.show();
    }
}
