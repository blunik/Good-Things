package app.good.things.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.good.things.R
import app.good.things.domain.model.Message
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import java.util.concurrent.ThreadLocalRandom

/**
 * Adapter for recyclerview
 */
internal class ImagesRecyclerAdapter(options: FirebaseRecyclerOptions<Message>) :
    FirebaseRecyclerAdapter<Message, ImagesRecyclerAdapter.MyViewHolder>(options) {
    private var listColor =
        listOf(
            R.color.Yellow,
            R.color.NavajoWhite,
            R.color.Pink,
            R.color.Orange,
            R.color.Coral,
            R.color.DeepPink,
            R.color.Fuchsia,
            R.color.Magenta,
            R.color.Red,
            R.color.Salmon,
            R.color.DarkOrchid,
            R.color.Chartreuse,
            R.color.MediumSlateBlue,
            R.color.LightSeaGreen,
            R.color.LimeGreen,
            R.color.Lime,
            R.color.Aqua,
            R.color.Cyan,
            R.color.DeepSkyBlue,
            R.color.PaleTurquoise,
        )

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val imageView: ImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.images_holder, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: Message) {
        val randomNum: Int = ThreadLocalRandom.current().nextInt(0, 19)
        holder.imageView.setBackgroundResource(listColor[randomNum])
        holder.textView.text = model.text
    }
}