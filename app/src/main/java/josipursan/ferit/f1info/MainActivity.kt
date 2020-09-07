package josipursan.ferit.f1info

import android.annotation.TargetApi
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Klaxon
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_one.*
import kotlinx.android.synthetic.main.fragment_two.*
import org.json.JSONObject
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {

    val circuitList = ArrayList<RecyclerViewItem>()
    val contextToPass = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startUI()

        val fbRef = FirebaseDatabase.getInstance().getReference("circuit_list")

        fbRef.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("not implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot)
            {
                if(snapshot.exists())
                {
                    for(circuit in snapshot.children)
                    {
                        var name = circuit.child("name").getValue().toString()
                        var country = circuit.child("country").getValue().toString()
                        var corners = circuit.child("corners").getValue().toString()
                        var length = circuit.child("length").getValue().toString()
                        var latitude = circuit.child("latitude").getValue().toString()
                        var longitude = circuit.child("longitude").getValue().toString()
                        var date = circuit.child("date").getValue().toString()

                        circuitList += RecyclerViewItem(R.drawable.race_track_icon, name, country, corners, length, longitude, latitude, date)
                    }
                }
                recyclerView_infoFragment.adapter = RecyclerViewAdapter(circuitList, contextToPass)
                recyclerView_infoFragment.layoutManager = LinearLayoutManager(applicationContext)
                recyclerView_infoFragment.setHasFixedSize(true)
            }

        })
    }

    private fun startUI()
    {
        viewPager.adapter = fragmentAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }
}
