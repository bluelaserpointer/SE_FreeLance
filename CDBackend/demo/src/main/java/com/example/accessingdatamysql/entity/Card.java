package com.example.accessingdatamysql.entity;

import javax.persistence.Entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Card", schema = "cardgame")
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer", "fieldHandler" })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "cardId")

public class Card {
    // 卡牌Id（PK）
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cardId;
    // 卡牌名称
    private String cardName;
    // 卡牌稀有度
    private String rarity;
    // 卡牌hp属性
    private Integer healthPoint;
    // 卡牌攻击属性
    private Integer attack;
    // 卡牌防御属性
    private Integer defense;
    // 卡牌攻击范围
    private Integer attackRange;
    // 卡牌冷却时间
    private Double cd;
    // 卡牌速度属性
    private Integer speed;

    // 可能需要添加技能相关的attributes

    public Card() {
    };

    public Card(String rarity, String cardName, Integer healthPoint, Integer attack, Integer defense,
            Integer attackRange, Double cd, Integer speed) {
        this.rarity = rarity;
        this.cardName = cardName;
        this.healthPoint = healthPoint;
        this.attack = attack;
        this.defense = defense;
        this.attackRange = attackRange;
        this.cd = cd;
        this.speed = speed;
    }

    public void setCard(String rarity, String cardName, Integer healthPoint, Integer attack, Integer defense,
            Integer attackRange, Double cd, Integer speed) {
        this.rarity = rarity;
        this.cardName = cardName;
        this.healthPoint = healthPoint;
        this.attack = attack;
        this.defense = defense;
        this.attackRange = attackRange;
        this.cd = cd;
        this.speed = speed;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer CardId) {
        this.cardId = CardId;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(Integer healthPoint) {
        this.healthPoint = healthPoint;

    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public Integer getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(Integer attackRange) {
        this.attackRange = attackRange;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Double getCd() {
        return cd;
    }

    public void setCd(Double cd) {
        this.cd = cd;
    }

    @Transient
    private CardDetails CardDetails;

    public CardDetails getCardDetails() {
        return CardDetails;
    }

    public void setCardDetails(CardDetails CardDetails) {
        this.CardDetails = CardDetails;
    }

    // @OneToMany(mappedBy = "Card")
    // private Set<Cart> cartRecords;

    // public Set<Cart> getCartRecords() {
    // return this.cartRecords;
    // }

    // public void setCartRecords(Set<Cart> cartRecords) {
    // this.cartRecords = cartRecords;
    // }

}