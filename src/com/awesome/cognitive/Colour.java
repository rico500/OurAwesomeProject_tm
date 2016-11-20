package com.awesome.cognitive;

/**
 * Created by ignat on 20/11/2016.
 */
public class Colour {

    public int r, g, b, a;
    public double scale;

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

    public Colour(Colour c){
        r = c.r;
        g = c.g;
        b = c.b;
        a = c.a;
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

    public void divide(int denum){
        r = r/denum;
        g = g/denum;
        b = b/denum;
        a = a/denum;
    }

    public static Colour scaleAdd(Colour[] cs){
        double r = 0, g = 0, b = 0, a = 0;

        for (Colour c : cs){
            r += c.r * c.scale;
            g += c.g * c.scale;
            b += c.b * c.scale;
            a += c.a * c.scale;
        }

        return new Colour((int) r, (int) g, (int) b, (int) a);
    }

    @Override
    public String toString(){
        return r + ", " + g + ", " + b + ", " + a;
    }
}
