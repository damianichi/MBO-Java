import java.util.Random;
 
public class matriz {
	
	int [][] matrizInicial;
	//int [][] mat_mxc= {{0,1},{1,0},{1,0},{0,1},{1,0}}; // test descomentar para probar ejemplo pdf y comentar la de abajo
	int [][] mat_mxc;
	int [][] mat_pxc;
	int celdas; //leer desde archivo el numero de celdad de la instancia.
    int NMaquinas; //leer desde archivo el numero de maquinas de la instancia.
    int NPartes;
	int fit=0;
	int Mmax; //maximo de maquinas por celda	
	
	
	public  matriz (int[][] matriz, int celdas, int Mmax){
		this.Mmax= Mmax;
		this.matrizInicial= matriz;
		this.celdas=celdas;
		this.NMaquinas=matrizInicial.length;
		this.NPartes=matrizInicial[0].length;
		this.crearMxC();
		while(!this.isFactible()){
			//System.out.println("creando nueva");
			this.crearMxC();}
		this.crearPxC();
	}
	public  matriz (int[][] matriz, int celdas, int Mmax, int[][] mxc){
		this.Mmax= Mmax;
		this.mat_mxc=this.copiarArr(mxc); //COPIAR ARREGLO
		this.matrizInicial= matriz;
		this.celdas=celdas;
		this.NMaquinas=matrizInicial.length;
		this.NPartes=matrizInicial[0].length;
		//this.crearMxC();
		this.crearPxC();
		this.calcularfit();

	}
	
	public matriz(String direccion){
		// codigo para cargar matriz desde txt
		
	}
	
	
	
	public int[][] copiarArr(int[][] src) {
	    int length = src.length;
	    int[][] target = new int[length][src[0].length];
	    for (int i = 0; i < length; i++) {
	        System.arraycopy(src[i], 0, target[i], 0, src[i].length);
	    }
	    return target;
	}
	
	
	
	
	
	
	
	
	public void crearMxC(){ // se crea una primera solucion con 1 aleatorios
		Random random = new Random();
		this.mat_mxc= new int [this.matrizInicial.length][celdas];		
		for (int x=0; x < this.mat_mxc.length; x++) {			
			int randommath = random.nextInt(celdas) + 1;			
			//System.out.println("("+x+","+(randommath-1)+")");
			mat_mxc[x][randommath-1]=1;
		}
		//if(!this.isFactible())crearMxC();
		//System.out.println(isFactible());	
		//this.showMatriz(this.mat_mxc); //ejemplo para mostrar matriz creada .. comentar
		
		
	}
	
	
	public boolean isFactible(){ //restriccion maximo maquinas
		int MmaxC=0;
		int maxMxc=0;
		for(int rr=0;rr<this.mat_mxc[0].length;rr++){
			for(int r=0;r<this.mat_mxc.length;r++){
				MmaxC=MmaxC+this.mat_mxc[r][rr];
			}
			if(MmaxC>this.Mmax) 
				return false;
			MmaxC=0;
		}
		for(int r=0;r<this.mat_mxc.length;r++){
			for(int rr=0;rr<this.mat_mxc[0].length;rr++){
				//System.out.println(maxMxc);
				maxMxc+=MmaxC+this.mat_mxc[r][rr];
			}
			if(maxMxc!=1){
				return false;
			}
			//System.out.println(maxMxc);
			
			maxMxc=0;
			
		}
		return true;
		
	}
	public void crearPxC(){
		this.mat_pxc= new int [this.matrizInicial[0].length][celdas];
		int cont=0;
		//System.out.println("indexPiezas "+ matrizInicial[0].length);
	//	System.out.println("indexceldas "+ this.mat_mxc[0].length);
	//	System.out.println("indexMaquinas "+ mat_mxc.length);
		int sumaColum=0;
		int second=0;
		//showMatriz(this.mat_mxc);
		for (int indexCelda=0;indexCelda<this.mat_mxc[0].length; indexCelda++){ // recorre celdas MxC	
			for(int indexPiezas=0; indexPiezas<matrizInicial[0].length; indexPiezas++){ //recorre las piezas
				for (int indexMaquinas=0; indexMaquinas< this.mat_mxc.length;indexMaquinas++){ //recorre las maquinas
					if(this.mat_mxc[indexMaquinas][indexCelda]==1){ //solo toma las maquinas ubicadas en la celda 1
						if(this.matrizInicial[indexMaquinas][indexPiezas]==1){// 
							sumaColum++;	
						}
					}
				}
				this.mat_pxc[indexPiezas][indexCelda]= sumaColum;
				sumaColum=0;
			}
			
		}
		this.FixMatriz(mat_pxc); //dejamos el numero mayor como el 1 y los demas como 0		
		//this.showMatriz(mat_pxc); //solo de ejemplo .. comentar
	}
	
