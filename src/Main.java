
public class Main {
	
	public static void main (String args[]){
		int[][] testmatriz = {{1,1,0,1,1,1,0},
						  	 {1,0,1,0,0,0,1},
						  	{1,0,1,0,0,0,1},
						  	{0,1,0,1,0,1,0},
						  	 {1,0,0,0,0,0,1}};
		
		int[][] testmatriz2={ { 0,0,0,0,1,0,0,1,0,1,0,1,0,0,0,0,0,0,0,1,0,1,1,0,0,1,0,0,1,0 },{
								0,1,0,0,1,0,0,0,1,0,0,0,0,0,0,0,1,0,0,1,1,1,0,0,1,1,1,0,0,0 },{
								0,0,0,0,0,0,0,0,0,0,1,1,1,0,1,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0 },{
								0,0,1,0,0,0,1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1 },{
								0,0,1,1,0,0,0,0,0,0,1,1,1,0,1,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0 },{
								0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,1,1,1,1,1 },{
								0,0,0,0,0,0,1,0,0,0,1,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0 },{
								1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0 },{
								0,1,0,0,0,0,0,0,1,1,0,1,0,0,0,0,0,0,0,0,0,1,0,1,1,0,0,1,0,1 },{
								1,0,0,0,0,0,0,0,0,0,0,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0 },{
								0,0,0,0,0,0,0,1,1,0,0,1,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,1 },{
								1,0,1,1,0,0,1,0,0,0,1,0,1,0,1,1,0,1,0,0,1,0,0,0,0,0,0,0,0,0 },{
								0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,0,1,0,0,0,0,0,1,0,1,1,1,0,1,0 },{
								0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,1,0,0,0,0,0 },{
								1,0,1,1,0,0,1,0,0,0,1,0,1,1,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0 },{
								0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,1,1,1,1,1,1,1 }};
		
		int celdas= 2;
		int MaxMaq =3;	
		
		//matriz appmatr = new matriz(testmatriz, celdas,MaxMaq);
		inidata alg = new inidata(testmatriz, celdas, MaxMaq);
		/*appmatr.showMatriz(testmatriz);
		System.out.println("---------------");

		//System.out.println(testmatriz[0].length);
		appmatr.crearMxC(testmatriz, celdas); // comentar para probar ejemplo de pdf
		//appmatr.showMatriz(appmatr.mat_mxc);

		System.out.println("---------------");
		appmatr.crearPxC(testmatriz, celdas);
		System.out.println("---------------");
		appmatr.calcularfit();
		System.out.println(appmatr.fit);
		
		appmatr.esFactible();
		System.out.println("--------------------");
		if(appmatr.esFactible2()){
			System.out.println("factible!!!!");
		}else{
			System.out.println("no FACIBLEEE!");
		}*/
		
		//---------------------------PRUEBAS ALGORITMO--------------------------
		//alg.pruebas();
		alg.algoritmo();
		System.out.println(alg.ag[0].fit);
		//appmatr.showMatriz(alg.tempElitist.get(0).mat_mxc);		
		//System.out.println(Math.round(Math.random()*10));

					
	}

}
