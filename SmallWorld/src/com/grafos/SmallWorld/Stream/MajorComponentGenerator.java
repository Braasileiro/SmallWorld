package com.grafos.SmallWorld.Stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

public class MajorComponentGenerator {
	
	private Graph graph;
	private List<String> nodeList;
	private List<String> edgeList;
	private List<Integer> auxList;
	private int majorComponent;
	private int nodeQuantityMajorComponent;
	private int edgeQuantityMajorComponent = 0;
	
	public MajorComponentGenerator(Graph graph, List<String> nodeList, List<String> edgeList, int majorComponent, int nodeQuantityMajorComponent) {
		this.graph = graph;
		this.nodeList = nodeList;
		this.edgeList = edgeList;
		this.majorComponent = majorComponent;
		this.nodeQuantityMajorComponent = nodeQuantityMajorComponent;
	}
	
	public void ShowComponent(){
		
		Graph newGraph = new SingleGraph("Major component");
		
		/*
		 * 
		 * Percorrer as 2 listas e gerar apenas o componente maior
		 * 
		 */	
		auxList = new ArrayList<Integer>();
		
		// Percorre a lista e quebra a string para gerar o componente recebido
		Iterator<String> iNode = nodeList.iterator();
		while(iNode.hasNext()) {
			String value = iNode.next();
			int cod      = Integer.parseInt(value.split("-")[0]); // codigo
			String node  = value.split("-")[1];				      // vertice
			
			// Usa apenas o maior componente
			if(cod == majorComponent) {
				//System.out.println("Codigo: " + cod + "| Vertice: " + node);
				auxList.add(Integer.parseInt(node));
				newGraph.addNode(node);
			}
		}
		
		Iterator<String> iEdge = edgeList.iterator();
		while(iEdge.hasNext()) {
			String valor = iEdge.next();
			int cod = Integer.parseInt(valor.split("-")[0]); // pega o codigo
			
			String aux = valor.split("-")[1];
				
			String node1 = aux.split(":")[0];
			String node2 = aux.split(":")[1];
			
			if(cod == majorComponent) {
				newGraph.addEdge(node1 + node2, node1, node2);
			}			
		}
		
		// conta a quantidade de todas as arestas do maior componente
		for(Edge edge : graph.getEachEdge()) {
			if(auxList.contains(Integer.parseInt(edge.getNode0().toString())) &&
			   auxList.contains(Integer.parseInt(edge.getNode1().toString()))) {
				edge.addAttribute("ui.style", "fill-color: blue;");
				edgeQuantityMajorComponent++;
			}
		}		
		newGraph.addAttribute("ui.stylesheet", "node{ size: 3px; fill-color: #777; text-mode: hidden; z-index: 0;} " +
											"edge{ shape: line; fill-color: #222; arrow-size: 3px, 2px;}");
		newGraph.addAttribute("ui.quality");
		newGraph.addAttribute("ui.antialias");
		
		newGraph.display();
		
		String message = "O maior componente é : " + majorComponent + // Essa linha vai sair
				 "\nQuantidade de vertices: " + nodeQuantityMajorComponent +
				 "\nQuantidade de arestas: "  + edgeQuantityMajorComponent;
		JOptionPane.showMessageDialog(null, message);
	}
}
