package com.example.pruebacertificacion.vista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebacertificacion.R
import com.example.pruebacertificacion.pojo.Books
import com.example.pruebacertificacion.viewModel.BooksViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentoLista.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentoLista : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var listaBooks = ArrayList<Books>()
    lateinit var viewAdapter: BooksAdaptador
    lateinit var mViewModel: BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_fragmento_lista, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler)

        recyclerView.layoutManager = LinearLayoutManager(context)

        mViewModel = ViewModelProvider(activity!!).get(BooksViewModel::class.java)

        mViewModel.fetchFromServer()

        mViewModel.getDataFromDB()

        viewAdapter = BooksAdaptador(listaBooks)

        recyclerView.adapter = viewAdapter

        mViewModel.getDataFromDB().observe(this, Observer {
            viewAdapter.updateData(it)
        })

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentoLista.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentoLista().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}