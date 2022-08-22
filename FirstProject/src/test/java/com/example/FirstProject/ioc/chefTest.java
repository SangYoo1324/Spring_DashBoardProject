package com.example.FirstProject.ioc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class chefTest {
@Test
    void tonkatsu(){
IngredientFactory  ingredientFactory= new IngredientFactory();
    //준비
    Chef chef = new Chef(ingredientFactory);
    String menu= "Tonkatsu";
    //수행
    String food= chef.cook(menu);
    //예상
    String expected = "Tonkatsu with Pork";
    //검증
    assertEquals(expected, food);
    System.out.println(expected+"  "+ food);
    }

    @Test
    void steak(){
        IngredientFactory  ingredientFactory= new IngredientFactory();
        //준비
        Chef chef = new Chef(ingredientFactory);
        String menu= "Steak";
        //수행
        String food= chef.cook(menu);
        //예상
        String expected = "Steak with Brisket";
        //검증
        assertEquals(expected, food);
        System.out.println(expected+"  "+ food);
    }
    }
