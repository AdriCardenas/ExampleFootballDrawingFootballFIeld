package com.example.testmatchfield

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.player_item.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var tactic: Tactic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createPlayers()

        for (row in tactic.rows) {
            addPlayerToField(row)
        }
    }

    private fun addPlayerToField(row: Row) {
        val inflater = LayoutInflater.from(this)
        var beforeView: View? = null
        var position = 0

        val viewList = arrayListOf<View>()

        for (player in row.players) {

            val view = inflater!!.inflate(R.layout.player_item, null)
            view.id = View.generateViewId()

            view.name.text = player.name

            viewList.add(view)
        }

        for (view in viewList) {
            field.addView(view)
        }

        val constraintSet = ConstraintSet()
        constraintSet.clone(field)

        while (position < viewList.size) {
            val currentView = viewList[position]
            if (beforeView != null) {
                if (position + 1 < row.players.size) {
                    val nextView = viewList[position + 1]
                    constraintSet.connect(currentView.id, ConstraintSet.START, beforeView.id, ConstraintSet.END, 0)
                    constraintSet.connect(currentView.id, ConstraintSet.END, nextView.id, ConstraintSet.START, 0)
                } else {
                    constraintSet.connect(currentView.id, ConstraintSet.START, beforeView.id, ConstraintSet.END, 0)
                    constraintSet.connect(currentView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0)
                }

            } else {
                constraintSet.connect(currentView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0)
                if (position + 1 < row.players.size) {
                    val nextView = viewList[position + 1]
                    constraintSet.connect(currentView.id, ConstraintSet.END, nextView.id, ConstraintSet.START, 0)
                } else {
                    constraintSet.connect(currentView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0)
                }
            }
            constraintSet.connect(currentView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)

            beforeView = currentView
            position++
        }

        constraintSet.applyTo(field)
    }

    private fun createPlayers() {

        val players = arrayListOf<Player>()
        players.add(Player("player1", "Adrian 1"))
        val row = Row(players)

        val players2 = arrayListOf<Player>()
        players2.add(Player("player2", "Adrian 2"))
        players2.add(Player("player3", "Adrian 3"))
        players2.add(Player("player4", "Adrian 4"))
        val row2 = Row(players2)

        val players3 = arrayListOf<Player>()
        players3.add(Player("player5", "Adrian 5"))
        players3.add(Player("player6", "Adrian 6"))
        players3.add(Player("player7", "Adrian 7"))
        players3.add(Player("player8", "Adrian 8"))
        val row3 = Row(players3)
//
        val players4 = arrayListOf<Player>()
        players4.add(Player("player9", "Adrian 9"))
        players4.add(Player("player10", "Adrian 10"))
        players4.add(Player("player11", "Adrian 11"))
        val row4 = Row(players4)

        val list: ArrayList<Row> = arrayListOf()
        list.add(row)
        list.add(row2)
        list.add(row3)
        list.add(row4)

        tactic = Tactic(list)
    }
}
