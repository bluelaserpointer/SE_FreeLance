package com.example.accessingdatamysql.entity;

import javax.persistence.Entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Enemy", schema = "Enemygame")
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer", "fieldHandler" })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "EnemyId")

public class Enemy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer enemyId;

    private String enemyName;

    private Integer expPoint;

    private Integer healthPoint;

    private Integer attack;

    private Integer defense;

    private Integer attackRange;

    private Double cd;

    private Integer speed;

    public Enemy() {
    };

    public Enemy(String enemyName, Integer expPoint, Integer healthPoint, Integer attack, Integer defense,
            Integer attackRange, Double cd, Integer speed) {
        this.enemyName = enemyName;
        this.expPoint = expPoint;
        this.healthPoint = healthPoint;
        this.attack = attack;
        this.defense = defense;
        this.attackRange = attackRange;
        this.cd = cd;
        this.speed = speed;
    }

    // When modifying enemy
    public void setEnemy(String enemyName, Integer expPoint, Integer healthPoint, Integer attack, Integer defense,
            Integer attackRange, Double cd, Integer speed) {
        this.enemyName = enemyName;
        this.expPoint = expPoint;
        this.healthPoint = healthPoint;
        this.attack = attack;
        this.defense = defense;
        this.attackRange = attackRange;
        this.cd = cd;
        this.speed = speed;
    }

    public Integer getEnemyId() {
        return enemyId;
    }

    public void setEnemyId(Integer enemyId) {
        this.enemyId = enemyId;
    }

    public Integer getExpPoint() {
        return expPoint;
    }

    public void setExpPoint(Integer expPoint) {
        this.expPoint = expPoint;
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

    public String getEnemyName() {
        return enemyName;
    }

    public void setEnemyName(String EnemyName) {
        this.enemyName = EnemyName;
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
    private EnemyDetails enemyDetails;

    public EnemyDetails getEnemyDetails() {
        return enemyDetails;
    }

    public void setEnemyDetails(EnemyDetails enemyDetails) {
        this.enemyDetails = enemyDetails;
    }
}