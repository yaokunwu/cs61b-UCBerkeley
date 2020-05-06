public class NBody{

	public static double readRadius(String txtfile){
		In in= new In(txtfile);
		int num=in.readInt();
		double radius=in.readDouble();
		return radius;	
	}

	public static Planet[] readPlanets(String txtfile){
		In in=new In(txtfile);
		int num= in.readInt();
		Planet[] planets= new Planet[num];
		in.readDouble();
		for(int i=0;i<num;i++){
			double xxPos=in.readDouble();
			double yyPos=in.readDouble();
			double xxVel=in.readDouble();
			double yyVel=in.readDouble();
			double mass=in.readDouble();
			String img=in.readString();
			planets[i]=new Planet(xxPos,yyPos,xxVel,yyVel,mass,img);
		}

		return planets;

	}

	public static void main(String[] args){
		double T,dt,radius;
		double time;
		Planet[] all_planets;
		String filename;
		T=Double.parseDouble(args[0]);
		dt=Double.parseDouble(args[1]);
		filename=args[2];
		radius=readRadius(filename);
		all_planets=readPlanets(filename);

		String imageToDraw="./images/starfield.jpg";

		StdDraw.setScale(-radius,radius);
		StdDraw.clear();
		StdDraw.picture(0,0,imageToDraw);
		
		for(Planet p : all_planets){
			p.draw();
		
		}

		StdDraw.enableDoubleBuffering();


		for(time=0.0;time<T;time=time+dt){
			double[] xForces= new double[all_planets.length];
			double[] yForces= new double[all_planets.length];
			for(int i=0; i<all_planets.length;i++){
				yForces[i]=all_planets[i].calcNetForceExertedByY(all_planets);
				xForces[i]=all_planets[i].calcNetForceExertedByX(all_planets);
			}
			for (int i=0;i<all_planets.length;i++){
				all_planets[i].update(dt,xForces[i],yForces[i]);
			}

			StdDraw.picture(0,0,imageToDraw);

			for (Planet p : all_planets){

				p.draw();
			}

			StdDraw.show();
			StdDraw.pause(10);

		}
		StdOut.printf("%d\n",all_planets.length);
		StdOut.printf("%.2e\n",radius);
		for(Planet p : all_planets){
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e",p.xxPos,p.yyPos,p.xxVel,p.yyVel,p.mass);
			StdOut.printf("%12s\n",p.imgFileName);
		}
		



	}

}
