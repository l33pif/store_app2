package com.l33pif.store_app

import android.app.Activity
import android.content.Intent
import android.support.v4.util.Pair
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_landing.view.*


class AdapterLanding(val data: List<ItemLanding?>?) : RecyclerView.Adapter<AdapterLanding.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
            Holder(parent?.inflate(R.layout.item_landing))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        data?.let {
            holder?.bindView(it[position])
        }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        fun bindView(itemLanding: ItemLanding?) {
            itemLanding?.let {
                with(it) {
                    itemView.txtTitleItem.text = title
                    itemView.txtDescItem.text = desc
                    itemView.txtPriceItem.text = "$ ${String.format("%.2f", price)}"

                    Glide.with(itemView.context).load(urlImage).into(itemView.imgItemHeader)

                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra("title", title)
                    intent.putExtra("desc", desc)
                    intent.putExtra("price", price)

                    val p1: androidx.core.util.Pair<View, String> = androidx.core.util.Pair.create(itemView.imgItemHeader, "transitionHeader")
                    val p2: androidx.core.util.Pair<View, String> = androidx.core.util.Pair.create(itemView.txtTitleItem, "transitionTitle")
                    val p3: androidx.core.util.Pair<View, String> = androidx.core.util.Pair.create(itemView.txtDescItem, "transitionDesc")
                    val p4: androidx.core.util.Pair<View, String> = androidx.core.util.Pair.create(itemView.txtPriceItem, "transitionPrice")
                    val options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(itemView.context as Activity, p1, p2, p3, p4)
                    itemView.context.startActivity(intent, options.toBundle())

                }
            }
        }
    }

}