package com.examples.photogalleryapp.ui.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.examples.photogalleryapp.Util.Constans
import com.examples.photogalleryapp.adapter.ImagesPageAdapter
import com.examples.photogalleryapp.databinding.FragmentImageListingBinding
import com.examples.photogalleryapp.listener.OnClickListener
import com.examples.photogalleryapp.ui.Home.Fragments.HomeFragmentDirections
import com.examples.photogalleryapp.ui.Home.Fragments.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageListingFragment : Fragment() {
    private val TAG = "ImageListingFragment"
    lateinit var binding: FragmentImageListingBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: ImagesPageAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageListingBinding.inflate(inflater)
        adapter = ImagesPageAdapter()

        arguments?.getString(Constans.KEY_NAME)?.let {
            viewModel.search(category = it)
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

        adapter.clickListener = object : OnClickListener {
            override fun onClickItem(id: Int) {
                val navDirections = HomeFragmentDirections.actionHomeFragmentToImageDetailsFragment(id)
                Navigation.findNavController(view).navigate(navDirections)
            }

        }
    }

    companion object {
        @JvmStatic
        fun newInstance(category: String) = ImageListingFragment().apply {
            arguments = Bundle().apply {
                putString(Constans.KEY_NAME, category)
            }
        }
    }
}