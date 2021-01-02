public class Planet {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    public static final double G = 6.67e-11;
    public Planet(double xP, double yP, double xV,
        double yV, double m, String img) {
            this.xxPos = xP;
            this.yyPos = yP;
            this.xxVel = xV;
            this.yyVel = yV;
            this.mass = m;
            this.imgFileName = img;
    }
    public Planet(Planet P) {
        this.xxPos = P.xxPos;
        this.yyPos = P.yyPos;
        this.xxVel = P.xxVel;
        this.yyVel = P.yyVel;
        this.mass = P.mass;
        this.imgFileName = P.imgFileName;
    }

    public double calcDistance(Planet P) {
        double dx = this.xxPos - P.xxPos;
        double dy = this.yyPos - P.yyPos;
        return Math.sqrt(dx*dx+dy*dy);
    }
    public double calcForceExertedBy(Planet P) {
        return (G*this.mass*P.mass)/(this.calcDistance(P)*this.calcDistance(P));
    }
    public double calcForceExertedByX(Planet P) {
        double dx = P.xxPos-this.xxPos;
        return (this.calcForceExertedBy(P)*dx)/this.calcDistance(P);
    }
    public double calcForceExertedByY(Planet P) {
        double dy = P.yyPos - this.yyPos;
        return (this.calcForceExertedBy(P)*dy)/this.calcDistance(P);
    }
    
    public double calcNetForceExertedByX(Planet[] planets) {
        double fx = 0.0;
        for (Planet p : planets) {
            if (this.equals(p))
                continue;
            fx += this.calcForceExertedByX(p);
        }
        return fx;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double fy = 0.0;
        for (Planet p : planets) {
            if (this.equals(p))
                continue;
            fy += this.calcForceExertedByY(p);
        }
        return fy;
    }

    public void update(double dt, double fx, double fy) {
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        this.xxVel = this.xxVel + ax * dt;
        this.yyVel = this.yyVel + ay * dt;
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
    }

}