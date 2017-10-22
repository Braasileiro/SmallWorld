package com.grafos.SmallWorld.Stream;

import java.util.Iterator;
import java.util.List;

import org.graphstream.graph.Graph;

public class MajorComponentGenerator {
	private Graph G;
	private List<String> componentData;
	private int majorComponent;
	private int nodeQuantityMajorComponent;
	
	public MajorComponentGenerator(Graph g, List<String> componentData, int majorComponent, int nodeQuantityMajorComponent) {
		this.G = g;
		this.componentData = componentData;
		this.majorComponent = majorComponent;
		this.nodeQuantityMajorComponent = nodeQuantityMajorComponent;
	}
	
	public void ShowComponent(){
		
		/*
		 * 
		 * Percorrer as 2 listas e gerar apenas o componente maior
		 * 
		 */
		
		System.out.println("O maior componente é : " + majorComponent);
		System.out.println("Quantidade de vertices: " + nodeQuantityMajorComponent);
		System.out.println();
		
		// Percorre a lista e quebra a string para gerar o componente recebido
		Iterator<String> i = componentData.iterator();
		while(i.hasNext()) {
			String valor = i.next();
			int cod = Integer.parseInt(valor.split("-")[0]);
			String vertice = valor.split("-")[1];			
			
			// Usa apenas o maior componente
			if(cod == majorComponent) {
				System.out.println("Codigo: " + cod + "| Vertice: " + vertice);
			}
		}
		
		//G.display();
	}
}
