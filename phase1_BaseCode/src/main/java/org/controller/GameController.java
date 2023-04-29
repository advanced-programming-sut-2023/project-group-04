package org.controller;

import com.google.gson.Gson;
import org.model.Empire;
import org.model.Game;
import org.model.MapCell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

public class GameController {
    public String showPopularityFactors() {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        HashMap<String, Integer> popularityFactors = empire.getPopularity();
        String popularity = "<<popularity factors>>\n";
        for (String key : popularityFactors.keySet()) {
            popularity += key + " :" + popularityFactors.get(key) + "\n";
        }
        return popularity;
    }

    public String showPopularity() {
        int popularitySum = 0;
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        HashMap<String, Integer> popularityFactors = empire.getPopularity();
        for (String key : popularityFactors.keySet()) {
            popularitySum += popularityFactors.get(key);
        }
        return "your popularity is: <<" + popularitySum + ">>";
    }

    public String showFoodList() {
        return null;
    }

    public String changeFoodRate(Matcher matcher) {
        return null;
    }

    public String showFoodRate() {
        return null;
    }

    public String changeTaxRate(Matcher matcher) {
        return null;
    }

    public String showTaxRate() {
        return null;
    }

    public String changeFearRate(Matcher matcher) {
        return null;
    }

    public String showFearRate() {
        return null;
    }

    public String dropBuilding(Matcher matcher) {
        return null;
    }

    public String selectBuilding(Matcher matcher) {
        return null;
    }

    public String createUnit(Matcher matcher) {
        return null;
    }

    public String repair(Matcher matcher) {
        return null;
    }

    public String selectUnit(Matcher matcher) {
        return null;
    }

    public String moveUnit(Matcher matcher) {
        return null;
    }

    public String patrolUnit(Matcher matcher) {
        return null;
    }

    public String setUnitMode(Matcher matcher) {
        return null;
    }

    public String groundAttack(Matcher matcher) {
        return null;
    }

    public String airAttack(Matcher matcher) {
        return null;
    }

    public String pourOil(Matcher matcher) {
        return null;
    }

    public String digTunnel(Matcher matcher) {
        return null;
    }

    public String engineerBuild(Matcher matcher) {
        return null;
    }

    public String disbandUnit(Matcher matcher) {
        return null;
    }

    public String nextTurn() {
        return null;
    }

    private ArrayList<MapCell> routing(int originX, int originY, int destinationX, int destinationY) {
        return null;
    }


}
