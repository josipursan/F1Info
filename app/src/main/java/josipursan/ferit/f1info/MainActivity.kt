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

}
