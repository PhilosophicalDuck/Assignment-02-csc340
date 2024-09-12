package com.csc340.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 Get a list of 10 elixirs and their effects plus side effects.

 @return a list of the elixirs: name, effects, side effects.
 */
@RestController
public class RestApiController {
    @GetMapping("/elixir")
    public Object getElixir() {
        try {
            String url = "https://wizard-world-api.herokuapp.com/Elixirs";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jsonListResponse = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jsonListResponse);

            List<Elixir> elixirList = new ArrayList<>();

            //The response from the above API is a JSON Array, which we loop through.
            for (JsonNode rt : root) {
                //Extract relevant info from the response and use it for what you want, in this case just print to the console.
                String name = rt.get("name").asText();
                String effect = rt.get("effect").asText();
                String sideEffect = rt.get("sideEffects").asText();

                //This is so every elixir has both effect and side effect
                if(Objects.equals(effect, "null") || Objects.equals(sideEffect, "null"))  {
                continue;}

                Elixir elixir = new Elixir(name, effect, sideEffect);
                elixirList.add(elixir);

                //I want only 10 elixirs
                if(elixirList.size() > 9) {
                    break;}
            }
            for (Elixir e : elixirList){
                System.out.println(e);}

            return elixirList;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestApiController.class.getName()).log(Level.SEVERE,
                    null, ex);
            return "error in /elixir";
        }

    }


    }
