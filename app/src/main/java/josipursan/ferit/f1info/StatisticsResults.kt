package josipursan.ferit.f1info

import android.content.Intent
import android.os.Bundle
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
        testTextView.setText(intent.getStringExtra(EXTRA_APICALLURL))
    }

}