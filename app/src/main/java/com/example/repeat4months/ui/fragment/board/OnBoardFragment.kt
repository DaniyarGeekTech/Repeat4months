package com.example.repeat4months.ui.fragment.board


import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.repeat4months.R
import com.example.repeat4months.base.BaseFragment
import com.example.repeat4months.databinding.FragmentOnBoardBinding
import com.example.repeat4months.ui.fragment.App
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class OnBoardFragment : BaseFragment<FragmentOnBoardBinding>(FragmentOnBoardBinding::inflate),
    BoardAdapter.StartListener{

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSingInClient: GoogleSignInClient

        private lateinit var adapter: BoardAdapter
        override fun setupUI() {
            adapter = BoardAdapter(this)
            binding!!.onBoardPager.adapter = adapter
            initGoogleSingClient()

        }
    private fun initGoogleSingClient() {
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        googleSingInClient = GoogleSignIn.getClient(requireActivity(), gso)
        auth = Firebase.auth
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val acount = task.getResult(ApiException::class.java)
                acount.idToken?.let { fireBaseAuthWithGoogle(it) }
            } catch (e: ApiException) {
                Log.e("--------", e.toString())
            }
        }
    }
    private fun fireBaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    controller.navigateUp()
                } else {
                    Toast.makeText(requireContext(), task.exception.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    override fun start() {
        App.prefs.saveBoardState()
        findNavController().navigate(R.id.noteFragment)
    }
    companion object {
        private const val RC_SIGN_IN = 9001
    }
    }