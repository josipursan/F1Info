package josipursan.ferit.f1info

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.json
import kotlinx.android.synthetic.main.fragment_two.*
import org.json.JSONObject

class fragmentTwo : Fragment(), View.OnClickListener
{
    companion object
    {
        fun newInstance() : fragmentTwo
        {
            return fragmentTwo()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_two, container, false)
        val queryBtn = view.findViewById<Button>(R.id.queryButton)
        queryBtn.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View?) {

        if(yearInput.text.isEmpty())
        {
            Toast.makeText(v?.context, "Please enter year", Toast.LENGTH_LONG).show()
        }
        else
        {
            // U uvjetima ispod zapravo ce ici pozivi novih intentova, odnosno otvarat ce se novi screen u kojem ce se prikazati rezultati querya.
            // Plan za sada : parametri koje korisnik unese ce se proslijediti u taj novi intent, tamo ce se handlati zahtjev na Ergast i prikazati rezultati.

            if(driverInput.text.isEmpty() && circuitInput.text.isEmpty() && teamInput.text.isEmpty())
            {
                Toast.makeText(v?.context, "Please enter one of three remaining fields", Toast.LENGTH_LONG).show()

            }
            else if(driverInput.text.isNotEmpty() && teamInput.text.isEmpty() && circuitInput.text.isEmpty())   // Driver query
            {
                buildDriverApiCall()
            }
            else if(circuitInput.text.isNotEmpty() && teamInput.text.isEmpty() && driverInput.text.isEmpty())   // Circuit query
            {
                buildCircuitApiCall()
            }
            else if(teamInput.text.isNotEmpty() && circuitInput.text.isEmpty() && driverInput.text.isEmpty())   // Team query
            {
                buildConstructorApiCall()
            }
            else
            {
                Toast.makeText(v?.context, "Please enter either circuit, driver, or team", Toast.LENGTH_LONG).show()
            }

        }
    }

    //poziv intenta u kojem se prikazuju rezultati api poziva - u tom intentu ce se s obzirom na oblik predanog api poziva odredivati kako parsati rezultat
    private fun callStatisticsResults(apiCallURL : String)
    {
        val intent = Intent(getActivity(), StatisticsResults::class.java)
        intent.putExtra(StatisticsResults.EXTRA_APICALLURL, apiCallURL)
        getActivity()?.startActivity(intent)
    }


    private fun buildDriverApiCall()
    {
        val apiCallURL = baseURL + forwardSlash + yearInput.text.toString() + forwardSlash + driverStringFilter + forwardSlash + driverInput.text.toString() + forwardSlash + resultsStringFilter + jsonFilter
        callStatisticsResults(apiCallURL)
    }

    private fun buildCircuitApiCall()
    {
        val apiCallURL = baseURL + forwardSlash + yearInput.text.toString() + forwardSlash + circuitsStringfilter + forwardSlash + circuitInput.text.toString() + forwardSlash + resultsStringFilter + jsonFilter
        callStatisticsResults(apiCallURL)
    }

    private fun buildConstructorApiCall()
    {
        val apiCallURL = baseURL + forwardSlash + yearInput.text.toString() + forwardSlash + constructorsStringFilter + forwardSlash + teamInput.text.toString() + forwardSlash + resultsStringFilter + jsonFilter
        callStatisticsResults(apiCallURL)
    }


    fun driverQuery(v: View?)
    {
        val queue = Volley.newRequestQueue(v?.context)
        val apiCallURL = baseURL + forwardSlash + yearInput.text + forwardSlash + driverStringFilter + forwardSlash + driverInput.text + "results.json"

        val apiExecution = StringRequest(Request.Method.GET, apiCallURL, Response.Listener<String>{response ->
            val resp = JSONObject(response)

        },
            Response.ErrorListener { Toast.makeText(v?.context, "Error occured", Toast.LENGTH_LONG).show() })

        queue.add(apiExecution)
    }

}