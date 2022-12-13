package com.examples.photogalleryapp.ui.Home.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.examples.photogalleryapp.Util.Constans
import com.examples.photogalleryapp.adapter.ImagesPageAdapter
import com.examples.photogalleryapp.data.api.ApiClient
import com.examples.photogalleryapp.databinding.FragmentImageListingBinding
import com.examples.photogalleryapp.repository.HomRepository
import com.examples.photogalleryapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageListingFragment : Fragment() {
    private val TAG = "ImageListingFragment"
    lateinit var binding: FragmentImageListingBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        arguments?.let {
            val category = arguments?.getString(Constans.KEY_NAME)
            Log.e(TAG, "onStart: $category")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageListingBinding.inflate(inflater)
        val adapter = ImagesPageAdapter()


        arguments?.getString(Constans.KEY_NAME)?.let {
            viewModel.search(it)
        }

        binding.apply {
            binding.recImagesList.setHasFixedSize(true)
            binding.recImagesList.adapter = adapter
            binding.recImagesList.layoutManager = GridLayoutManager(requireContext(), 2)
        }

        viewModel.images.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        @JvmStatic
        fun newInstance(category: String)= ImageListingFragment().apply {
                arguments = Bundle().apply {
                    putString(Constans.KEY_NAME,category)
                }
        }
    }
}