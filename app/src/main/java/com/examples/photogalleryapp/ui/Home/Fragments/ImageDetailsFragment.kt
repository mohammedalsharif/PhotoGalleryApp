package com.examples.photogalleryapp.ui.Home.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.examples.photogalleryapp.R
import com.examples.photogalleryapp.databinding.FragmentImageDetailsBinding


class ImageDetailsFragment : Fragment() {

    private lateinit var binding: FragmentImageDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=FragmentImageDetailsBinding.inflate(inflater)

        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.animToolbar)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayShowTitleEnabled(false)
        binding.animToolbar.setNavigationIcon(R.drawable.ic_back)
        binding.animToolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed() }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<View>(R.id.headerLayout).visibility = View.GONE
    }

}