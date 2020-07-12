package com.example.accessingdatamysql.controller;

import com.example.accessingdatamysql.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

// import java.sql.Timestamp;
import java.util.*;

// import javax.validation.constraints.Null;

import com.example.accessingdatamysql.service.CardService;

@CrossOrigin(origins = "*")
@RestController // This means that this class is a Controller
@RequestMapping(path = "/card") // This means URL's start with /demo (after Application path)
public class CardController {
  @Autowired // This means to get the bean called CardRepository
  // Which is auto-generated by Spring, we will use it to handle the data
  private CardService CardService;

  @RequestMapping(value = "/getCard")
  public Card findCardByCardId(@RequestParam("cardId") Integer cardId) {
    return CardService.getOneCard(cardId);
  }

  @RequestMapping(value = "/addCard")
  public @ResponseBody String addNewCard(@RequestParam("cardName") String cardName,
      @RequestParam("rarity") String rarity, @RequestParam("healthPoint") Integer healthPoint,
      @RequestParam("attack") Integer attack, @RequestParam("defense") Integer defense,
      @RequestParam("attackRange") Integer attackRange, @RequestParam("cd") Double cd,
      @RequestParam("speed") Integer speed, @RequestParam("cardImg") String cardImg,
      @RequestParam("shortDescription") String shortDescription,
      @RequestParam("cardDescription") String cardDescription) {
    return CardService.addNewCard(cardName, rarity, healthPoint, attack, defense, attackRange, cd, speed, cardImg,
        shortDescription, cardDescription);
  }

  @RequestMapping(value = "/updateCard")
  public @ResponseBody String updateCard(@RequestParam("cardId") Integer cardId,
      @RequestParam("cardName") String cardName, @RequestParam("rarity") String rarity,
      @RequestParam("healthPoint") Integer healthPoint, @RequestParam("attack") Integer attack,
      @RequestParam("defense") Integer defense, @RequestParam("attackRange") Integer attackRange,
      @RequestParam("cd") Double cd, @RequestParam("speed") Integer speed, @RequestParam("cardImg") String cardImg,
      @RequestParam("shortDescription") String shortDescription,
      @RequestParam("cardDescription") String cardDescription) {
    System.out.println("In controller");

    return CardService.updateCard(cardId, cardName, rarity, healthPoint, attack, defense, attackRange, cd, speed,
        cardImg, shortDescription, cardDescription);
  }

  @RequestMapping(value = "/getAllCards")
  public List<Card> getAllCards() {
    return CardService.getAllCards();
  }

  @RequestMapping(value = "/deleteCards")
  public String deleteCards(@RequestParam("cardIds") List<Integer> cardIds) {
    return CardService.deleteCards(cardIds);
  }

  @RequestMapping(value = "/deleteAllCards")
  public String deleteAll() {
    return CardService.deleteAll();
  }

  @RequestMapping(value = "/deleteCard")
  public List<Card> deleteCard(@RequestParam("cardId") Integer cardId)
  {
    return CardService.deleteCard(cardId);
  }

}