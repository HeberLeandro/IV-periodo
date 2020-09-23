#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define THREADS 10
#define ARRAY 100

void *multArray(void * arr);

typedef struct {
	int idx, length;
}thread_arg, *ptr_thread_arg;	

int main (void){
	
	int array[ARRAY];
	
	//armazena o resultado quando as threads retornarem
	int results [THREADS];
	
	pthread_t threads [THREADS];
	
	//inicia o Array
	for(int i = 0; i<ARRAY; i++){
		array[i] = i+1;
	}
	
	//inicia as threads	
	for(int i =0; i<THREADS; i++){
		pthread_create(&threads[i], NULL, multArray,(void*)array, i*THREADS, ((i+1)*THREADS)-1);
	}
	
	
	//junta as threads dps da soma
	for(int j =0; j<THREADS; j++){
		pthread_join(threads[j], (void *)&results[j]);
	}
	
	int mult=0;
	for(int l=0; l<THREADS; l++){
		for(int i =0; i<ARRAY; i++){
			printf("Multiplicação %d \n", results[l][i]);
		}
	}

	return 0;
}

void *multArray(void *arr, int start, int final){
	
	int mult[start-final];
	
	int *a = (int *) arr;
	
	for(int i =start; i< final; i++)mult[i] = a[i] + 2;
	 
	return (void *)mult;
}

