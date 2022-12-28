package app.good.things.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import app.good.things.R
import app.good.things.databinding.FragmentLoginBinding
import app.good.things.presentation.model.CodeStatus
import kotlinx.coroutines.launch

/**
 * Fragment Login
 */
internal class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel


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
                }
            }
        }
    }

    private fun showInvalidCode() =
        Toast.makeText(context, R.string.invalid_code, Toast.LENGTH_SHORT).show()

    private fun nextScreen() {
    }

}