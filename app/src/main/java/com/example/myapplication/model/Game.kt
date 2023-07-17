package com.example.myapplication.model

class Game {
    var playerGroup : PlayerGroup = PlayerGroup("")
    private var numOfWordsPerPlayer : Int = 0
    private var words = mutableListOf<String>()
    private var currentPlayer : Player = Player("")
    private var currentGamePhase : Int = 1
    var listOfPairs = mutableListOf<Pair<Player, Player>>()


    fun getNumOfWordsPerPlayer() : Int {
        return numOfWordsPerPlayer
    }

    fun setPlayerGroup(plGroup: PlayerGroup) {
        playerGroup = plGroup
    }

    fun makeRandomPairs(){
        var PGList : List<Player> = playerGroup.getPGList()
        for(player in PGList) {
            while (!player.isPaired()) {
                val random = (0 until PGList.size).random()
                if (!PGList[random].isPaired()) {
                    listOfPairs.add(Pair(player, PGList[random]))
                    player.setPaired(true)
                    PGList[random].setPaired(true)
                }
            }
        }
    }


}



fun main() {
    var g1 = Game()
    println(g1)
}