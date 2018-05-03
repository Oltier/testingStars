package hu.elte.inf.zoltantudlik.testingStars.people

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.zoltantudlik.testing_stars.R
import hu.elte.inf.zoltantudlik.testingStars.rest.entities.Rating
import hu.elte.inf.zoltantudlik.testingStars.rest.entities.User
import java.text.DecimalFormat

class PeopleAdapter(private val peopleClickListener: PeopleClickListener<User>) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {
    private val UserList = ArrayList<User>()

    fun setData(UserLoadList: List<User>) {
        UserList.clear()
        UserList.addAll(UserLoadList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.UserName.text = UserList[position].name
        holder.UserRate.text = DecimalFormat("0.00").format(UserList[position]._ratings.avg()).toString()
        holder.itemView.setOnClickListener { peopleClickListener.onClick(UserList[position]) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false))
    }

    override fun getItemCount(): Int {
        return UserList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.person_name)
        lateinit var UserName: TextView
        @BindView(R.id.person_rate)
        lateinit var UserRate: TextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}

private fun List<Rating>.avg(): Float {
    return this.map { it.value }.sumBy { it }.toFloat() / this.size
}
