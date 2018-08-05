package ca.nick.rxaacfun.ui

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ca.nick.rxaacfun.R
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

        viewModel = ViewModelProviders.of(this).get(TheViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        compositeDisposable += changes
            .observeOn(Schedulers.io())
            .subscribe(::onEntityChange)

        compositeDisposable += addFab.clicks()
            .map { TheEntityChange.Insert }
            .subscribe(changes)

        compositeDisposable += deleteAllFab.clicks()
            .map { TheEntityChange.DeleteAll }
            .subscribe(changes)

        compositeDisposable += adapter.bind(viewModel.entities())
    }

    override fun onStop() {
        super.onStop()

        compositeDisposable.clear()
    }

    private fun onEntityChange(change: TheEntityChange) {
        when (change) {
            is TheEntityChange.Insert -> viewModel.createEntity()
            is TheEntityChange.DeleteAll -> viewModel.deleteAll()
        }
    }
}
