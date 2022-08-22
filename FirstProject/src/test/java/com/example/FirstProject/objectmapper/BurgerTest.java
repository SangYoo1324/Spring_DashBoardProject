package com.example.FirstProject.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BurgerTest {
    @Test
    public void javaToJSON() throws JsonProcessingException {
        ObjectMapper objectMapper  = new ObjectMapper();
        /*
        {
        "name": "bigmac",
        "price": 5500,
        "ingredients": ["patty1", "patty2", "tomato", "sauce"]
        }
         */

        ObjectNode objectNode= objectMapper.createObjectNode();
        objectNode.put("name", "bigmac");
        objectNode.put("price", 5500);

        ArrayNode arrayNode =objectMapper.createArrayNode(); // 오브젝트 요소 중 list를 json 어레이로 변환
        arrayNode.add("patty1");
        arrayNode.add("patty2");
        arrayNode.add("tomato");
        arrayNode.add("sauce");
        objectNode.set("ingredients", arrayNode);
        String json2 = objectNode.toString();



        //준비

        List<String> ingredients= Arrays.asList("patty1","patty2", "tomato", "sauce" );
     Burger burger = new Burger("bigmac", 5500, ingredients);
        //수행
    String json = objectMapper.writeValueAsString(burger);
        //예상
String expected = "{\"name\":\"bigmac\",\"price\":5500,\"ingredients\":[\"patty1\",\"patty2\",\"tomato\",\"sauce\"]}";
        //검증
        assertEquals(expected, json);

        //이쁘게 본래 JSON 형태로 출력하기
        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.toPrettyString());
    }

@Test
    public void JsontoJava() throws JsonProcessingException {
        //준비
ObjectMapper objectMapper = new ObjectMapper();
String json = "{\"name\":\"bigmac\",\"price\":5500,\"ingredients\":[\"patty1\",\"patty2\",\"tomato\",\"sauce\"]}";
    //수행
    Burger burger= objectMapper.readValue(json, Burger.class);
    //예상
    List<String> ingredients= Arrays.asList("patty1","patty2", "tomato", "sauce" );
    Burger expected = new Burger("bigmac", 5500, ingredients);
    //검증
    assertEquals(expected.toString(), burger.toString());

    System.out.println(json);
    System.out.println(burger.toString());



    //이쁘게 본래 JSON 형태로 출력�
    JsonNode jsonNode= objectMapper.readTree(json);
    System.out.println(jsonNode.toPrettyString());
}
}