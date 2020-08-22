package josipursan.ferit.f1info

import android.app.ActionBar
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.statistics_results.*

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

        if(driverStringFilter in intent.getStringExtra(EXTRA_APICALLURL))    // Obzirom na ove if-ove ce se pozivati razliciti queryi, odnosno razliciti nacini popunjavanja TableLayout-a u statistics_results.xml
        {
            testTextView.setText("Driver")
        }
        else if(circuitsStringfilter in intent.getStringExtra(EXTRA_APICALLURL))
        {
            testTextView.setText("Circuits")
        }
        else if(constructorsStringFilter in intent.getStringExtra(EXTRA_APICALLURL))
        {
            testTextView.setText("Constructors")
        }

        createTable()
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
}