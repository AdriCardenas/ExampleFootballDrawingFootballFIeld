package com.example.testmatchfield

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        for (player in row.players) {
            val view = inflater!!.inflate(R.layout.player_item, field)

            view.name.text = player.name

            val constraintSet = ConstraintSet()

            constraintSet.clone(field)
            if (beforeView != null) {

            }else{

            }
            beforeView = view
        }
    }

    private fun createPlayers() {

        val players = arrayListOf<Player>()
        players.add(Player("Adrian 1"))
        val row = Row(players)

        val players2 = arrayListOf<Player>()
        players2.add(Player("Adrian 2"))
        players2.add(Player("Adrian 3"))
        players2.add(Player("Adrian 4"))
        val row2 = Row(players2)

        val players3 = arrayListOf<Player>()
        players3.add(Player("Adrian 5"))
        players3.add(Player("Adrian 6"))
        players3.add(Player("Adrian 7"))
        players3.add(Player("Adrian 8"))
        val row3 = Row(players3)

        val players4 = arrayListOf<Player>()
        players4.add(Player("Adrian 9"))
        players4.add(Player("Adrian 10"))
        players4.add(Player("Adrian 11"))
        val row4 = Row(players4)

        val list: ArrayList<Row> = arrayListOf()
        list.add(row)
        list.add(row2)
        list.add(row3)
        list.add(row4)

        tactic = Tactic(list)
    }
}
