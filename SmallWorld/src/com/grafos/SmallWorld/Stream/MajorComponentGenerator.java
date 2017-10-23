package com.grafos.SmallWorld.Stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;

public class MajorComponentGenerator {
	
	private Graph graph;
	private List<String> componentData;
	private List<Integer> auxList;
	private int majorComponent;
	private int nodeQuantityMajorComponent;
	private int edgeQuantityMajorComponent = 0;
	
	public MajorComponentGenerator(Graph graph, List<String> componentData, int majorComponent, int nodeQuantityMajorComponent) {
		this.graph = graph;
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
		auxList = new ArrayList<Integer>();
		
		// Percorre a lista e quebra a string para gerar o componente recebido
		Iterator<String> i = componentData.iterator();
		while(i.hasNext()) {
			String value = i.next();
			int cod      = Integer.parseInt(value.split("-")[0]); // codigo
			String node  = value.split("-")[1];				      // vertice		
			//String edge  = value.split("-")[2];					  // aresta
			
			// Usa apenas o maior componente
			if(cod == majorComponent) {
				//System.out.println("Codigo: " + cod + "| Vertice: " + node);
				/*
				 * Quando for criar o componente, pegar os dados direto da string
				 */
				auxList.add(Integer.parseInt(node));
			}
		}
		
		// colore todas as arestas do maior componente
		for(Edge edge : graph.getEachEdge()) {
			if(auxList.contains(Integer.parseInt(edge.getNode0().toString())) &&
			   auxList.contains(Integer.parseInt(edge.getNode1().toString()))) {
				edge.addAttribute("ui.style", "fill-color: red;");
				edgeQuantityMajorComponent++;
			}
		}
		
		String message = "O maior componente é : " + majorComponent + // Essa linha vai sair
				 "\nQuantidade de vertices: " + nodeQuantityMajorComponent +
				 "\nQuantidade de arestas: "  + edgeQuantityMajorComponent;
		JOptionPane.showMessageDialog(null, message);
		
		//G.display();
	}
}
