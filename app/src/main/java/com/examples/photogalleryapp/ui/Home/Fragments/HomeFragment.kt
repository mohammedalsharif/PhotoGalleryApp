package com.examples.photogalleryapp.ui.Home.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.examples.photogalleryapp.adapter.TabAdapter
import com.examples.photogalleryapp.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    val TABS_TITLE_LIST = listOf(
        "Editor's Choice",
        "Backgrounds",
        "Fashion",
        "Nature",
        "Education",
        "Feelings",
        "Health",
        "People",
        "Religion",
        "Places",
        "Animals",
        "Industry",
        "Computer",
        "Food",
        "Sports",
        "Transportation",
        "Travel",
        "Buildings",
        "Business",
        "Music"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = createTabAdapter()
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, poss ->
            tab.text=TABS_TITLE_LIST[poss]
        }.attach()
    }

    private fun createTabAdapter(): TabAdapter {
        val tabAdapter: TabAdapter = TabAdapter(requireActivity())
        tabAdapter.setSize(TABS_TITLE_LIST.size)
        return tabAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }


}