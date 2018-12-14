import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class inidata {
	
	
	//------------- primera parte algoritmo--------
	int keep =2;
	private final double p= 0.416;
	private final int poblacion= 5000;
	private final Integer max_p= 40; //gen max
	private int NP1;
	private int NP2;
	private int dim1;
	private int dim2;
	private final double periodo= 1.2;
	private double particion=0.416;
	private double BAR= particion;
	private int [][] land1; //variables donde se crean hijos de mariposas
    private int [][] land2; 
    private double maxStepSize = 1.0;	
	private double r1=0;
	private double r2=0;
	private double r3=0;
	
	matriz bestSol; //var para almacenar mejor solucion
	matriz Maux;	//objeto de paso para calcular fit en base a matriz inicial y pxc

	ArrayList<matriz> tempElitist = new ArrayList<matriz>();	// tempelitist
	private matriz[] ag ;	// array con todas las soluciones
	private matriz[] ag1;	// soluciones poblacion 1
	private matriz[] ag2;	//soluciones poblaciones 2
	int [][]matrizI; //matriz inicial
	int celdas;
	int Mmax;
	private int bestSolucion;
	String archivoDir;	
	// --------------end-------------------
	//public inidata(int [][]matrizI, int celdas,int Mmax){
	public inidata(String archivoDir){	//inicializa variables para el algoritmo
		this.archivoDir= archivoDir;//nombre del archivo que contiene la instancia
		LeerArchivo(archivoDir);		
		bestSol= new matriz(matrizI,celdas,Mmax);
		NP1=(int) Math.round(p*poblacion);
		NP2 = poblacion - NP1;
		//System.out.println("NP:"+NP1+ "-"+NP2);
		land1 =new int [matrizI.length][celdas];
		land2 = new int [matrizI.length][celdas];
		ag = new matriz[poblacion];	// array con todas las soluciones
		ag1 = new matriz [NP1];	// soluciones poblacion 1
		ag2 = new matriz [NP2];
		this.dim1=matrizI.length;
		this.dim2=matrizI[0].length;
		this.celdas=celdas;
		
		this.mboObject(matrizI, celdas, Mmax);	//inicializamos con soluciones random
	}
	
	public void mboObject(int [][]matrizI, int celdas,int Mmax){ //le entregamos la matriz Inicial, celda y Mmax
		int Nps=0;
		for (int i = 0; i < this.poblacion; ++i){		
			ag[i]=new matriz(matrizI,celdas, Mmax); //reparte las soluciones para trabajar con sub poblaciones 1 y 2
			if (i < NP1){
				ag1[Nps]=ag[i];
				Nps++;
			}
			else{
				ag2[i-Nps]=ag[i];

			}
		}
	}
	public void algoritmo(){

		Random random = new Random();
		this.actualizaPxC(ag);			//actualiza las matrices PxC dentro de cada objeto matriz de ag
		this.CalculaCosto(ag);			// calcular el fit para cada solucion
		this.ordenarSol(ag);			// ordena las soluciones encontradas de menor a mayor
		//this.verSolAG(); //para ver soluciones post primer orden

		
		this.bestSol.mat_mxc= this.ag[0].mat_mxc;  //en una primera instancia guarda las mejores soluciones encontradas
		this.bestSol.fit= this.ag[0].fit;			// y su fit
	

		for (int hx=0; hx< this.max_p; hx++){ //bucle generacion 
		

		
			//////////////////Dividir toda la población en dos subpoblaciones%%% %%%
			//Divide a toda la población en Población1 (Tierra1) y Población2 (Tierra2)
			//De acuerdo a su forma física.
			//Las mariposas monarca en Population1 son mejores o iguales a Population2.
			//Por supuesto, podemos dividir aleatoriamente a toda la población en Población1 y Población2.

			for (int popindex = 0; popindex < poblacion; ++popindex){ //actualiza la distribucion de las soluciones para las poblaciones
				if (popindex < NP1){
					ag1[popindex].mat_mxc=this.copiarArray(ag[popindex].mat_mxc);

				}
				else{
					ag2[popindex-NP1].mat_mxc= this.copiarArray(ag[popindex].mat_mxc);
					
				}
			}
			
			// Migration operator 


			for(int k1=0; k1 <NP1;k1++){
				land1 =new int [matrizI.length][celdas];
				for(int parnum1 = 0; parnum1 < dim1; parnum1++ ){
						r1 = Math.random()*periodo;
						if(r1 <= particion){
							r2=random.nextInt(NP1);
							land1[parnum1] = this.copiarArray18(ag1[(int) r2].mat_mxc[parnum1]);
						}else {
							r3=random.nextInt(NP2);
							land1[parnum1] = this.copiarArray18(ag2[(int) r3].mat_mxc[parnum1]);						
						}
				}
				
				if(this.LandIsFactible(land1)){
					Maux= new matriz(this.matrizI,this.celdas,this.Mmax,land1);
					if(Maux.fit<ag[k1].fit){
						ag[k1].mat_mxc= this.copiarArray(land1);
					
					}
				
				
				
				}else{
					
					k1--;
					}
			}
			
			
			
			// Butterfly adjusting operator 
			
			
			
		for(int k2=0; k2 <NP2;k2++){
			double scale = maxStepSize / Math.pow(hx+1, 2);
	        int StepSize = (int) Math.ceil(exprnd(2*max_p));

			double[][] delatax= levyFly(StepSize,this.bestSol.NMaquinas); 

			land2 = new int [matrizI.length][celdas];
			for(int parnum2 = 0; parnum2 < dim1; parnum2++ ){
					
					if(Math.random() >= particion){
						land2[parnum2]= this.copiarArray18(this.bestSol.mat_mxc[parnum2]);
					}else{
						int R4= random.nextInt(NP2);
						land2[parnum2]= this.copiarArray18(ag2[R4].mat_mxc[parnum2]);
						if(Math.random()>BAR){
							for(int FLand=0;FLand<this.celdas;FLand++){
								land2[parnum2][FLand]=this.sigmoidal(land2[parnum2][FLand]+ scale*(delatax[parnum2][FLand]-0.5));
							}
						}
					}
					
			
			}
			if(this.LandIsFactible(land2)){
				Maux=null;
				Maux= new matriz(this.matrizI,this.celdas,this.Mmax,land2);
				if(Maux.fit<ag[NP1+k2].fit){
					ag[NP1+k2].mat_mxc= this.copiarArray(land2);
				}
				
			}else k2--;
		}
	
		this.actualizaPxC(ag);
		this.CalculaCosto(ag);
		this.ordenarSol(ag);
		
		if(ag[0].isFactible() && ag[0].fit <= bestSol.fit){
			this.bestSol.mat_mxc= this.copiarArray(ag[0].mat_mxc);
			this.bestSol.fit= this.ag[0].fit;
		}
		
		
		}
		
		//this.bestSol.showMatriz(bestSol.mat_mxc); //habilitar para ver matriz MxC 
		System.out.println("solucion encontrada de "+this.archivoDir+"  con best encontrada:" +this.bestSol.fit+" de un esperado de: "+this.bestSolucion);
		
	}

	
	public void CalculaCosto(matriz[] ag){ //calcula el fit de cada solucion en el array AG(todas) ......hecho para actualizar fit EN cada generacion
		for(int i=0; i< this.poblacion;i++){
			ag[i].calcularfit();			
		}
		
	}
	
	public matriz[] ordenarSol(matriz[] matriz){		//ordena arreglo de soluciones (matriz[])
		return quicksort(matriz,0,matriz.length-1);
	}
	public matriz[] quicksort(matriz numeros[], int izq, int der){ //algoritmo de ordenamiento quicksort
		if(izq>der){
			return numeros;
		}
		int i= izq;int d=der;
		if(izq!=der){
			int pivote;
			matriz aux;
			pivote=izq;
			while(izq!=der){
				while(numeros[der].fit>=numeros[pivote].fit && 	izq<der)
					der--;
				while(numeros[izq].fit<numeros[pivote].fit && izq<der)
					izq++;
				
				if(der!=izq){
					aux = numeros[der];
					numeros[der]=numeros[izq];
					numeros[izq]= aux;
					
				}
				if(izq==der){
					quicksort(numeros, i, izq-1);
					quicksort(numeros, izq+1, d);
	
				}
			}}else{
				return numeros;}
			return numeros;
		
		
			
	} 
	
	public void actualizaPxC(matriz[] ag){  //actualiza las matrices de partes x celdas
		for(int l=0; l<ag.length;l++){
			ag[l].crearPxC();	
		}
		
	}
	
	public void verSolAG(){  // mostrar todas las soluciones  con su fit y si esta es factible
		for(int i=0; i<this.ag.length;i++){
			this.ag[i].showMatriz(this.ag[i].mat_mxc);
			System.out.println("es factible?: "+this.ag[i].isFactible());
			System.out.println("Fit: "+this.ag[i].fit);
			System.out.println("--------------------");
		}
		
		System.out.println("-----------FIIIIiiIIIIIn---------");
	}

	
	public boolean LandIsFactible(int [][] matrizF){ //restriccion maximo maquinas
		int MmaxC=0;
		int maxMxc=0;
		for(int rr=0;rr<matrizF[0].length;rr++){
			for(int r=0;r<matrizF.length;r++){
				MmaxC=MmaxC+matrizF[r][rr];

			}
			if(MmaxC>this.Mmax) 
				return false;
			MmaxC=0;
		}
		//se agrego esto ...
		for(int r=0;r<matrizF.length;r++){
			for(int rr=0;rr<matrizF[0].length;rr++){
				//System.out.println(maxMxc);
				maxMxc+=MmaxC+matrizF[r][rr];
			}
			if(maxMxc!=1){
				return false;
			}
			//System.out.println(maxMxc);
			
			maxMxc=0;
			
		}
		//--------hasta aca-----
		return true;
		
	}
	
	public void LeerArchivo(String archivoDir){


		File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;
	      int leer=0;

	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         //archivo = new File ("BoctorProblem_90_instancias/MCDP_Boctor_Problem01_C2_M8.txt");
		     archivo = new File (archivoDir);

	    	 fr = new FileReader (archivo);
	         br = new BufferedReader(fr);

	         // Lectura del fichero
	         String linea;
	         String[] dataa;
	         int[] dataaM;
	         this.bestSolucion=0;
	         int partes=0;
	         int maquinas=0;
	         this.celdas=0;
	         this.Mmax=0;
	         ArrayList<int[]> matrizList=new ArrayList<int []>();
	         while((linea=br.readLine())!=null){
	        	 
	        	 if(leer!=0){
	        		leer++;
	        		if(leer<=7||leer>=14)
	        			if(leer<=7){
	        				dataa= linea.split("=");
	        				if(leer==2){	
	        					bestSolucion= Integer.parseInt(dataa[1]);
	        				}
	        				if(leer==4){
	        					
	        					maquinas= Integer.parseInt(dataa[1]);
	        				}
	        				if(leer==5){
	        					partes= Integer.parseInt(dataa[1]);
	        				}
	        				if(leer==6){
	        					celdas= Integer.parseInt(dataa[1]);
	        					
	        				}
	        				if(leer==7){
	        					Mmax= Integer.parseInt(dataa[1]);
	        				}
	        			}else{
	        				dataa= linea.split(" ");
	        				dataaM=new int[partes];
	        				for (int i=0;i<partes;i++){
	        					if(!dataa[i].equals("\\")){
	        						dataaM[i]=Integer.parseInt(dataa[i]);
	        					}
	        				}
	        				matrizList.add(dataaM);
	        			}
	 	            
	 	          }
	        	 if(linea.equals("}")){
	        		 leer++;

	        	 }        	 
	        	 
	         }
	         int [][] matriz=new int[maquinas][partes];
	         for(int i=0;i<matrizList.size();i++){
	        	 matriz[i]=matrizList.get(i);
	         }
	         this.matrizI=matriz;
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	
	}
	
	//--------------para la heurística
	private double sum(double[] vector){
		double sumatotal=0;
		for(int i=0;i<vector.length;i++){
			sumatotal+= vector[i];
			
		}
		return sumatotal;
		
	}
	public  Double exprnd(double mu){    //simulacion distribucion exponential     
        double random = Math.random(); //de 0-1
        double exponencial = ((Math.log(random)/mu) * (-1));
        return exponencial;
	}
	private int sigmoidal(double valor){ //adaptar sigmoidal + 0-1
		valor=1/(1+Math.pow(Math.E, -valor));
		if(valor>=0.5){
			return 1;

		}else{
			return 0;

		}
		
	}
	private double[][] levyFly(int stepSize, int nMaquinas) { //matriz con distribucion de levy
		double[][] matrizNew=new double[this.matrizI.length][this.celdas];
		double [] fx=new double[stepSize];
		
		for(int i=0; i<matrizNew.length;i++){
			for(int j=0;j<celdas;j++){
				for(int k=0;k<fx.length;k++){
					fx[k]= Math.tan(Math.PI*Math.random());
				}
				matrizNew[i][j]=this.sum(fx);
			}
			
		}
		return matrizNew;
	}
	
	// --------------------------fin heur------------------
	
	public int[][] copiarArray(int[][] src) { //copia matrices (2x2)
	    int length = src.length;
	    int[][] target = new int[length][src[0].length];
	    for (int i = 0; i < length; i++) {
	        System.arraycopy(src[i], 0, target[i], 0, src[i].length);
	    }
	    return target;
	}
	
	public int[] copiarArray18(int[] src) { //copia arreglo 
	    int length = src.length;
	    int[] target = new int[length];
	    
	    System.arraycopy(src, 0, target, 0, src.length);
	    
	    return target;
	}
	
		
}
