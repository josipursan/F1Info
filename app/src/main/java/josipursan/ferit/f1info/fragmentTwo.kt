package josipursan.ferit.f1info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class fragmentTwo : Fragment()
{
    companion object
    {
        fun newInstance() : fragmentTwo
        {
            return fragmentTwo()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        val view = inflater.inflate(R.layout.fragment_two, container, false)
        return view
    }
}