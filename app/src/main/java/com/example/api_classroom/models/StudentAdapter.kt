import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.api_classroom.R
import com.example.api_classroom.models.ExampleAdapter
import kotlinx.android.synthetic.main.example_course.view.*

class StudentAdapter(private val exampleList: Array<String?>,private val exampleList2: Array<String?>,private val exampleList3: Array<String?>,private val exampleList4: Array<String?>): RecyclerView.Adapter<StudentAdapter.StudentViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.example_student, parent, false)
        return StudentAdapter.StudentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val currentItem = exampleList[position]
        val currentItem2 = exampleList2[position]
        val currentItem3 = exampleList3[position]
        val currentItem4 = exampleList4[position]


        holder.textView1.text = currentItem
        holder.textView2.text = currentItem2
        holder.textView3.text = currentItem3
        holder.textView4.text = currentItem4
    }

    override fun getItemCount() = exampleList.size

    class StudentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView1: TextView = itemView.text_view_1
        val textView2: TextView = itemView.text_view_2
        val textView3: TextView = itemView.text_view_3
        val textView4: TextView = itemView.text_view_4


    }
}