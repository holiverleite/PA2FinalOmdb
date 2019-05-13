package com.example.pa2finalomdb

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pa2finalomdb.model.Movie
import kotlinx.android.synthetic.main.fragment_detalhes_filme.*
import kotlinx.android.synthetic.main.fragment_detalhes_filme.view.*

class DetalhesFilmeFragment: Fragment() {
    var movie : Movie = Movie()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val layoutFragment = inflater.inflate(R.layout.fragment_detalhes_filme,null)
        loadValues(layoutFragment)

        return layoutFragment
    }

    fun loadValues(view: View) {
        view.title_textView.text = movie.title
        view.year_textView.text = movie.year
        view.released_textView.text = movie.released
        view.director_textView.text = movie.director
        view.actors_textView.text = movie.actors
        view.idioma_textView.text = movie.language
        view.country_textView.text = movie.country
        view.avaliacao_textView.text = movie.ratings.first().source
        view.notaImdb_textView.text = movie.imdbRating
        view.type_textView.text = movie.typeMovie
        view.produtora_textView.text = movie.production
        view.website_textView.text = movie.website
    }
}