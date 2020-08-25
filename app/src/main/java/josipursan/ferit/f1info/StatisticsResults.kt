package josipursan.ferit.f1info

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.statistics_results.*
import org.json.JSONObject


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
            testTextView.setText(intent.getStringExtra(EXTRA_APICALLURL))
            executeAPI()
        }
        else if(constructorsStringFilter in intent.getStringExtra(EXTRA_APICALLURL))
        {
            testTextView.setText(intent.getStringExtra(EXTRA_APICALLURL))
            executeAPI()
        }

        createTable()
    }


    private fun executeAPI()
    {
        val intent : Intent = getIntent()
        val queue = Volley.newRequestQueue(this)

        val apiExecution = JsonObjectRequest(Request.Method.GET, intent.getStringExtra(
            EXTRA_APICALLURL), null, Response.Listener { response -> showQueryResult(response) },
            Response.ErrorListener{error -> Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()})

        queue.add(apiExecution)
    }

    private fun showQueryResult(ergastResponse : JSONObject)
    {
        var firstLayer = ergastResponse.getJSONObject("MRData").getJSONObject("RaceTable").getJSONArray("Races").getJSONObject(0)
        var resultsLayer = firstLayer.getJSONArray("Results")
        //var test = firstLayer.getJSONArray("Results").getJSONObject(0).getJSONObject("Driver")["driverId"] //["raceName"]

        var test = firstLayer["date"]

        var tableHeaderBasicData = JSONObject()
        tableHeaderBasicData.put("Race name", firstLayer["raceName"])
        tableHeaderBasicData.put("Season", firstLayer["season"])
        tableHeaderBasicData.put("Date", firstLayer["date"])


        //lateinit var matrixtest : Array<Array<Any>>
        var twoDimArrayOfDataForTable = Array(5){ arrayOfNulls<Any>(resultsLayer.length())}
        // 0 -> position
        // 1 -> points
        // 2 -> givenName
        // 3 -> familyName
        // 4 -> constructorName

        var i = 0
        for(i in 0..resultsLayer.length()-1)
        {
            twoDimArrayOfDataForTable[0][i] = resultsLayer.getJSONObject(i)["position"]
            twoDimArrayOfDataForTable[1][i] = resultsLayer.getJSONObject(i)["points"]

            //matrixtest[0][1] = resultsLayer.getJSONObject(0)["position"]
            //matrixtest[1][i] = resultsLayer.getJSONObject(i)["points"]
        }

        Toast.makeText(this, twoDimArrayOfDataForTable[0][1].toString(), Toast.LENGTH_LONG).show()







        // Kad se naleti na "Races" ili "Results" segment u JSON-u, radi se o polju unutar JSON-a ([] oznake), zbog cega za pristup tom segmentu treba korisiti getJSONArray funkciju (vidi liniju 108)
        // U tom trenutku kad smo pokupili getJSONArray, moze se iskoristiti length() metoda da se dobije broj utrka za tog vozaca za tu sezonu.
        // Kada smo usli u listu pomocu metode getJSONArray, potrebno je koji JSONObject zelimo, tj. u slucaju utrke koju utrku zelimo (0,1,2,....). Ovo se radi metodom getJSONObject kojoj se predaje indeks.
        // Kada smo odabrali utrku koju zelimo, jednostavno u tom polju odabiremo koji podataka nam treba.
    }

    // test popunjavanja tablice - za sada je plan praznu tablicu definiranu u statistics_results.xml popuniti s podacima ovisno o queryu ->
    // Za svaki query ide drugaciji nacin popunjavanja zbog drugacijih podataka koje svaki api poziv vraca.
    // Treba povecati velicinu fonta, centrirati elemente u tablici, staviti odgovarajuce paddinge, itd. da to sve ostavlja odredeni dojam.
    private fun createTable()
    {
        var dataTableHead = TableRow(this)
        dataTableHead.setBackgroundColor(Color.YELLOW)
        dataTableHead.setLayoutParams(
            TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT
            )
        )

        var tVLabel = TextView(this)
        tVLabel.setText("Hello")
        tVLabel.setTextColor(Color.CYAN)
        dataTableHead.addView(tVLabel)

        var anotherTV = TextView(this)
        anotherTV.setText("Hennloo")
        anotherTV.setTextColor(Color.MAGENTA)
        dataTableHead.addView(anotherTV)

        dataTable.addView(dataTableHead)
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