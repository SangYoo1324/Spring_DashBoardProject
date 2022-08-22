package com.example.FirstProject.ioc;

public class Chef {
    private IngredientFactory  ingredientFactory;
    //chef 가 식재료 공장과 협업하기 위한 DI
    public Chef(IngredientFactory ingredientFactory) {
        //여기서 식재료 공장 객체 생성
        this.ingredientFactory = ingredientFactory;
    }

    //식재료 공장 객체를 등록만 해놓음( 객체 생성은 x)

    public String cook(String menu){
      //재료준비
       Ingredient ingredient = ingredientFactory.get(menu);


        //요리 반환
        return "Steak with"+" "+ingredient.getName();


    }
}
