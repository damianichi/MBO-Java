import java.util.ArrayList;
import java.util.Random;

public class inidata {
	
	
	//------------- primera parte algoritmo--------
	int keep =2;
	double p= 0.416;
	int poblacion= 1000;
	final Integer max_p= 50; //gen max
	int NP1;
	int NP2 ;
	int dim1;
	int dim2;
	double periodo= 1.2;
	double particion=0.416;
	double BAR= particion;
    int [][] land1; //hay q agregarle las variables de maquina celda
    int [][] land2; // es supone q tiene q tener misma dimension o tipo que la "solucion" a buscar 
    double maxStepSize = 1.0;	
	double r1=0;
	double r2=0;
	double r3=0;
	int celdas;
	matriz bestSol;
	
	
	int totalfit;

	
	ArrayList<matriz> tempElitist = new ArrayList<matriz>();	// tempelitist
	matriz[] ag ;	// array con todas las soluciones
	matriz[] ag1;	// soluciones poblacion 1
	matriz[] ag2;	//soluciones poblaciones 2
	// --------------end-------------------
	public inidata(int [][]matrizI, int celdas,int Mmax){
		bestSol= new matriz(matrizI,celdas,Mmax);
		NP1=(int) Math.round(p*poblacion);
		NP2 = poblacion - NP1;
		System.out.println("NP:"+NP1+ "-"+NP2);
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
			ag[i]=new matriz(matrizI,celdas, Mmax); // en el parentesis inicializar una matriz
			if (i < NP1){
				//ag1[Nps]=new matriz(matrizI, celdas, Mmax);
				ag1[Nps]=ag[i];
				Nps++;
			}
			else{
				//ag2[i-Nps]=new matriz(matrizI,celdas,Mmax);
				ag2[i-Nps]=ag[i];

			}
		}
	}
	public void algoritmo(){

		//this.pruebas();
		Random random = new Random();
		this.actualizaPxC(ag);
		//this.verSolAG(); //para ver soluciones iniciales

		this.CalculaCosto(ag);
		this.ordenarSol(ag);
		//this.verSolAG(); //para ver soluciones post primer orden

		
			this.bestSol.mat_mxc= this.ag[0].mat_mxc;
			this.bestSol.fit= this.ag[0].fit;
		
		
		
		//solucionparche();
		//this.bestSol.showMatriz(this.bestSol.mat_mxc);

		for (int hx=0; hx< this.max_p; hx++){
			// estrategia elitista al parecer agrega y respalda las 2 primeras soluciones en cada generacion.
			for (int ik=0; ik<keep; ik++){  
				if(ag[ik].esFactible2()){
					tempElitist.add(ag[ik]);
				}
			}
			//////////////////Dividir toda la población en dos subpoblaciones%%% %%%
			//Divide a toda la población en Población1 (Tierra1) y Población2 (Tierra2)
			//De acuerdo a su forma física.
			//Las mariposas monarca en Population1 son mejores o iguales a Population2.
			//Por supuesto, podemos dividir aleatoriamente a toda la población en Población1 y Población2.

			for (int popindex = 0; popindex < poblacion; ++popindex){
				if (popindex < NP1){
					ag1[popindex]=ag[popindex];

				}
				else{
					ag2[popindex-NP1]= ag[popindex];
					
				}
			}
			
			
			// Migration operator Falta como calcular pa la segunda dimension ... a la vuelta
			

			for(int k1=0; k1 <NP1;k1++){

				for(int parnum1 = 0; parnum1 < dim1; parnum1++ ){
						r1 = Math.random()*periodo;

						if(r1 <= particion){

							r2=random.nextInt(NP1);
							//r2=Math.round(NP1 * Math.random());
							land1[parnum1] = ag1[(int) r2].mat_mxc[parnum1];
						}else {
							r3=random.nextInt(NP2);
							land1[parnum1] = ag2[(int) r3].mat_mxc[parnum1];						
						}
					
				}
				
				ag[k1].mat_mxc= land1;
				
				
		}
			
			
			
			// Butterfly adjusting operator negro aplicale a este porfi <3
			
			
			//double scale = maxStepSize/ Math.pow(max_p, 2);
			//int StepSzie = Math.ceil()
		
		for(int k2=0; k2 <NP2;k2++){
			double scale = maxStepSize/ Math.pow(max_p, 2);
			//int StepSzie = Math.ceil() 	stepSize = enteraSup(exprnd(2* max_t)
			double delatax; //	delatax = levyFlight(Stepsize,dim)

			for(int parnum2 = 0; parnum2 < dim1; parnum2++ ){
					
					if(Math.random() >= particion){
						land2[parnum2]= this.bestSol.mat_mxc[parnum2];
					}else{
						int R4= random.nextInt(NP2);
						land2[parnum2]= ag2[R4].mat_mxc[parnum2];
						if(Math.random()>BAR){
							//land2[parnum2]=(int) Math.round(land2[parnum2][parnum22]+ scale*(Math.random()-0.5));/*scale*(delatax[parnum2]-0,5)*/
							
						}
					}
					
			
			}
			if(hx<3){
				//System.out.println("ANTES----<:"+hx+" gen");
			//ag[NP1+k2].showMatriz(ag[NP1+k2].mat_mxc);
			}
			ag[NP1+k2].mat_mxc= land2;
			if(hx<3){

				//System.out.println("land2  DESPUES:");
				//ag[NP1+k2].showMatriz(ag[NP1+k2].mat_mxc);
			}
	}

			
		this.actualizaPxC(ag);
		this.CalculaCosto(ag);
		this.ordenarSol(ag);
		
		if(ag[0].isFactible() && ag[0].fit <= bestSol.fit){
			System.out.println("SE CAMBIOOO");
			this.bestSol.mat_mxc= ag[0].mat_mxc;
			this.bestSol.fit= this.ag[0].fit;
		}
			//solucionparche();
		
		
		}
		//this.verSolAG(); //ver soluciones finales
		//this.bestSol.showMatriz(this.bestSol.mat_mxc);
		//HABILITAR ESTAS
		System.out.println("solucion encontrada"+this.bestSol.fit);
		this.bestSol.showMatriz(bestSol.mat_mxc);
		
		//this.pruebas();
	}

	
	//metodo solo de pruebas
	public void pruebas(){			//muestra el fit antes y despues de ordenar!
		this.CalculaCosto(ag);
		for(int i=0; i<ag.length;i++){
			System.out.println("fit ag:"+ag[i].fit);
		}
		System.out.println("------------------");
		this.ag= this.ordenarSol(ag);
		for(int i=0; i<ag.length;i++){
			//System.out.println(ag[i].fit);
		}
		
	}
	
	public void CalculaCosto(matriz[] ag){ //calcula el fit de cada solucion en el array AG(todas)
		for(int i=0; i< this.poblacion;i++){
			ag[i].calcularfit();			
		}
		
	}
	
	public matriz[] ordenarSol(matriz[] matriz){		
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
	
	public void solucionparche(){ // solo si es factible respaldamos la mejor solucion!
		for(int y=0; y<ag.length;y++){
			if(ag[y].esFactible2()){
				if((ag[y].fit<this.totalfit)||bestSol==null){
					System.out.println("se encontro una mejor");
					this.bestSol= ag[y];
					this.totalfit= ag[y].fit;
					
					break;
				}else{
					//System.out.println("no hice nada");
				}
			}
			
		}
	}
	
	public void verSolAG(){
		for(int i=0; i<this.ag.length;i++){
			this.ag[i].showMatriz(this.ag[i].mat_mxc);
			System.out.println("es factible?: "+this.ag[i].isFactible());
			System.out.println("Fit: "+this.ag[i].fit);
			System.out.println("--------------------");
		}
	}
	
	
}
