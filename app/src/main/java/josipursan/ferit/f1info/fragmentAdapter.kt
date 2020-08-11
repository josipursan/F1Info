package josipursan.ferit.f1info

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class fragmentAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager)
{
    val fragments = arrayOf(
        fragmentOne.newInstance(),
        fragmentTwo.newInstance()
    )

    val fragmentTitles = arrayOf("Info feed", "Statistics")

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitles[position]
    }
}