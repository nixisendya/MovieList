package com.example.lab4
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.lab4.databinding.FragmentDetailsBinding

class MovieDetailsFragment : Fragment() {

    companion object {

        private const val MOVIEMODEL = "model"

        fun newInstance(movieModel: MovieModel): MovieDetailsFragment {
            val args = Bundle()
            args.putSerializable(MOVIEMODEL, movieModel)
            val fragment = MovieDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // 1
        val fragmentDetailsBinding =
            FragmentDetailsBinding.inflate(inflater, container, false)

        // 2
        val model = arguments!!.getSerializable(MOVIEMODEL) as MovieModel
        // 3
        fragmentDetailsBinding.movieModel = model
        model.text = String.format(getString(R.string.description_format),
            model.description, model.url)
        return fragmentDetailsBinding.root
    }

}