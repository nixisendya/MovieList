package com.example.lab4

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity(), MovieListFragment.OnMovieSelected  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.root_layout, MovieListFragment.newInstance(), "movieList")
                .commit()
        }
    }

    override fun onMovieSelected(movieModel: MovieModel) {
        val detailsFragment =
            MovieDetailsFragment.newInstance(movieModel)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.root_layout, detailsFragment, "movieDetails")
            .addToBackStack(null)
            .commit()
    }
}
