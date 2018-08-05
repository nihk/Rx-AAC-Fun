package ca.nick.rxaacfun.ui

import android.support.v7.recyclerview.extensions.ListAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import ca.nick.rxaacfun.R
import ca.nick.rxaacfun.data.TheEntity
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class TheAdapter : ListAdapter<TheEntity, TheViewHolder>(TheDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TheViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_the_entity, parent, false)
            .let { TheViewHolder(it) }
    }

    override fun onBindViewHolder(holder: TheViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun bind(entities: Flowable<List<TheEntity>>): Disposable {
        return entities
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { submitList(it) }
    }
}