public class Planet{
	public double xxPos,yyPos,xxVel,yyVel,mass;
	public String imgFileName;
	public static double G=6.67*1e-11;
	public Planet(double xP, double yP, double xV, double yV, double m,
			String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	};
	public Planet(Planet p){
		xxPos=p.xxPos;
		yyPos=p.yyPos;
		xxVel=p.xxVel;
		yyVel=p.yyVel;
		mass=p.mass;
		imgFileName=p.imgFileName;
		
	};

	public double calcDistance(Planet ano_p){
	double r,dx,dy;
	dx=ano_p.xxPos-this.xxPos;
	dy=ano_p.yyPos-this.yyPos;
	r=Math.sqrt(dx*dx+dy*dy);
	return r;
	}

	public double calcForceExertedBy(Planet ano_p){
	double F;
	F=G*this.mass*ano_p.mass/(this.calcDistance(ano_p)*this.calcDistance(ano_p));
	return F;
	}
	public double calcForceExertedByY(Planet ano_p){
	double F,dy,Fy;
	F=this.calcForceExertedBy(ano_p);
	dy=ano_p.yyPos-this.yyPos;
	Fy=F*dy/this.calcDistance(ano_p);
	return Fy;
	}
	public double calcForceExertedByX(Planet ano_p){
	double F,dx,Fx;
	F=this.calcForceExertedBy(ano_p);
	dx=ano_p.xxPos-this.xxPos;
	Fx=F*dx/this.calcDistance(ano_p);
	return Fx;
	}
	
	public double calcNetForceExertedByX(Planet[] allPlanets){
	double sum_Fx=0,Fx;
		for (Planet planet : allPlanets){
			if(planet==this){
				continue;
			}
			Fx=this.calcForceExertedByX(planet);
			sum_Fx=sum_Fx+Fx;

		}
		return sum_Fx;

	}

	public double calcNetForceExertedByY(Planet[] allPlanets){
	double sum_Fy=0,Fy;
		for (Planet planet : allPlanets){
			if(planet==this){
				continue;
			}
			Fy=this.calcForceExertedByY(planet);
			sum_Fy=sum_Fy+Fy;

		}
		return sum_Fy;
	}


	public void update(double dt, double Fx, double Fy){
		double accx,accy;
		accx=Fx/this.mass;
		accy=Fy/this.mass;
		this.xxVel=this.xxVel+dt*accx;
		this.yyVel=this.yyVel+dt*accy;
		this.xxPos=this.xxPos+dt*this.xxVel;
		this.yyPos=this.yyPos+dt*this.yyVel;

	}

	public void draw(){
		StdDraw.picture(this.xxPos,this.yyPos,"./images/"+this.imgFileName);	

	}

}
