package josipursan.ferit.f1info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_two.*

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
            // Plan za sada : parametri koje korisnik unese ce se proslijediti u taj novi intent, tamo ce se handlati zahtjev na Ergast i priakzati rezultati.

            if(driverInput.text.isEmpty() && circuitInput.text.isEmpty() && teamInput.text.isEmpty())
            {
                Toast.makeText(v?.context, "Please enter one of three remaining fields", Toast.LENGTH_LONG).show()
            }
            else if(driverInput.text.isNotEmpty() && teamInput.text.isEmpty() && circuitInput.text.isEmpty())
            {
                Toast.makeText(v?.context, "Driver name entered", Toast.LENGTH_SHORT).show()
            }
            else if(circuitInput.text.isNotEmpty() && teamInput.text.isEmpty() && driverInput.text.isEmpty())
            {
                Toast.makeText(v?.context, "Circuit entered", Toast.LENGTH_SHORT).show()
            }
            else if(teamInput.text.isNotEmpty() && circuitInput.text.isEmpty() && driverInput.text.isEmpty())
            {
                Toast.makeText(v?.context, "Team entered", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(v?.context, "Please enter either circuit, driver, or team", Toast.LENGTH_LONG).show()
            }

        }
    }

}