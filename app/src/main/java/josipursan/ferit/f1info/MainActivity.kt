package josipursan.ferit.f1info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startUI()
    }

    private fun startUI()
    {
        viewPager.adapter = fragmentAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    // Sada je potrebno napraviti listu RecyclerViewItem-a koja se prosljeduje ReyclerVieAdapteru koji sve te iteme prikazuje u RecyclerViewu
    // Ovo znaci da tu (ili u nekoj fajli sastrane) moram handlati rukovanje s Reddit API-jem i povlacenje podataka iz Firebase-a za postove o stazama.

}
