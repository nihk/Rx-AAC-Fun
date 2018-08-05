package ca.nick.rxaacfun.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import ca.nick.rxaacfun.data.TheEntity
import kotlinx.android.synthetic.main.item_the_entity.view.*

class TheViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var entity: TheEntity? = null

    fun bind(entity: TheEntity) {
        this@TheViewHolder.entity = entity
        itemView.itemContent.text = entity.content
    }

    fun getId(): Long? = entity?.id
}