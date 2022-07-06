/**
 * @author stevenyu
 */
public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xxPos, double yyPos,
                double xxVel, double yyVel,
                double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
        }

    public Body(Body b) {
        this(b.xxPos,b.yyPos,b.xxVel,b.yyVel,b.mass,b.imgFileName);
    }

    public double calcDistance( Body b){
        double distance;
        distance = Math.sqrt(Math.pow(b.xxPos-this.xxPos,2)+
                Math.pow(b.yyPos - this.yyPos,2));
        return distance;
    }

    public double calcForceExertedBy( Body b){
        double force ;
        force = (6.67E-11*this.mass*b.mass)/Math.pow(calcDistance(b),2);
        return force;
    }

    public double calcForceExertedByX( Body b){
        double forceX ;
        forceX = (calcForceExertedBy(b)*(b.xxPos-this.xxPos))/calcDistance(b);
        return forceX;
    }

    public double calcForceExertedByY( Body b){
        double forceY ;
        forceY = (calcForceExertedBy(b)*(b.yyPos-this.yyPos))/calcDistance(b);
        return forceY;
    }

    public double calcNetForceExertedByX(Body[] allBodys){
        double netForceX =0;
        for(Body b : allBodys){
            if (!this.equals(b)){
            netForceX +=calcForceExertedByX(b);}
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Body[] allBodys){
        double netForceY =0;
        for(Body b : allBodys){
            if (!this.equals(b)){
            netForceY +=calcForceExertedByY(b);}
        }
        return netForceY;
    }
/**For example, samh.update(0.005, 10, 3)
would adjust the velocity and position
if an x-force of 10 Newtons and a y-force of 3 Newtons
were applied for 0.005 seconds.*/
    public void update(double dt ,double fx,double fy){
        double accelerationX,accelerationY;
        accelerationX = fx/this.mass;
        accelerationY = fy/this.mass;
        this.xxVel += dt*accelerationX;
        this.yyVel += dt*accelerationY;
        this.xxPos += dt*this.xxVel;
        this.yyPos += dt*this.yyVel;
    }

    public void draw(){
        StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
        StdDraw.show();
    }
}
