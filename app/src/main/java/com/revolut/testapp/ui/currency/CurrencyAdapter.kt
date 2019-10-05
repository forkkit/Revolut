package com.revolut.testapp.ui.currency

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.revolut.testapp.R
import com.revolut.testapp.ui.currency.model.CurrencyData
import com.revolut.testapp.util.Constants.DEFAULT_RATE
import com.revolut.testapp.util.Constants.DEFAULT_VALUE
import com.revolut.testapp.util.ImageLoader

private const val FIRST_POSITION = 0

class CurrencyAdapter(
    private var currencyList: MutableList<CurrencyData>,
    private val imageLoader: ImageLoader,
    private var currencyListListener: CurrencyListListener
) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CurrencyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_currency,
                                                                       parent,
                                                                       false))

    override fun getItemCount() = currencyList.size

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val item = currencyList[position]
        holder.bind(item)
    }

    fun updateCurrencyData(updatedList: MutableList<CurrencyData>) {
        currencyList = updatedList

        notifyDataSetChanged()
    }

    inner class CurrencyViewHolder(
        itemView: View,
        private val currencyFlagImageView: ImageView = itemView.findViewById(R.id.currencyFlagImageView),
        private val currencyCodeTextView: TextView = itemView.findViewById(R.id.currencyCodeTextView),
        private val currencyTextView: TextView = itemView.findViewById(R.id.currencyTextView),
        private val currencyRateEditText: TextInputEditText = itemView.findViewById(R.id.currencyRateEditText),
        currencyDataContainer: ConstraintLayout = itemView.findViewById(R.id.currencyDataContainer)
    ) : RecyclerView.ViewHolder(itemView) {

        init {
            currencyDataContainer.setOnFocusChangeListener { _, hasFocus -> if (hasFocus) slideToTop() }
            currencyRateEditText.setOnFocusChangeListener { _, hasFocus -> if (hasFocus) slideToTop() }

            currencyRateEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val text = s.toString()
                    val baseCurrency = currencyList[FIRST_POSITION]

                    if (layoutPosition == FIRST_POSITION && text != baseCurrency.value) {
                        val baseCurrencyRate = if (text.isEmpty()) DEFAULT_RATE else text.toDouble()
                        val baseCurrencyValue = if (text.isEmpty()) DEFAULT_VALUE else text

                        currencyList.map { it.value = (baseCurrencyRate.times(it.rate)).toString() }

                        baseCurrency.rate = baseCurrencyRate
                        baseCurrency.value = baseCurrencyValue

                        currencyListListener.onCurrencyListUpdated(currencyList)
                    }
                }
            })
        }

        fun bind(currencyData: CurrencyData) {
            imageLoader.loadCircularImage(currencyData.currencyFlag, currencyFlagImageView)
            currencyCodeTextView.text = currencyData.currencyCode
            currencyTextView.text = currencyData.currency
            currencyRateEditText.setText(currencyData.value)
            currencyRateEditText.setSelection(currencyRateEditText.text?.length ?: 0)
        }

        private fun slideToTop() {
            layoutPosition.takeIf { it > FIRST_POSITION }?.also { currentPosition ->
                currencyList.removeAt(currentPosition).also {
                    currencyList.add(FIRST_POSITION, it)
                    currencyListListener.onCurrencyListUpdated(currencyList)
                }
                notifyItemMoved(currentPosition, FIRST_POSITION)
            }
        }
    }
}
