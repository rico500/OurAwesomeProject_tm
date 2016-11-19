package com.awesome.cognitive;

/**
 * Created by ignat on 20/11/2016.
 */
public class Colour {

    public int r, g, b, a;

    public Colour(int r, int g, int b){
        this.r = check(r);
        this.g = check(g);
        this.b = check(b);
        this.a = 255;
    }

    public Colour(int r, int g, int b, int a){
        this.r = check(r);
        this.g = check(g);
        this.b = check(b);
        this.a = check(a);
    }

    private int check(int k){
        return Math.max(Math.min(k, 255),0);
    }

    public void add(Colour c){
        r += c.r;
        g += c.g;
        b += c.b;
        a += (a + c.a) / 2;
    }

    @Override
    public String toString(){
        return r + ", " + g + ", " + b + ", " + a;
    }
}
