package com.example.FirstProject.ioc;

public class IngredientFactory {
    public Ingredient get(String menu) {

        switch (menu) {
    case "Tonkatsu":  return new Pork("Pork");
            case "Steak":  return new Beef("Meat");
            default: return null;
        }
    }

}
