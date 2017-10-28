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
	int i, j, contNode = 0;
	long somatorioAdj = 0;
	
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

		// Preenche a matriz com zeros
		for(int i = 0; i < nodeQuantityMajorComponent; i++) {
			for (int j = 0; j < nodeQuantityMajorComponent; j++) {
				matrizAdj[i][j] = 0;
			}
			//System.out.println("Linha: " + i + " : Conteudo: " + matrizAdj[i][j]);
		}
		
		// Marca todos os vertices
		for(Node node : graph.getEachNode()) {
			node.addAttribute("ui.color", "white");
			node.addAttribute("ui.style", "fill-color: white;");
			node.setAttribute("codigo", contNode); // cada no recebe um cod
			node.setAttribute("valor", Integer.MAX_VALUE); // todos iniciam com valor infinito
			contNode++;
		}
		
		// Atribui o valor 0 ao primeiro node
		graph.getNode(0).setAttribute("valor", 0);
		
		contNode = 0;
		
		// Percorre quem nao foi visitado ainda
		for(Node node : graph.getEachNode()) {
			if(node.getAttribute("ui.color") == "white") {
				Bfs(node);
			}
		}
		
		// Percorre e imprime a lista preenchida
		for(int i = 0; i < nodeQuantityMajorComponent; i++) {
			for (int j = 0; j < nodeQuantityMajorComponent; j++) {
				if(matrizAdj[i][j] != 0) {
					System.out.println("Adjacencias: " + matrizAdj[i][j]);
					somatorioAdj = somatorioAdj + matrizAdj[i][j];
				}
			}
			System.out.println("Vertice: " + i);
			System.out.println();
		}
		
		
		
		
		
		// Resultado		
		System.out.println("Somatorio de adjacencias: " + somatorioAdj);
		
		double media = (1/((0.5*nodeQuantityMajorComponent)*(nodeQuantityMajorComponent + 1)) * somatorioAdj);
		System.out.println("Resultado: " + media);
		
	}
	
	
	
	// Busca em largura
	public void Bfs(Node node) {
		Queue<Node> queue = new LinkedList<Node>();
		
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
						
			// Adjacencias de cada no
			Iterator<Node> nodesAdj = aux.getNeighborNodeIterator();
			while(nodesAdj.hasNext()) {
				Node w = nodesAdj.next();
				
				if(w.getAttribute("ui.color") != "black" && w.getAttribute("ui.color") != "grey") {
					// visita o w
					w.addAttribute("ui.color", "grey");
					w.addAttribute("ui.style", "fill-color: grey;");
					w.changeAttribute("valor", (Integer.parseInt(aux.getAttribute("valor").toString()) + 1));
					
					queue.add(w);
				}
				
				// Adiciona em cada linha da matriz, os vertices adj a esta linha (vertice)
				i = Integer.parseInt(aux.getAttribute("codigo").toString());
				j = Integer.parseInt(w.getAttribute("codigo").toString());
				
				matrizAdj[i][j]++;				
				
			}
		}
	}
	
}
