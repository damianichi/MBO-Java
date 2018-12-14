import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	
	public String Info[];
	public static void main (String args[]){	
		int[][] testmatriz = {{1,1,0,1,1,1,0},	//datos de prueba
						  	 {1,0,1,0,0,0,1},
						  	{1,0,1,0,0,0,1},
						  	{0,1,0,1,0,1,0},
						  	 {1,0,0,0,0,0,1}};
		
		int[][] testmatriz2={ { 0,0,0,0,1,0,0,1,0,1,0,1,0,0,0,0,0,0,0,1,0,1,1,0,0,1,0,0,1,0 },{ //datos de prueba
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
		
		int celdas= 2;	//datos de prueba
		int MaxMaq =8;	//datos de prueba
		inidata alg ;
		
		//--------------------PRUEBAS DE archivos
		
				File dir = new File("BoctorProblem_90_instancias/");
				String[] ficheros = dir.list();
				System.out.println(Arrays.toString(ficheros));
		
				
		for(int i=0; i<ficheros.length;i++){
			if (ficheros != null){
				String direccion="BoctorProblem_90_instancias/"+ficheros[i];
				alg= new inidata(direccion);				
				alg.algoritmo(); 				//EJECUTAR ALGORITMO
			}	
				
		}
		/*//al descomentar inicia algoritmo con solo una instancia
		if (ficheros != null){
			String direccion="BoctorProblem_90_instancias/MCDP_Boctor_Problem01_C3_M6.txt";
			alg= new inidata(direccion);
			alg.algoritmo();
		
		}	*/		
	}
	
	
	
	
	

}
