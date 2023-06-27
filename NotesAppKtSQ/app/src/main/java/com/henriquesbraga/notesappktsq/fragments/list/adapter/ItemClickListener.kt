package com.henriquesbraga.notesappktsq.fragments.list.adapter

import android.view.View

interface ItemClickListener {
  fun onItemClick(view: View?, position: Int)
}