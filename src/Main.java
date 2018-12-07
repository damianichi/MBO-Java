import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	
	public String Info[];
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
		int MaxMaq =8;	
		
		//--------------------PRUEBAS DE archivos
		
				File dir = new File("BoctorProblem_90_instancias/");
				String[] ficheros = dir.list();
				System.out.println(Arrays.toString(ficheros));
		
		if (ficheros != null){
		String direccion="BoctorProblem_90_instancias/"+ficheros[0];
		//matriz appmatr = new matriz(testmatriz, celdas,MaxMaq);
		inidata alg = new inidata(direccion);
		//inidata alg = new inidata(testmatriz2, celdas, MaxMaq);

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
		
		alg.algoritmo(); 				//EJECUTAR ALGORITMO
		
		
		
		
		/* // ejemplo para ver AG
		for(int i=0; i<alg.ag.length;i++){
			alg.ag[i].showMatriz(alg.ag[i].mat_mxc);
			System.out.println("--------------------");
		}
		System.out.println("FIN");
		*/

		//System.out.println(alg.ag[0].fit);
		//appmatr.showMatriz(alg.tempElitist.get(0).mat_mxc);		
		//System.out.println(Math.round(Math.random()*10));
		
		
		
		


		
		
		

		}			
	}
	
	
	
	
	

}
