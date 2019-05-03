package com.example.pa2finalomdb

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class DetalhesFilmeFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val layoutFragment = inflater.inflate(R.layout.fragment_detalhes_filme,null)

        return layoutFragment
    }
}