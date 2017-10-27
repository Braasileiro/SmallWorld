package com.grafos.SmallWorld.Stream;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class BreadthFirstSearch {
	Graph graph = new SingleGraph("Graph");
	int cont = 0;
	
	public BreadthFirstSearch(Graph graph) {
		this.graph = graph;
	}
	
	public void Compute() {
		Search(graph);
		System.out.println(cont);
	}
	
	// Deixa os vertices brancos, nao visitados
	public void Search(Graph graph) {
		// Marca todos os vertices
		for(Node node : graph.getEachNode()) {
			node.addAttribute("ui.color", "white");
			node.addAttribute("ui.style", "fill-color: white;");
		}
		
		// Percorre quem nao foi visitado ainda
		for(Node node : graph.getEachNode()) {
			if(node.getAttribute("ui.color") == "white") {
				Bfs(node);
			}
		}
	}
	
	public void Bfs(Node node) {
		Queue<Node> queue = new LinkedList<Node>();
		
		//Marca node com a cor cinza
		node.addAttribute("ui.color", "grey");
		node.addAttribute("ui.style", "fill-color: grey;");
				
		queue.add(node);
		
		Iterator<Node> nodesList = queue.iterator();
		while(nodesList.hasNext()) {
			Node aux = queue.remove(); // Primeiro elemento da Fila
			
			//Marca aux com a cor preta
			aux.addAttribute("ui.color", "black");
			aux.addAttribute("ui.style", "fill-color: black;");
			cont++;
			
			Iterator<Node> nodesAdj = aux.getNeighborNodeIterator();
			while(nodesAdj.hasNext()) {
				Node w = nodesAdj.next();
				if(w.getAttribute("ui.color") != "black" && w.getAttribute("ui.color") != "grey") {
					// visita o w				
					w.addAttribute("ui.color", "grey");
					w.addAttribute("ui.style", "fill-color: grey;");
					queue.add(w);
				}
			}
		}
	}
	
}
