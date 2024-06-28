public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV,
				  double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		double dx = p.xxPos - this.xxPos;
		double dy = p.yyPos - this.yyPos;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public double calcForceExertedBy(Planet p) {
		double distance = this.calcDistance(p);
		return (G * mass * p.mass) / (distance * distance);
	}

	public double calcForceExertedByX(Planet p) {
		double dx = p.xxPos - this.xxPos;
		double distance = this.calcDistance(p);
		double force =this.calcForceExertedBy(p);
		return force * dx / distance;
	}

	public double calcForceExertedByY(Planet p) {
		double dy = p.yyPos - this.yyPos;
		double distance = this.calcDistance(p);
		double force =this.calcForceExertedBy(p);
		return force * dy / distance;
	}

	public boolean equals(Planet p) {
		if (xxPos == p.xxPos && yyPos == p.yyPos && xxVel == p.xxVel &&
		    yyVel == p.yyVel && mass == p.mass && imgFileName == p.imgFileName) {
			return true;
		}
		return false;
	}

	public double calcNetForceExertedByX(Planet[] allPlanets) {
		double netforcex = 0;
		for (Planet p : allPlanets) {
    		if (this.equals(p)) {
    			continue;
    		}
    		netforcex += this.calcForceExertedByX(p);
		}
		return netforcex;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets) {
		double netforcey = 0;
		for (Planet p : allPlanets) {
    		if (this.equals(p)) {
    			continue;
    		}
    		netforcey += this.calcForceExertedByY(p);
		}
		return netforcey;
	}

	public void update(double dt, double fX, double fY) {
		double aX = fX / mass;
		double aY = fY / mass;
		xxVel += aX * dt;
		yyVel += aY * dt;
		xxPos += xxVel * dt;
		yyPos += yyVel * dt;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
}