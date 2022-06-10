package com.bangkit.bahanbaku.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.bahanbaku.adapter.DialogIngredientsAdapter
import com.bangkit.bahanbaku.data.local.entity.IngredientEntity
import com.bangkit.bahanbaku.data.remote.response.RecipeEntity
import com.bangkit.bahanbaku.databinding.DialogIngredientsBinding
import com.bangkit.bahanbaku.ui.ingredient.IngredientActivity
import com.bangkit.bahanbaku.ui.updatelocation.UpdateLocationActivity
import com.bangkit.bahanbaku.utils.setWidthPercent

class IngredientsDialog : DialogFragment() {

    private val binding: DialogIngredientsBinding by lazy {
        DialogIngredientsBinding.inflate(layoutInflater)
    }

    private lateinit var detailActivity: DetailActivity
    private var recipe: RecipeEntity? = null
    private val list = arrayListOf<IngredientEntity>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailActivity = requireActivity() as DetailActivity
        recipe = detailActivity.recipe
        setWidthPercent(90)

        setupView()
    }

    private fun setupView() {
        if (recipe != null) {
            detailActivity.cleanseIngredients(recipe!!.ingredients).forEach {
                list.add(IngredientEntity(it, true))
            }
        }

        binding.rvIngredient.apply {
            adapter = DialogIngredientsAdapter(list)
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.btnGetIngredients.setOnClickListener {
            val ingredientsCheckedList = arrayListOf<String>()
            list.forEach {
                if (it.checked) {
                    ingredientsCheckedList.add(it.ingredient)
                }
            }

            if (detailActivity.isLocationNull) {
                val intent = Intent(requireContext(), UpdateLocationActivity::class.java)
                intent.putExtra(UpdateLocationActivity.EXTRA_TO_INGREDIENTS, true)
                intent.putExtra(IngredientActivity.EXTRA_SEARCH, ingredientsCheckedList)
                startActivity(intent)
            } else {
                val intent = Intent(requireContext(), IngredientActivity::class.java)
                intent.putExtra(IngredientActivity.EXTRA_SEARCH, ingredientsCheckedList)
                startActivity(intent)
            }
        }
    }
}