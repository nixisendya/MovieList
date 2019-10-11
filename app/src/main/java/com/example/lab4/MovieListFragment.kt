package com.example.lab4

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.lab4.databinding.RecyclerItemBinding

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager

class MovieListFragment : Fragment() {

    private lateinit var imageResIds: IntArray
    private lateinit var names: Array<String>
    private lateinit var descriptions: Array<String>
    private lateinit var urls: Array<String>
    private lateinit var listener: OnMovieSelected

    companion object {

        fun newInstance(): MovieListFragment {
            return MovieListFragment()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is OnMovieSelected) {
            listener = context
        } else {
            throw ClassCastException(
                context.toString() + " must implement OnMovieSelected.")
        }


        // Get movie names and descriptions.
        val resources = context.resources
        names = resources.getStringArray(R.array.names)
        descriptions = resources.getStringArray(R.array.descriptions)
        urls = resources.getStringArray(R.array.urls)

        // Get movie images.
        val typedArray = resources.obtainTypedArray(R.array.images)
        val imageCount = names.size
        imageResIds = IntArray(imageCount)
        for (i in 0 until imageCount) {
            imageResIds[i] = typedArray.getResourceId(i, 0)
        }
        typedArray.recycle()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_list, container,
            false)
        val activity = activity as Context
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = MovieListAdapter(activity)
        return view
    }

    internal inner class MovieListAdapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {

        private val layoutInflater = LayoutInflater.from(context)

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val recyclerMovieModelBinding =
                RecyclerItemBinding.inflate(layoutInflater, viewGroup, false)
            return ViewHolder(recyclerMovieModelBinding.root, recyclerMovieModelBinding)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            val movie = MovieModel(imageResIds[position], names[position],
                descriptions[position], urls[position])
            viewHolder.setData(movie)

            viewHolder.itemView.setOnClickListener {
                listener.onMovieSelected(movie)
            }
        }

        override fun getItemCount() = names.size
    }

    internal inner class ViewHolder constructor(itemView: View,
                                                private val recyclerItemMovieListBinding:
                                                RecyclerItemBinding
    ) :
        RecyclerView.ViewHolder(itemView) {

        fun setData(movieModel: MovieModel) {
            recyclerItemMovieListBinding.movieModel = movieModel
        }
    }

    interface OnMovieSelected {
        fun onMovieSelected(movieModel: MovieModel)
    }

}