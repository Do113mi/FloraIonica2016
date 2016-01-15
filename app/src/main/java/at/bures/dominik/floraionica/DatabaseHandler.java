package at.bures.dominik.floraionica;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dominik on 18.12.15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "flowerpower"; // Uppercase geht?

    // FlowerPower table name
    private static final String TABLE_Pflanzentable = "pflanzentable";


    // +++ UPPERCASE? +++

    // Name der Spalten in der Datenbank.

    //Interne Datebank ID
    private static final String KEY_ID = "id";      // 0
    private static final String KEY_FundpunktNr = "fundpunktNr";    // 1
    private static final String KEY_Taxon = "taxon";        // 2
    private static final String KEY_Datum = "datum";      // 3
    private static final String KEY_Insel = "Insel";    // 4
    private static final String KEY_Lokalitaet = "lokalität";   // 5
    private static final String KEY_KmFeld = "kmFeld";  // 6
    private static final String KEY_Habitat = "habitat";    // 7
    private static final String KEY_Beobachter = "beobachter"; //8
    // Position fehlt noch
    private static final String KEY_Bezirk = "bezirk";
    private static final String KEY_Herbar = "Herbar";
    private static final String KEY_PalDat = "palDat";
    private static final String KEY_KulturNr = "kulturNr";
    private static final String KEY_Status = "status";
    private static final String KEY_Habitus = "habitus";
    private static final String KEY_Anmerkungen = "anmerkungen";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /*  onCreate:
    *
    *  Datenbank (lokal) wird erzeugt!
    *
    * ACHTUNG, Beistriche und !Leerzeichen! beachteten sonst ERROR.
    */

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FLOWER_TABLE =
                "CREATE TABLE " + TABLE_Pflanzentable + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FundpunktNr + " VARCHAR(45),"
                        + KEY_Taxon + " VARCHAR(55)," + KEY_Datum + " DATE," + KEY_Insel + " VARCHAR(45)," + KEY_Lokalitaet + " TEXT,"
                        + KEY_KmFeld + " VARCHAR(55)," + KEY_Habitat + " TEXT," + KEY_Beobachter + " TEXT," + KEY_Bezirk + " VARCHAR(45),"
                        + KEY_Herbar + " VARCHAR(45)," + KEY_PalDat + " VARCHAR(45)," + KEY_KulturNr + " VARCHAR(45)," + KEY_Status + " VARCHAR(45),"
                        + KEY_Habitus + " TEXT," + KEY_Anmerkungen + " TEXT"


                        + ")";
        db.execSQL(CREATE_FLOWER_TABLE);
    }

    /*  onUpgrade:
    *
    *  Datenbank wird geloescht und neu erzeugt.
    *
    */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Pflanzentable);

        // Create tables again
        onCreate(db);
    }

    /*  addFlower:
    *
    *  Es wird eine Flower mit den eingegebenen Werten aus der App in die Datenbank (lokal) gespeichert.
    *
    */

    void addFlower(DatenPflanze pflanze) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // 1. Tab FUNDPUNKT Eingabe:

        // in die Datenbank-Spalte "KEY_FundpunktNr" werden die werden .get() Werte geschrieben.
        values.put(KEY_FundpunktNr, pflanze.getFundpunktNr());

        values.put(KEY_Datum, "");// hax lol
        //values.put(KEY_Datum, pflanze.getDatum().toString());
        values.put(KEY_Insel, pflanze.getInsel());
        values.put(KEY_Lokalitaet, pflanze.getLokalitaet());
        values.put(KEY_KmFeld, pflanze.getKmFeld());
        values.put(KEY_Habitat, pflanze.getHabitat());
        values.put(KEY_Beobachter, pflanze.getBeobachter());

        // 2. Tab BEOBACHTUNGEN Eingabe:

        values.put(KEY_Taxon, pflanze.getTaxon());
        values.put(KEY_Bezirk, pflanze.getBezirk());
        values.put(KEY_Herbar, pflanze.getHerbar());
        values.put(KEY_PalDat, pflanze.getPalDat());
        values.put(KEY_KulturNr, pflanze.getKulturNr());
        values.put(KEY_Status, pflanze.getStatus());
        values.put(KEY_Habitus, pflanze.getHabitus());
        values.put(KEY_Anmerkungen, pflanze.getAnmerkungen());

        // Inserting Row
        db.insert(TABLE_Pflanzentable, null, values);
        db.close(); // Closing database connection
    }


    // Getting single contact
    DatenPflanze getFlower(int fundpunktNr) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_Pflanzentable, new String[]{KEY_ID, KEY_FundpunktNr}, KEY_FundpunktNr + "=?",
                new String[]{String.valueOf(KEY_FundpunktNr)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        //DatenPflanze flower = new DatenPflanze( (cursor.getString(0), cursor.getString(1), cursor.getString(2)) ;
        // return contact
        return null; //flower
    }



    /*  getAllFlowers():
    *
    *  Gibt alle Pflanzen in der Datenbank (lokal) zurueck.
    *
    */

    public List<DatenPflanze> getAllFlowers() {
        List<DatenPflanze> pflanzenList = new ArrayList<DatenPflanze>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_Pflanzentable;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DatenPflanze pflanze = new DatenPflanze();

                //FundpunktNr wird mittels Cursor gesetzt. Wert an der Stelle "0" wird geholt.
                pflanze.setFundpunktNr(cursor.getString(1));
                //Log.d("interne ID:", cursor.getString(0));
                //pflanze.setDatum(cursor.getString()1));   //date?
                pflanze.setInsel(cursor.getString(4));
                //Log.d("Test2", cursor.getString(2));
                pflanze.setLokalitaet(cursor.getString(5));
                //Log.d("Test3", cursor.getString(3));
                pflanze.setKmFeld(cursor.getString(6));
                //Log.d("Test4", cursor.getString(4));
                pflanze.setHabitat(cursor.getString(7));
                //Log.d("Test5", cursor.getString(5));
                pflanze.setBeobachter(cursor.getString(8));     //oida was ist bitte curser? wie eine Array oder wos?


                // FUCK SHIT UP

                //Log.d("Test6", cursor.getString(6));


                pflanze.setTaxon(cursor.getString(2));
                pflanze.setBezirk(cursor.getString(9));
                pflanze.setHerbar(cursor.getString(10));
                pflanze.setPalDat(cursor.getString(11));
                pflanze.setKulturNr(cursor.getString(12));
                pflanze.setStatus(cursor.getString(13));
                pflanze.setHabitus(cursor.getString(14));
                pflanze.setAnmerkungen(cursor.getString(15));

                // Adding flowers to list
                pflanzenList.add(pflanze);
            } while (cursor.moveToNext());
        }

        // return flower list
        return pflanzenList;
    }

    // Updating single Flower
    public int updateFlower(DatenPflanze pflanze) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Taxon, pflanze.getTaxon());
        values.put(KEY_Bezirk, pflanze.getBezirk());


        // updating row mittels FundpunktNr
        // getFundpunktNr(); ?!
        return db.update(TABLE_Pflanzentable, values, KEY_FundpunktNr + " = ?", new String[]{String.valueOf(pflanze.getFundpunktNr())});


    }

    // Deleting single flower. Oder anstatt pflanze zu übergeben, mittels Funpunktnummer identifizieren und löschen?
    public void deleteFlower(DatenPflanze pflanze) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Pflanzentable, KEY_FundpunktNr + " = ?",
                new String[]{String.valueOf(pflanze.getFundpunktNr())});
        db.close();
    }


    /*  deleteByID (frueher deleteId):
     *
     *  Loescht einzelne Flower (Eintrag) in der Datenbank per ID (nicht Fundpunktnummer, sondern Datenbankeigene ID)
     *
     *  +++ METHODE ZUM TESTEN, NICHT IN DER APP AUFRUFBAR +++
     */

    public void deleteByID(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Pflanzentable, KEY_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }


    /*  updateByFund:
     *
     *  Workaround für updateFlowers();
     *
     *  Gedacht:
     *  1. Tab ist bereits in der Datenbank (lokal) gespeichert, inkl FundpunktNr
     *  mit getFundpunktNr() nach der ROW abfragen und einfach per SQL Statement updaten.
     *
     */

    public void updateByFund(String fundpunkt) {

        String query;

        //
    }

       /*  getPflanzenCount():
        *
        *  Gibt zurueck wieviele Eintraege in der Datenbank (lokal) gespeichert sind.
        *
        *  +++ METHODE ZUM TESTEN, NICHT IN DER APP AUFRUFBAR +++
        */

    public int getPflanzenCount() {
        String countQuery = "SELECT  * FROM " + TABLE_Pflanzentable;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}