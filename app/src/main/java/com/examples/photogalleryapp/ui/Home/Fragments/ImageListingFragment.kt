package com.examples.photogalleryapp.ui.Home.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.examples.photogalleryapp.adapter.ImagesPageAdapter
import com.examples.photogalleryapp.data.api.ApiClient
import com.examples.photogalleryapp.databinding.FragmentImageListingBinding
import com.examples.photogalleryapp.repository.HomRepository
import com.examples.photogalleryapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageListingFragment : Fragment() {
    lateinit var binding: FragmentImageListingBinding
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageListingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ImagesPageAdapter()

        binding.apply {
            binding.recImagesList.setHasFixedSize(true)
            binding.recImagesList.adapter = adapter
            binding.recImagesList.layoutManager = GridLayoutManager(requireContext(), 2)

        }
        viewModel.images.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ImageListingFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}