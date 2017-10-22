package com.grafos.SmallWorld.Stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.JOptionPane;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import com.grafos.SmallWorld.Stream.MajorComponentGenerator;

public class DepthFirstSearch {
	public Graph G;
	
	private final int VISIT_TIME = 5;

	public DepthFirstSearch(Graph G) {
		this.G = G;
	}

	public void Compute() {
		Search();
	}
	
	private int nodeQuantity = 0;
	List<String> componentData = new ArrayList<String>();

	public void Search() {
		int componentsQuantity = 0;
		int edgeQuantityMajorComponent = 0;
		int majorComponent = 0;
		int codComponent = 0;
		
		// para todos os nos do grafo, deixa todos vertices brancos
		// todos sao marcados como nao visitados
		for (Node node : G.getEachNode()) {
			node.addAttribute("ui.color", "white");
			node.addAttribute("ui.style", "fill-color: white;");
		}
		
		try {
			Thread.sleep(VISIT_TIME);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		// cria a fila com todos que estao brancos
		for (Node node : G.getEachNode()) {
			Queue<Node> queue = null;
			// se o no ainda nao foi visitado...
			if (node.getAttribute("ui.color") == "white") {
				queue = new LinkedList<Node>();
				
				// contagem de todos os vertices de UM COMPONENTE por vez
				Bfs(node, queue, G, codComponent);
				
				// pega o id do maior componente
				if (nodeQuantity > edgeQuantityMajorComponent) {					
					edgeQuantityMajorComponent = nodeQuantity;
					majorComponent = codComponent;
				}
				
				componentsQuantity++;
				codComponent++;	
				
				nodeQuantity = 0;
			}
		}
		
		JOptionPane.showMessageDialog(null, "\nQuantidade de componentes conexos: " + componentsQuantity);
		
		// Gerar o maior componente isolado dos outros
		MajorComponentGenerator componentGenerator = new MajorComponentGenerator(G, componentData, majorComponent);
		componentGenerator.ShowComponent();
	}

	public void Bfs(Node v, Queue queue, Graph g, int codComponent) {
		nodeQuantity++;
		
		// adiciona os dados na lista
		componentData.add(codComponent + "-" + v);
		
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
				
				try {
					Thread.sleep(VISIT_TIME);
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
								
				Bfs(w, queue, g, codComponent);
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
