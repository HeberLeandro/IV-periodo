#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define THREADS 10
#define ARRAY 100

void *multArray(void * arr);

typedef struct{
	int *arr;
	int start, final;
}args;
	

int main (void){
	
	int array[ARRAY];
	
	//armazena o resultado quando as threads retornarem, a threads retarnam um array de int
	int *results[THREADS];
	
	pthread_t threads [THREADS];
	
	//inicia o Array
	for(int i = 0; i<ARRAY; i++){
		array[i] = i+1;
	}
	
	//inicia as threads	
	for(int i =0; i<THREADS; i++){
		args *a = (args*)malloc(sizeof(args));
		a->arr = array;
		a->start = i*THREADS;
		a->final = ((i+1)*THREADS)-1;
		//printf("start -> %d, final -> %d\n", a->start,a->final);
		pthread_create(&threads[i], NULL, multArray,(void*)a);
	}
	
	
	//junta as threads dps da soma
	for(int j =0; j<THREADS; j++){
		pthread_join(threads[j], (void *)&results[j]);
		printf("juntando thread %d\n",j);
	}
	
	
	for(int l=0; l<THREADS; l++){
		//for(int i =0; i<THREADS; i++){
			printf("Mult %i \n", *results[l]);
		//}
	}

	return 0;
}

void *multArray(void *arg){
	
	args *a = (args *) arg;

	int mult[THREADS];	
	for(int i = a->start, j = 0; i <= a->final; i++, j++){
		mult[j] = a->arr[i] * 1;
		//printf("mult %d\n",mult[j]);
	}
	printf("start %d terminei! \n", a->start);
	 
	return (void *)&mult[0];
}

