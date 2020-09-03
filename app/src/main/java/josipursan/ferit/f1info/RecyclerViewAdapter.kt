package josipursan.ferit.f1info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.info_feed_items.view.*

class RecyclerViewAdapter(private val itemList: List<RecyclerViewItem>) : RecyclerView.Adapter<RecyclerViewAdapter.AdapterViewHolder>()
{
    class AdapterViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val imageView : ImageView = itemView.imageView_infoFeedItem
        val upperText : TextView = itemView.topTextView_infoFeedItem
        val bottomText : TextView = itemView.bottomTextView_infoFeedItem
        val info1Text : TextView = itemView.info1_textView
        val info2Text : TextView = itemView.info2_textView
    }

    //poziva se za svaki item koji je na screenu, i nekoliko najblizih van screena
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.info_feed_items, parent, false)
        //LayoutInflater pretvara XML layout fileove u view objekte. parent predstavlja tamo gdje se viewholder stavlja (recyclerview u ovom slucaju), context predstavlja activity u koji se recyclerview stavlja
        return AdapterViewHolder(itemView)
    }

    //poziva se stalno - kod scrolla se popunjavaju podaci za pozvane iteme, promjene podataka itema, itd...Moze se pozvati puno puta u sekundi.
    //Uzima trenutacni item u popunjava ga s odgovarajucim vrijednostima itema iz dataseta
    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val currentItem = itemList[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.upperText.text = currentItem.upperText
        holder.bottomText.text = currentItem.bottomText
        holder.info1Text.text = "Corners : " + currentItem.info1
        holder.info2Text.text = "Length : " + currentItem.info2
    }

    override fun getItemCount() = itemList.size
}