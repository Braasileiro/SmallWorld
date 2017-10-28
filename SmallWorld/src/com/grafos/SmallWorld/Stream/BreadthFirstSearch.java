package com.grafos.SmallWorld.Stream;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class BreadthFirstSearch {
	Graph graph = new SingleGraph("Graph");
	int nodeQuantityMajorComponent;
	int matrizAdj[][];
	int i = 0, j = 0, contEdge = 0, contNode = 0;
	
	public BreadthFirstSearch(Graph graph, int nodeQuantityMajorComponent) {
		this.graph = graph;
		this.nodeQuantityMajorComponent = nodeQuantityMajorComponent;
		matrizAdj = new int[nodeQuantityMajorComponent][nodeQuantityMajorComponent];
	}
	
	public void Compute() {
		Search(graph);
	}
	
	// Deixa os vertices brancos, nao visitados
	public void Search(Graph graph) {
		
		// Preenchendo matriz com infinito
		for(int i = 0; i < nodeQuantityMajorComponent; i++) {
			for(int j = 0; j < nodeQuantityMajorComponent; j++) {
				matrizAdj[i][j] = Integer.MAX_VALUE;
			}
		}
		
		matrizAdj[0][0] = 0;
		
		// Marca todos os vertices
		for(Node node : graph.getEachNode()) {
			node.addAttribute("ui.color", "white");
			node.addAttribute("ui.style", "fill-color: white;");
			node.setAttribute("codigo", contNode);
			contNode++;
		}
		
		contNode = 0;
		
		// Percorre quem nao foi visitado ainda
		for(Node node : graph.getEachNode()) {
			if(node.getAttribute("ui.color") == "white") {
				Bfs(node);
			}
		}
		
		// percorre a matriz
		for(int i = 0; i < nodeQuantityMajorComponent; i++) {
			for(int j = 0; j < nodeQuantityMajorComponent; j++) {
				
				if(matrizAdj[i][j] != Integer.MAX_VALUE) {
					System.out.println(i + ":" + j + " = " + matrizAdj[i][j]);
					contNode++;
				}
			}
		}
		
		System.out.println(contNode);
		
	}
	
	public void Bfs(Node node) {
		Queue<Node> queue = new LinkedList<Node>();
		
		int qtdAdj = 0;
		
		//Marca node com a cor cinza
		node.addAttribute("ui.color", "grey");
		node.addAttribute("ui.style", "fill-color: grey;");
				
		queue.add(node);
		
		Iterator<Node> nodesList = queue.iterator();
		while(nodesList.hasNext()) {
			Node aux = queue.remove(); // Primeiro elemento da Fila			
			
			// Marca o elemento retirado com a cor preta
			// qtd vertices
			aux.addAttribute("ui.color", "black");
			aux.addAttribute("ui.style", "fill-color: black;");
						
			// Adjacencias
			Iterator<Node> nodesAdj = aux.getNeighborNodeIterator();
			while(nodesAdj.hasNext()) {
				Node w = nodesAdj.next();
				if(w.getAttribute("ui.color") != "black" && w.getAttribute("ui.color") != "grey") {
					// visita o w
					w.addAttribute("ui.color", "grey");
					w.addAttribute("ui.style", "fill-color: grey;");
					queue.add(w);
					
					// Auxiliares para percorrer a matriz
					int nAux = Integer.parseInt(aux.getAttribute("codigo").toString());
					int nW	 = Integer.parseInt(w.getAttribute("codigo").toString());
					
					//System.out.println(	aux.getAttribute("codigo").toString() + ":" +
					//				  	w.getAttribute("codigo").toString() + " - " + contNode);
					
					if(matrizAdj[nAux][nW] == Integer.MAX_VALUE) {
						matrizAdj[nAux][nW] = 1;
					}
					else {
						matrizAdj[nAux][nW] = 0;
					}
					//else if(matrizAdj[nAux][nW] > 0 && matrizAdj[nAux][nW] != Integer.MAX_VALUE) {
						//matrizAdj[nAux][nW] = 1;
					//}
					
					contNode++;					
				}
			}
		}
	}
	
}