	public void showMatriz(int[][] matrizz ){//muestra la matriz
		for(int h=0;h<matrizz.length;h++){
            for(int j=0;j<matrizz[h].length;j++) {
            	System.out.print (matrizz[h][j] +" ");
            }
            System.out.println("\n");
        }
		
	}
	
	public void FixMatriz(int [][] matriz){ //funcion para determinar cual tiene prioridad en celdas 
		int Max=0;
		int posicion=0;
		for (int fila=0; fila<matriz.length;fila++){
			for(int columna=0;columna<matriz[0].length;columna++){
				if(matriz[fila][columna]>Max){
					Max= matriz[fila][columna];
					posicion=columna;
				}
			}
			matriz[fila]= new int[matriz[0].length];
			matriz[fila][posicion]= 1;
			Max=0;
			posicion=0;
		}
		
	}
	
	public void calcularfit(){ 				// calcula el coste interceldario con F.O
		int totalSumatoria=0;
        for(int k = 0; k < this.mat_mxc[0].length; k++){ //desde celda 1 hasta celda total calcular...
            for(int i = 0; i < this.mat_mxc.length; i++){ //desde maquina 1 hasta el total de maquinas calcular...
                for(int j = 0; j <this.matrizInicial[0].length; j++){ //desde la pieza 1 hasta el total de piezas calcular...
                   /* System.out.println("=======================");  //no es obligación mostrar
                    System.out.println(matrizInicial[i][j]);        //no es obligación mostrar
                    System.out.println("=======================");  //no es obligación mostrar
                    System.out.println(mat_pxc[j][k]);              //no es obligación mostrar
                    System.out.println("=======================");  //no es obligación mostrar
                    System.out.println(mat_mxc[i][k]);              //no es obligación mostrar
                    System.out.println("=======================");  //no es obligación mostrar
                   */ totalSumatoria = totalSumatoria + ( (matrizInicial[i][j])*(mat_pxc[j][k])*(1 - (mat_mxc[i][k])) );
                    /*System.out.println(totalSumatoria);
                    System.out.println("***********************");*/
                }
            }
        }
        this.fit=totalSumatoria;
	}	
		
	public boolean esFactible2(){		// considera restriccion para partes, maquinas y maquinas por celdas etc.
    
    int cont=0;
    int i = 0;
    int j = 0;
      
    for(i = 0; i < this.NMaquinas; i++) {    //recorremos hacia el lado.
        for(j = 0; j < this.celdas; j++) {
            cont = cont + (mat_mxc[i][j]);  //sumamos hacia el lado.
            if(cont > 1){  //si la suma es distinta de 1 entonces no es factible.
                return false;
            }
        }
        cont = 0;   //reiniciamos contador para volver a iterar.
    }
    for(i = 0; i < this.NPartes; i++) {  //recorremos hacia el lado.
        for(j = 0; j < this.celdas; j++) {
            cont =cont + (mat_pxc[i][j]);  //sumamos hacia el lado
            if(cont > 1){    //si la suma es distinta de 1 entonces no es factible.
                return false;
            }
        }
        cont = 0; //reiniciamos contador para volver a iterar.
    }
      
    for(i = 0; i < this.celdas; i++) {          //recorremos hacia abajo
        for(j = 0; j < this.NMaquinas; j++){
            cont = cont + (mat_mxc[j][i]); //sumamos hacia abajo.
        }
        if(cont > Mmax){ //si la suma no supera el máximo total de máquinas aceptadas, entonces la matriz sigue siendo factible.
        	return false;
        }
        cont = 0;
    }
    
    return true;
    	
}


	
	
	

}
