package com.example.myapplication.model

import com.example.myapplication.db.DatabaseServiceProvider
import java.io.Serializable

class Game : Serializable {
    //ne moze da se zove playerGroup zbog JVM, ima isti naziv za neki modul
    var plGroup : PlayerGroup = PlayerGroup("")
    private var numOfWordsPerPlayer : Int = 0
    private var words = mutableListOf<String>()
    private var currentPlayer : Player = Player("")
    private var currentGamePhase : Int = 1
    var listOfPairs = mutableListOf<Pair<Player, Player>>()

    fun getNumOfWordsPerPlayer() : Int {
        return numOfWordsPerPlayer
    }

    fun setPlayerGroup(pg: PlayerGroup) {
        plGroup = pg
    }

    fun makeRandomPairs(){
        var players : List<Player> = plGroup.getPGList()
        for(player in players) {
            while (!player.isPaired()) {
                val random = (0 until players.size).random()
                if (!players[random].isPaired()) {
                    listOfPairs.add(Pair(player, players[random]))
                    player.setPaired(true)
                    players[random].setPaired(true)
                }
            }
        }
    }

    fun sortByAnswers() : List<Pair<Player, Player>>{
        val sortedPLGroup = mutableListOf<Pair<Player, Player>>()
        for(pair in listOfPairs)
            sortedPLGroup.add(pair)
        return sortedPLGroup.sortedWith(answersComparator)
    }

}


fun main() {
    //var g1 = Game()
    //g1.setPlayerGroup(DatabaseServiceProvider.db.getPlayerGroup("matf"))
    //println(g1)
}