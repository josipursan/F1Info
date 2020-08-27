package josipursan.ferit.f1info

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.statistics_results.*
import org.json.JSONObject
import org.w3c.dom.Text


class StatisticsResults : AppCompatActivity() {
    companion object
    {
        val EXTRA_APICALLURL : String = "josipursan.ferit.f1info.EXTRA_APICALLURL"
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.statistics_results)

        val intent : Intent = getIntent()

        if(circuitsStringfilter in intent.getStringExtra(EXTRA_APICALLURL))
        {
            //testTextView.setText(intent.getStringExtra(EXTRA_APICALLURL))
            executeAPI()
        }
        else if(constructorsStringFilter in intent.getStringExtra(EXTRA_APICALLURL))
        {
            //testTextView.setText(intent.getStringExtra(EXTRA_APICALLURL))
            executeAPI()
        }

        createTable()
    }


    private fun executeAPI()
    {
        val intent : Intent = getIntent()
        val queue = Volley.newRequestQueue(this)

        val apiExecution = JsonObjectRequest(Request.Method.GET, intent.getStringExtra(
            EXTRA_APICALLURL), null, Response.Listener { response -> circuitQueryDataGathering(response) },
            Response.ErrorListener{error -> Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()})

        queue.add(apiExecution)
    }

    private fun circuitQueryDataGathering(ergastResponse : JSONObject)
    {
        var firstLayer = ergastResponse.getJSONObject("MRData").getJSONObject("RaceTable").getJSONArray("Races").getJSONObject(0)
        var resultsLayer = firstLayer.getJSONArray("Results")

        var twoDimArrayOfDataForTable = Array(6){ arrayOfNulls<Any>(resultsLayer.length())}
        // 0 -> position
        // 1 -> points
        // 2 -> givenName
        // 3 -> familyName
        // 4 -> constructorName

        // Podaci koji popunjavaju header tablice, tj. osnovne podatke o utrci
        twoDimArrayOfDataForTable[5][0] = firstLayer["raceName"]
        twoDimArrayOfDataForTable[5][1] = firstLayer["season"]
        twoDimArrayOfDataForTable[5][2] = firstLayer["date"]

        // Pozicija, bodovi, ime, prezime, ekipa - podaci koji se povlace iz Results JSON arraya iterativno jer za svakog vozaca postoji JSONObject u Results JSONArrayu
        for(i in 0..resultsLayer.length()-1)
        {
            twoDimArrayOfDataForTable[0][i] = resultsLayer.getJSONObject(i)["position"]
            twoDimArrayOfDataForTable[1][i] = resultsLayer.getJSONObject(i)["points"]
            twoDimArrayOfDataForTable[2][i] = resultsLayer.getJSONObject(i).getJSONObject("Driver")["givenName"]
            twoDimArrayOfDataForTable[3][i] = resultsLayer.getJSONObject(i).getJSONObject("Driver")["familyName"]
            twoDimArrayOfDataForTable[4][i] = resultsLayer.getJSONObject(i).getJSONObject("Constructor")["name"]
        }

        createTableCircuitData(twoDimArrayOfDataForTable)

        //Toast.makeText(this, twoDimArrayOfDataForTable[5][3].toString(), Toast.LENGTH_LONG).show()



        // Kad se naleti na "Races" ili "Results" segment u JSON-u, radi se o polju unutar JSON-a ([] oznake), zbog cega za pristup tom segmentu treba korisiti getJSONArray funkciju (vidi liniju 108)
        // U tom trenutku kad smo pokupili getJSONArray, moze se iskoristiti length() metoda da se dobije broj utrka za tog vozaca za tu sezonu.
        // Kada smo usli u listu pomocu metode getJSONArray, potrebno je koji JSONObject zelimo, tj. u slucaju utrke koju utrku zelimo (0,1,2,....). Ovo se radi metodom getJSONObject kojoj se predaje indeks.
        // Kada smo odabrali utrku koju zelimo, jednostavno u tom polju odabiremo koji podataka nam treba.
    }

    private fun createTableCircuitData(gatheredData : Array<Array<Any?>>)
    {
        Toast.makeText(this, gatheredData[2][0].toString(), Toast.LENGTH_LONG).show()
    }

    // test popunjavanja tablice - za sada je plan praznu tablicu definiranu u statistics_results.xml popuniti s podacima ovisno o queryu ->
    // Za svaki query ide drugaciji nacin popunjavanja zbog drugacijih podataka koje svaki api poziv vraca.
    // Treba povecati velicinu fonta, centrirati elemente u tablici, staviti odgovarajuce paddinge, itd. da to sve ostavlja odredeni dojam.
    private fun createTable()
    {


        var testTv = TextView(this)
        testTv.setText("Wuba wuba wub")

        val params1 = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.MATCH_PARENT
            ).apply{
            gravity = Gravity.RIGHT
            rightMargin = 10
            topMargin = 15
        }

        leftSection.addView(testTv, params1)

        var test2 = TextView(this)
        test2.setText("pls werk")
        test2.setTextSize(20F)

        val params2 = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        ).apply{
            gravity = Gravity.LEFT
            leftMargin = 10
            topMargin = 15
        }

        rightSection.addView(test2, params2)


    }



    /*fun driverQuery(v: View?)
    {
        val queue = Volley.newRequestQueue(v?.context)
        val apiCallURL = baseURL + forwardSlash + yearInput.text + forwardSlash + driverStringFilter + forwardSlash + driverInput.text + "results.json"

        val apiExecution = StringRequest(
            Request.Method.GET, apiCallURL, Response.Listener<String>{ response ->
                val resp = JSONObject(response)

            },
            Response.ErrorListener { Toast.makeText(v?.context, "Error occured", Toast.LENGTH_LONG).show() })

        queue.add(apiExecution)
    }*/
}