package io.github.omievee.currentchallenge.listview

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.YelpQuery
import io.github.omievee.currentchallenge.R
import io.github.omievee.currentchallenge.clicklistener.RestaurantClickListener
import kotlinx.android.synthetic.main.burrito_items_view.view.*

class BurritoRestaurantsView(context: Context, attributeSet: AttributeSet? = null) :
    ConstraintLayout(context, attributeSet), View.OnClickListener {
    var listener: RestaurantClickListener? = null

    init {
        View.inflate(context, R.layout.burrito_items_view, this)
        main.setOnClickListener(this)
    }


    var burritoRestaurant: YelpQuery.Business? = null
    fun bind(burritoRestaurant: YelpQuery.Business) {
        this.burritoRestaurant = burritoRestaurant
        restName.text = burritoRestaurant.name()
        restAddress.text = burritoRestaurant.location()?.address1()
        restPricing.text = when (burritoRestaurant.price().isNullOrEmpty()) {
            true -> context.getString(R.string.pricing_not_available)
            else -> burritoRestaurant.price()
        }
        restPhone.text = burritoRestaurant.phone()


        Glide.with(context)
            .load(Uri.parse(burritoRestaurant.photos()?.get(0)))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(restImage)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.main -> {
                listener?.onRestaurantClicked(burritoRestaurant ?: return)
            }
        }
    }

}