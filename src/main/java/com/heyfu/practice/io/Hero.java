package com.heyfu.practice.io;

/**
 * Description:
 *
 * @author heyefu
 * Create in: 2018-04-10
 * Time: 14:24
 **/
public class Hero {

    private String name;
    private float hp;
    private int damage;

    public Hero(String name, float hp) {
        this.name = name;
        this.hp = hp;
    }

    public void attack(Hero h){

    }

    public synchronized void recover(){
        while (this.hp >= 10){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.hp++;
        this.notifyAll();
    }

    public synchronized void hurt(){
        while (this.hp <= 1){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.hp--;
        this.notifyAll();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
