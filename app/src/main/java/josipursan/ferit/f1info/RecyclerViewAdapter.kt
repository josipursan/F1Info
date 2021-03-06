package josipursan.ferit.f1info

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.info_feed_items.view.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class RecyclerViewAdapter(private val itemList: List<RecyclerViewItem>, val context: Context) : RecyclerView.Adapter<RecyclerViewAdapter.AdapterViewHolder>()
{
    class AdapterViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val imageView : ImageView = itemView.imageView_infoFeedItem
        val upperText : TextView = itemView.topTextView_infoFeedItem
        val bottomText : TextView = itemView.bottomTextView_infoFeedItem
        val info1Text : TextView = itemView.info1_textView
        val info2Text : TextView = itemView.info2_textView
        val info3Text : TextView = itemView.info3_textView

        val coordinatesButton : Button = itemView.showCircuit_button
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
        holder.info3Text.text = "Date : " + currentItem.date

        holder.coordinatesButton.setOnClickListener()
        {
            val uri : String = String.format(Locale.ENGLISH, "geo:%f,%f", currentItem.longitude.toFloat(), currentItem.latitude.toFloat())  //ide prvo latitude pa longitude, ali si ocigledno okrenio te vrijednosti kod unosenja u Firebase
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = itemList.size
}