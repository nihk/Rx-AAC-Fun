package ca.nick.rxaacfun.data

import android.support.v7.util.DiffUtil
import ca.nick.rxaacfun.data.TheEntity

object TheDiffCallback : DiffUtil.ItemCallback<TheEntity>() {

    override fun areItemsTheSame(oldItem: TheEntity, newItem: TheEntity) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: TheEntity, newItem: TheEntity) =
        oldItem == newItem
}