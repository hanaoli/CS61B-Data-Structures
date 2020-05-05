import java.lang.Math;

public class Planet {

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = Math.pow(this.xxPos - p.xxPos, 2);
        double dy = Math.pow(this.yyPos - p.yyPos, 2);
        return Math.sqrt(dx + dy);
    }

    public double calcForceExertedBy(Planet p) {
        double r = this.calcDistance(p);
        return 6.67e-11 * this.mass * p.mass / Math.pow(r, 2);
    }

    public double calcForceExertedByX(Planet p) {
        double f = this.calcForceExertedBy(p);
        double r = this.calcDistance(p);
        return f * (p.xxPos - this.xxPos) / r;
    }

    public double calcForceExertedByY(Planet p) {
        double f = this.calcForceExertedBy(p);
        double r = this.calcDistance(p);
        return f * (p.yyPos - this.yyPos) / r;
    }

    public double calcNetForceExertedByX(Planet[] p) {
        double netX = 0;
        int i = 0;
        while (i < p.length) {
            if (this.equals(p[i])) {
                i += 1;
                continue;
            }
            netX += this.calcForceExertedByX(p[i]);
            i += 1;
        }
        return netX;
    }

    public double calcNetForceExertedByY(Planet[] p) {
        double netY = 0;
        int i = 0;
        while (i < p.length) {
            if (this.equals(p[i])) {
                i += 1;
                continue;
            }
            netY += this.calcForceExertedByY(p[i]);
            i += 1;
        }
        return netY;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / this.mass;
        double aY = fY / this.mass;

        this.xxVel = this.xxVel + dt * aX;
        this.yyVel = this.yyVel + dt * aY;

        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
    }
}