package com.example.ttarkane.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ttarkane.R
import com.example.ttarkane.databinding.FragmentSearchinfoBinding
import com.example.ttarkane.presentation.adapters.GitHubRepoUserAdapter

class SearchInfoFragment : Fragment() {

    private var _binding: FragmentSearchinfoBinding? = null
    private val binding get() = _binding!!

    private val adapter = GitHubRepoUserAdapter()

    private val viewModel by lazy {
        ViewModelProvider(this)[SearchInfoViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchinfoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addTextChangeListeners()
        binding.rvGithubinfoList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvGithubinfoList.adapter = adapter
        adapter.onUserClickListener = {
            if (it.htmlUrl != null) {
                val browserIntent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.htmlUrl))
                startActivity(browserIntent)
            } else {
                Toast.makeText(requireActivity(), "Такой ссылки нет :(", Toast.LENGTH_SHORT).show()
            }
        }
        adapter.onRepoClickListener = {
            val bundle = DirectoryFragment.newInstanceRootDirectory(
                it.owner?.login.toString(), it
                    .name ?: ""
            )
            findNavController().navigate(R.id.SecondFragment, bundle)
        }
        viewModel.repoList.observe(requireActivity()) {
            adapter.addDataRepo(it)
        }
        viewModel.userList.observe(requireActivity()) {
            adapter.addDataUser(it)
        }
        binding.ibButtonSearch.setOnClickListener {
//            adapter.clearList()
            viewModel.loadData(binding.etSearchBarInput.text.toString())

        }

    }

    private fun addTextChangeListeners() {
        binding.etSearchBarInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}