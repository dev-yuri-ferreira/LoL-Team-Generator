package com.moony.LoLTeamGenerator

import android.os.Parcel
import android.os.Parcelable

class Player():Parcelable {
    constructor(name:String,topSkill:String,
                jngSkill:String,midSkill:String,
                adcSkill:String,supSkill:String) : this() {
        this.playerName = name
        this.playerLanes["Top"] = topSkill
        this.playerLanes["Jng"] = jngSkill
        this.playerLanes["Mid"] = midSkill
        this.playerLanes["Adc"] = adcSkill
        this.playerLanes["Sup"] = supSkill
    }

    var playerName:String? = null
    val playerLanes = mutableMapOf<String,String>()

    constructor(parcel: Parcel) : this() {
        playerName = parcel.readString()
        parcel.readMap(playerLanes, String.javaClass.classLoader)

    }

    fun getLane(lane:String):String?{
        return playerLanes[lane]
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(playerName)
        parcel.writeMap(playerLanes)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }

}