package ca.nick.rxaacfun.ui

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.support.v7.widget.helper.ItemTouchHelper.*
import ca.nick.rxaacfun.R
import ca.nick.rxaacfun.data.DeleteAllChange
import ca.nick.rxaacfun.data.DeleteChange
import ca.nick.rxaacfun.data.InsertChange
import ca.nick.rxaacfun.data.TheEntityChange
import ca.nick.rxaacfun.plus
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TheViewModel
    private var compositeDisposable = CompositeDisposable()
    private val adapter = TheAdapter()
    private val changes: PublishRelay<TheEntityChange> = PublishRelay.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = adapter
        itemTouchHelper.attachToRecyclerView(recyclerView)

        viewModel = ViewModelProviders.of(this).get(TheViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        compositeDisposable += changes
            .observeOn(Schedulers.io())
            .subscribe(::onEntityChange)

        compositeDisposable += addFab.clicks()
            .map { InsertChange }
            .subscribe(changes)

        compositeDisposable += deleteAllFab.clicks()
            .map { DeleteAllChange }
            .subscribe(changes)

        compositeDisposable += adapter.bind(viewModel.entities())
    }

    override fun onStop() {
        super.onStop()

        compositeDisposable.clear()
    }

    private fun onEntityChange(change: TheEntityChange) = when (change) {
        is InsertChange -> viewModel.createEntity()
        is DeleteAllChange -> viewModel.deleteAll()
        is DeleteChange -> viewModel.delete(change.id)
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(UP or DOWN, LEFT or RIGHT) {

        override fun onMove(
            recyclerView: RecyclerView?,
            source: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
            (viewHolder as TheViewHolder).getId()?.let {
                changes.accept(DeleteChange(it))
            }
        }
    })
}
