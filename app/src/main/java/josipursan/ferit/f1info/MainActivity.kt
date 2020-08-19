package josipursan.ferit.f1info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Klaxon
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_two.*
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {

    val applicationOnlyGrantType = "https://oauth.reddit.com/grants/installed_client"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startUI()


        /*val queue = Volley.newRequestQueue(this)
        val url = "https://ergast.com/api/f1/2008/5/results.json"

        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener<String>{response ->
                val json = JSONObject(response)
                val extractedElement = json.getJSONObject("MRData")
                Toast.makeText(this, extractedElement.getString("RaceTable").toString(), Toast.LENGTH_LONG).show()
                //Toast.makeText(this, response::class.simpleName, Toast.LENGTH_LONG).show()
            },
            Response.ErrorListener { Toast.makeText(this, "Failure", Toast.LENGTH_LONG).show()})

        queue.add(stringRequest)*/


    }

    private fun startUI()
    {
        viewPager.adapter = fragmentAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }




    // Sada je potrebno napraviti listu RecyclerViewItem-a koja se prosljeduje ReyclerVieAdapteru koji sve te iteme prikazuje u RecyclerViewu
    // Ovo znaci da tu (ili u nekoj fajli sa strane) moram handlati rukovanje s Reddit API-jem i povlacenje podataka iz Firebase-a za postove o stazama.

}
