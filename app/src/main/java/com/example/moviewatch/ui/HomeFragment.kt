package com.example.moviewatch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviewatch.HomeViewModel
import com.example.moviewatch.data.InnerResults
import com.example.moviewatch.databinding.FragmentHomeBinding
import com.example.moviewatch.adapters.TrendingMediaAdapter

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var trendingMovies: ArrayList<InnerResults> = ArrayList()
    private val adapter: TrendingMediaAdapter = TrendingMediaAdapter(trendingMovies)



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.trendingList.adapter = adapter

        subscribe()
        return root
    }

    fun subscribe() {
//        Repository.trendingMovies.observe(viewLifecycleOwner){
//            adapter.submitList(it)
//        }
        homeViewModel.trendingMovies.observe(viewLifecycleOwner) { results ->
                adapter.submitList(results)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}