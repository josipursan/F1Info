package josipursan.ferit.f1info

import android.annotation.TargetApi
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.cardview.widget.CardView
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

        val firstCardExpandButton = view.findViewById<Button>(R.id.firstCardDropDownArrow)
        firstCardExpandButton.setOnClickListener(this)

        val secondCardExpandButton = view.findViewById<Button>(R.id.secondCardDropDownArrow)
        secondCardExpandButton.setOnClickListener(this)

        val first_card = view.findViewById<CardView>(R.id.firstCard)
        first_card.setOnClickListener(this)

        val second_card = view.findViewById<CardView>(R.id.secondCard)
        second_card.setOnClickListener(this)



        return view
    }


    override fun onClick(v: View?) {

        if(v?.id == R.id.queryButton)
        {
            if(yearInput.text.isEmpty())
            {
                Toast.makeText(v?.context, "Please enter year", Toast.LENGTH_LONG).show()
            }
            else
            {
                if(driverInput.text.isEmpty() && circuitInput.text.isEmpty() && teamInput.text.isEmpty())
                {
                    Toast.makeText(v?.context, "Please enter one of three remaining fields", Toast.LENGTH_LONG).show()
                }
                else if(driverInput.text.isNotEmpty() && teamInput.text.isEmpty() && circuitInput.text.isEmpty())   // Driver query
                {
                    buildDriverApiCallAndShowResults()
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

        else if(v?.id == R.id.firstCardDropDownArrow || v?.id == R.id.firstCard)
        {
            @TargetApi(19)
            if(firstExpandableSection.visibility == View.GONE)
            {
                TransitionManager.beginDelayedTransition(firstCard, AutoTransition())
                firstExpandableSection.visibility = View.VISIBLE
                firstCardTitle.setText(R.string.firstCardDescription)
                firstCardTitle.setTextSize(15F)
                firstCardDropDownArrow.setBackgroundResource(R.drawable.ic_arrow_drop_up_black_24dp)
            }
            else
            {
                firstExpandableSection.visibility = View.GONE
                firstCardTitle.setText(R.string.firstCardTitle)
                firstCardTitle.setTextSize(20F)
                firstCardDropDownArrow.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp)
            }
        }

        else if(v?.id == R.id.secondCardDropDownArrow || v?.id == R.id.secondCard)
        {
            @TargetApi(19)
            if(secondExpandableSection.visibility == View.GONE)
            {
                TransitionManager.beginDelayedTransition(secondCard, AutoTransition())
                secondExpandableSection.visibility = View.VISIBLE
                secondCardTitle.setText(R.string.secondCardDescription)
                secondCardTitle.setTextSize(15F)
                secondCardDropDownArrow.setBackgroundResource(R.drawable.ic_arrow_drop_up_black_24dp)
            }
            else
            {
                secondExpandableSection.visibility = View.GONE
                secondCardTitle.setText(R.string.secondCardTitle)
                secondCardTitle.setTextSize(20F)
                secondCardDropDownArrow.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp)
            }
        }

    }


    private fun buildDriverApiCallAndShowResults()
    {
        val apiCallURL = baseURL + forwardSlash + yearInput.text.toString() + forwardSlash + driverStringFilter + forwardSlash + driverInput.text.toString() + forwardSlash + resultsStringFilter
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(apiCallURL))
        startActivity(browserIntent)
    }

    private fun buildCircuitApiCall()
    {
        val apiCallURL = baseURL + forwardSlash + yearInput.text.toString() + forwardSlash + circuitsStringfilter + forwardSlash + circuitInput.text.toString() + forwardSlash + resultsStringFilter// + jsonFilter
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(apiCallURL))
        startActivity(browserIntent)
    }

    private fun buildConstructorApiCall()
    {
        val apiCallURL = baseURL + forwardSlash + yearInput.text.toString() + forwardSlash + constructorsStringFilter + forwardSlash + teamInput.text.toString() + forwardSlash + resultsStringFilter// + jsonFilter
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(apiCallURL))
        startActivity(browserIntent)
    }
}