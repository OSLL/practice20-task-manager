import MainAdapter.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.PieChart
import com.makentoshe.androidgithubcitemplate.R
import com.makentoshe.androidgithubcitemplate.Rating
import com.makentoshe.androidgithubcitemplate.Task
import com.makentoshe.androidgithubcitemplate.TaskNote

private const val VIEW_TYPE_RATING_CHART: Int = 1
private const val VIEW_TYPE_NOTE: Int = 2
class MainAdapter(
    private var data: List<TaskNote>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    // holder class to hold reference
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //get view reference


    }
    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_RATING_CHART else VIEW_TYPE_NOTE
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  when (viewType) {
            VIEW_TYPE_RATING_CHART -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.rating_layout, parent)
                RatingChartHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.note_layout, parent)
                NoteHolder(view)
            }
        }
    }

    class RatingChartHolder(view: View) : RecyclerView.ViewHolder(view) {
        var pie_chart: PieChart = view.findViewById(R.id.rating_chart)
    }

    class NoteHolder(view: View) : RecyclerView.ViewHolder(view) {
        var note_title: TextView = view.findViewById(R.id.note_title)
        var note_text: TextView = view.findViewById(R.id.note_text)
        var note_date: TextView = view.findViewById(R.id.note_date)
        var note_image: ImageView = view.findViewById(R.id.note_image)
        var note_pin_icon: ImageView = view.findViewById(R.id.pin_icon)
        var note_alarm_icon: ImageView = view.findViewById(R.id.alarm_icon)
        var note_bookmark_icon: ImageView = view.findViewById(R.id.bookmark_icon)
    }
    private fun bindRatingChartHolder(holder: RatingChartHolder, position: Int) {
        val rating = data[position] as Rating
    }
    private fun bindNoteHolder(holder: NoteHolder, position: Int) {
        val task = data[position] as Task
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RatingChartHolder -> bindRatingChartHolder(holder, position)
            is NoteHolder -> bindNoteHolder(holder, position)
        }
    }
}//Скобочка класса аааа



