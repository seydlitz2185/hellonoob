/**
 * @author stevenyu
 */
public  class NBody {
    public static String imageToDraW = "images/starfield.jpg";
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt =Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] planets =readBodies(filename);
        {
            StdDraw.enableDoubleBuffering();
            StdDraw.setScale(-radius, radius);
            StdDraw.clear();
            StdDraw.picture(0,0,imageToDraW);
            StdDraw.show();
            for (Body planet :  planets) {
                planet.draw();
            }
            /**StdAudio.play("/Users/stevenyu/Desktop/lab1-checkoff/NBody/src/audio/cjmg.mp3");*/
            StdAudio.play("/Users/stevenyu/Desktop/lab1-checkoff/NBody/src/audio/2001.mid");
        }

        for(int i = 0;i<(int)T;i+=dt){
            double[] xForces = new double[(int)T];
            double[] yForces = new double[(int)T];
            for(Body p: planets) {
                xForces[i] = p.calcNetForceExertedByX(planets);
                yForces[i] = p.calcNetForceExertedByY(planets);
                p.update(dt,xForces[i],yForces[i]); p.draw();
                }

            StdDraw.picture(0,0,imageToDraW);
            for (Body p :  planets) {
                      p.draw();
                }
            StdDraw.show();
            StdDraw.pause(10);

        }

    }

    public static double readRadius(String s){
        In in = new In(s);
        in.readInt();
        return in.readDouble();
    }

    public static Body[] readBodies(String s){
        In in = new In(s);
        int num = in.readInt();
        in.readDouble();
        Body[]b = new Body[num];
        int i = 0;
        while (!in.isEmpty() && i<num){
                b[i] = new Body(in.readDouble(), in.readDouble(),
                        in.readDouble(), in.readDouble(),
                        in.readDouble(), in.readString());
                i++;
        }
        return b;
    }
}
