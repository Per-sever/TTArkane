package com.example.ttarkane.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ttarkane.R
import com.example.ttarkane.databinding.FragmentSecondBinding
import com.example.ttarkane.presentation.adapters.directory_repo.DirectoryAdapter

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val viewModel by lazy {
        ViewModelProvider(this)[DirectoryViewModel::class.java]
    }

    private val adapter = DirectoryAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView3.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView3.adapter = adapter
        val repo = arguments?.getString(EXTRA_REPO) ?: ""
        val owner = arguments?.getString(EXTRA_OWNER) ?: ""
        viewModel.loadData(owner, repo)
        viewModel.directoryList.observe(requireActivity()) {
            adapter.submitList(it)
        }

        adapter.onFileClickListener = {
            val bundle = WebViewFragment.newInstance(it, owner, repo)
            findNavController().navigate(
                R.id.action_DirectoryListFragment_to_WebViewFragment,
                bundle
            )
        }
//        binding.buttonSecond.setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        fun newInstance(owner: String, repo: String): Bundle {
            return Bundle().apply {
                putString(EXTRA_OWNER, owner)
                putString(EXTRA_REPO, repo)
            }
        }

        private const val EXTRA_OWNER = "owner_extra"
        private const val EXTRA_REPO = "repo_extra"
    }
}