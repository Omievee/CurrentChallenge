package io.github.omievee.currentchallenge.listview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.YelpQuery
import io.github.omievee.currentchallenge.clicklistener.RestaurantClickListener
import io.github.omievee.currentchallenge.viewholder.BaseViewHolder

class BurritoAdapter(
    private val listOfBurritoRestaurants: List<YelpQuery.Business>,
    val listener: RestaurantClickListener
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(BurritoRestaurantsView(parent.context).apply {
            layoutParams = ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        })
    }

    override fun getItemCount(): Int {
        return listOfBurritoRestaurants.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        ((holder.itemView) as BurritoRestaurantsView).bind(listOfBurritoRestaurants[position])
        (holder.itemView).listener = listener
    }
}