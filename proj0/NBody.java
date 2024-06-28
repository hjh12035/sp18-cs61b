public class NBody {
	public static double readRadius(String FileName) {
		In in = new In(FileName);
		int Number = in.readInt();
		double Radius = in.readDouble();
		return Radius;
	}

	public static Planet[] readPlanets(String FileName) {
		In in = new In(FileName);
		int Number = in.readInt();
		double Radius = in.readDouble();		
		Planet[] planets = new Planet[Number];
		for (int i = 0; i < Number; i++) {
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m =in.readDouble();
			String img = in.readString();
 			planets[i] = new Planet(xP, yP, xV, yV, m, img);
		}
		return planets;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		
		String backgroundimg = "images/starfield.jpg";
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, backgroundimg);
		for (Planet p : planets) {
			p.draw();
		}

		StdDraw.enableDoubleBuffering();
		double Time = 0;
		while (Time < T) {
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for (int i = 0; i < planets.length; ++i) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for (int i = 0; i < planets.length; ++i) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.clear();
			StdDraw.picture(0, 0, backgroundimg);
			for (Planet p : planets) {
				p.draw();
		    }
		    StdDraw.show();
		    StdDraw.pause(10);
		    Time += dt;
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            			  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            			  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}
	}
}