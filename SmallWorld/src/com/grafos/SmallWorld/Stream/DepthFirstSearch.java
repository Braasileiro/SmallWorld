package com.grafos.SmallWorld.Stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.JOptionPane;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import com.grafos.SmallWorld.Stream.MajorComponentGenerator;

public class DepthFirstSearch {
	public Graph graph;
	
	private final int VISIT_TIME = 1;

	public DepthFirstSearch(Graph graph) {
		this.graph = graph;
	}

	public void Compute() {
		Search();
	}
	
	private int nodeQuantity = 0;
	List<String> nodeList = new ArrayList<String>();
	List<String> treeEdgeList = new ArrayList<String>();

	public void Search() {
		int componentsQuantity = 0;
		int nodeQuantityMajorComponent = 0;
		int majorComponent = 0;
		int codComponent = 0;
		
		// para todos os nos do grafo, deixa todos vertices brancos
		// todos sao marcados como nao visitados
		for (Node node : graph.getEachNode()) {
			node.addAttribute("ui.color", "white");
			node.addAttribute("ui.style", "fill-color: white;");
		}
		
		// tempo
		try {
			Thread.sleep(VISIT_TIME);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		// cria a fila com todos que estao brancos
		for (Node node : graph.getEachNode()) {
			Queue<Node> queue = null;
			// se o no ainda nao foi visitado...
			if (node.getAttribute("ui.color") == "white") {
				queue = new LinkedList<Node>();
				
				// contagem de todos os vertices de UM COMPONENTE por vez
				Dfs(node, queue, graph, codComponent);
				
				// pega o id do maior componente
				if (nodeQuantity > nodeQuantityMajorComponent) {					
					nodeQuantityMajorComponent = nodeQuantity;
					majorComponent = codComponent;
				}
								
				componentsQuantity++;
				codComponent++;	
				
				nodeQuantity = 0;
			}
		}		
		JOptionPane.showMessageDialog(null, "\nQuantidade de componentes conexos: " + componentsQuantity);
		
		// Gerar o maior componente isolado dos outros
		MajorComponentGenerator componentGenerator = new MajorComponentGenerator(graph, nodeList, treeEdgeList, majorComponent, nodeQuantityMajorComponent);
		componentGenerator.ShowComponent();
	}

	public void Dfs(Node v, Queue queue, Graph g, int codComponent) {
		nodeQuantity++;
		
		// adiciona os dados dos vertices na lista
		nodeList.add(codComponent + "-" + v);
		
		queue.add(v);
		v.addAttribute("ui.color", "grey");
		v.addAttribute("ui.style", "fill-color: grey;");
		
		try {
			Thread.sleep(VISIT_TIME);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
				
		// Para cada vértice adjacente a v
		Iterator<Node> nodesAdj = v.getNeighborNodeIterator();
		while (nodesAdj.hasNext()) {
			Node w = nodesAdj.next();
						
			// Se w não foi visitado
			if (w.getAttribute("ui.color") != "black" && w.getAttribute("ui.color") != "grey") {
				// visita o w				
				w.addAttribute("ui.color", "grey");
				w.addAttribute("ui.style", "fill-color: grey;");
				
				// Adiciona os dados das arestas na lista
				// grava somente os dados da arvore geradora
				treeEdgeList.add(codComponent + "-" + v + ":" + w);
				
				try {
					Thread.sleep(VISIT_TIME);
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
								
				Dfs(w, queue, g, codComponent);
			}
		}
		// ja visitou o v
		queue.remove(v);
		v.addAttribute("ui.color", "black");
		v.addAttribute("ui.style", "fill-color: black;");
		
		try {
			Thread.sleep(VISIT_TIME);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
