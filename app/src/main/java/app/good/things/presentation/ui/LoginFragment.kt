package app.good.things.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import app.good.things.R
import app.good.things.databinding.FragmentLoginBinding
import app.good.things.presentation.model.CodeStatus
import app.good.things.presentation.prefs
import app.good.things.presentation.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

/**
 * Fragment Login
 */
internal class LoginFragment : BaseFragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Check existence individual code
        if (!prefs.individualCode.isNullOrEmpty()) {
            nextScreen()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentLoginBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.next.setOnClickListener {
            viewModel.checkCode(binding.editText.text.toString())
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        lifecycleScope.launch {
            viewModel.codeStatus.collect {
                when (it) {
                    CodeStatus.VALID_CODE -> nextScreen()
                    CodeStatus.INVALID_CODE -> showInvalidCode()
                    CodeStatus.ERROR -> showError()
                }
            }
        }
    }


    private fun nextScreen() {
        findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
    }

}