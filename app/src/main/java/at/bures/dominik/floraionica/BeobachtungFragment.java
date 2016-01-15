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




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.beobachtung_layout, container, false);




        //textFund = (EditText) view.findViewById(R.id.editTextFundpunktNr);
        //textFund = (EditText)getView().findViewById(R.id.editTextFundpunktNr); NULLPOIINTER

        //return inflater.inflate(R.layout.beobachtung_layout,null);
        return view;

    }


    @Override
    public void onClick(View v) {

        Toast.makeText(this.getActivity(), "DANK 420", Toast.LENGTH_LONG).show();
        //Eingabefelder speichern:


        //Log.d("adfaf",neue.Fundp);


        //DatabaseHandler db = new DatabaseHandler(getContext(), "PfDB", null, 1);
        //DatabaseHandler db = new DatabaseHandler(this);

        //Log.d("Insert2: ", neue.Fundpunkt.toString());


        //db.updateFlower(new DatenPflanze(null, null, null, null, null, null, null, textTaxon.getText().toString(), textBezirk.getText().toString(), textHerbar.getText().toString(), null, null, null, null, null));

        //db.addFlower(new DatenPflanze(null, null, null, null, null, null, null, textTaxon.getText().toString(), textBezirk.getText().toString(), textHerbar.getText().toString(), null, null, null, null, null));

        //Log.d("Fundpunkt: ", textFundpunktNr.getText().toString());

        // Reading all flowers

    }

    private void showToastMessage(String msg) {

        //Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        Toast toast1 = Toast.makeText(null, msg, Toast.LENGTH_SHORT);

        toast1.show();
    }
}
