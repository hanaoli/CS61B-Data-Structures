public class NBody {
    /* Return radius of the universe */
    public static double readRadius(String path) {
        In file = new In(path);
        file.readDouble();
        return file.readDouble();
    }
    /* Return array of planets in the path text file */
    public static Planet[] readPlanets(String path) {
        In file = new In(path);
        int N = file.readInt();
        Planet[] p = new Planet[N];
        file.readDouble();
        int i = 0;
        while (i < N) {
            double xP = file.readDouble();
            double yP = file.readDouble();
            double xV = file.readDouble();
            double yV = file.readDouble();
            double m = file.readDouble();
            String img = file.readString();
            p[i] = new Planet(xP, yP, xV, yV, m, img);
            i += 1;
        }
        return p;
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Please enter the correct command line arguments.");
            System.out.println("e.g. java NBody 157788000.0 25000.0 data/planets.txt");
        }

        double T = Double.parseDouble(args[0]);
        double dT = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Planet[] p = readPlanets(filename);

        String imageToDraw = "images/starfield.jpg";
        int i = 0;

        double time = 0.0;
        while (time < T) {
            double[] xForces = new double[p.length];
            double[] yForces = new double[p.length];

            i = 0;
            while (i < p.length) {
                xForces[i] = p[i].calcNetForceExertedByX(p);
                yForces[i] = p[i].calcNetForceExertedByY(p);
                i += 1;
            }

            i = 0;
            while (i < p.length) {
                p[i].update(dT, xForces[i], yForces[i]);
                i += 1;
            }

            StdDraw.setScale(-radius, radius);
            StdDraw.picture(0, 0, imageToDraw);

            i = 0;
            while (i < p.length) {
                p[i].draw();
                i += 1;
            }

            StdDraw.show(10);
            time += dT;
        }

        StdOut.printf("%d\n", p.length);
        StdOut.printf("%.2e\n", radius);
        for (i = 0; i < p.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", p[i].xxPos, p[i].yyPos, p[i].xxVel, p[i].yyVel, p[i].mass, p[i].imgFileName);
        }
    }
}
