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
import com.example.ttarkane.databinding.FragmentDirectoryBinding
import com.example.ttarkane.presentation.adapters.directory_repo.DirectoryAdapter


class DirectoryFragment : Fragment() {

    private var _binding: FragmentDirectoryBinding? = null
    private val binding get() = _binding!!

    private val adapter = DirectoryAdapter()

    private val viewModel by lazy {
        ViewModelProvider(this)[DirectoryViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDirectoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvDirectoryList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDirectoryList.adapter = adapter
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